import React, { useEffect, useState } from 'react'
import { FormControl, InputLabel, MenuItem, Select } from '@mui/material'
import axios from 'axios';
import "./RegisterChildComponent.scss"

import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
function RegisterChildComponent() {
    var dataRegister = []
    var truelyDayHoc = 0
    var sochi = 0
    var truelytime = 0

    const [hocky, setHocKy] = useState('1')
    const [course, setCourse] = useState([])
    const [idCourse, setIdCourse] = useState('')
    const [classCourse, setClassCourse] = useState([])
    const [idClassCourse, setIdClassCourse] = useState('')
    const [idClassCourseDetail, setIdClassCourseDetail] = useState('')
    const [classCourseDetail, setClassCourseDetail] = useState([])
    const [lecture, setLecture] = useState([])
    const [register, setRegister] = useState([])
    const [registerHocKy, setRegisterHocKy] = useState([])
    const [render, setRender] = useState(true)
    const [checkTimeHoc, setCheckTimeHoc] = useState([])
    const [checkDayHoc, setCheckDayHoc] = useState([])
    const [nameRegister, setNameRegister] = useState('')
    const user = JSON.parse(localStorage.getItem("user"))
    const [soChiMonHoc, setSoChiMonHoc] = useState()
    const [open, setOpen] = useState(false);
    const [openErr, setOpenErr] = useState(false);
    const [openSuccess, setOpenSuccess] = useState(false);
    const [openDelete, setOpenDelete] = useState(false);
    const [titleErr, setTitleError] = useState('')
    const [titleSuccess, setTitleSuccess] = useState('')
    const [contentErr, setContentError] = useState('')
    const [contentSuccess, setContentSuccess] = useState('')
    const [idClassCourseDetailDelete, setIdClassCourseDetailDelete] = useState('')
    const [userNameDeleteRegister, setUserNameDeleteRegister] = useState('')
    const [courseIdDelete, setCourseDelete] = useState('')
    const [coursePrevRegister, setCoursePrevRegister] = useState([])
    const [tinhTrangMonHoc, setTinhTrangMonHoc] = useState(true)
    // const [dataRegister, setDataRegister] = useState([])


    var tongTinChi = 0

    const handleChange = (event) => {
        setHocKy(event.target.value);
    };
    const handleClose = async () => {
        // console.log(dataRegister);
        // console.log(coursePrevRegister);
        // console.log(coursePrevRegister.every(item => dataRegister.includes(item)));
        if (tongTinChi + soChiMonHoc <= 30) {
            if (coursePrevRegister.every(item => dataRegister.includes(item))) {
                try {
                    await axios.post(`http://localhost:8999/register/add`, {
                        userName: user.username,
                        classCouserDetailId: idClassCourseDetail,
                        hocKyDangKy: hocky,
                        couserId: idCourse,
                        trinhTrangMonHoc: false
                    }, {
                        headers: { Authorization: `Bearer ${user.token}` }
                    })
                    // setIdCourse(0)

                    // setIdClassCourse(0)
                    setRender(!render)
                    setClassCourse([])
                    setClassCourseDetail([])
                    setContentSuccess("Bạn Đã Đăng Ký Môn Học Thành Công")
                    setTitleSuccess("Đăng Ký Lớp Học Thành Công")
                    setOpenSuccess(true)
                    setTimeout(() => {
                        setOpenSuccess(false)
                    }, 3000);
                } catch (error) {
                    console.log(error);
                }
                console.log(idClassCourseDetail);
                setOpen(false);
            } else {
                setTitleError("Bạn Chưa Học Môn Tiên Quyết Trước Đó")
                setContentError("Bạn Không Thể Đăng Ký Vì Môn Học Tiên Quyết Vẫn Chưa Học")
                setOpenErr(true)
                setTimeout(() => {
                    setOpenErr(false)
                }, 3000)
            }
        } else {
            setOpen(false)
            setTitleError("Số Tín Chỉ Đã Vượt Quá Chỉ Tiêu Trong 1 Kỳ")
            setContentError("Bạn Không Thể Đăng ký Vì Mỗi Kỹ Chỉ Cho Phép Tối Đa 30 chỉ")
            setOpenErr(true)
            setTimeout(() => {
                setOpenErr(false)
            }, 3000)
        }
    };

    //Course
    useEffect(() => {
        // http://localhost:8999/api/course/findall
        const getCourse = async () => {
            try {
                const courseData = await axios.get('http://localhost:8999/api/course/findAll', {
                    headers: { Authorization: `Bearer ${user.token}` }

                })
                setCourse(courseData.data)
            } catch (error) {
                console.log(error);
            }
        }
        getCourse()
    }, [render, user.token])
    //classCourse
    useEffect(() => {
        const getClassCourse = async () => {
            try {
                const courseData = await axios.post('http://localhost:8999/api/ClassCourse/findAllClassCourseByCourseId', {
                    maMonHoc: idCourse ? idCourse : "0"
                }, {
                    headers: { Authorization: `Bearer ${user.token}` }

                })
                setClassCourse(courseData.data)
            } catch (error) {
                console.log(error);
            }
        }
        getClassCourse()
    }, [idCourse, user.token])
    //classCourseDetai
    useEffect(() => {
        const getClassCourseDetail = async () => {
            try {
                const classCourseDetailData = await axios.post('http://localhost:8999/api/classCourseDetail/findByClassCourse',
                    { idClass: idClassCourse }
                    , {
                        headers: { Authorization: `Bearer ${user.token}` }

                    })
                setClassCourseDetail(classCourseDetailData.data)
            } catch (error) {
                console.log(error);
            }
        }
        getClassCourseDetail()
    }, [idClassCourse])
    // lecture
    useEffect(() => {
        const getLecture = async () => {
            try {
                const lectureData = await axios.get('http://localhost:8999/api/lecture/findAll',
                    {
                        headers: { Authorization: `Bearer ${user.token}` }

                    })
                setLecture(lectureData.data)
            } catch (error) {
                console.log(error);
            }
        }
        getLecture()
    }, [user.token, render])
    //Allregister
    useEffect(() => {
        // http://localhost:8999/register/getByUserName
        const getRegister = async () => {
            try {
                const registerData = await axios.get('http://localhost:8999/register/getByUserName', {
                    headers: { Authorization: `Bearer ${user.token}` }

                })
                setRegister(registerData.data)
            } catch (error) {
                console.log(error);
            }
        }
        getRegister()
    }, [render])
    //registerByHocKy
    // getCourseByUserNameAndHocKy
    useEffect(() => {
        const getRegisterByHocKy = async () => {
            try {
                const registerData = await axios.get(`http://localhost:8999/register/getRegister/${hocky}`,
                    {
                        headers: { Authorization: `Bearer ${user.token}` }

                    })
                setRegisterHocKy(registerData.data)
            } catch (error) {
                console.log(error);
            }
        }
        getRegisterByHocKy()
    }, [hocky, render, user.token])

    const handleCheckDayHoc = async (item) => {
        try {
            const dayHocData = await axios.post('http://localhost:8999/api/classCourseDetail/getClassCourseDetailByNgayHoc',
                {
                    ngayHoc: item.ngayHoc
                }
                , {
                    headers: { Authorization: `Bearer ${user.token}` }

                })
            setCheckDayHoc(dayHocData.data)

            registerHocKy.map(item => {
                // console.log(item.classCouserDetailId);
                if (dayHocData.data.includes(item.classCouserDetailId)) {
                    truelyDayHoc += 1
                }
                if (checkTimeHoc.includes(item.classCouserDetailId)) {
                    truelytime += 1
                }
            })

        } catch (error) {
            console.log(error);
        }
    }
    const handleCheckTimeHoc = async (item) => {
        try {
            const timeHocData = await axios.post('http://localhost:8999/api/classCourseDetail/getClassCourseDetailByTimeHoc',
                {
                    timeHoc: item.timeHoc
                }
                , {
                    headers: { Authorization: `Bearer ${user.token}` }

                })

            setCheckTimeHoc(timeHocData.data)
            registerHocKy.map(item => {
                if (timeHocData.data.includes(item.classCouserDetailId)) {
                    truelytime += 1
                }
            })
        } catch (error) {
            console.log(error);
        }
    }
    const handleRegister = async (item) => {
        await handleCheckDayHoc(item)
        await handleCheckTimeHoc(item)
        setIdClassCourseDetail(item.idClassCourseDetail)
        if (item.soLuongDaDangKy >= item.siSo) {
            setTitleError("Lớp Học Đã Đầy")
            setContentError("Lớp Học Đã Đầy, Không Thể Đăng Ký Thêm")
            setOpenErr(true)
            setTimeout(() => {
                setOpenErr(false)
            }, 3000)
            truelyDayHoc = 0
            truelytime = 0
        } else {
            if (truelyDayHoc == 0) {
                setOpen(true)
                setRender(!render)
                truelyDayHoc = 0
                truelytime = 0


            } else {
                if (truelytime == 0) {
                    setOpen(true)
                    setRender(!render)
                    truelyDayHoc = 0
                    truelytime = 0
                    // setOpenSuccess(true)
                    // setContentSuccess("Bạn Đã Đăng Ký Môn Học Thành Công")
                    // setTitleSuccess("Đăng Ký Lớp Học Thành Công")
                    // setTimeout(() => {
                    //     openSuccess(false)
                    // }, 3000);
                }
                else {
                    setTitleError("Trùng Với Môn Học")
                    setContentError("Có Môn Học Trùng Vào Khoản Thời Gian Này Vui Lòng Kiểm Tra Lại")
                    setOpenErr(true)
                    setTimeout(() => {
                        setOpenErr(false)
                    }, 3000)

                }
            }
        }
    }
    const handleChangeIdAndName = (item) => {
        setIdCourse(item.id)
        setNameRegister(item.tenMonHoc)
        setSoChiMonHoc(item.soTinChi)
        setCoursePrevRegister(item.listCoursePrev)

    }
    const showDialog = () => (
        <Dialog
            open={open}
            onClose={() => setOpen(false)}
            aria-labelledby="alert-dialog-title"
            aria-describedby="alert-dialog-description"
        >
            <DialogTitle id="alert-dialog-title">
                {"Đăng Ký Môn Học"}
            </DialogTitle>
            <DialogContent>
                <DialogContentText id="alert-dialog-description">
                    {`Bạn Có Muốn Chắc Đăng Ký Môn Học ${nameRegister} Hay Không ?`}
                </DialogContentText>
            </DialogContent>
            <DialogActions>
                <Button onClick={() => setOpen(false)}>Hủy Đăng Ký</Button>
                <Button onClick={handleClose} autoFocus>
                    Đăng Ký
                </Button>
            </DialogActions>
        </Dialog>
    )
    const showErrDialog = (title, content) => (
        <Dialog
            open={openErr}
            onClose={() => setOpenErr(false)}
            aria-labelledby="alert-dialog-title"
            aria-describedby="alert-dialog-description"
        >
            <DialogTitle id="alert-dialog-title">
                {titleErr}
            </DialogTitle>
            <DialogContent>
                <DialogContentText id="alert-dialog-description">
                    {contentErr}
                </DialogContentText>
            </DialogContent>
            <DialogActions>
                <Button onClick={() => setOpenErr(false)}>Hủy</Button>
            </DialogActions>
        </Dialog>
    )
    const showSuccessDialog = (title, content) => (
        <Dialog
            open={openSuccess}
            onClose={() => setOpenSuccess(false)}
            aria-labelledby="alert-dialog-title"
            aria-describedby="alert-dialog-description"
        >
            <DialogTitle id="alert-dialog-title">
                {titleSuccess}
            </DialogTitle>
            <DialogContent>
                <DialogContentText id="alert-dialog-description">
                    {contentSuccess}
                </DialogContentText>
            </DialogContent>
        </Dialog>
    )
    const handleGetObjectRemove = (item) => {
        setOpenDelete(true)
        setIdClassCourseDetailDelete(item.classCouserDetailId)
        setUserNameDeleteRegister(item.userName)
        setCourseDelete(item.couserId)
        setTinhTrangMonHoc(item.trinhTrangMonHoc)
    }
    const handleDelete = async () => {
        // console.log(idClassCourseDetailDelete);
        // console.log(userNameDeleteRegister);
        // console.log(courseIdDelete);
        // console.log(tinhTrangMonHoc);
        if (!tinhTrangMonHoc) {
            try {
                await axios.post(`http://localhost:8999/register/delete`, {
                    userName: userNameDeleteRegister,
                    classCouserDetailId: idClassCourseDetailDelete,
                    hockydangky: hocky,
                    couserId: courseIdDelete
                }, {
                    headers: { Authorization: `Bearer ${user.token}` }
                })
                setRender(!render)
                console.log("render");
                setOpenDelete(false)
                setContentSuccess("Bạn Đã Hủy Đăng Ký Môn Học Thành Công")
                setTitleSuccess("Hủy Đăng Ký Lớp Học Thành Công")
                setOpenSuccess(true)
                setTimeout(() => {
                    setOpenSuccess(false)
                }, 3000);

            } catch (error) {
                setTitleError('Thông Báo Hủy Đăng Ký Học Phần')
                setContentError(error)
                setOpenErr(true)
                setTimeout(() => {
                    setOpenErr(false)
                }, 3000)
            }
        } else {
            setTitleError('Thông Báo Hủy Đăng Ký Học Phần')
            setContentError('Không Thể Hủy Học Phần Bởi Lớp Học Đã Được Mở')
            setOpenErr(true)
            setTimeout(() => {
                setOpenErr(false)
            }, 3000)
        }
        // setRender(!render)
    }
    const showDeleteDialog = (item) => (
        <Dialog
            open={openDelete}
            onClose={() => setOpenErr(false)}
            aria-labelledby="alert-dialog-title"
            aria-describedby="alert-dialog-description"
        >
            <DialogTitle id="alert-dialog-title">
                {`Hủy Học Phần`}
            </DialogTitle>
            <DialogContent>
                <DialogContentText id="alert-dialog-description">
                    {`Bạn Có Chắc Muốn Hủy Học Phần Này Chứ?`}
                </DialogContentText>
            </DialogContent>
            <DialogActions>
                <Button onClick={() => setOpenDelete(false)}>Suy Nghĩ Lại</Button>
                <Button onClick={() => handleDelete()}>Hủy Đăng Ký</Button>
            </DialogActions>
        </Dialog>
    )
    // useEffect(() => {


    // }, [render, user.token])
    register.map(item => {
        dataRegister.push(item.couserId)
    })

    return (
        <div className='registerChild'>

            <div className="header">
                <h2 className='title-web'>Đăng Ký Học Phần</h2>
            </div>
            {showDialog()}
            {showErrDialog()}
            {showDeleteDialog()}
            {showSuccessDialog()}
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

            <div className="danhSachMonHoc">
                <div className='header-list-course'>
                    <h3>DANH SÁCH MÔN HỌC</h3>
                </div>

                <table className='table-course'>
                    <tr>
                        <th>STT</th>
                        <th>Tên Môn Học</th>
                        <th>Số Tín Chỉ</th>
                        <th>Bắt Buộc</th>
                        <th>Trạng Thái</th>
                        <th>Học Phần Tiên Quyết</th>
                    </tr>
                    {course.map((item, index) => {
                        if (!dataRegister.includes(item.id)) {
                            return (
                                <tr key={index} className='course-component' onClick={() => { handleChangeIdAndName(item) }}>
                                    <td>{index + 1}</td>
                                    <td>{item.tenMonHoc} </td>
                                    <td>{item.soTinChi}</td>
                                    <td>{item.batBuoc ? `Có` : `Không`}</td>
                                    <td>{item.trangThai ? "Đang Mở Lớp" : "Chưa Có Lớp"}</td>
                                    <td>{item.listCoursePrev.join(", ")}</td>
                                </tr>
                            )
                        }
                    })}

                </table>
            </div>

            <div className="danhSachMonHoc">
                <div className='header-list-course'>
                    <h3>DANH SÁCH LỚP HỌC</h3>
                </div>

                <table className='table-course'>
                    <tr>
                        <th>STT</th>
                        <th>Tên Lớp Học</th>
                        <th>Chi tiết</th>
                    </tr>
                    {classCourse.map((item, index) => (
                        <tr key={index} className='course-component'>
                            <td>{index + 1}</td>
                            <td>{item.nameClass}</td>
                            <td onClick={() => setIdClassCourse(item.idClass)}>Xem Chi Tiết</td>

                        </tr>
                    ))}
                </table>
            </div>


            <div className="danhSachMonHoc">
                <div className='header-list-course'>
                    <h3>CHI TIẾT LỚP HỌC</h3>
                </div>
                <table className='table-course'>
                    <tr>
                        <th>STT</th>
                        <th>Giảng Viên Phụ Trách</th>
                        <th>Số Lượng Sinh Viên</th>
                        <th>Số Lượng Sinh Viên Đã Đăng Ký</th>
                        <th>Thời Gian Học</th>
                        <th>Trạng thái</th>
                    </tr>
                    {classCourseDetail.map((item, index) => {
                        var namelecture = ""
                        for (let index = 0; index < lecture.length; index++) {
                            if (lecture[index].giangVienid == item.lecturer) {
                                namelecture = lecture[index].tenGiangVien
                            }
                        }
                        return (
                            <tr key={index} className='course-component'>
                                <td>{index + 1}</td>
                                <td>{namelecture}</td>
                                <td>{item.siSo}</td>
                                <td>{item.soLuongDaDangKy}</td>
                                <td>{item.ngayHoc + " " + item.timeHoc + " " + item.timeEnd}</td>
                                <td onClick={() => handleRegister(item)}>Đăng Ký Học Phần</td>
                            </tr>
                        )
                    })}
                </table>
            </div>


            <div className="danhSachMonHoc">
                <div className='header-list-course'>
                    <h3>CHI DANH SÁCH LỚP HỌC PHẦN ĐÃ ĐĂNG KÝ</h3>
                </div>
                <table className='table-course'>
                    <tr>
                        <th>STT</th>
                        <th>Tên Môn Học</th>
                        <th>Số Tín Chỉ</th>
                        <th>Trình Trạng Môn Học</th>
                        <th>Trạng Thái</th>
                    </tr>
                    {registerHocKy.map((item, index) => {
                        var nameCourse = ""
                        for (let index = 0; index < course.length; index++) {
                            if (course[index].id == item.couserId) {
                                nameCourse = course[index].tenMonHoc
                                sochi = course[index].soTinChi
                                tongTinChi += course[index].soTinChi
                            }
                        }

                        return (
                            <tr key={index} className='course-component'>
                                <td>{index + 1}</td>
                                <td>{nameCourse}</td>
                                <td>{sochi}</td>
                                <td>{item.trinhTrangMonHoc ? "Đã Mở Lớp" : "Đang Đợi Mở Lớp"}</td>
                                <td onClick={() => handleGetObjectRemove(item)}>Hủy Học Phần</td>
                            </tr>

                        )

                    })

                    }
                    <tr>
                        <th>Tổng Số Tín: </th>
                        <th></th>
                        <th>{tongTinChi}</th>
                        <th></th>
                    </tr>
                </table>
            </div>
        </div>
    )
}

export default RegisterChildComponent