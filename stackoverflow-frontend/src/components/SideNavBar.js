import React from "react"
import { HeaderMenuItems } from "./HeaderMenuItems"

class SideNavBar extends React.Component {
    render() {
        return (
            <nav className="side-nav-bar">
                <ul className="side-nav-bar-ul">
                    {
                        HeaderMenuItems.map((menuItem, index) => {
                            return (
                                <li className="side-nav-bar-li" key={index}>
                                    <a className='side-nav-bar-li-a' href={menuItem.url}>{menuItem.title}</a>
                                </li>
                            )

                        }

                        )
                    }
                </ul>
            </nav>
        )
    }
}
export default SideNavBar