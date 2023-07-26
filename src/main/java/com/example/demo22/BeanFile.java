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
public class BeanFile implements People {
    @Value("${my.first.property}")
    private String fileName;

    public BeanFile() {
        System.out.println("BeanFile.BeanFile");
    }

    @PostConstruct
    public void init() throws IOException {
        this.readHuman();
        Utils.menu();
    }

//    public void menu() throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        int input;
//        do {
//            System.out.println("\nВведите 1 для вывода всех людей, 2 для добавления нового, 0 для выхода ");
//            while (reader.ready() && reader.read() != '\n') {
//            }
//            String s = reader.readLine(); // вылет!
//            input = Integer.parseInt(s);
//            switch (input) {
//                case 0 -> System.out.println("Пока!\n");
//                case 1 -> this.readHuman();
//                case 2 -> this.addHuman();
//                default -> System.out.println("Вы ввели " + input + " - неверное значение");
//            }
//        }
//        while (input != 0);
//        reader.close();
//    }

    @Override
    public void readHuman() {
        System.out.println("Список всех людей из файла:");
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addHuman() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Введите имя: ");
            String name = reader.readLine();
            System.out.print("Введите возраст: ");
            int age = Integer.parseInt(reader.readLine());

            FileWriter fileWriter = new FileWriter(fileName, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println(name);
            printWriter.println(age);
            printWriter.close();
            System.out.println("Добавлен человек " + name + " " + age + " лет");
        } catch (IOException e) {
            e.printStackTrace();
        }

//            System.out.println("Введите имя: ");
//        String name = reader.readLine();
//        System.out.println("Введите возраст: ");
//        int age = Integer.parseInt(reader.readLine());
//        people.add(new Human(name, age));
//        System.out.println("Добавлен человек " + name + " " + age + " лет");
//        this.readHuman();
//        reader.close();
    }
}