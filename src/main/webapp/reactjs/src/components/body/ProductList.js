import styled from "styled-components";
import Product from "./Product";

const ProductList = ({products, orderItems, setOrderItems}) => {

  const productComponents = products.map(product =>
    <Product product={product} orderItems={orderItems} setOrderItems={setOrderItems}/>
  );

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