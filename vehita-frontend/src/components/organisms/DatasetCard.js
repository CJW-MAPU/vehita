import React from 'react';
import styled from 'styled-components';

const Card = styled.div`
  background: white;
  padding: 10px;
  border-radius: 10px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  text-align: center;
  img {
    width: 150px;
    height: 150px;
    border-radius: 10px;
    object-fit: cover;
  }
  p {
    color: black;
  }
`;

const DatasetCard = ({ location, car, price, imgSrc }) => (
  <Card>
    <img src={imgSrc} alt={`Map of ${location}`} />
    <p>{location}</p>
    <p>{car}</p>
    <p>{price}</p>
  </Card>
);

export default DatasetCard;
