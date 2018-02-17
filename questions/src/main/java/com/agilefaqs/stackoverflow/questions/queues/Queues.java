package com.agilefaqs.stackoverflow.questions.queues;

import com.agilefaqs.stackoverflow.questions.model.Question;
import feign.Feign;
import feign.Headers;
import feign.RequestLine;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class Queues {

    @Autowired
    private SearchServiceFeignClient searchServiceFeignClient;

    private static Logger log = LoggerFactory.getLogger(Queues.class);


    public  <T> Channel<T> createChannelForTopic(String topic) {
        return (Channel<T>) new HttpChannelForSearchService(topic, searchServiceFeignClient);
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

        private SearchServiceFeignClient searchServiceFeignClient;
        private Executor executor = Executors.newSingleThreadExecutor();

        public HttpChannelForSearchService(String topic, SearchServiceFeignClient searchServiceFeignClient) {
            this.searchServiceFeignClient = searchServiceFeignClient;
            this.topic = topic;
        }

        @Override
        public void publish(Question data) {
            log.info(String.format("Publishing data to search service : %s",data));
            executor.execute(() -> searchServiceFeignClient.postQuestion(data));

        }
    }




    @FeignClient("search-service")
    public interface SearchServiceFeignClient {

        @RequestMapping(method = RequestMethod.POST, value = "/search/onQuestionsPosts")
        Boolean postQuestion(@RequestBody Question question);

    }
}
