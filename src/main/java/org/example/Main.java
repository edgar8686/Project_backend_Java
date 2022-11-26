package org.example;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = new File("resources/properties");
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);
    }
}