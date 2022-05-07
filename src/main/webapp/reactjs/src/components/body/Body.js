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
      console.log(response);
      if(response.status === 200) {
        setProducts(response.data);
      } else {
        alert('오류 발생!');
      }
    }).catch((error) => {
      alert('오류 발생!');
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