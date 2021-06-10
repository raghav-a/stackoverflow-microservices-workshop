import React from "react"

class LoginForm extends React.Component{

    constructor(props) {
        super(props);
        this.state = { 
            error: false,
            userId: '',
            password: '',
            token: null,
            errorMessage: ''
        };
      }
      mySubmitHandler = (event) => {        
            event.preventDefault();
            const apiUrl = 'http://localhost:8765/api/sessions/login'
            fetch(apiUrl, 
                    { 
                        method: 'POST', 
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            userId: this.state.userId, 
                            password: this.state.password                        
                        })
                    })
                .then((response) => response.json())
                .then((data) => {
                    console.log("Login response is : ", data);
                    this.setState({ loading: false, token: data });
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
        if(this.state.token){
            return <div>You have successfully logged in. </div>
        }
        return (
            <div className='user-login'>
                <form onSubmit={this.mySubmitHandler}>
                    <div>{this.state.errorMessage}</div>
                    <p>Enter your name and password:</p>
                    <input
                        type='text'
                        name='userId'
                        placeholder="Your user name"
                        onChange={this.myChangeHandler}
                    />
                    <input
                        type='password'
                        name='password'
                        placeholder="password"
                        onChange={this.myChangeHandler}
                    />
                    <input
                        value="LOGIN"
                        type='submit'
                    />
                </form>
            </div>
        );
      }
}

export default LoginForm

