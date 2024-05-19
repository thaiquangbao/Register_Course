import { FormControl, InputLabel, MenuItem, Select } from '@mui/material'
import React, { useState } from 'react'
import Paper from '@mui/material/Paper';
import Stack from '@mui/material/Stack';
import { styled } from '@mui/material/styles';
import "./SlideBarComponent.scss"
import { useNavigate } from 'react-router';
const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : 'hsla(0, 0%, 100%, 0)',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'left',
    color: theme.palette.text.secondary,
}));
function SideBarComponent() {
    const [num, setNum] = useState(1)
    const nav = useNavigate()
    const user = JSON.parse(localStorage.getItem("user"));
    const changeNum1 = () => {
        // nav("/register/RegisterChild")
        nav("/register/RegisterChild")
    }
    const handleChange = (event) => {
        setNum(event.target.value);
    };
    const changeNum2 = () => {
        nav("/register/RegisterFather")

    }
    return (
        <div className='sidebar'>
            <div className='option-change-page'>
                <FormControl fullWidth>

                    <Select
                        labelId=""
                        id="1"
                        value={num}
                        label="Age"
                        onChange={handleChange}
                        style={{ fontSize: '15px' }}
                    >
                        <MenuItem value={1} onClick={changeNum1}>Đăng Ký Học Phần</MenuItem>
                        <MenuItem value={2} onClick={changeNum2}>Xem Lịch Học</MenuItem>

                    </Select>
                </FormControl>
            </div>
            <div className='first-sections'>
                <img className='img-avt' src="https://th.bing.com/th/id/R.8b167af653c2399dd93b952a48740620?rik=%2fIwzk0n3LnH7dA&pid=ImgRaw&r=0" alt="" />
            </div>
            <div className="info">
                <div className="">
                    <Stack
                        direction="column"
                        justifyContent="center"
                        alignItems="flex-start"
                        spacing={0.5}
                    >
                        <Item> <p className='txt-info'>Họ Và Tên:{user.fullname}</p></Item>
                        <Item> <p className='txt-info'>Lớp Danh Nghĩa: {user.lopHP}</p></Item>
                        <Item> <p className='txt-info'>Quê Quán:  {user.address}</p></Item>
                        <Item> <p className='txt-info'>Giới Tính: {user.gender ? "Nam" : "Nữ"} </p></Item>
                        <Item> <p className='txt-info'> Tình Trạng Học Vấn: {user.role == "SV" ? "Còn Học" : "Tốt Nghiệp"}</p></Item>
                    </Stack>
                </div>
            </div>

            {/* <img className='img-avt' src="https://th.bing.com/th/id/R.8b167af653c2399dd93b952a48740620?rik=%2fIwzk0n3LnH7dA&pid=ImgRaw&r=0" width="200px" height="200px" alt="" />
            <div className="info">
                <div className="">
                    <p className='txt-info'>Họ Và Tên: {user.fullname}</p>
                    <p className='txt-info'>Lớp Danh Nghĩa: {user.lopHP}</p>
                    <p className='txt-info'>Quê Quán:  {user.address}</p>
                    <p className='txt-info'>Giới Tính:  {user.gender ? "Nam" : "Nữ"}</p>

                    <p className='txt-info'> Tình Trạng Học Vấn: {user.role == "SV" ? "Còn Học" : "Tốt Nghiệp"}</p>
                </div>
            </div> */}

        </div>
    )
}

export default SideBarComponent