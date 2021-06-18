import React, { useState, useEffect } from "react"

function AnswersList(props) {
    const [state, setState] = useState({
        questionId: props.questionId,
        loading: false,
        error: false,
        answers: []
    });

    const updateState = (data) => {
        setState({
            ...state, //need to understand thiss
            ...data
        })
    }

    useEffect(() => {
        const apiUrl = 'http://localhost:8765/api/answers/getAll'
        updateState({ loading: true })
        fetch(apiUrl,
            {
                Method: 'GET',
                headers: {
                    'questionId': state.questionId
                }
            })
            .then((response) => response.json())
            .then((data) => {
                console.log("Answer fetched are: ", data);
                updateState({ loading: false, answers: data });
            })
            .catch((e) => updateState({ loading: false, error: true }));

    }, [])

    if (state.loading)
        return <div>Loading ...</div>
    if (state.error)
        return <div>Error in fetching answers ...</div>
    if (state.answers.length > 0)
        return (<div>
            <h3>{state.answers.length} Answers</h3>
            <ul className="answer-list">
                {state.answers.map(answer => {
                    return <li key={answer.id} className="answer-item">
                        <p>{answer.answer}</p>
                        <hr className="solid"></hr>
                        <div><b>Posted by:</b>{answer.postedBy}</div>
                        <div>number of votes : {answer.votes}</div>
                    </li>
                }
                )}
            </ul>
        </div>)
    return <div></div>

}

export default AnswersList