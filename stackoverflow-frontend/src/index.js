import React, { useState } from 'react';
import ReactDOM from 'react-dom';
import MainContainer from "./components/MainContainer"
import './css/index.css';
import { BrowserRouter } from "react-router-dom";



ReactDOM.render(
    <React.StrictMode>

        <BrowserRouter>
            <MainContainer />
        </BrowserRouter>

    </React.StrictMode >
    , document.getElementById('root'));