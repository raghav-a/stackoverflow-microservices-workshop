package com.agilefaqs.stackoverflow.search.model;

import java.util.List;

public class Question {

    private String id;
    private String question;
    private String title;
    private String postedBy;
    private List<String> tags;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;

        Question question = (Question) o;

        return id != null ? id.equals(question.id) : question.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Question() {
    }

    public Question(String id, String question, String title, String postedBy, List<String> tags) {
        this.id = id;
        this.question = question;
        this.title = title;
        this.postedBy = postedBy;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Question{" +
            "id='" + id + '\'' +
            ", question='" + question + '\'' +
            ", title='" + title + '\'' +
            ", postedBy='" + postedBy + '\'' +
            ", tags=" + tags +
            '}';
    }
}
