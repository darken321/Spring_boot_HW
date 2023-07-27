package com.example.demo22;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("file")
public class FileBean implements People {
    @Value("${my.file.property}")
    private String myFileProperty;

    public FileBean() {
        System.out.println("Конструктор FileBean");
    }

    @PostConstruct
    public void init() {
        System.out.println("Метод init класса FileBean PostConstruct ");
    }

    @Override
    public List<Human> readHuman() {
        System.out.println("Список всех людей из файла:");
        List<Human> people = new ArrayList<>();
        try (FileReader fileReader = new FileReader(myFileProperty);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String name;
            while ((name = bufferedReader.readLine()) != null) {
                people.add(new Human(name, Integer.parseInt(bufferedReader.readLine())));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    @Override
    public void addHuman(Human human) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(myFileProperty, true))) {
            printWriter.println(human.getName() + "\n" + human.getAge());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}