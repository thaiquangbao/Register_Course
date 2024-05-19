// Remove the unused import statement for React
import { useNavigate } from 'react-router'
import { useState } from 'react'
import './LoginComponent.scss'
import education from "../../../public/img/education.jpg"
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Alert from '@mui/material/Alert';
import axios from 'axios';
import Cookies from 'universal-cookie';

function LoginComponent() {
    const [studentId, setStudentId] = useState('');
    const [password, setPassword] = useState('');
    const [alertError, setAlertError] = useState(false);
    const [alertSuccess, setAlertSuccess] = useState(false);
    const [contentError, setContentErr] = useState('')
    const cookies = new Cookies();
    const navigate = useNavigate()
    const login = async () => {
        if (!studentId || !password) {
            setContentErr('Vui Lòng Nhập Đầy đủ Thông Tin')
            setAlertError(true);
            setTimeout(() => {
                setAlertError(false);
            }, 3000)
        } else {
            // http://localhost:8999/auth/login
            try {
                const axiosLogin = await axios.post("http://localhost:8999/auth/login", {
                    userName: studentId,
                    password: password
                }, {
                    headers: { "Content-type": "application/json" }

                })

                if (axiosLogin.data.statusCode == 500) {
                    setContentErr('Thông tin đăng nhập không chính xác')
                    setAlertError(true);
                    setTimeout(() => {
                        setAlertError(false);
                    }, 3000)
                }
                else {
                    cookies.set('jwt', axiosLogin.data.token);
                    localStorage.setItem("user", JSON.stringify(axiosLogin.data))
                    setAlertSuccess(true);
                    setTimeout(() => {
                        setAlertSuccess(false);
                        navigate("/register/RegisterChild")
                    }, 3000)
                }
            } catch (error) {
                console.log(error);
            }

        }
    }
    const handleStudentIdChange = (event) => {
        setStudentId(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };
    return (
        <div className='backGroundLogin'>
            {alertSuccess &&
                <Alert variant="outlined" severity="success" className='successForm'>
                    Thông tin đăng nhập chính xác
                </Alert>
            }
            {alertError &&
                <Alert variant="filled" severity="error" className='errorForm'>
                    {contentError}
                </Alert>
            }
            <img className='loginImg' src={education} alt="" />
            <div className="login-form">
                <div className='header-login-form'>
                    <h2>CỔNG THÔNG TIN SINH VIÊN</h2>
                </div>

                <TextField
                    id="standard-basic"
                    label="Nhập mã sinh viên"
                    variant="standard"
                    className='textField'
                    value={studentId}
                    onChange={handleStudentIdChange}
                />
                <TextField
                    id="standard-basic"
                    label="Nhập mật khẩu"
                    variant="standard"
                    className='textField'
                    value={password}
                    onChange={handlePasswordChange}
                />
                <FormControlLabel
                    control={<Checkbox size="small" />}
                    label="Đã tốt nghiệp"
                    className='checkBox'
                />
                <Button variant="outlined" onClick={login}>Đăng nhập</Button>


            </div>
        </div>
    )
}

export default LoginComponent