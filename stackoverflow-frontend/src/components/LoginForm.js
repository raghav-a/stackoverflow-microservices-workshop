import React, { useState, useContext, useEffect } from "react"
import { setCookie } from '../utils/cookies';
import UserContext from "../contexts/User/UserContext";
import { login } from '../services/auth';
import fetchWrapper from '../services/wrapper';


function LoginForm() {
    const { user, setUser } = useContext(UserContext);

    const [state, setState] = useState({
        error: false,
        userId: '',
        password: '',
        errorMessage: '',
        token: ''
    })

    const updateState = (data) => {
        setState({
            ...state, //need to understand thiss
            ...data
        })
    }

    const mySubmitHandlerRefactored = event => {
        event.preventDefault();
        updateState({ loading: true });
        login(state.userId, state.password)
            .then(data => {
                console.log("Login response is : ", data);
                //setCookie('user-token', data.token);
                updateState({
                    loading: false,
                    token: data.token
                });
                setUser(data.userDetail);
            })
            .catch(e => {
                console.log("Login error is : ", e);
                updateState({
                    loading: false,
                    error: true,
                    errorMessage: e.message
                });
            });
    }

    const mySubmitHandler = (event) => {
        event.preventDefault();
        const apiUrl = 'http://localhost:8765/api/sessions/login'
        fetchWrapper(apiUrl,
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    userId: state.userId,
                    password: state.password
                })
            })
            .then((data) => {
                console.log("Login response is : ", data);
                setCookie('user-token', data.token);
                updateState({ loading: false });
                updateState({ token: data.token });
                setUser(data);

                // Updating the global state

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

    if (state.token) {
        return <div>You have successfully logged in. </div>
    }
    return (
        <div className='user-login'>
            <form onSubmit={mySubmitHandler}>
                <div>{state.errorMessage}</div>
                <p>Enter your name and password:</p>
                <input
                    type='text'
                    name='userId'
                    placeholder="Your user name"
                    onChange={myChangeHandler}
                />
                <input
                    type='password'
                    name='password'
                    placeholder="password"
                    onChange={myChangeHandler}
                />
                <input
                    value="LOGIN"
                    type='submit'
                />
            </form>
        </div>
    );
}

export default LoginForm


