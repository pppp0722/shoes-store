import styled from "styled-components";

const Brand = ({brand, setBrand}) => {
  
  const handleBtnClick = () => {
    setBrand(brand);
  }
  return (
      <Wrap>
        <BrandButton onClick={handleBtnClick}>
          {brand}
        </BrandButton>
      </Wrap>
  );
}

const Wrap = styled.div`
  font-size: 20px;
  display: inline-block;
  padding: 0 5px 0 5px;
`

const BrandButton = styled.button`
  width: 100px;
`


export default Brand;