package com.agilefaqs.stackoverflow.questions.api;

import com.agilefaqs.stackoverflow.questions.controllers.QuestionsController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionsApiTest {

    @Autowired
    private QuestionsController questionsController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
            .standaloneSetup(questionsController)
            .build();
    }

    @Test
    public void getQuestions() throws Exception {
        mockMvc.perform(get("/questions/1"))
            .andExpect(status().isOk());
        mockMvc.perform(get("/questions/2"))
            .andExpect(status().isOk());
        mockMvc.perform(get("/questions/3"))
            .andExpect(status().isOk());
    }

    @Test
    public void getInvalidQuestions() throws Exception {
        mockMvc.perform(get("/questions/4"))
            .andExpect(status().isOk());

    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void add() throws Exception {
    }

    @Test
    public void upvote() throws Exception {
    }

    @Test
    public void downvote() throws Exception {
    }

}