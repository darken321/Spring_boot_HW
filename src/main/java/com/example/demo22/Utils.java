package com.example.demo22;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
@Component
public class Utils {
    @Value("${spring.profiles.active}")
    String profile;

    public Utils() {
        System.out.println("Utils.Utils");
    }

    public void menu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int input;
        do {
            System.out.println("\nВведите 1 для вывода всех людей, 2 для добавления нового, 0 для выхода ");
            String s = reader.readLine(); // вылет!
            input = Integer.parseInt(s);
            switch (input) {
                case 0 -> System.out.println("Пока!\n");
                case 1 -> System.out.println("Показать всех");
                case 2 -> System.out.println("Добавить человека");
                default -> System.out.println("Вы ввели " + input + " - неверное значение");
            }
        }
        while (input != 0);
        reader.close();
    }
}
