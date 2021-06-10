import React from "react"

class AnswersList extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            questionId: this.props.questionId,
            loading: false,
            error: false,
            answers: []
        }
    }

    render(){
        if(this.state.loading)
            return <div>Loading ...</div>
        if(this.state.error)    
            return <div>Error in fetching answers ...</div>    
        if(this.state.answers.length > 0)
            return (<div>
            <h3>{this.state.answers.length} Answers</h3>
            <ul className="answer-list">
                {this.state.answers.map(answer =>
                {
                    return <li className="answer-item">
                                <p>{answer.answer}</p>
                                <hr class="solid"></hr>
                                <div><b>Posted by:</b>{answer.postedBy}</div>
                                <div>number of votes : {answer.votes}</div>
                            </li>
                }
                )}
            </ul>
            </div>)
        return <div></div>    
    }

    
    componentDidMount(){
        const apiUrl = 'http://localhost:8765/api/answers/getAll'
        this.setState({ loading: true })
        fetch(apiUrl, 
            { 
                Method: 'GET' , 
                headers: {
                'questionId': this.state.questionId
            }
        })
            .then((response) => response.json())
            .then((data) => {
                console.log("Answer fetched are: ", data);
                this.setState({ loading: false, answers: data });
            })
            .catch((e) => this.setState({ loading: false, error: true }));
    }


}

export default AnswersList