import React, { useContext } from "react"
import { Link } from "react-router-dom"
import { getMenuItems } from "./HeaderMenuItems"
import UserContext from "../contexts/User/UserContext"

function SideNavBar() {

    const { user } = useContext(UserContext);


    return (
        <nav className="side-nav-bar">
            <ul className="side-nav-bar-ul">
                {
                    getMenuItems(user != null).map((menuItem, index) => {
                        return (
                            <li className="side-nav-bar-li" key={index}>
                                <Link className='side-nav-bar-li-a' to={menuItem.url}>{menuItem.title}</Link>
                            </li>
                        )

                    }

                    )
                }
            </ul>
        </nav>
    )

}
export default SideNavBar