package com.agilefaqs.stackoverflow.hystrix;

import com.agilefaqs.stackoverflow.exceptions.ApplicationException;
import com.netflix.hystrix.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import rx.Observable;
import java.util.function.Function;
import java.util.function.Supplier;


public class GenericHystrixCommand<T> extends HystrixCommand<T> {

    private static Logger log = LoggerFactory.getLogger(GenericHystrixCommand.class);

    private Supplier<T> toRun;
    private Function<Throwable, T> fallback;

    public static <T> T execute(String groupKey, String commandkey, Supplier<T> toRun) {
        return execute(groupKey, commandkey, toRun, null, 3000, 20, 5);
    }
    public static <T> T execute(String groupKey, String commandkey,
                                Supplier<T> toRun, Function<Throwable, T> fallback, int timeout, int thresholdValue, int threadPoolSizze) {
        return new GenericHystrixCommand<>(groupKey, commandkey, toRun, fallback, timeout, thresholdValue, threadPoolSizze).execute();
    }
    public static <T> Observable<T> executeObservable(String groupKey, String commandkey,
                                                      Supplier<T> toRun) {
        return executeObservable(groupKey, commandkey, toRun, null);
    }
    public static <T> Observable<T> executeObservable(String groupKey, String commandkey,
                                                      Supplier<T> toRun, Function<Throwable, T> fallback) {
        return new GenericHystrixCommand<>(groupKey, commandkey, toRun, fallback, 3000, 20, 5)
            .toObservable();
    }
    public GenericHystrixCommand(String groupKey, String commandkey,
                                 Supplier<T> toRun, Function<Throwable, T> fallback, int timeout, int thresholdValue, int threadPoolSizze) {
        super(Setter
            .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
            .andThreadPoolPropertiesDefaults(
                HystrixThreadPoolProperties.Setter()
                .withCoreSize(threadPoolSizze)
            )
            .andCommandKey(HystrixCommandKey.Factory.asKey(commandkey))
            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                .withExecutionTimeoutInMilliseconds(timeout)
                .withCircuitBreakerRequestVolumeThreshold(thresholdValue)
                .withExecutionIsolationStrategy(
                    HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
            )
        );
        this.toRun = toRun;
        this.fallback = fallback;
    }
    protected T run() throws Exception {
        return this.toRun.get();
    }
    @Override
    protected T getFallback() {
        log.warn(String.format("Exception occurred while executing command %s %s",commandGroup,commandKey),
            getExecutionException());
        return (this.fallback != null)
            ? getFallbackValue()
            : super.getFallback();
    }

    private T getFallbackValue() {
        final T fallbackValue = this.fallback.apply(getExecutionException());
        if(fallbackValue==null)
            throw new ApplicationException("Fallback is null", HttpStatus.NOT_FOUND);
        return fallbackValue;
    }
}