package com.counter.service;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class CounterServiceTest {

    private final CounterService service = new CounterService();

    @Test
    public void incrementAsyncTest() throws InterruptedException {
        String name = "name";
        service.create("name");

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        int i;
        for (i = 0; i < 100; i++) {
            executorService.submit(() -> service.increment(name));
        }
        executorService.shutdown();
        executorService.awaitTermination(3, TimeUnit.SECONDS);

        Integer counter = service.get(name);
        assertThat(counter).isEqualTo(i);
    }

}