package com.codecool.UI;

import java.util.Map;

public class View {

    public void print(String toPrint) {
        System.out.println(toPrint);
    }

    public void print(String[] toPrint) {
        for (String string : toPrint) {
            System.out.println(string);
        }
    }

    public void printWithSleep(String text, int time){
        print(text);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public <K, V> void printMap(Map<K, V> map) {
        map.forEach((k, v) -> System.out.println("" + k + ": " + v));
    }
}
