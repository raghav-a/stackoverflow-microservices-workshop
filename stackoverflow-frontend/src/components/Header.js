import React, { useState, useContext } from "react"
import { getMenuItems } from "./HeaderMenuItems"
import UserContext from '../contexts/User/UserContext'
import { Link } from 'react-router-dom'

export default function ({ title = "StackOverflow" }) {
    // state = { clicked: false }
    const [clicked, setClicked] = useState(false);
    const { user } = useContext(UserContext);

    console.log("User is ", user);
    const handleClick = () => {
        setClicked(!clicked);
    }

    const userElement = user ? <div>Welcome {user.userDetail.firstName} !!!</div> : <div></div>
    const loggedInUser = user ? user.token != null : false;

    return (
        <nav className="header-navigation">
            <h1 className="navbar-logo">{title}<i className="fab fa-react"></i></h1>

            <h2 className="header-user-name">{userElement}</h2>
            <ul className={clicked ? "nav-menu active" : "nav-menu"} >
                {
                    getMenuItems(loggedInUser).map((menuItem, index) => {
                        return (
                            <li key={index}>
                                <Link className={menuItem.cName} to={menuItem.url}>{menuItem.title} </Link>
                            </li>
                        )

                    }

                    )
                }
            </ul>
            <section className="profile-section"></section>
        </nav>
    )

}