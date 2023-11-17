// APage.js
import React, {useState} from 'react';
import { useNavigate } from 'react-router-dom';

const APage = () => {
    const navigate = useNavigate();

    const [userId, setUserId] = useState('');

    const handleInputChange = (e) => {
        setUserId(e.target.value);
    };

    const changeExample = (pagename) => {
        localStorage.setItem('userId', userId);
        navigate(`/${pagename}`);
    };

    return (
    <div>
        <h1>Main 페이지</h1>
        <div>
            <input type="text" value={userId} onChange={handleInputChange} />
        </div>//
            <button onClick={() => changeExample("Store")}>STORE</button>
        <div>
            <button onClick={() => changeExample("Inven")}>INVEN</button>
        </div>
        <div>
            <button onClick={() => changeExample("MyPage")}>MyPage</button>
        </div>
    </div>
    );
};

export default APage;
