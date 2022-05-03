import styled from "styled-components";
import Brand from "./Brand";

const BrandList = ({setBrand}) => {
  return (
      <Wrap>
        <Brand brand="Nike" setBrand={setBrand}/>
        <Brand brand="Adidas" setBrand={setBrand}/>
        <Brand brand="New Balance" setBrand={setBrand}/>
        <Brand brand="Converse" setBrand={setBrand}/>
        <Brand brand="Vans" setBrand={setBrand}/>
        <Brand brand="etc" setBrand={setBrand}/>
      </Wrap>
  );
}

const Wrap = styled.div`
  display: flex;
  justify-content: center;
  padding: 10px 0 10px 0;
`

export default BrandList;