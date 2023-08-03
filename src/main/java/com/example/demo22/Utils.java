package com.example.demo22;

import java.util.List;
import java.util.Objects;

public class Utils {
    public static int indexOfHuman(String name, List<Human> people) {
        for (int i = 0; i < people.size(); i++) {
            if (Objects.equals(people.get(i).getName(), name)) {
                return i;
            }
        }
        return -1;
    }
}