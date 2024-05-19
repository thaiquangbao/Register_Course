import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import RegisterComponent from './component/registerPage/RegisterComponent.jsx';
import LoginComponent from './component/loginPage/LoginComponent.jsx';
import RegisterChildComponent from './component/registerPage/child/RegisterChildComponent.jsx';
import RegisterFather from './component/registerPage/father/RegisterFatherComponent.jsx';
const router = createBrowserRouter([
  {
    path: "/",
    element: <LoginComponent />,
  },
  {
    path: "/register",
    element: <RegisterComponent />,
    children: [
      {
        path: "/register/RegisterFather",
        element: <RegisterFather />,
        // loader: contactLoader,
      },
      {
        path: "/register/RegisterChild",
        element: <RegisterChildComponent />,
        // loader: contactLoader,
      },
    ],
  },
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
