package com.example.demo22;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Profile("map")
public class MapBean implements People {
    int maxId = 1;

    private final Map<Integer, Human> people = new HashMap<>();

    public MapBean() {
        System.out.println("Конструктор MapBean");
    }

    @PostConstruct
    public void init() {
        System.out.println("Метод init класса ListBean PostConstruct");
        people.put(maxId++, new Human(maxId,"Дима", 25));
        people.put(maxId++, new Human(maxId,"Миша", 33));
        people.put(maxId++, new Human(maxId,"Лена", 28));
        people.put(maxId++, new Human(maxId,"Миша", 38));
    }

    @Override
    public List<Human> readHuman() {
        return new ArrayList<>(people.values());
    }

    @Override
    public Human addHuman(Human human) {
        human.setId(maxId++);
        people.put(maxId, human);
        return human;
    }

    @Override
    public boolean deleteHuman(String name) {
        return people.entrySet().removeIf(entry -> Objects.equals(name, entry.getValue().getName()));
    }
}