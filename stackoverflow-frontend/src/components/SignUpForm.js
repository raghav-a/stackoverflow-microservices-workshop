import React from "react"
import { NavLink } from "react-router-dom";

class SignUpForm extends React.Component{

    constructor(props) {
        super(props);
        this.state = { 
            error: false,
            userId: '',
            password: '',
            email: '',
            firstName: '',
            lastName: '',
            errorMessage: '',
            createdUserId: null
        };
      }

      mySubmitHandler = (event) => {
        event.preventDefault();
        const apiUrl = 'http://localhost:8765/api/users'
        fetch(apiUrl, 
            { 
                method: 'POST', 
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    userId: this.state.userId, 
                    password: this.state.password,
                    email: this.state.email,
                    firstName: this.state.firstName,
                    lastName: this.state.lastName,
                })
            }
        )
            .then((response) => response.json())
            .then((data) => {
                console.log("Login response is : ", data);
                this.setState({ loading: false, createdUserId: data });
            })
            .catch((e) => {
                console.log("Login error is : ", e);
                this.setState({error: true, errorMessage: e.message })
            });
      }
      
      myChangeHandler = (event) => {
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({[nam]: val});
      }

      render() {

        if(!this.state.createdUserId){
            return (
            <div className="user-sign-up">
            <form onSubmit={this.mySubmitHandler}>
                <div>{this.state.errorMessage}</div>            
                <input
                    type='text'
                    id="fname"
                    name='firstName' placeholder="Your first name.."
                    onChange={this.myChangeHandler}
                />
                <input
                    type='text'
                    id="lname"
                    name='lastName' 
                    placeholder="Your last name.."
                    onChange={this.myChangeHandler}
                />            
                <input
                    type='text'
                    id="userId"
                    name='userId'
                    placeholder="Your user id"
                    onChange={this.myChangeHandler}
                />
                
                <input
                    type='password'
                    id="password"
                    name='password'
                    placeholder="Your password  "
                    onChange={this.myChangeHandler}
                />
                
                <input
                    type='text'
                    id="email"
                    name='email'
                    placeholder="email@example.com"
                    onChange={this.myChangeHandler}
                />
                <input
                    value='SIGN UP'
                    type='submit'
                />
            </form>
            </div>
            );
        }
        else{
            return (<div>You have successfully signed up. You can login now. </div>);
        }
      }
}

export default SignUpForm

