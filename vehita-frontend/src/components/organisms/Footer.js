import React from 'react';
import styled from 'styled-components';

const FooterContainer = styled.footer`
  padding: 20px;
  text-align: center;
  margin-top: 40px;
  p {
    margin: 5px 0;
    color: black;
  }
`;

const Footer = () => (
  <FooterContainer>
    <p>Vehita</p>
    <p>서비스</p>
    <p>고객센터</p>
    <p>전화: 02-XXX-XXXX</p>
    <p>평일: 09:00 ~ 18:00</p>
  </FooterContainer>
);

export default Footer;
