package com.agilefaqs.stackoverflow.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "auth-config")
public class AuthConfig {

    private Map<String, List<Api>> serviceApiMap;

    public Map<String, List<Api>> getServiceApiMap() {
        return serviceApiMap;
    }

    public void setServiceApiMap(Map<String, List<Api>> serviceApiMap) {
        this.serviceApiMap = serviceApiMap;
    }

    public boolean needsAuthentication(String url, String method){
        System.out.println("authentication needed ?? "+url + ":"+method);
        final String[] tokens = url.split("/");
        final List<Api> apis = serviceApiMap.get(tokens[2]);
        for (Api api : apis) {
            if(api.matches(url, method))
                return true;
        }
        return false;
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

        public boolean matches(String regex, String method) {
            final boolean matches = regex.matches(regex);
            System.out.println("result for regex : "+matches);
            return this.method.equals(method) && matches;
        }
    }
}
