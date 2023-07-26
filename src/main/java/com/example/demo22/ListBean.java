package com.example.demo22;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("list")
public class ListBean implements People {
    private final List<Human> people = new ArrayList<>();

    public ListBean() {
        System.out.println("ListBean.ListBean");
    }

    @PostConstruct
    public void init() throws IOException {
        System.out.println("BeanList.init");
        people.add(new Human("Дима", 25));
        people.add(new Human("Миша", 33));
        this.readHuman();
        this.menu();
    }

    public void menu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int input;
        do {
            System.out.println("\nВведите 1 для вывода всех людей, 2 для добавления нового, 0 для выхода ");
            while (reader.ready() && reader.read() != '\n') {
            }
            String s = reader.readLine(); // вылет!
            input = Integer.parseInt(s);
            switch (input) {
                case 0 -> System.out.println("Пока!\n");
                case 1 -> this.readHuman();
                case 2 -> this.addHuman();
                default -> System.out.println("Вы ввели " + input + " - неверное значение");
            }
        }
        while (input != 0);
        reader.close();
    }

    @Override
    public void readHuman() {
        System.out.println("Список всех людей из листа:");
        for (Human h : people) {
            System.out.println(h);
        }
    }

    @Override
    public void addHuman() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите имя: ");
        String name = reader.readLine();
        System.out.print("Введите возраст: ");
        int age = Integer.parseInt(reader.readLine());
        people.add(new Human(name, age));
        System.out.println("Добавлен человек " + name + " " + age + " лет");
        this.readHuman();
        reader.close();
    }
}