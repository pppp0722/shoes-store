import styled from 'styled-components';
import ProductList from './ProductList';
import {useState, useEffect} from 'react';
import axios from 'axios';

const Body = ({brand, orderItems, setOrderItems}) => {

  const [products, setProducts] = useState([]);

  useEffect(() => {
    axios.get('api/v1/products', {
      params: {
        brand: brand
      }
    }).then((response) => {
      setProducts(response.data);
    }).catch((error) => {
      alert('데이터를 가져오는 중 오류가 발생하였습니다!');
    });
  }, [brand]);

  return (
      <Wrap>
        <ProductList products={products} orderItems={orderItems} setOrderItems={setOrderItems}/>
      </Wrap>
  );
}

const Wrap = styled.div`
  height: 700px;
`

export default Body;