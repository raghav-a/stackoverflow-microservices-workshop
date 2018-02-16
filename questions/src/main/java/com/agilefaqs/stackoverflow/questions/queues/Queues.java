package com.agilefaqs.stackoverflow.questions.queues;

import com.agilefaqs.stackoverflow.questions.model.Question;
import feign.Feign;
import feign.Headers;
import feign.RequestLine;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.web.bind.annotation.RequestBody;

public class Queues {


    public static <T> Channel<T> createChannelForTopic(String topic) {
        return (Channel<T>) new HttpChannelForSearchService(topic);
    }

    public static <T> Channel<T> createEmptyChannelForTopic(String topic) {
        return new Channel<T>() {
            @Override
            public void publish(T data) {

            }
        };
    }

    public static interface Channel<T> {
        public void publish(T data);
    }



    private static class HttpChannelForSearchService implements Channel<Question> {
        private String topic;

        SearchServiceClient searchServiceClient;

        public HttpChannelForSearchService(String topic) {


            searchServiceClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
               // .logger(new Slf4jLogger(SearchServiceClient.class))
                //.logLevel(feign.Logger.Level.FULL)
                .target(SearchServiceClient.class, "http://localhost:3333/search");


            this.topic = topic;
        }

        @Override
        public void publish(Question data) {

            System.out.printf("Publishing data to search service");
            searchServiceClient.postQuestion(data);

        }
    }

    private interface SearchServiceClient {

        @RequestLine("POST /onQuestionsPosts")
        @Headers("Content-Type: application/json")
        void postQuestion(Question book);
    }
}
