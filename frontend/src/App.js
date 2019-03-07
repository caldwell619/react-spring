import React, { Component } from 'react';
import axios from 'axios';

class App extends Component {
  componentDidMount(){
        axios.get("/api/test").then(res => console.log(res))
            .catch(error => console.log(error))
  }
  render() {
    return (
      <h1>Doing stuff</h1>
    );
  }
}

export default App;
