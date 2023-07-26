package com.example.demo22;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface People {
    void readHuman();
    void addHuman() throws IOException;
}
