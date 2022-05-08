import styled from "styled-components";
import { useState } from "react";
import axios from "axios";
import OrderList from "./OrderList";

const FindOrder = ({setShowFindModal}) => {

    const [email, setEmail] = useState("");

    const [orders, setOrders] = useState([]);

    const handleCloseBtnClick = () => {
        setShowFindModal(false);
    }

    const handleEmailChange = e => {
        setEmail(e.target.value);
    }

    const handleSubmitBtnClick = () => {
        if(email === "") {
            alert("잘못된 입력!");
            return;
        }

        axios.get("api/v1/orders", {
            params: {
              email: email
            }
          })
        .then(response => {
            console.log(response);
            if(response.status === 200) {
                setOrders(response.data);
                alert("조회 완료!");
            } else if(response.status === 204) {
                alert("데이터가 존재하지 않습니다.");
                setOrders([]);
              }else {
                alert('오류 발생!');
                setOrders([]);
              }
        }).catch(error => {
            console.log(error);
            if(error.response.status === 400) {
                alert(error.response.data);
            } else if(error.response.status === 500) {
                alert(error.response.data);
            }
            setOrders([]);
        });
    }

    return (
        <Wrap>
            <CloseBtn onClick={handleCloseBtnClick}>X</CloseBtn>
            <InputBox>
                <Input type="text" placeholder="email" onChange={handleEmailChange}></Input>
            </InputBox>
            <InputBox>
                <SubmitBtn onClick={handleSubmitBtnClick}>Submit</SubmitBtn>
            </InputBox>
            <OrderList orders={orders}/>
        </Wrap>
  );
}

const Wrap = styled.div`
  position: fixed;
  width: 900px;
  height: 900px;
  top: 30px;
  left: 50%;
  transform: translate(-50%, 0);
  z-index: 99;
  background-color: rgba(0, 0, 0, 0.5);
`

const CloseBtn = styled.button`
    display: block;
    float: right;
`

const InputBox = styled.div`
    width: 300px;
    margin: 40px auto;
`

const Input = styled.input`
    width: 300px;
`

const SubmitBtn = styled.button`
    width: 300px;
`

export default FindOrder;