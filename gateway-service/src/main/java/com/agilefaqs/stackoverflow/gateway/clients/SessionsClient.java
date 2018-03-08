package com.agilefaqs.stackoverflow.gateway.clients;


import com.agilefaqs.stackoverflow.gateway.config.AuthConfig;
import com.agilefaqs.stackoverflow.gateway.model.AuthRequest;
import com.agilefaqs.stackoverflow.hystrix.GenericHystrixCommand;
import com.agilefaqs.stackoverflow.hystrix.HystrixCommandBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Supplier;


@Component
public class SessionsClient {

    private static Logger log = LoggerFactory.getLogger(SessionsClient.class);
    private SessionsFeignClient sessionsFeignClient;
    private Map<String, UserDetail> sessionsTokens = new ConcurrentHashMap<>();

    @Autowired
    public SessionsClient(SessionsFeignClient sessionsFeignClient) {
        this.sessionsFeignClient = sessionsFeignClient;
    }

    public UserDetail validateToken(AuthRequest authRequest) {
        return new HystrixCommandBuilder<UserDetail>()
            .groupKey("gateway.auth")
            .commandKey("session.validate")
            .threadPoolSize(10)
            .timeout(3000)
            .thresholdValue(20)
            .supplier(fetchUserDetailsFromSessions(authRequest))
            .fallback(getFromLocalCache(authRequest))
            .execute();

    }

    private Supplier<UserDetail> fetchUserDetailsFromSessions(AuthRequest authRequest) {
        return () -> {
            final UserDetail userDetail = sessionsFeignClient.validateToken(authRequest);
            updateInLocalCache(authRequest, userDetail);
            return userDetail;
        };
    }

    private void updateInLocalCache(AuthRequest authRequest, UserDetail isValid) {
        sessionsTokens.put(authRequest.getToken(), isValid);
    }

    private Function<Throwable, UserDetail> getFromLocalCache(AuthRequest authRequest) {
        log.warn("Fetching data from fallback.");
        return e -> sessionsTokens.get(authRequest.getToken());
    }
}
