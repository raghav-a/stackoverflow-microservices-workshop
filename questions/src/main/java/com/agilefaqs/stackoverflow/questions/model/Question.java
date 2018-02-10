package com.agilefaqs.stackoverflow.questions.model;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import static java.util.Objects.nonNull;

public class Question {

    private String questionId;
    private String question;
    private List<String> tags;
    private Integer votes = 0;



    public void validate() {
        nonNull(question);
    }

    public String id() {
        return "questionId";
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public void upvote() {
        votes++;
    }

    public void downvote() {
        votes--;
    }
}
