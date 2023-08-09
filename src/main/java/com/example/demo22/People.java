package com.example.demo22;

import org.springframework.stereotype.Component;

import java.util.List;

public interface People {
    List<Human> readHuman();

    Human addHuman(Human human);

    boolean deleteHuman(String name);
}
