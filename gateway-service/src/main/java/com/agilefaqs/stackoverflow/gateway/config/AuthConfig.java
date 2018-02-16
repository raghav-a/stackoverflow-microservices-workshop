package com.agilefaqs.stackoverflow.gateway.config;

import com.agilefaqs.stackoverflow.gateway.filters.AuthenticationFilter;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@Component
@ConfigurationProperties(prefix = "auth-config")
public class AuthConfig {

    private static Logger log = LoggerFactory.getLogger(AuthConfig.class);

    private Map<String, List<Api>> serviceApiMap;

    public Map<String, List<Api>> getServiceApiMap() {
        return serviceApiMap;
    }

    public void setServiceApiMap(Map<String, List<Api>> serviceApiMap) {
        this.serviceApiMap = serviceApiMap;
    }

    public boolean needsAuthentication(String url, String method) {
        final String[] tokens = url.split("/");
        Preconditions.checkArgument(tokens.length >= 3);
        final Boolean authenticationNeeded = Optional.ofNullable(serviceApiMap.get(tokens[2]))
            .map(apis -> apis.stream().anyMatch(api -> api.matches(url, method)))
            .orElse(false);
        log.info(String.format("authentication needed for %s : %s : %s",url ,method, authenticationNeeded));
        return authenticationNeeded;
    }

    @Override
    public String toString() {
        return "AuthConfig{" +
            "serviceApiMap=" + serviceApiMap +
            '}';
    }

    public static class Api {
        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String method;
        private String url;

        @Override
        public String toString() {
            return "Api{" +
                "method='" + method + '\'' +
                ", url='" + url + '\'' +
                '}';
        }

        boolean matches(String regex, String method) {
            final boolean matches = regex.matches(regex);
            log.info("result for regex in Api: " + matches);
            return this.method.equals(method) && matches;
        }
    }
}
