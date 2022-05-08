import styled from "styled-components";
import Logo from "./Logo";
import BrandList from "./BrandList";
import { useState } from "react";
import AddProduct from "./AddProduct";
import FindOrder from "./FindOrder";

const Header = ({setBrand}) => {

  const [showModal, setShowModal] = useState(false);

  const [showFindModal, setShowFindModal] = useState(false);

  const handleBtnClick = () => {
    setShowModal(true);
  }

  const handleFindBtnClick = () => {
    setShowFindModal(true);
  }

  return (
      <Wrap>
        <Logo/>
        <AddBtn onClick={handleBtnClick}>Add Product</AddBtn>
        {showModal ? <AddProduct setShowModal={setShowModal}/> : null}
      
        <FindBtn onClick={handleFindBtnClick}>Find Order</FindBtn>
        {showFindModal ? <FindOrder setShowFindModal={setShowFindModal}/> : null}

        <BrandList setBrand={setBrand}/>
      </Wrap>
  );
}

const Wrap = styled.div`
  background-color: skyblue;
`

const AddBtn = styled.button`
  width: 100px;
  margin: 0 10px 0 375px;
`

const FindBtn = styled.button`
  width: 100px;
  margin: 0 0 0 0;
`

export default Header;