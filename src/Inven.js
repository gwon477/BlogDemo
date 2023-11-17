import React, {useEffect, useState} from "react";
import axios from "axios";


function Inven(){
    const userId = localStorage.getItem('userId');

    const [items, setItems] = useState([]);
    
    
    useEffect(() => {
        inventory(userId)
    }, []);

    const inventory = async (userId) => {
        try {
            //   // Make a GET request using Axios to your API endpoint with the retrieved ID
                    const response = await axios.get(`http://localhost:8080/Inven/api/${userId}`); // Update the URL accordingly
                    setItems(response.data);
                } catch (error) {
                    console.error('Error fetching user data:', error);
            }
    }

    const itemlen = items.length;

    return(
        <>
            <p>A. 인벤토리 페이지에 접속하면 사용자의 아이디를 사용해 
            인벤토리 아이템의 목록을 보여준다. </p>
            <div>
                userId = {userId}
            </div>
            <div style={{ display: 'flex', flexDirection: 'column' }}>
        {items.map((item, index) => (
          <div key={index} style={{ border: '1px solid #ccc', padding: '10px', marginBottom: '10px' }}>
            <div><strong>Name:</strong> {item.itemName}</div>
            <div><strong>Info:</strong> {item.itemInfo}</div>
          </div>
        ))}
      </div>
        </>
    )
}
export default Inven;