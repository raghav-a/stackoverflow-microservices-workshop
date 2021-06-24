import userEvent from "@testing-library/user-event";
import React, { useContext } from "react";
import UserContext from "../contexts/User/UserContext";

function UserProfile() {

    const { user } = useContext(UserContext);

    return (
        <div className='user-profile'>
            <label>First Name : </label>{user.firstName}
            <br />
            <label>Last Name : </label>{user.lastName}
            <br />
            <label>User Id : </label>{user.userId}
            <br />
            <label>Email : </label>{user.email}

        </div>)
}

export default UserProfile;