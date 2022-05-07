import styled from "styled-components";
import { useState } from "react";
import axios from "axios";

const AddProduct = ({setShowModal}) => {

    const [name, setName] = useState("");
    const [category, setCategory] = useState("SNEAKERS");
    const [brand, setBrand] = useState("");
    const [price, setPrice] = useState(0);
    const [description, setDescription] = useState("");

    const handleCloseBtnClick = () => {
        setShowModal(false);
    }

    const handleNameChange = e => {
        setName(e.target.value);
    }

    const handleCategoryChange = e => {
        setCategory(e.target.value);
    }

    const handleBrandChange = e => {
        setBrand(e.target.value);
    }

    const handlePriceChange = e => {
        setPrice(e.target.value);
    }

    const handleDescriptionChange = e => {
        setDescription(e.target.value);
    }

    const handleSubmitBtnClick = () => {
        if(name === "" || brand === "" || price === "" || description === "") {
            alert("잘못된 입력!");
            return;
        }

        const requestParam = {
            name: name,
            category: category,
            brand: brand,
            price: price,
            description: description
        };

        axios.post("api/v1/products", requestParam)
        .then(response => {
            console.log(response);
            if(response.status === 200) {
                alert("추가 완료!");
            } else {
                alert("오류 발생!");
            }
        }).catch(error => {
            alert('오류 발생!');
        });
    }

    return (
        <Wrap>
            <CloseBtn onClick={handleCloseBtnClick}>X</CloseBtn>
            <form>
                <InputBox>
                    <Input type="text" placeholder="name" onChange={handleNameChange}></Input>
                </InputBox>
                <InputBox>
                    <RadioInput type="radio" id="sneakers" name="category" onChange={handleCategoryChange} value="SNEAKERS" checked></RadioInput>
                    <label htmlFor="sneakers">Sneakers</label>
                </InputBox>
                <InputBox>
                    <RadioInput type="radio" id="shoes" name="category" onChange={handleCategoryChange} value="SHOES"></RadioInput>
                    <label htmlFor="shoes">Shoes</label>
                </InputBox>
                <InputBox>
                    <Input placeholder="brand" onChange={handleBrandChange}></Input>
                </InputBox>
                <InputBox>
                    <Input type="number" placeholder="price" onChange={handlePriceChange}></Input>
                </InputBox>
                <InputBox>
                    <Input style={{height: 200}}placeholder="description" onChange={handleDescriptionChange}></Input>
                </InputBox>
                <InputBox>
                    <SubmitBtn onClick={handleSubmitBtnClick}>Submit</SubmitBtn>
                </InputBox>
            </form>
        </Wrap>
  );
}

const Wrap = styled.div`
  position: fixed;
  width: 900px;
  height: 900px;
  top: 30px;
  left: 50%;
  transform: translate(-50%, 0);
  z-index: 99;
  background-color: rgba(0, 0, 0, 0.5);
`

const CloseBtn = styled.button`
    display: block;
    float: right;
`

const InputBox = styled.div`
    width: 300px;
    margin: 40px auto;
`

const Input = styled.input`
    width: 300px;
`

const RadioInput = styled.input`
`

const SubmitBtn = styled.button`
    width: 300px;
`

export default AddProduct;