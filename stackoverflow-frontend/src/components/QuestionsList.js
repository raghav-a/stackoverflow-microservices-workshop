import React, { useState } from "react";
import Pagination from "./Pagination";
import Question from "./Question";

function QuestionsList(props) {

    const [currentPage, setCurrentPage] = useState(1);
    const [itemsPerPage, setItemsPerPage] = useState(7);

    const indexOfLastPost = currentPage * itemsPerPage;
    const indexOfFirstPost = indexOfLastPost - itemsPerPage;
    const currentPosts = props.questions.slice(indexOfFirstPost, indexOfLastPost);

    const paginate = (currentPage) => setCurrentPage(currentPage)



    //console.log("render of QuestionsList " + this.props.questions);
    return (
        <div>
            <div className='questions-container'>
                <ul className='questions-list'>
                    {currentPosts.map(question => {

                        return <li className='questions-item' key={question.id}>
                            <Question question={question} />
                        </li>
                    })}
                </ul>

            </div>
            <Pagination postsPerPage={itemsPerPage} totalPosts={props.questions.length} paginate={paginate} />
        </div>
    )
}
export default QuestionsList