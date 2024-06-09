import React from 'react';
import styled from 'styled-components';
import Button from '../atoms/Button';

const NavBarContainer = styled.nav`
  display: flex;
  justify-content: space-between;
  align-items: center;
  h1 {
    margin: 0;
    color: black;
  }
  div {
    display: flex;
    gap: 10px;
  }
`;

const MainSize = styled.h1`
  font-size: 2.0em;
`;

const NavBar = () => (
  <NavBarContainer>
    <MainSize>Vehita</MainSize>
    <div>
      <Button>Market</Button>
      <Button>Sign in</Button>
      <Button primary>Sign up</Button>
    </div>
  </NavBarContainer>
);

export default NavBar;
