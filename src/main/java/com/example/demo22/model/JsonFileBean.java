package com.example.demo22.model;

import com.example.demo22.Converter;
import com.example.demo22.exception.FileReadErrorException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
@Profile("json")
public class JsonFileBean implements People {
    int maxId = 0;

    @Value("${json.file.property}")
    private String jsonFileProperty;

    public JsonFileBean() {
        System.out.println("Конструктор FileBean");
    }

    @PostConstruct
    public void init() {
        System.out.println("Метод init класса FileBean PostConstruct ");
        File file = new File(jsonFileProperty);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Метод по считыванию списка humans из файла
     *
     * @return список humans
     * @throws ConversionFailedException
     * @throws NumberFormatException     при ошибке парсигна параметров human
     */
    @Override
    @SneakyThrows(IOException.class)
    public List<Human> readHuman() throws ConversionFailedException, NumberFormatException {
        Map<Integer, Human> map = new HashMap<>();
        File jsonFile = new File(jsonFileProperty);
        ObjectMapper mapper = new ObjectMapper();
        int i = 0;
        try {
            List<Human> humans = mapper.readValue(jsonFile, new TypeReference<>() {
            });
            for (Human human : humans) {
                map.put(i++, human);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(map.values());
    }

    @Override
    public Human addHuman(Human human) {

        List<Human> list = readHuman();
        human.setId(maxId++);
        list.add(human);
        writeAllHumansToFile(list);
        return human;
    }

    //удаляем human по имени
    @Override
    public boolean deleteHuman(String name) {
        //считал всех в лист
        List<Human> list = readHuman();

        if (list.removeIf(entry -> Objects.equals(name, entry.getName()))) {
            //если такое имя есть, то удаляем и пишем всех в файл
            writeAllHumansToFile(list);
            return true;
        }
        return false;
    }

    private void writeAllHumansToFile(List<Human> list) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(new File(jsonFileProperty), list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}