package com.agilefaqs.stackoverflow.questions.dao;

import com.agilefaqs.stackoverflow.questions.model.Question;

public interface QuestionsDao {

    Question get(String questionId);

    void save(Question question);
}
