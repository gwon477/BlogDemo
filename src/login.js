import axios from "axios";
import React,{ useState } from "react";
import { useNavigate } from 'react-router-dom';

function LoginPage() {

    const [userEmail, setUserEmail] = useState('');
    const [blogName, setBlogName] = useState('');
    const [nickName, setNickName] = useState('');

    const navigate = useNavigate();

    const checkUesr = async() => {
        try{
            const response = await axios.get('http://localhost:8080/Login/api/signup', {
                params: {
                    userEmail,
                    blogName,
                    nickName,
                },
            });
            if(response.data[0] === "0"){
                alert("회원가입 되었습니다.");
                localStorage.setItem("userEmail",response.data[1]);
                navigate('/')
            }else{
                alert("로그인 되었습니다");
                localStorage.setItem("userEmail",response.data[1]);
                navigate('/')
            }
            //setIsUser(response.data);

        }catch(error){
            console.error('Error:',error);
        }
    }

    return(
        <div>
            <div>
                loginPage
            </div>
            <ul>
                <li>
                    사용자의 정보를 입력받는 페이지.
                </li>
                <li>
                    사용자의 이메일 정보, 블로그 이름, 닉네임을 입력받는다.
                </li>
                <li>
                    소셜 로그인으로 구현하는 경우 소셜로 부터 해당 정보들을 받을 수 있다.
                </li>
            </ul>

            <div>
                <strong>Email</strong> <input type="text" onChange={(e) =>setUserEmail(e.target.value)}></input>
            </div>
            <div>
                <strong>BlogName</strong> <input type="text" onChange={(e) =>setBlogName(e.target.value)}></input>
            </div>
            <div>
                <strong>NickNmae</strong> <input type="text" onChange={(e) =>setNickName(e.target.value)}></input>
            </div>
            <button onClick={checkUesr}>로그인</button>
        </div>
    )
}
export default LoginPage;