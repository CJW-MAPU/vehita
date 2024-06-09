import React from 'react';
import styled from 'styled-components';
import DatasetCard from './DatasetCard';

const Container = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: center;
  margin-top: 20px;
`;

const datasets = [
  { id: 1, location: '서울-부산', car: 'Avante CN7', price: '0.000076 ETH' },
  { id: 2, location: '양양-강원도', car: 'Genesis G80', price: '0.000012 ETH' },
  { id: 3, location: '서울-영동', car: 'Genesis G80', price: '0.000043 ETH' },
  { id: 4, location: '한국-강릉', car: 'Avante CN7', price: '0.000072 ETH' },
  { id: 5, location: '인천-서울', car: 'Genesis G80', price: '0.000049 ETH' },
  { id: 6, location: '서울-강릉', car: 'Avante CN7', price: '0.000058 ETH' }
];

const DatasetContainer = () => (
  <Container>
    {datasets.map(dataset => (
      <DatasetCard
        key={dataset.id}
        location={dataset.location}
        car={dataset.car}
        price={dataset.price}
        imgSrc={`/map${dataset.id}.png`}
      />
    ))}
  </Container>
);

export default DatasetContainer;
