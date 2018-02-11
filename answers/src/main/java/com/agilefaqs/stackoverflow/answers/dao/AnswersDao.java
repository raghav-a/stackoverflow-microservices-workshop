package com.agilefaqs.stackoverflow.answers.dao;

import com.agilefaqs.stackoverflow.answers.model.Answer;

import java.util.List;

public interface AnswersDao {
    void save(Answer answer);

    Answer get(String answerId);

    List<Answer> getAllAnswersFor(String questionId);
}
