package com.agilefaqs.stackoverflow.answers.model;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

public class Answer {


    private String id;
    @NotNull
    private String answer;
    @NotNull
    private String questionId;
    private List<String> tags;
    private Map<String,Integer> votes = new HashMap<>();
    private String postedBy;

    public Answer(String id, String postedBy, String answer, String questionId, List<String> tags) {
        this.id = id;
        this.postedBy = postedBy;
        this.answer = answer;
        this.questionId = questionId;
        this.tags = tags;
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

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getPostedBy() {
        return postedBy;
    }
}
