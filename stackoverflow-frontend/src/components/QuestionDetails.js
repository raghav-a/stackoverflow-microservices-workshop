import React from "react"
class QuestionsDetails extends React.Component {


    constructor(props) {
        super(props)
        const questionId = new URLSearchParams(this.props.location.search).get("questionId");
        this.state = {
            loading: false,
            error: false,
            questionId: questionId
        }
    }

    render() {
        if (this.state.loading)
            return <div>Loading question details</div>
        if (this.state.error)
            return <div>api error in Loading question details</div>
        if (!this.state.questionData)
            console.log("Data is", this.state.questionData);
        return (
            <div className="question-detail">
                <div>{this.state.questionData.title}</div>
                <div>{this.state.questionData.question}</div>
                <div>Posted by - {this.state.questionData.postedBy}</div>
                <div>Votes - {this.state.questionData.votes}</div>
                {this.state.questionData.tags.map(tag => (<a href="#"><b>{tag} </b></a>))}
            </div>
        )
    }

    componentDidMount() {
        const apiUrl = `http://localhost:8765/api/questions/${this.state.questionId}`;
        this.setState({ loading: true })
        fetch(apiUrl, { Method: 'GET' })
            .then((response) => response.json())
            .then((data) => {
                console.log("Value is ", data);
                this.setState({ loading: false, questionData: data });
            })
            .catch((e) => this.setState({ loading: false, error: true }));


        // console.log("component did mount " + this.state.questions);
        // console.log(this.state.questions);
    }
}
export default QuestionsDetails