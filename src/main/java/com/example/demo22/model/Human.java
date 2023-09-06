package com.example.demo22.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Human {
    @JsonIgnore
    private static int currentId = 0;
    @JsonProperty
    private int id ;
    @JsonProperty("Human name")
    private String name;
    @JsonProperty("Human age")
    private int age;

    @Override
    public String toString() {
        return
               "\u001B[0mid " + "\u001B[38;5;206m"+ id +
               "  \u001B[0m name "+ "\u001B[38;5;206m" + name +
               "  \u001B[0m age " + "\u001B[38;5;206m" + age + "\u001B[0m";
    }
}
