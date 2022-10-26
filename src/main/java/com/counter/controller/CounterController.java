package com.counter.controller;

import com.counter.controller.dto.CounterDto;
import com.counter.service.CounterService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/counter")
public class CounterController {

    private final CounterService service;

    public CounterController(CounterService service) {
        this.service = service;
    }

    @PostMapping
    public void create(@RequestBody CounterDto dto) {
        service.create(dto.name());
    }

    @GetMapping
    public Integer get(@RequestParam String name) {
        return service.get(name);
    }

    @DeleteMapping
    public void delete(@RequestParam String name) {
        service.delete(name);
    }

    @PostMapping("/increment")
    public void increment(@RequestParam String name) {
        service.increment(name);
    }

    @GetMapping("/sum")
    public Integer getSumOfAllCounters() {
        return service.sumOfCounters();
    }

    @GetMapping("/names")
    public Set<String> getCounterNames() {
        return service.getCounterNames();
    }
}
