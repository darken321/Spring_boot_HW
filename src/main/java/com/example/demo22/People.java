package com.example.demo22;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

public interface People {
    List<Human> readHuman();
    void addHuman(Human human);
}
