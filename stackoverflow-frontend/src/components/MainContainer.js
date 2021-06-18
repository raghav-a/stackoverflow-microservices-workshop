import React, { useMemo, useState } from "react"
import QuestionsContainer from "./QuestionsContainer"
import QuestionDetails from "./QuestionDetails"
import LoginForm from "./LoginForm"
import SignUpForm from "./SignUpForm"
import Header from "./Header"
import SideNavBar from "./SideNavBar"
import { Route, Switch, Redirect } from "react-router-dom"
import UserContext from "../contexts/User/UserContext"
import UserProfile from "./UserProfile"

function MainContainer() {

    const [user, setUser] = useState(null);

    //const value = useMemo(() => ({ user, setUser }), [user, setUser])

    return (
        <UserContext.Provider value={{ user, setUser }}>
            <div>
                <Header title="StackOverflow" />
                <section>
                    <SideNavBar />
                    <div className="context-container">
                        <Switch>
                            <Route path="/" exact component={QuestionsContainer} />
                            <Route path="/question" component={QuestionDetails} />
                            <Route path="/login" component={LoginForm} />
                            <Route path="/signup" component={SignUpForm} />
                            <Route path="/profile" component={UserProfile} />
                            <Route path='/default' render={() => <Redirect to="/" />} />
                        </Switch>
                    </div>
                </section>

            </div >
        </UserContext.Provider>
    )
}

export default MainContainer