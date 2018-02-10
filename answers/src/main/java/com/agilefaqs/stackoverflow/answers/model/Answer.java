package com.agilefaqs.stackoverflow.answers.model;

import java.util.List;

import static java.util.Objects.nonNull;

public class Answer {

    private String id;
    private String answer;
    private String questionId;
    private List<String> tags;
    private Integer votes = 0;



    public void validate() {
        nonNull(answer);
    }

    public String id() {
        return "questionId";
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
