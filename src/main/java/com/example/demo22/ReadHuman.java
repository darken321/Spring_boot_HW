package com.example.demo22;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class ReadHuman {
    public Human readHuman(BufferedReader bufferedReader) {
        String name;
        int age;
        try {
            System.out.print("Введите имя: ");
            name = bufferedReader.readLine();
            System.out.print("Введите возраст: ");
            age = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new Human(name, age);
    }
}