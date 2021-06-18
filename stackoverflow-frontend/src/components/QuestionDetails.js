import React, { useEffect, useState } from "react"
import AnswersList from "./AnswersList"
import fetchWrapper from '../services/wrapper';


function QuestionsDetails(props) {

    const questionIdValue = new URLSearchParams(props.location.search).get("questionId");
    console.log("Value of question id is" + questionIdValue);
    const [state, setState] = useState({
        loading: false,
        error: false,
        questionId: questionIdValue,
    });

    console.log("Value of state question id is", state);

    const updateState = (data) => {
        setState({
            ...state, //need to understand thiss
            ...data
        })
    }

    useEffect(() => {
        console.log("Calling ueEffect");
        const apiUrl = `http://localhost:8765/api/questions/${state.questionId}`;
        updateState({ loading: true })
        fetchWrapper(apiUrl, {
            method: 'GET'
        }).then((data) => {
            console.log("Question details : ", data);
            updateState({ loading: false, questionData: data });
        }).catch((e) => updateState({ loading: false, error: true }));
    }, []);


    console.log("Rendering with state ", state);
    if (state.loading)
        return <div>Loading question details</div>
    if (state.error)
        return <div>api error in Loading question details</div>
    if (state.questionData) {
        return (
            <div>
                <div className="question-detail">
                    <h1>{state.questionData.title}</h1>
                    <hr className="solid"></hr>
                    <div>
                        <a href="upvote"><i className="fas fa-angle-up"></i></a>
                        <a href="downvote"><i className="fas fa-angle-down"></i></a>
                    </div>
                    <div>{state.questionData.question}</div>
                    <div><b>Posted by:</b>- {state.questionData.postedBy}</div>
                    <div>Votes - {state.questionData.votes}</div>
                    {state.questionData.tags.map(tag => (<a key={tag} href="#" className="tag"><b>{tag} </b></a>))}
                </div>
                <div>
                    <AnswersList questionId={state.questionId} />
                </div>
            </div>

        )

    }
    return <div>Something</div>
}

export default QuestionsDetails