package com.example.demo22;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Profile("!file")
public class MapBean implements People {
    int maxId = 0;

    private final Map<Integer, Human> people = new HashMap<>();

    public MapBean() {
        System.out.println("Конструктор MapBean");
    }

    @PostConstruct
    public void init() {
        System.out.println("Метод init класса ListBean PostConstruct");
        people.put(maxId++, new Human("Дима", 25));
        people.put(maxId++, new Human("Миша", 33));
        people.put(maxId++, new Human("Лена", 28));
        people.put(maxId++, new Human("Миша", 38));
    }

    @Override
    public Map<Integer, Human> readHuman() {
        return people;
    }

    @Override
    public void addHuman(Human human) {
        people.put(maxId++, human);
    }

    @Override
    public boolean deleteHuman(String name) {
        return people.entrySet().removeIf(entry -> entry.getValue().getName().equals(name));
    }
}