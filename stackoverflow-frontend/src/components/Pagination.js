import React from 'react'
import { Link } from "react-router-dom"

const Pagination = ({ postsPerPage, totalPosts, paginate }) => {
    const pageNumbers = []
    for (let i = 1; i <= Math.ceil(totalPosts / postsPerPage); i++) {
        pageNumbers.push(i);
    }
    return <nav>
        <ul className='pagination'>
            {pageNumbers.map(number => (
                <li key={"page" + '-' + number} className='page-item'>
                    <Link onClick={() => paginate(number)} className='page-link '>
                        {number}
                    </Link>
                </li>
            ))}

        </ul>
    </nav>
}

export default Pagination