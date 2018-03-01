package com.agilefaqs.stackoverflow.questions.model;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

public class Question {

    private String id;
    @NotNull(message = "Question can not be null")
    private String question;
    @NotNull
    private String title;
    private List<String> tags;
    private Map<String,Integer> votes = new HashMap<>();
    private String postedBy;

    public Question() {
    }

    public Question(String id, String postedBy, String title, String question,List<String> tags) {
        this.id = id;
        this.postedBy = postedBy;
        this.question = question;
        this.title = title;
        this.tags = tags;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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


    public void upvote(String userId) {
        votes.put(userId,1);
    }

    public void downvote(String userId) {
        votes.put(userId,-1);
    }


    @Override
    public String toString() {
        return "Question{" +
            "id='" + id + '\'' +
            ", question='" + question + '\'' +
            ", title='" + title + '\'' +
            ", tags=" + tags +
            ", votes=" + votes +
            '}';
    }

    public void update(Question input) {
        if (input.getTitle() != null)
            setTitle(input.getTitle());
        if (input.getQuestion() != null)
            setQuestion(input.getQuestion());
        if (input.getTags() != null)
            setTags(input.getTags());
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getPostedBy() {
        return postedBy;
    }
}
