import React, { useState } from "react"
import { NavLink } from "react-router-dom";

function SignUpForm() {

    const [state, setState] = useState({
        error: false,
        userId: '',
        password: '',
        email: '',
        firstName: '',
        lastName: '',
        errorMessage: '',
        createdUserId: null
    });

    const updateState = (data) => {
        setState({
            ...state, //need to understand thiss
            ...data
        })
    }


    const mySubmitHandler = (event) => {
        event.preventDefault();
        const apiUrl = 'http://localhost:8765/api/users'
        fetch(apiUrl,
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    userId: state.userId,
                    password: state.password,
                    email: state.email,
                    firstName: state.firstName,
                    lastName: state.lastName,
                })
            }
        )
            .then((response) => response.json())
            .then((data) => {
                console.log("Login response is : ", data);
                updateState({ loading: false, createdUserId: data.userId });
            })
            .catch((e) => {
                console.log("Login error is : ", e);
                updateState({ error: true, errorMessage: e.message })
            });
    }

    const myChangeHandler = (event) => {
        let nam = event.target.name;
        let val = event.target.value;
        updateState({ [nam]: val });
    }



    if (!state.createdUserId) {
        return (
            <div className="user-sign-up">
                <form onSubmit={mySubmitHandler}>
                    <div>{state.errorMessage}</div>
                    <input
                        type='text'
                        id="fname"
                        name='firstName' placeholder="Your first name.."
                        onChange={myChangeHandler}
                    />
                    <input
                        type='text'
                        id="lname"
                        name='lastName'
                        placeholder="Your last name.."
                        onChange={myChangeHandler}
                    />
                    <input
                        type='text'
                        id="userId"
                        name='userId'
                        placeholder="Your user id"
                        onChange={myChangeHandler}
                    />

                    <input
                        type='password'
                        id="password"
                        name='password'
                        placeholder="Your password  "
                        onChange={myChangeHandler}
                    />

                    <input
                        type='text'
                        id="email"
                        name='email'
                        placeholder="email@example.com"
                        onChange={myChangeHandler}
                    />
                    <input
                        value='SIGN UP'
                        type='submit'
                    />
                </form>
            </div>
        );
    }
    else {
        return (<div>You have successfully signed up. You can login now. </div>);
    }
}


export default SignUpForm

