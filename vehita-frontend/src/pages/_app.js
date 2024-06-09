import App from "next/app";
import Head from "next/head";

import { ThemeProvider } from "styled-components";
import Reset from "../commons/reset";

export default class RootApp extends App {
  constructor(props) {
    super(props);
  }

  render() {
    const { Component, ...other } = this.props;
    return (
      <>
        <Reset />
        <Head>
          <title>vehita.io</title>
          <link rel="shortcut icon" href="/favicon.ico" />
          <meta
            name="description"
            content=""
          />
          <meta property="og:title" content="Vehita" />
          <meta
            property="og:description"
            content=""
          />
        </Head>
        <main>
          <Component {...other} />
        </main>
      </>
    );
  }
}
