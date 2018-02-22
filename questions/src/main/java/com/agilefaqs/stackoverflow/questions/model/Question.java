package com.agilefaqs.stackoverflow.questions.model;

import java.util.List;

import static java.util.Objects.nonNull;

public class Question {

    private String id;
    private String question;
    private String title;
    private List<String> tags;
    private Integer votes = 0;
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


    public void validate() {
        nonNull(title);
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
