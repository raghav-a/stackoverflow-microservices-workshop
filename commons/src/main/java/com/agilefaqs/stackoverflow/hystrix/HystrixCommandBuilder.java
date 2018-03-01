package com.agilefaqs.stackoverflow.hystrix;

import java.util.function.Function;
import java.util.function.Supplier;

public class HystrixCommandBuilder<T> {


    private Supplier<T> supplier;
    private Function<Throwable, T> fallback;
    private String commandKey;
    private String groupKey;

    private int timeout;
    private int thresholdValue;
    private int threadPoolSize;

    public HystrixCommandBuilder<T> supplier(Supplier<T> supplier){
        this.supplier = supplier;
        return this;
    }

    public HystrixCommandBuilder<T> fallback(Function<Throwable, T> fallback){
        this.fallback = fallback;
        return this;
    }

    public HystrixCommandBuilder<T> threshold(Long threshold){
        this.fallback = fallback;
        return this;
    }

    public HystrixCommandBuilder<T> threadPoolSize(Long threshold){
        this.fallback = fallback;
        return this;
    }

    public HystrixCommandBuilder<T> groupKey(String groupKey){
        this.groupKey = groupKey;
        return this;
    }

    public HystrixCommandBuilder<T> commandKey(String commandKey){
        this.commandKey = commandKey;
        return this;
    }

    public HystrixCommandBuilder<T> timeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public HystrixCommandBuilder<T> thresholdValue(int thresholdValue) {
        this.thresholdValue = thresholdValue;
        return this;
    }


    public HystrixCommandBuilder<T> threadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
        return this;
    }

    public T execute(){
        return GenericHystrixCommand
            .execute(groupKey, commandKey, supplier, fallback, timeout, thresholdValue, threadPoolSize);
    }
}
