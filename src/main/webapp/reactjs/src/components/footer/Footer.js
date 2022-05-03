import styled from "styled-components";
import Order from "./Order";

const Footer = ({orderItems, setOrderItems}) => {

  return (
      <Wrap>
        <Order orderItems={orderItems} setOrderItems={setOrderItems}/>
      </Wrap>
  );
}

const Wrap = styled.div`
`

export default Footer;