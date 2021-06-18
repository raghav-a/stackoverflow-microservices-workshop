import React from 'react';
import { Link } from "react-router-dom";

function Question(props) {
    const URL = "/question/?questionId=" + props.question.id;
    return (
        <div>
            <div>
                <Link className='question-link' to={URL}><h4>{props.question.title}</h4></Link>
            </div>
            <div>{props.question.question}</div>
            <br />
            {props.question.tags.map(tag => (<a key={tag} href="#" className="tag"><b>{tag} </b></a>))}
        </div>
    )

}

export default Question