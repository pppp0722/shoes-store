import styled from 'styled-components';
import ProductList from './ProductList';
import {useState, useEffect} from 'react';
import axios from 'axios';

const Body = ({brand, orderItems, setOrderItems}) => {

  const [products, setProducts] = useState([]);

  const [category, setCategory] = useState("ALL");

  useEffect(() => {
    axios.get('api/v1/products', {
      params: {
        brand: brand
      }
    }).then((response) => {
      console.log(response);
      if(response.status === 200) {
        setProducts(response.data);
      } else if(response.status === 204) {
        setProducts([]);
        alert("데이터가 존재하지 않습니다.");
      } else {
        alert('오류 발생!');
      }
    }).catch((error) => {
      console.log(error);
      alert(error.response.data);
      if(error.response.status === 400) {
        alert(error.response.data);
      } else if(error.response.status === 500) {
        alert(error.response.data);
      }
    });
  }, [brand]);

  const handleSelectChange = e => {
    setCategory(e.target.value);
  }

  return (
      <Wrap>
        <CategorySelect onChange={handleSelectChange}>
          <option value="ALL">All</option>
          <option value="SNEAKERS">Sneakers</option>
          <option value="SHOES">Shoes</option>
        </CategorySelect>
        <ProductList category={category} products={products} orderItems={orderItems} setOrderItems={setOrderItems}/>
      </Wrap>
  );
}

const Wrap = styled.div`
  height: 700px;
`

const CategorySelect = styled.select`
  margin: 5px 5px 5px 5px;
`

export default Body;