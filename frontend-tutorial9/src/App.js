import React, { Component } from 'react';
import Layout from './components/Layout/Layout';
import Restorans from './containers/Restorans/Restorans';
// import logo from './logo.svg';
// import './App.css';

class App extends Component{
  render(){
    return(
      <div>
        <Layout>
          <Restorans />
        </Layout>
      </div>
    );
  }
}

export default App;
