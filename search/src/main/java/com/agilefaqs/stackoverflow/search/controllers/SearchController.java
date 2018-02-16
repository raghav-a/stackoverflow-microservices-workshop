package com.agilefaqs.stackoverflow.search.controllers;

import com.agilefaqs.stackoverflow.search.model.Question;
import com.agilefaqs.stackoverflow.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    SearchService searchService;


    @RequestMapping(value = "/onQuestionsPosts", method = RequestMethod.POST)
    public ResponseEntity<?> onQuestions(@RequestBody Question input) {
        searchService.addData(input);
        return ResponseEntity
            .noContent()
            .build();

    }


    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<Question> getAll(@RequestParam("searchQuery") String searchQuery) {
        return searchService.getAllFor(searchQuery);
    }


}
