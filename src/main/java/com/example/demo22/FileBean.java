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
        List<Human> list = new ArrayList<>();
        File file = new File(myFileProperty);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(myFileProperty))) {
            String name;
            int age;
            while ((name = bufferedReader.readLine()) != null) {
                age = Integer.parseInt(bufferedReader.readLine());
                list.add(new Human(name, age));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void addHuman(Human human) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(myFileProperty, true))) {
            printWriter.println(human.getName() + "\n" + human.getAge());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteHuman(String searchName) {
        List<Human> list = this.readHuman();
        int index = Utils.indexOfHuman(searchName, list);
        if (index != -1) {
            list.remove(index);
            new File(myFileProperty).delete();
            list.forEach(this::addHuman);
        }
        return index != -1;
    }
}