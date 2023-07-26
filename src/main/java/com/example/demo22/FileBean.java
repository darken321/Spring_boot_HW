package com.example.demo22;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@Profile("file")
public class FileBean implements People {
    @Value("${my.file.property}")
    private String myFileProperty;

    private final Utils utils;
    @Autowired
    public FileBean(Utils utils) {
        System.out.println("FileBean.FileBean");
        this.utils = utils;
    }

    @PostConstruct
    public void init() throws IOException {
        this.readHuman();
//        utils.menu();
        this.menu();
    }

    @Override
    public void readHuman() {
        System.out.println("Список всех людей из файла:");
        try {
            FileReader fileReader = new FileReader(myFileProperty);
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

            FileWriter fileWriter = new FileWriter(myFileProperty, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println(name);
            printWriter.println(age);
            printWriter.close();
            System.out.println("Добавлен человек " + name + " " + age + " лет");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                case 1 -> this.readHuman();
                case 2 -> this.addHuman();
                default -> System.out.println("Вы ввели " + input + " - неверное значение");
            }
        }
        while (input != 0);
        reader.close();
    }
}