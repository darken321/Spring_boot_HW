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
                System.out.print("\u001B[92m");
                System.out.println("\nВведите " +
                                   "1 для вывода всех, " +
                                   "2 для добавления, " +
                                   "3 для удаления, " +
                                   "0 для выхода");
                System.out.print("\u001B[0m");

                try {
                    input = Integer.parseInt(reader.readLine());
                    switch (input) {
                        case 0 -> System.out.println("Пока!\n");
                        case 1 -> readAll();
                        case 2 -> add(reader);
                        case 3 -> delete(reader);
                        default -> System.out.println("\u001B[31mВы ввели " + input + " - неверное значение\u001B[0m");
                    }
                }
                catch (NumberFormatException e){
                    System.out.println("\u001B[31mВы ввели неверное значение!!!\u001B[0m");
                    input = -1;
                }

            } while (input != 0);
        }
    }

    private void readAll() {
        if (people.readHuman().size()==0) {
            System.out.println("Список пуст");
            return;
        }
        people.readHuman().forEach(System.out::println);
    }

    private void add(BufferedReader reader) {
        try {

            System.out.print("\u001B[93mВведите имя: \u001B[0m");
            String name = reader.readLine().trim();
            System.out.print("\u001B[93mВведите возраст: \u001B[0m");
            int age = Integer.parseInt(reader.readLine().trim());
            people.addHuman(new Human(0,name, age));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void delete(BufferedReader reader) {
        System.out.print("\u001B[93mВведите имя для удаления: \u001B[0m");
        try {
            String name = reader.readLine().trim();
            System.out.println("Удаление " + "\u001B[38;5;206m" + name + "\u001B[0m" + (people.deleteHuman(name) ? "" : "\u001B[31m не\u001B[0m") + " успешно");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}