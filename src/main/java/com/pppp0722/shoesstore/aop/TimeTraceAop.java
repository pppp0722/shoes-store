package com.pppp0722.shoesstore.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimeTraceAop {

    @Around("execution(* com.pppp0722.shoesstore..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        log.debug("start: {}", joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long end = System.currentTimeMillis();
            long time = end - start;
            log.debug("end: " + joinPoint.toString() + " " + time + "ms");
        }
    }

}
