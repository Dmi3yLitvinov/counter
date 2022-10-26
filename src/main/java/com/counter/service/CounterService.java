package com.counter.service;

import com.counter.exception.ApplicationCounterException;
import com.counter.exception.ApplicationCounterNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CounterService {

    private static final Map<String, Integer> COUNTER = new ConcurrentHashMap<>();

    public void create(String name) {
        if (COUNTER.containsKey(name)) {
            throw new ApplicationCounterException("Counter '%s' already exists".formatted(name));
        }
        COUNTER.put(name, 0);
    }

    public void increment(String name) {
        checkExistence(name);
        COUNTER.computeIfPresent(name, (k, v) -> v + 1);
    }

    public Integer get(String name) {
        checkExistence(name);
        return COUNTER.get(name);
    }

    public void delete(String name) {
        checkExistence(name);
        COUNTER.remove(name);
    }

    public Integer sumOfCounters() {
        return COUNTER.values().stream()
                .reduce(0, Integer::sum);
    }

    public Set<String> getCounterNames() {
        return COUNTER.keySet();
    }

    private void checkExistence(String name) {
        if (!COUNTER.containsKey(name)) {
            throw new ApplicationCounterNotFoundException(name);
        }
    }
}
