package ru.job4j.io;

import java.io.*;

public class Analysis {

    private static boolean isErrorStatus(String status) {
        return "400".equals(status) || "500".equals(status);
    }

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            boolean isDowntime = false;
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] elements = line.split(" ");
                if (!isDowntime && isErrorStatus(elements[0])) {
                    isDowntime = true;
                    writer.write(String.format("%s;", elements[1]));
                } else if (isDowntime & !isErrorStatus(elements[0])) {
                    isDowntime = false;
                    writer.write(String.format("%s;", elements[1]));
                    writer.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
