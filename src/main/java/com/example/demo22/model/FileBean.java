package com.example.demo22.model;

import com.example.demo22.exception.FileReadErrorException;
import com.example.demo22.model.Human;
import com.example.demo22.model.People;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
@Profile("file")
public class FileBean implements People {
    int maxId = 0;

    @Value("${my.file.property}")
    private String myFileProperty;

    public FileBean() {
        System.out.println("Конструктор FileBean");
    }

    @PostConstruct
    public void init() {
        System.out.println("Метод init класса FileBean PostConstruct ");
        File file = new File(myFileProperty);
        if (!file.exists()) {
            try (PrintWriter printWriter = new PrintWriter(new FileWriter(myFileProperty))) {
                printWriter.println(maxId);
            } catch (IOException e) {
                throw new FileReadErrorException(e.getMessage());
            }
        }
    }

    /**
     * Метод по считыванию списка humans из файла
     * @return список humans
     * @throws ConversionFailedException
     * @throws NumberFormatException при ошибке парсигна параметров human
     */
    @Override
    @SneakyThrows(IOException.class)
    public List<Human> readHuman() throws ConversionFailedException, NumberFormatException {
        Map<Integer, Human> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(myFileProperty))) { //FileNotFoundException
            int id;
            String name;
            int age;
            String readLine = reader.readLine();//IOException
            maxId = Integer.parseInt(readLine);

            while ((name = reader.readLine()) != null) {
                String idReader = reader.readLine();
                String ageReader = reader.readLine();
                id = Integer.parseInt(idReader);
                age = Integer.parseInt(ageReader);
                map.put(id, new Human(id, name, age));
            }
        } //catch (IOException e) {
//            throw new CannotReadFileException(e.getMessage());
//        }
        return new ArrayList<>(map.values());
    }

    @Override
    public Human addHuman(Human human) {

        //чтение числа элементов
        try (BufferedReader reader = new BufferedReader(new FileReader(myFileProperty))) {
            maxId = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Human> list = readHuman();
        human.setId(maxId++);
        list.add(human);

        writeAllHumansToFile(list);
        return human;
    }

    @Override
    public boolean deleteHuman(String name) {
        List<Human> list = readHuman();
        if (list.removeIf(entry -> Objects.equals(name, entry.getName()))) {
            writeAllHumansToFile(list);
            return true;
        }
        return false;
    }

    private void writeAllHumansToFile(List<Human> list) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(myFileProperty))) {
            printWriter.println(maxId);
            list.forEach(h -> printWriter.println(h.getName() + "\n" + h.getId() + "\n" + h.getAge()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}