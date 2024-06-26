import { createGlobalStyle } from "styled-components";
import reset from "styled-reset";

const GlobalStyles = createGlobalStyle`
  ${reset}
  a{
    text-decoration: none;
    color: inherit;
  }
  *{
    box-sizing: border-box;
  }
  body {
    font-family: sans-serif;
    font-size: 14px;
    background: #FFFFFF;
    color: white;
    overflow-x: hidden;
    line-height: 130%;
  }
  @font-face {
    font-family: 'cafe24-air';
    src: url("/fonts/Cafe24Ssurroundair.ttf");
  }
`

export default GlobalStyles;