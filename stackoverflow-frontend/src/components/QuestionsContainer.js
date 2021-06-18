import React, { useEffect, useState } from "react"
import QuestionsList from "./QuestionsList";

function QuestionsContainer() {

    const [state, setState] = useState({
        loading: false,
        error: false,
        questions: []
    });

    const updateState = (data) => {
        setState({
            ...state, //need to understand thiss
            ...data
        })
    }

    useEffect(() => {
        const apiUrl = 'http://localhost:8765/api/questions/listAll';
        updateState({ loading: true })
        fetch(apiUrl, { Method: 'GET' })
            .then((response) => response.json())
            .then((data) => updateState({ loading: false, questions: data }))
            .catch((e) => {
                console.log("Api error ", e);
                updateState({ loading: false, error: true });
            });
        console.log("Questions container rendered with data: " + state.questions);
    }, [])

    if (state.loading)
        return <div>loading...</div>
    if (state.error)
        return <div>Api error</div>
    return state.questions.length > 0 ? (
        <QuestionsList questions={state.questions} />
    ) : <div>No questions found</div>;
}


export default QuestionsContainer