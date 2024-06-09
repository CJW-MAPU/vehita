import React from 'react';
import Header from '../organisms/Header';
import { SubTitle } from '../atoms/Title';
import DatasetContainer from '../organisms/DatasetContainer';
import Footer from '../organisms/Footer';

const MainTemplate = () => (
  <>
    <Header />
    <SubTitle>Vehicle Dataset Preview</SubTitle>
    <DatasetContainer />
    <Footer />
  </>
);

export default MainTemplate;
