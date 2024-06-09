import React from 'react';
import styled from 'styled-components';
import NavBar from '../molecules/NavBar';
import { MainTitle } from '../atoms/Title';
import Button from '../atoms/Button';

const HeaderContainer = styled.header`
  padding: 20px;
  text-align: center;
`;

const Main = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 400px; /* Adjust the height as needed */
  position: relative;
  background-image: url('/main.jpg');
  background-size: cover;
  background-position: center;
  color: black;

  &::after {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(255, 255, 255, 0.5); /* White overlay with 50% opacity */
    z-index: 1;
  }
`;

const Content = styled.div`
  position: relative;
  z-index: 2;
  text-align: center;
`;

const TitleContainer = styled.div`
  text-align: center;
  margin-bottom: 20px; /* Adjust the spacing as needed */
`;

const ButtonContainer = styled.div`
  margin-top: 20px; /* Adjust the spacing as needed */
`;

const Header = () => (
  <HeaderContainer>
    <NavBar />
    <Main>
      <Content>
        <TitleContainer>
          <MainTitle>Trading vehicle driving data with ERC-20 Tokens</MainTitle>
        </TitleContainer>
        <ButtonContainer>
          <Button primary>White paper</Button>
        </ButtonContainer>
      </Content>
    </Main>
  </HeaderContainer>
);

export default Header;
