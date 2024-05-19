import { FormControl, Grid, InputLabel, MenuItem, Select } from '@mui/material';
import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import "./RegisterFatherComponent.scss"
import axios from 'axios';

function RegisterFather() {
    const user = JSON.parse(localStorage.getItem("user"))
    const [registerHocKy, setRegisterHocKy] = useState([])
    const [course, setCourse] = useState([])
    const [render, setRender] = useState(true)
    const [classCourseDetail, setClassCourseDetail] = useState([])
    const [danhSachDangKy, setDanhSachDangKy] = useState([]);
    const [hocky, setHocKy] = useState('1')
    const [lecture, setLecture] = useState([])

    const handleChange = (event) => {
        setHocKy(event.target.value);
    };
    // useEffect(() => {
    //     // http://localhost:8999/api/course/findAll
    //     const getCourse = async () => {
    //         try {
    //             const courseData = await axios.get('http://localhost:8999/api/course/findAll', {
    //                 headers: { Authorization: `Bearer ${user.token}` }

    //             })
    //             setCourse(courseData.data)
    //         } catch (error) {
    //             console.log(error);
    //         }
    //     }
    //     getCourse()
    // }, [])
    // useEffect(() => {
    //     const getLecture = async () => {
    //         try {
    //             const lectureData = await axios.get('http://localhost:8999/api/lecture/findAll',
    //                 {
    //                     headers: { Authorization: `Bearer ${user.token}` }

    //                 })
    //             setLecture(lectureData.data)
    //         } catch (error) {
    //             console.log(error);
    //         }
    //     }
    //     getLecture()
    // }, [])
    // useEffect(() => {
    //     const getRegisterByHocKy = async () => {
    //         try {
    //             const registerData = await axios.get(`http://localhost:8999/register/getRegister/${hocky}`,
    //                 {
    //                     headers: { Authorization: `Bearer ${user.token}` }

    //                 })
    //             setRegisterHocKy(registerData.data)
    //         } catch (error) {
    //             console.log(error);
    //         }
    //     }
    //     getRegisterByHocKy()
    // }, [hocky, render, user.token])
    // useEffect(() => {
    //     const getClassCourseDetail = async () => {
    //         try {
    //             const classCourseDetailData = await axios.get('http://localhost:8999/api/classCourseDetail/findAll',
    //                 {
    //                     headers: { Authorization: `Bearer ${user.token}` }

    //                 })
    //             setClassCourseDetail(classCourseDetailData.data)
    //         } catch (error) {
    //             console.log(error);
    //         }
    //     }
    //     getClassCourseDetail()
    // }, [])
    useEffect(() => {
        const fetchData = async () => {
            try {
                const courseData = await axios.get('http://localhost:8999/api/course/findAll', {
                    headers: { Authorization: `Bearer ${user.token}` }
                });
                setCourse(courseData.data);

                const lectureData = await axios.get('http://localhost:8999/api/lecture/findAll', {
                    headers: { Authorization: `Bearer ${user.token}` }
                });
                setLecture(lectureData.data);

                const registerData = await axios.get(`http://localhost:8999/register/getRegister/${hocky}`, {
                    headers: { Authorization: `Bearer ${user.token}` }
                });
                setRegisterHocKy(registerData.data);

                const classCourseDetailData = await axios.get('http://localhost:8999/api/classCourseDetail/findAll', {
                    headers: { Authorization: `Bearer ${user.token}` }
                });
                setClassCourseDetail(classCourseDetailData.data);

            } catch (error) {
                console.log(error);
            }
        };

        fetchData();
    }, [hocky, render, user.token]);

    const updateRegistrationList = async () => {
        const newRegistrationList = registerHocKy.map(dangKy => {
            const classData = classCourseDetail.find(detail => detail.idClassCourseDetail === dangKy.classCouserDetailId);
            const courseData = course.find(course => course.id === dangKy.couserId);
            return {
                id: dangKy.id,
                classCourseDetail: classData,
                course: courseData,
                lecture: lecture.find(item => item.giangVienid = classData.lecturer)
            };
        });
        const sortedData = newRegistrationList.sort((a, b) => {
            const timeA = new Date("1970-01-01T" + a.classCourseDetail.timeHoc);
            const timeB = new Date("1970-01-01T" + b.classCourseDetail.timeHoc);
            return timeA - timeB;
        });
        setDanhSachDangKy(sortedData);
    };


    const renderLichHoc = (day, time1, time2) => (
        danhSachDangKy.map(item => {
            return (
                <div className="calendar-items" style={{ flexDirection: 'column' }}>
                    {
                        item.classCourseDetail.ngayHoc.split(' ')[1] == day
                            && item.classCourseDetail.timeHoc == time1 ||
                            item.classCourseDetail.ngayHoc.split(' ')[1] == day
                            && item.classCourseDetail.timeHoc == time2

                            ?
                            <div className='calender-item-child'>
                                <div className="calender-item-child-small" style={{ fontWeight: 'bold' }}> Môn học </div>
                                <div className="calender-item-child-small"> {`${item.course.tenMonHoc}`}</div>
                                <div className="calender-item-child-small" style={{ fontWeight: 'bold' }}> Tên giảng viên </div>
                                <div className="calender-item-child-small">  {`${item.lecture.tenGiangVien}`}</div>
                                <div className='calender-item-child-small'>
                                    <div style={{ display: 'flex', marginTop: '10px', justifyContent: 'center' }}>
                                        <div className="calender-item-child-small-time" style={{ fontWeight: 'bold' }}>{`Giờ bắt đầu: `}</div>
                                        <div className="calender-item-child-small-time">{`${item.classCourseDetail.timeHoc}`}</div>
                                    </div>
                                    <div style={{ display: 'flex', marginTop: '10px', justifyContent: 'center' }}>
                                        <div className="calender-item-child-small-time" style={{ fontWeight: 'bold' }}>{`Giờ kết thúc: `}</div>
                                        <div className="calender-item-child-small-time">   {` ${item.classCourseDetail.timeEnd}`}</div>
                                    </div>
                                </div>
                            </div> : <></>}
                </div>
            )
        })
    )
    const renderDayofWeek = () => {
        console.log(danhSachDangKy);
        return (
            <table className='table-big'>
                <tr>
                    <th>Buổi</th>
                    <th>Thứ 2</th>
                    <th>Thứ 3</th>
                    <th>Thứ 4</th>
                    <th>Thứ 5</th>
                    <th>Thứ 6</th>
                    <th>Thứ 7</th>
                    <th>Chủ Nhật</th>

                </tr>

                <tr>
                    <td className='header'>Sáng</td>
                    <td>{renderLichHoc(`2`, `07:00:00`, `09:00:00`)}</td>
                    <td>{renderLichHoc(`3`, `07:00:00`, `09:00:00`)}</td>
                    <td>{renderLichHoc(`4`, `07:00:00`, `09:00:00`)}</td>
                    <td>{renderLichHoc(`5`, `07:00:00`, `09:00:00`)}</td>
                    <td>{renderLichHoc(`6`, `07:00:00`, `09:00:00`)}</td>
                    <td>{renderLichHoc(`7`, `07:00:00`, `09:00:00`)}</td>
                    <td>{renderLichHoc(`8`, `07:00:00`, `09:00:00`)}</td>
                </tr>
                <tr>
                    <td className='header'>Chiều</td>
                    <td>{renderLichHoc(`2`, `12:30:00`, `15:30:00`)}</td>
                    <td>{renderLichHoc(`3`, `12:30:00`, `15:30:00`)}</td>
                    <td>{renderLichHoc(`4`, `12:30:00`, `15:30:00`)}</td>
                    <td>{renderLichHoc(`5`, `12:30:00`, `15:30:00`)}</td>
                    <td>{renderLichHoc(`6`, `12:30:00`, `15:30:00`)}</td>
                    <td>{renderLichHoc(`7`, `12:30:00`, `15:30:00`)}</td>
                    <td>{renderLichHoc(`8`, `12:30:00`, `15:30:00`)}</td>
                </tr>
                <tr>
                    <td className='header'>Tối</td>
                    <td>{renderLichHoc(`2`, `17:30:00`, `19:00:00`)}</td>
                    <td>{renderLichHoc(`3`, `17:30:00`, `19:00:00`)}</td>
                    <td>{renderLichHoc(`4`, `17:30:00`, `19:00:00`)}</td>
                    <td>{renderLichHoc(`5`, `17:30:00`, `19:00:00`)}</td>
                    <td>{renderLichHoc(`6`, `17:30:00`, `19:00:00`)}</td>
                    <td>{renderLichHoc(`7`, `17:30:00`, `19:00:00`)}</td>
                    <td>{renderLichHoc(`8`, `17:30:00`, `19:00:00`)}</td>
                </tr >

            </table >
        )
    }
    useEffect(() => {
        updateRegistrationList();
    }, [hocky, registerHocKy, classCourseDetail, course])
    return (
        <div className='RegisterFather'>
            <div className="main-calendar">
                THỜI KHÓA BIỂU
                <div className="header-select-box">
                    <FormControl fullWidth>
                        <InputLabel >Học Kỳ</InputLabel>
                        <Select
                            labelId=""
                            id=""
                            value={hocky}
                            label="Age"
                            onChange={handleChange}
                            style={{ fontSize: '12px' }}
                        >
                            <MenuItem value={1}>Học Kỳ 1</MenuItem>
                            <MenuItem value={2}>Học Kỳ 2</MenuItem>
                            <MenuItem value={3}>Học Kỳ 3</MenuItem>
                            <MenuItem value={4}>Học Kỳ 4</MenuItem>
                            <MenuItem value={5}>Học Kỳ 5</MenuItem>
                            <MenuItem value={6}>Học Kỳ 6</MenuItem>
                            <MenuItem value={7}>Học Kỳ 7</MenuItem>
                            <MenuItem value={8}>Học Kỳ 8</MenuItem>
                        </Select>
                    </FormControl>
                </div>
                <div className="calendar-body">
                    <div className='calender-child'>
                        {renderDayofWeek()}
                    </div>
                </div>

            </div>

        </div>
        // </div >
    )
}

export default RegisterFather