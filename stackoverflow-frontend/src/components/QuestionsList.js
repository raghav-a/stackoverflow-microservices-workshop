import React from "react"
import { Link } from "react-router-dom";

class QuestionsList extends React.Component {
    render() {
        console.log("render of QuestionsList " + this.props.questions);
        return (
            <div className='questions-container'>
                <ul className='questions-list'>
                    {this.props.questions.map(question => {
                        const URL = "/question/?questionId=" + question.id;
                        return <li className='questions-item' key={question.id}>
                            <div>
                                <Link to={URL}><h4>{question.title}</h4></Link>
                            </div>
                            <div>{question.question}</div>
                            {question.tags.map(tag => (<a href="#"><b>{tag} </b></a>))}

                        </li>
                    })}
                </ul>
            </div>
        )
    }
}
export default QuestionsList