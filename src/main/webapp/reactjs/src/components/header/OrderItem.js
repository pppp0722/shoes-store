import styled from "styled-components";

const OrderItem = ({orderItem}) => {

    return (
      <Wrap>
        <Div>product id: {orderItem.productId}</Div>
        <Div>price: {orderItem.price}</Div>
        <Div>quantity: {orderItem.quantity}</Div>
        <Div>created at: {orderItem.createdAt}</Div>
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

export default OrderItem;