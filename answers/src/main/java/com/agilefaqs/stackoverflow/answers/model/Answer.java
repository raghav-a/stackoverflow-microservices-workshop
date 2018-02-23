package com.agilefaqs.stackoverflow.answers.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

public class Answer {

    private String id;
    private String answer;
    private String questionId;
    private List<String> tags;
    private Map<String,Integer> votes = new HashMap<>();

    public Answer(String id, String answer, String questionId, List<String> tags) {
        this.id = id;
        this.answer = answer;
        this.questionId = questionId;
        this.tags = tags;
    }


    public void validate() {
        nonNull(answer);
    }

    public String id() {
        return id;
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
        return votes.values().stream().mapToInt(i -> i).sum();
    }

   // public void setVotes(Integer votes) {
     //   this.votes = votes;
    //}

    public void upvote(String userId) {
        votes.put(userId,1);
    }

    public void downvote(String userId) {
        votes.put(userId,-1);
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
