import React from "react";
import { GlobalStyle, Container } from './styles';
import MainTemplate from '../components/templates/MainTemplate';

const Home = () => {
  return (
    <React.StrictMode>
      <GlobalStyle />
      <Container>
        <MainTemplate />
      </Container>
    </React.StrictMode>
  )
};

export default Home;
