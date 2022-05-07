import styled from "styled-components";
import {useState} from "react";
import Header from "./components/header/Header";
import Footer from "./components/footer/Footer";
import Body from "./components/body/Body";

const App = () => {
  const [brand, setBrand] = useState("Nike");
  const [orderItems, setOrderItems] = useState([]);

  return (
      <Wrap>
        <Header setBrand={setBrand}/>
        <Body brand={brand} orderItems={orderItems} setOrderItems={setOrderItems}/>
        <Footer orderItems={orderItems} setOrderItems={setOrderItems}/>
      </Wrap>
  );
}

const Wrap = styled.div`
  width: 960px;
  margin: 0 auto;
  background-color: skyblue;
`;

export default App;
