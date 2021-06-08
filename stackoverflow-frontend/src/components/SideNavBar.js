import React from "react"

class SideNavBar extends React.Component {
    render() {
        return (
            <nav className="side-nav-bar">
                <ul>
                    <li><a href="#">London</a></li>
                    <li><a href="#">Paris</a></li>
                    <li><a href="#">Tokyo</a></li>
                </ul>
            </nav>
        )
    }
}
export default SideNavBar