import styled from 'styled-components';

const Button = styled.button`
  background: ${props => props.primary ? '#007bff' : 'transparent'};
  color: ${props => props.primary ? 'white' : '#007bff'};
  border: ${props => props.primary ? 'none' : '1px solid #007bff'};
  padding: 10px 20px;
  cursor: pointer;
  border-radius: 5px;
`;

export default Button;
