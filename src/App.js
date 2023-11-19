import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import React from 'react';
import APage from './APage';
import BPage from './BPage';
import Inven from './Inven';
import MyPage from './MyPage';
import Login from './login';

function App() {
  
  return (
    <Router>
      <Routes>
        <Route path="/Store" element={<BPage/>} />
        <Route path="/" element={<APage/>} />
        <Route path="/Inven" element={<Inven/>} />
        <Route path='/MyPage' element={<MyPage/>} />
        <Route path='/Login' element={<Login/>} />
      </Routes>
    </Router>
  );
}

export default App;
