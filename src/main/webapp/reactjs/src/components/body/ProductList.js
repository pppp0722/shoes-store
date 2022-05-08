import styled from "styled-components";
import Product from "./Product";

const ProductList = ({category, products, orderItems, setOrderItems}) => {

  const productComponents = category !== 'ALL' ? products.filter(product => product.category === category).map(product =>
    <Product product={product} orderItems={orderItems} setOrderItems={setOrderItems}/>) :
    products.map(product =>
      <Product product={product} orderItems={orderItems} setOrderItems={setOrderItems}/>);

  return (
    <Wrap>
      {productComponents}
    </Wrap>
  );
}

const Wrap = styled.div`
  height: 700px;
  overflow: auto;
`

export default ProductList;