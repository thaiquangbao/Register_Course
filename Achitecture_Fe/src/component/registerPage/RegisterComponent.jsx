import React from 'react'
import SideBarComponent from './slideBar/SideBarComponent'
import { Outlet } from 'react-router'
import "./RegisterComponent.scss"
import Box from '@mui/material/Box';
function RegisterComponent() {


    return (
        <div className='registerComponent'>
            <SideBarComponent />
            <Outlet />
        </div>
    )
}

export default RegisterComponent