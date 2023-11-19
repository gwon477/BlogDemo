// APage.js
import React, {useState} from 'react';
import { useNavigate } from 'react-router-dom';

const APage = () => {
    const navigate = useNavigate();

    const userEmail = localStorage.getItem('userEmail');

    const changeExample = (pagename) => {
        navigate(`/${pagename}`);
    };

    return (
    <div>
        <h1>Main 페이지</h1>
        <strong>UserEmail</strong> : {userEmail}
        <div>//
            <button onClick={() => changeExample("Store")}>STORE</button>
        </div>
        <div>
            <button onClick={() => changeExample("Inven")}>INVEN</button>
        </div>
        <div>
            <button onClick={() => changeExample("MyPage")}>MyPage</button>
        </div>
        <div>
            <button onClick={() => changeExample("Login")}>Login</button>
        </div>
    </div>
    );
};

export default APage;
