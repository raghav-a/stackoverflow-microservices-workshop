import React from "react"
import QuestionsList from "./QuestionsList";
class QuestionsContainer extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            loading: false,
            error: false,
            questions: []
        }
    }


    render() {
        if (this.state.loading)
            return <div>loading...</div>
        if (this.state.error)
            return <div>Api error</div>
        return this.state.questions.length > 0 ? (
            <QuestionsList questions={this.state.questions} />
        ) : <div>No questions found</div>;
    }

    componentDidMount() {
        console.log("component did mount 1");
        const apiUrl = 'http://localhost:8765/api/questions/listAll';
        this.setState({ loading: true })
        fetch(apiUrl, { Method: 'GET' })
            .then((response) => response.json())
            .then((data) => this.setState({ loading: false, questions: data }))
            .catch((e) => this.setState({ loading: false, error: true }))
            ;


        console.log("component did mount " + this.state.questions);
        console.log(this.state.questions);
    }
}
export default QuestionsContainer