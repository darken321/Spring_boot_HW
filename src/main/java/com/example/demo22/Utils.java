package com.example.demo22;

import java.util.List;

public class Utils {
    public static int indexOfHuman(String name, List<Human> people){
        int index = -1;
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getName().equals(name)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
