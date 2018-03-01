package com.agilefaqs.stackoverflow.search.service;

import com.agilefaqs.stackoverflow.search.model.Question;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang.StringUtils.containsIgnoreCase;

@Component
public class SearchService {

    private static Logger log = LoggerFactory.getLogger(SearchService.class);
    private final Map<String, Question> idToQuestionMap = new MapMaker().makeMap();
    private final LoadingCache<String, Set<Question>> tagToQuestionMap = CacheBuilder.newBuilder().build(new CacheLoader<String, Set<Question>>() {
        @Override
        public HashSet<Question> load(String key) throws Exception {
            return new HashSet<>();
        }
    });

    public SearchService() {

        addData(new Question("1", "hari",
            "New features in java 8", "Can some one share the new features in java 8 and also some resources to learn them",
            Lists.newArrayList("java")));
        addData(new Question("2", "raghav", "Good sites for leaning spring boot", "Can some one share some good sites to lean spring boot in detail.", Lists.newArrayList("spring", "microservices")));
        addData(new Question("3", "raghav", "Set principal in servlet filter", "I want to populate Principal object from the data passed in the headers of http request. How to do this in a servlet filter?", Lists.newArrayList("java", "servlets")));

    }

    public void addData(Question input) {
        idToQuestionMap.put(input.getId(), input);
        for (String tag : input.getTags()) {
            tagToQuestionMap.getUnchecked(tag).add(input);
        }

    }

    public List<Question> getAllFor(String searchQuery) {
        return idToQuestionMap.values().stream().filter(
            question -> containsIgnoreCase(question.getQuestion(), searchQuery))
            .collect(Collectors.toList());
    }

    public Set<Question> getAllForTag(String tag) {
        return tagToQuestionMap.getUnchecked(tag);
    }
}
