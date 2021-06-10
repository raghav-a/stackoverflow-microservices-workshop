import React from "react"
import QuestionsContainer from "./QuestionsContainer"
import QuestionDetails from "./QuestionDetails"
import LoginForm from "./LoginForm"
import SignUpForm from "./SignUpForm"
import Header from "./Header"
import SideNavBar from "./SideNavBar"
import { Route, Switch, Redirect } from "react-router-dom"

class MainContainer extends React.Component {
    render() {
        return (
            <div>
                <Header />
                <section>
                    <SideNavBar />
                    <div className="context-container">
                        <Switch>
                            <Route path="/" exact component={QuestionsContainer} />
                            <Route path="/question" component={QuestionDetails} />
                            <Route path="/login" component={LoginForm} />
                            <Route path="/signup" component={SignUpForm} />
                            <Route path='/default' render={() => <Redirect to="/" />} />
                        </Switch>
                    </div>
                </section>
            </div>
        )
    }
}
export default MainContainer