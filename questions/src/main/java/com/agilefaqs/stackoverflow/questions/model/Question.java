package com.agilefaqs.stackoverflow.questions.model;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class Question {

    private String id;
    private String question;
    private List<String> tags;
    private Integer votes = 0;

    public Question() {
    }

    public Question(String id, String question, List<String> tags) {
        this.id = id;
        this.question = question;
        this.tags = tags;
    }


    public void validate() {
        nonNull(question);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
