package com.example.demo22;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Menu {
    private final People people;
    private final ReadHuman readHuman;

    @Autowired
    public Menu(People people, ReadHuman readHuman) {
        this.people = people;
        this.readHuman = readHuman;
        System.out.println("Конструктор класса Menu");
    }

    @PostConstruct
    public void menu() throws IOException {
        System.out.println("метод menu PostConstruct");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int input;
            do {
                System.out.println("\nВведите 1 для вывода всех людей, 2 для добавления нового, 0 для выхода ");
                input = Integer.parseInt(reader.readLine());
                switch (input) {
                    case 0 -> System.out.println("Пока!\n");
                    case 1 -> readAll();
                    case 2 -> add(reader);
                    default -> System.out.println("Вы ввели " + input + " - неверное значение");
                }
            } while (input != 0);
        }
    }

    private void readAll() {
        people.readHuman().forEach(System.out::println);
    }

    private void add(BufferedReader reader) throws IOException {
        people.addHuman(readHuman.readHuman(reader));
//        System.out.print("Введите ваше имя: ");
//        String name = reader.readLine().trim();
//        System.out.print("Введите ваш возраст: ");
//        int age = Integer.parseInt(reader.readLine().trim());
//        people.addHuman(new Human(name, age));
    }
}