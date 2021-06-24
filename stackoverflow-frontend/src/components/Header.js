import React, { useState, useContext } from "react"
import { getMenuItems } from "./HeaderMenuItems"
import UserContext from '../contexts/User/UserContext'
import { Link } from 'react-router-dom'
import { Button } from "@material-ui/core";

export default function ({ title = "StackOverflow" }) {
    // state = { clicked: false }
    const [clicked, setClicked] = useState(false);
    const { user } = useContext(UserContext);

    console.log("Header : User is ", user);
    const handleClick = () => {
        setClicked(!clicked);
    }

    const userElement = user ? <div>Welcome {user.firstName} !!!</div> : <div></div>

    return (
        <nav className="header-navigation">
            <h1 className="navbar-logo">{title}<i className="fab fa-react"></i></h1>

            <h2 className="header-user-name">{userElement}</h2>
            <ul className={clicked ? "nav-menu active" : "nav-menu"} >
                {
                    getMenuItems(user != null).map((menuItem, index) => {
                        return (
                            <li key={index}>
                                <Link to={menuItem.url}>
                                    <Button variant="contained" color="primary">
                                        {menuItem.title}
                                    </Button>
                                </Link>
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