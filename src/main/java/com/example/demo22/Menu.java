package com.example.demo22;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
@Profile("!api")
public class Menu {
    private final People people;

    @Autowired
    public Menu(People people) {
        this.people = people;
        System.out.println("Конструктор класса Menu");
    }

    @PostConstruct
    public void menu() throws IOException {
        System.out.println("метод menu PostConstruct");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int input;
            do {
                System.out.println("\nВведите " +
                                   "1 для вывода всех, " +
                                   "2 для добавления, " +
                                   "3 для удаления, " +
                                   "0 для выхода");
                input = Integer.parseInt(reader.readLine());
                switch (input) {
                    case 0 -> System.out.println("Пока!\n");
                    case 1 -> readAll();
                    case 2 -> add(reader);
                    case 3 -> delete(reader);
                    default -> System.out.println("Вы ввели " + input + " - неверное значение");
                }
            } while (input != 0);
        }
    }

    private void readAll() {
        if (people.readHuman().entrySet().size()==0) {
            System.out.println("Список пуст");
            return;
        }
        people.readHuman().entrySet().forEach(System.out::println);
    }

    private void add(BufferedReader reader) {
        try {
            System.out.print("Введите имя: ");
            String name = reader.readLine().trim();
            System.out.print("Введите возраст: ");
            int age = Integer.parseInt(reader.readLine().trim());
            people.addHuman(new Human(name, age));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void delete(BufferedReader reader) {
        System.out.print("Введите имя для удаления: ");
        try {
            String name = reader.readLine().trim();
            System.out.println("Удаление " + name + " " + (people.deleteHuman(name) ? "" : "не") + "успешно");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}