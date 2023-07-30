package com.example.demo22;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("!file")
public class ListBean implements People {
    private final List<Human> people = new ArrayList<>();

    public ListBean() {
        System.out.println("Конструктор ListBean");
    }

    @PostConstruct
    public void init() {
        System.out.println("Метод init класса ListBean PostConstruct");
        people.add(new Human("Дима", 25));
        people.add(new Human("Миша", 33));
        people.add(new Human("Лена", 28));
    }

    @Override
    public ArrayList<Human> readHuman() {
        return (ArrayList<Human>) people;
    }

    @Override
    public void addHuman(Human human) {
        people.add(human);
    }

    @Override
    public boolean deleteHuman(String name) {
        int index = Utils.indexOfHuman(name, people);
            if (index>=0) {
                people.remove(index);
            }
        return index != -1;
    }
}