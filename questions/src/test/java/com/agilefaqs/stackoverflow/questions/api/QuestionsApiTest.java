package com.agilefaqs.stackoverflow.questions.api;

import com.agilefaqs.stackoverflow.exceptions.ControllerExceptionAdvice;
import com.agilefaqs.stackoverflow.questions.controllers.QuestionsController;
import com.agilefaqs.stackoverflow.questions.dao.HashMapQuestionsDao;
import com.agilefaqs.stackoverflow.questions.dao.QuestionsDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionsApiTest {

    private final String questionThreeAsJson = "{\"id\":\"3\",\"question\":\"I want to populate Principal object from the data passed in the headers of http request. How to do this in a servlet filter?\",\"title\":\"Set principal in servlet filter\",\"tags\":[\"java\",\"servlets\"],\"votes\":0,\"postedBy\":\"raghav\"}";
    private final String questionTwoAsJson = "{\"id\":\"2\",\"question\":\"Can some one share some good sites to lean spring boot in detail.\",\"title\":\"Good sites for leaning spring boot\",\"tags\":[\"spring\",\"microservices\"],\"votes\":0,\"postedBy\":\"raghav\"}";
    private final String questionOneAsJson = "{\"id\":\"1\",\"question\":\"Can some one share the new features in java 8 and also some resources to learn them\",\"title\":\"New features in java 8\",\"tags\":[\"java\"],\"votes\":0,\"postedBy\":\"hari\"}";
    private final String toBePosted = "{\"question\":\"Let's ask something new\",\"title\":\"New question\",\"tags\":[\"random\"]}";
    private final String updatedQuestion = "{\"id\":\"3\",\"question\":\"I want to populate Principal object from the data passed in the headers of http request. How to do this in a servlet filter?\",\"title\":\"Set principal in servlet filter\",\"tags\":[\"java\",\"servlets\",\"newTag\"]}";

    @Autowired
    private QuestionsController questionsController;

    @Autowired
    private QuestionsDao questionsDao;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
            .standaloneSetup(questionsController)
            .setControllerAdvice(new ControllerExceptionAdvice())
            .build();
        ((HashMapQuestionsDao)questionsDao).initializeData();
    }

    @After
    public void tearDown(){
        ((HashMapQuestionsDao)questionsDao).initializeData();
    }

    @Test
    public void fetchQuestionsForValidQuestionIds() throws Exception {

        mockMvc.perform(get("/questions/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(questionOneAsJson));
        mockMvc.perform(get("/questions/2"))
            .andExpect(status().isOk())
            .andExpect(content().json(questionTwoAsJson));
        mockMvc.perform(get("/questions/3"))
            .andExpect(status().isOk())
            .andExpect(content().json(questionThreeAsJson));
    }

    @Test
    public void statusCodeShouldBeNotFoundOnGetQuestionWithInvalidId() throws Exception {
        mockMvc.perform(get("/questions/4"))
            .andExpect(status().isNotFound());

    }

    @Test
    public void updateAnExistingQuestion() throws Exception {
        mockMvc.perform(put("/questions/3")
            .header("X-USER-ID", "raghav")
            .contentType(MediaType.APPLICATION_JSON)
            .content(updatedQuestion)
        )
            .andExpect(status().isNoContent());
        assertTrue(questionsDao.get("3").getTags().contains("newTag"));
    }

    @Test
    public void postANewQuestion() throws Exception {
        mockMvc.perform(post("/questions")
            .header("X-USER-ID", "raghav")
            .contentType(MediaType.APPLICATION_JSON)
            .content(toBePosted)
        )
            .andExpect(status().isCreated())
            .andExpect(content().json("{\"questionId\":\"4\"}"));
        assertNotNull(questionsDao.get("4"));
    }

    @Test
    public void upvoteQuestion() throws Exception {
        mockMvc.perform(post("/questions/1/upvote")
            .header("X-USER-ID", "raghav"))
            .andExpect(status().isNoContent());
        assertEquals(questionsDao.get("1").getVotes(), new Integer(1));
    }

    @Test
    public void downvoteQuestion() throws Exception {
        mockMvc.perform(post("/questions/1/downvote")
            .header("X-USER-ID", "raghav"))
            .andExpect(status().isNoContent());
        assertEquals(questionsDao.get("1").getVotes(), new Integer(-1));
    }

}