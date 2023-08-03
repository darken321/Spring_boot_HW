package com.example.demo22;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
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
        List<Human> list = people.readHuman();
        if (list.size()==0) {
            System.out.println("Список пуст");
            return;
        }
        list.forEach(System.out::println);
    }

    private void add(BufferedReader reader) {
        people.addHuman(this.readHuman(reader));
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

    private Human readHuman(BufferedReader bufferedReader) {
        try {
            System.out.print("Введите имя: ");
            String name = bufferedReader.readLine().trim();
            System.out.print("Введите возраст: ");
            int age = Integer.parseInt(bufferedReader.readLine().trim());
            return new Human(name, age);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}