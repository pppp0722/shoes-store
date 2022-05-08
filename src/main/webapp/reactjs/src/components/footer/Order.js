import styled from "styled-components";
import OrderList from "./OrderList";
import { useState } from "react";
import axios from "axios";

const Order = ({orderItems, setOrderItems}) => {

  const [totalPrice, setTotalPrice] = useState(0);
  const [email, setEmail] = useState("");
  const [address, setAddress] = useState("");
  const [postcode, setPostcode] = useState("");

  const handleEmailInputChange = (e) => {
    setEmail(e.target.value);
  }

  const handleAddressInputChange = (e) => {
    setAddress(e.target.value);
  }

  const handlePostcodeInputChange = (e) => {
    setPostcode(e.target.value);
  }

  const checkEmail = (str) => {
    var reg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    if(!reg.test(str)) {
      return false;
    } else {
      return true;
    }
  }   

  const handleBtnClick = () => {
    if(orderItems.length === 0) {
      return alert("주문할 제품들을 선택해주세요!");
    }

    if(!checkEmail(email)) {
      return alert("이메일 형식을 확인해주세요!");
    }

    if(address === "" || postcode === "") {
      return alert("주소와 우편번호를 입력해주세요!");
    }

    const orderItemArr = [];

    orderItems.map(orderItem => {
      const orderItemJson = {};
      orderItemJson.productId = orderItem.productId;
      orderItemJson.price = orderItem.price;
      orderItemJson.quantity = orderItem.quantity;
      orderItemArr.push(orderItemJson);
    });

    const requestParam = {
      email: email,
      address: address,
      postcode: postcode,
      orderItems: orderItemArr
    };

    axios.post("api/v1/orders", requestParam)
    .then(response => {
      console.log(response);
      if(response.status === 200) {
        alert('주문 완료!');
        reset();
      } else {
        alert('오류 발생!');
      }
    }).catch(error => {
      console.log(error);
      if(error.response.status === 400) {
        alert(error.response.data);
      } else if(error.response.status === 500) {
        alert(error.response.data);
      }
    });
  }

  const reset = () => {
    setTotalPrice(0);
    setEmail("");
    setAddress("");
    setPostcode("");
    setOrderItems([]);
  }

  return (
      <Wrap>
        <OrderList orderItems={orderItems} setOrderItems={setOrderItems} totalPrice={totalPrice} setTotalPrice={setTotalPrice}/>
        <label for="email">Email</label>
        <Input type="email" name="email" value={email} onChange={handleEmailInputChange}/>
        <label for="address">Address</label>
        <Input name="address" value={address} onChange={handleAddressInputChange}/>
        <label for="postcode">Postcode</label>
        <Input name="postcode" value={postcode} onChange={handlePostcodeInputChange}/>
        <PriceDiv>{totalPrice}원</PriceDiv>
        <BuyBtn onClick={handleBtnClick}>Buy</BuyBtn>
      </Wrap>
  );
}

const Wrap = styled.div`
  text-align: center;
`

const Input = styled.input`
  width: 170px;
  margin: 0 10px 0 5px;
`

const PriceDiv = styled.div`
  display: inline-block;
  margin: 0 5px 0 5px;
  width: 100px;
  text-align: right;
`

const BuyBtn = styled.button`
  height: 50px;
  width: 100px;
`

export default Order;