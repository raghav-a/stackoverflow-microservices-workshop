import React from "react"
import QuestionsContainer from "./QuestionsContainer"
import QuestionDetails from "./QuestionDetails"
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
                    <Switch>
                        <Route path="/" exact component={QuestionsContainer} />
                        <Route path="/question" component={QuestionDetails} />
                        <Route path='/default' render={() => <Redirect to="/" />} />
                    </Switch>
                </section>
            </div>
        )
    }
}
export default MainContainer