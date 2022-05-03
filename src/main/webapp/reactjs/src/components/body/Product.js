import styled from "styled-components";

const Product = ({product, orderItems, setOrderItems}) => {

  const handleImgClick = () => {
    const newOrderItem = {
      productId: product.productId,
      name: product.name,
      price: product.price    
    };

    let isExist = false;
    orderItems.forEach(orderItem => {
      if(orderItem.productId === product.productId) {
        isExist = true;
      }
    });
    if(!isExist) {
      setOrderItems([...orderItems, newOrderItem]);
    }
  }

  return (
      <Wrap>
        <Image onClick={handleImgClick}>{product.name}</Image>
      </Wrap>
  );
}

const Wrap = styled.div`
  display: inline-block;
  padding: 5px 5px 5px 5px;
`

const Image = styled.div`
  width: 147px;
  height: 147px;
  background-color: blue;s
`

export default Product;