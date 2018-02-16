package com.agilefaqs.stackoverflow.search.service;

import com.agilefaqs.stackoverflow.search.model.Question;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class SearchService {

    private List<Question> allData = new ArrayList<>();

    public void addData(Question input) {
        allData.add(input);
    }

    public List<Question> getAllFor(String searchQuery) {
        return allData.stream().filter(
            question -> question.getQuestion().contains(searchQuery))
            .collect(Collectors.toList());
    }
}
