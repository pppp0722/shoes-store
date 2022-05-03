import styled from "styled-components";
import Logo from "./Logo";
import BrandList from "./BrandList";
import { useState } from "react";
import AddProduct from "./AddProduct";

const Header = ({setBrand}) => {

  const [showModal, setShowModal] = useState(false);

  const handleBtnClick = () => {
    setShowModal(true);
  }

  return (
      <Wrap>
        <Logo/>
        <AddBtn onClick={handleBtnClick}>Add Product</AddBtn>
        {showModal ? <AddProduct setShowModal={setShowModal}/> : null}
        <BrandList setBrand={setBrand}/>
      </Wrap>
  );
}

const Wrap = styled.div`
  background-color: skyblue;
`

const AddBtn = styled.button`
  width: 200px;
  margin: 0 380px 0 380px;
`

export default Header;