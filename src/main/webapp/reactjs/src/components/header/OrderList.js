import styled from "styled-components";
import Order from "./Order";

const OrderList = ({orders}) => {

  const orderComponents = orders.map(order =>
      <Order order={order}/>);

  return (
    <Wrap>
      {orderComponents}
    </Wrap>
  );
}

const Wrap = styled.div`
  height: 700px;
  overflow: auto;
`

export default OrderList;