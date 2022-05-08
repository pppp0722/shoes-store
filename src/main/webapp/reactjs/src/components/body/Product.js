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
        </Image>
        <PriceDiv>{product.price}원</PriceDiv>
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

const PriceDiv = styled.div`
  text-align: right;
  height: 20px;
`

export default Product;