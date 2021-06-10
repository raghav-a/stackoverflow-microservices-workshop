import React from "react"
import AnswersList from "./AnswersList"

class QuestionsDetails extends React.Component {


    constructor(props) {
        super(props)
        const questionId = new URLSearchParams(this.props.location.search).get("questionId");
        this.state = {
            loading: false,
            error: false,
            questionId: questionId,            
        }
    }

    render() {
        console.log("Rendering with state ", this.state);
        if (this.state.loading)
            return <div>Loading question details</div>
        if (this.state.error)
            return <div>api error in Loading question details</div>
        if (this.state.questionData){            
        return (
            <div>
            <div className="question-detail">
                <h1>{this.state.questionData.title}</h1>
                <hr class="solid"></hr>
                <div>{this.state.questionData.question}</div>
                <div><b>Posted by:</b>- {this.state.questionData.postedBy}</div>
                <div>Votes - {this.state.questionData.votes}</div>
                {this.state.questionData.tags.map(tag => (<a href="#" className="tag"><b>{tag} </b></a>))}
            </div>
            <div>
                <AnswersList questionId={this.state.questionId}/>
            </div>
            </div>
        
        )
        
        }
        return <div>Something</div>
    }

    componentDidMount() {
        const apiUrl = `http://localhost:8765/api/questions/${this.state.questionId}`;
        this.setState({ loading: true })
        fetch(apiUrl, { Method: 'GET' })
            .then((response) => response.json())
            .then((data) => {
                console.log("Question details : ", data);
                this.setState({ loading: false, questionData: data });
            })
            .catch((e) => this.setState({ loading: false, error: true }));


        // console.log("component did mount " + this.state.questions);
        // console.log(this.state.questions);
    }
}
export default QuestionsDetails