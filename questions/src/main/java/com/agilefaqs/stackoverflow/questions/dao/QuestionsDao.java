package com.agilefaqs.stackoverflow.questions.dao;

import com.agilefaqs.stackoverflow.questions.model.Question;

import java.util.Collection;
import java.util.List;

public interface QuestionsDao {

    Question get(String questionId);

    void save(Question question);

    Collection<Question> getAll();
}
