import styled from "styled-components";
import { useState } from "react";
import OrderItemList from "./OrderItemList";

const Order = ({order}) => {

    const [showModal, setShowModal] = useState(false);

    const handleBtnClick = () => {
        setShowModal(true);
    }

    return (
      <Wrap>
        <Div>order id: {order.orderId}</Div>
        <Div>email: {order.email}</Div>
        <Div>address: {order.address}</Div>
        <Div>postcode: {order.postcode}</Div>
        <Div>order status: {order.orderStatus}</Div>
        <Div>created at: {order.createdAt}</Div>
        <DetailBtn onClick={handleBtnClick}>주문 상품 보기</DetailBtn>
        {showModal ? <OrderItemList orderId={order.orderId} setShowModal={setShowModal}/> : null}
      </Wrap>
  );
}

const Wrap = styled.div`
    background-color: white;
    width: 700px;
    margin: 10px auto;
`

const Div = styled.div`
    padding: 0 5px 0 5px;
`

const DetailBtn = styled.button`
`

export default Order;