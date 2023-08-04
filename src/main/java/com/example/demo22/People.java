package com.example.demo22;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface People {
    Map<Integer, Human> readHuman();

    Human addHuman(Human human);

    boolean deleteHuman(String name);
}
