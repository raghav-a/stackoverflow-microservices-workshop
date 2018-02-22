package com.agilefaqs.stackoverflow.search.service;

import com.agilefaqs.stackoverflow.search.model.Question;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SearchService {

    private final Map<String, Question> idToQuestionMap = new HashMap<>();
    private List<Question> allData = new ArrayList<>();

    public SearchService() {
        idToQuestionMap.put("1", new Question("1", "hari",
            "New features in java 8", "Can some one share the new features in java 8 and also some resources to learn them",
            Lists.newArrayList("java")));
        idToQuestionMap.put("2", new Question("2", "raghav","Good sites for leaning spring boot", "Can some one share some good sites to lean spring boot in detail.", Lists.newArrayList("spring", "microservices")));
        idToQuestionMap.put("3", new Question("3", "raghav", "Set principal in servlet filter", "I want to populate Principal object from the data passed in the headers of http request. How to do this in a servlet filter?", Lists.newArrayList("java", "servlets")));

    }

    public void addData(Question input) {
        allData.add(input);
    }

    public List<Question> getAllFor(String searchQuery) {
        return allData.stream().filter(
            question -> question.getQuestion().contains(searchQuery))
            .collect(Collectors.toList());
    }
}
