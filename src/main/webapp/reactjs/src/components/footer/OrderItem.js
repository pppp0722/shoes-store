import styled from "styled-components";
import {useState, useEffect} from "react";

const OrderItem = ({orderItem, orderItems, setOrderItems, totalPrice, setTotalPrice}) => {

  const [num, setNum] = useState(1);

  useEffect(() => {
    setTotalPrice(totalPrice + orderItem.price);
  }, []);

  const handleDelBtnClick = () => {
    setTotalPrice(totalPrice - orderItem.price * num);

    const newOrderItems = orderItems.filter(data => {
      return data.productId !== orderItem.productId;
    });

    setOrderItems(newOrderItems);
  }

  const handleOpBtnClick = (operator) => {
    if(operator === '+' && num < 99) {
      setNum(num + 1);
      setTotalPrice(totalPrice + orderItem.price);
    } else if(num > 1) {
      setNum(num - 1);
      setTotalPrice(totalPrice - orderItem.price);
    }
  }

  return (
      <Wrap>
        <DelBtn onClick={handleDelBtnClick}>X</DelBtn>
        <Name>{orderItem.name}</Name>
        <Price>{orderItem.price}원</Price>
        <Num>{num}개</Num>
        <PlusBtn onClick={() => handleOpBtnClick('+')}>+</PlusBtn>
        <MinusBtn onClick={() => handleOpBtnClick('-')}>-</MinusBtn>
      </Wrap>
  );
}

const Wrap = styled.div`
  display: inline-block;
  text-align: left;
  padding: 5px 5px 5px 5px;
`

const DelBtn = styled.button`
`

const Name = styled.div`
  display: inline-block;
  padding: 0 5px 0 5px;
  width: 200px;
  overflow: hidden;
  text-overflow: ellipsis;
`

const Price = styled.div`
  display: inline-block;
  padding: 0 5px 0 5px;
  width: 100px;
  background-color: blue;
  text-align: right;
`

const Num = styled.div`
  width: 35px;
  display: inline-block;
  padding: 0 5px 0px 5px;
  text-align: right;
`

const PlusBtn = styled.button`
`

const MinusBtn = styled.button`
`

export default OrderItem;