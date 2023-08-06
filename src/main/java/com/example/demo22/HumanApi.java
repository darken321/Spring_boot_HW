package com.example.demo22;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Profile("api")
public class HumanApi {
    int maxId = 0;
    private final People people;

    @Autowired
    public HumanApi(People people) {
        this.people = people;
        System.out.println("Конструктор класса HumanApiBean");
    }

    @PostConstruct
    public void init() {
        System.out.println("Метод init класса HumanApiBean PostConstruct");
    }

    @GetMapping("api/v1/humans")
    public List<Human> read() {
        return people.readHuman();
    }

    @PostMapping("api/v1/humans")
    public Human add(@RequestBody Human human) {
        people.addHuman(human);
        return human;
    }

    @DeleteMapping("api/v1/humans/{name}")
    public void delete(@PathVariable String name) {
        people.deleteHuman(name);
    }
}