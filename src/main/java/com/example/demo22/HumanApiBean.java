package com.example.demo22;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Profile("api")
public class HumanApiBean implements People {
    int maxId = 0;

    private final Map<Integer, Human> people = new HashMap<>();

    @PostConstruct
    public void init() {
        System.out.println("Метод init класса ListBean PostConstruct");
        people.put(maxId++, new Human("Дима", 25));
        people.put(maxId++, new Human("Миша", 33));
        people.put(maxId++, new Human("Лена", 28));
        people.put(maxId++, new Human("Миша", 38));
        people.put(maxId++, new Human("test", 111));
    }

    @Override
    @GetMapping("api/v1/people")
    public Map<Integer, Human> readHuman() {
        return people;
    }

    @Override
    @PostMapping("api/v1/people")
    //ошибка!!
    public Human addHuman(@RequestBody Human human) {
        people.put(maxId++, human);
        return human;
    }

    @Override
    @GetMapping("api/v1/delete/{name}")
    public boolean deleteHuman(@PathVariable String name) {
        return people.entrySet().removeIf(entry -> entry.getValue().getName().equals(name));
    }
}
