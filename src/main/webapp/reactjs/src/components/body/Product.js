import styled from "styled-components";

const Product = ({product, orderItems, setOrderItems}) => {

  const handleImgClick = () => {

    let isExist = false;
    orderItems.forEach(orderItems => {
      if(orderItems.productId === product.productId) {
        isExist = true;
      }
    });
    if(!isExist) {
      setOrderItems([...orderItems, product]);
    }
  }

  return (
      <Wrap>
        <Image onClick={handleImgClick}>
          <div>{product.name}</div>
          <div>{product.price}원</div>
        </Image>
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
  background-color: white;
`

export default Product;