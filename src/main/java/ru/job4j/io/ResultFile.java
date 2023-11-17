package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            out.write("Hello world!".getBytes());
            out.write(System.lineSeparator().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
