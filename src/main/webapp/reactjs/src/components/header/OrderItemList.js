import styled from "styled-components";
import { useState, useEffect } from "react";
import axios from "axios";
import OrderItem from "./OrderItem";


const OrderItemList = ({orderId, setShowModal}) => {

    const [orderComponents, setOrderComponents] = useState();

    const handleCloseBtnClick = () => {
        setShowModal(false);
    }

    useEffect(() => {
        axios.get("api/v1/order-items", {
            params : {
                orderId: orderId
            }
        }).then(response => {
            console.log(response);
            setOrderComponents(response.data.map(orderItem => <OrderItem orderItem={orderItem}/>));
        }).catch(error => {

        });
    }, []);

    return (
        <Wrap>
            <CloseBtn onClick={handleCloseBtnClick}>X</CloseBtn>
            <Div>order items by order id: {orderId}</Div>
            {orderComponents}
        </Wrap>
  );
}

const Wrap = styled.div`
  position: fixed;
  width: 900px;
  height: 900px;
  top: 0;
  left: 50%;
  transform: translate(-50%, 0);
  z-index: 99;
  background-color: white;
`

const Div = styled.div`
    margin: 5px 0 5px 100px;
`

const CloseBtn = styled.button`
    display: block;
    float: right;
`

export default OrderItemList;