import styled from "styled-components";

const Logo = () => {
  return (
      <Wrap>
        👟Shoes Store
      </Wrap>
  );
}

const Wrap = styled.div`
  text-align: center;
  font-size: 30px;
  font-weight: bold;
  padding: 10px 0 10px 0;
`

export default Logo;