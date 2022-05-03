import styled from "styled-components";
import OrderList from "./OrderList";
import { useState } from "react";

const Order = ({orderItems, setOrderItems}) => {

  const [totalPrice, setTotalPrice] = useState(0);

  return (
      <Wrap>
        <OrderList orderItems={orderItems} setOrderItems={setOrderItems} totalPrice={totalPrice} setTotalPrice={setTotalPrice}/>
        {totalPrice}원
        <BuyBtn>Buy</BuyBtn>
      </Wrap>
  );
}

const Wrap = styled.div`
  text-align: center;
`

const BuyBtn = styled.button`
  height: 50px;
  width: 200px;
`

export default Order;