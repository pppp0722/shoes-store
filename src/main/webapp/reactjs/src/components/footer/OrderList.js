import styled from "styled-components";
import OrderItem from "./OrderItem";

const OrderList = ({orderItems, setOrderItems, totalPrice, setTotalPrice}) => {
  const index = 0;

  const orderItemComponents = orderItems.map((orderItem, index) =>
    <OrderItem index={index} orderItem={orderItem} orderItems={orderItems} setOrderItems={setOrderItems} totalPrice={totalPrice} setTotalPrice={setTotalPrice}/>
  );

  return (
      <Wrap>
        {orderItemComponents}
      </Wrap>
  );
}

const Wrap = styled.div`
  height: 65px;
  overflow: auto;
`

export default OrderList;