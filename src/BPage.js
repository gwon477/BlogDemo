import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const BPage = () => {

    //const [userData, setUserData] = useState('');
    const [userPoint, setUserPoint] = useState(-1); 
    const [userItem,setUserItem] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {
    // // Retrieve the user ID from localStorage
        const userId = localStorage.getItem('userId');

    // Call the API with the retrieved ID
        fetchUserData(userId);
        checkInven(userId);
    }, []);

    //사용자 포인트 가져오기
    const fetchUserData = async (userId) => {
        try {
    //   // Make a GET request using Axios to your API endpoint with the retrieved ID
            const response = await axios.get(`http://localhost:8080/api/userData/${userId}`); // Update the URL accordingly
            setUserPoint(response.data);
        } catch (error) {
            console.error('Error fetching user data:', error);
            setUserPoint(-1);
        }
    };

    //사용자 포인트 업데이트
    const updatePoint = async (userId, Point, itemId) => {
        try{
            await axios.get(`http://localhost:8080/api/purchase/${userId}?point=${Point}&itemId=${itemId}`);
        }catch (error) {
            console.error('Error fetching user data:', error);
        }
    }

    //사용자 인벤토리 체크
    const checkInven = async (userId) => {
      try{
        const response = await axios.get(`http://localhost:8080/api/invencheck/${userId}`);
        setUserItem(response.data)
    }catch (error) {;
        console.error('Error fetching user data:', error);
    }
    }    

    const handlePurchase = async(itemNum,productName, price) => {
        const userId = localStorage.getItem('userId');
        const Point = userPoint - price;
        if (userItem.includes(itemNum)){
          alert("이미 구매한 아이템입니다.")
        }else{
        // 가격과 사용자 포인트 비교
          if (userPoint >= price) {
            // 알림 창 띄우기
              const isConfirmed = window.confirm(`"${productName}" 상품을 ${price} 포인트에 구매하시겠습니까?`);
      
              if (isConfirmed) {
                  // "확인" 버튼 클릭 시
                  alert("구매되었습니다.");
                  // 사용자 포인트 업데이트
                  setUserPoint(Point);
                  updatePoint(userId,Point,itemNum)

                  const isConfirmed = window.confirm("인벤토리로 이동하시겠습니까?");
                  if(isConfirmed){
                    navigate('/Inven')
                  }else{

                  }
                  //invencontroller(userId,itemNum)
              } else {
              // "취소" 버튼 클릭 시
                  alert("취소되었습니다.");
              // 페이지 Redirect
              // 여기에 원래 페이지로 Redirect하는 로직을 추가해야 합니다.
              // 예시: window.location.href = '/original-page';
              }
          } else {
              // 포인트가 부족한 경우 알림 창 띄우기
                  alert("포인트가 부족합니다.");
              // 여기에 원래 페이지로 Redirect하는 로직을 추가해야 합니다.
              // 예시: window.location.href = '/original-page';
          }
        }
      checkInven(userId);
      };
      
      

    return (
        <div>
        <h1>상점 페이지</h1>
        <div>
          <h3>UserPoint = {userPoint}</h3>
        </div>
        <div style={{ display: 'flex', justifyContent: 'space-around', flexWrap: 'wrap' }}>
          <div style={{ flex: '1', border: '1px solid #ccc', padding: '10px', margin: '10px', textAlign: 'center' }}>
            <h4>상품1</h4>
            <p>가격: 10</p>
            <button onClick={() => handlePurchase(1,'상품1', 10)}>구매</button>
          </div>
          <div style={{ flex: '1', border: '1px solid #ccc', padding: '10px', margin: '10px', textAlign: 'center' }}>
            <h4>상품2</h4>
            <p>가격: 50</p>
            <button onClick={() => handlePurchase(2,'상품2', 50)}>구매</button>
          </div>
          <div style={{ flex: '1', border: '1px solid #ccc', padding: '10px', margin: '10px', textAlign: 'center' }}>
            <h4>상품3</h4>
            <p>가격: 150</p>
            <button onClick={() => handlePurchase(3,'상품3', 150)}>구매</button>
          </div>
        </div>
        <p>userPas:{userItem}</p>
      </div>
    );
};

export default BPage;
