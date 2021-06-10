import React from "react"
import { HeaderMenuItems } from "./HeaderMenuItems"

class Header extends React.Component {
    state = { clicked: false }

    handleClick = () => {
        this.setState({ clicked: !this.state.clicked })
    }

    render() {
        return (
            <nav className="header-navigation">
                <h1 className="navbar-logo">Stackoverflow<i className="fab fa-react"></i></h1>
                <div className="menu-icon" onClick={this.handleClick}>
                    <i className={this.state.clicked ? "fas fa-times" : "fas fa-bars"}></i></div>
                <ul className={this.state.clicked ? "nav-menu active" : "nav-menu"} >
                    {
                        HeaderMenuItems.map((menuItem, index) => {
                            return (
                                <li key={index}>
                                    <a className={menuItem.cName} href={menuItem.url}>{menuItem.title}</a>
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
}
export default Header

// npm install react-router-dom