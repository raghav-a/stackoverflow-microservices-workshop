import userEvent from "@testing-library/user-event";
import React, { useContext } from "react";
import UserContext from "../contexts/User/UserContext";

function UserProfile() {

    const { user } = useContext(UserContext);

    return (
        <div className='user-profile'>
            <label>First Name : </label>{user.userDetail.firstName}
            <br />
            <label>Last Name : </label>{user.userDetail.lastName}
            <br />
            <label>User Id : </label>{user.userDetail.userId}
            <br />
            <label>Email : </label>{user.userDetail.email}

        </div>)
}

export default UserProfile;