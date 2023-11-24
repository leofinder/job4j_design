package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        List<String> phrases = readPhrases();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        boolean activeMode = true;
        Random random = new Random();
        String message = "";
        Scanner input = new Scanner(System.in);
        while (!OUT.equals(message)) {
            message = input.nextLine();
            if (STOP.equals(message) || CONTINUE.equals(message)) {
                activeMode = CONTINUE.equals(message);
            }
            log.add(String.format("%s - %s", LocalDateTime.now().format(formatter), message));
            if (activeMode) {
                String phrase = phrases.get(random.nextInt(phrases.size() - 1));
                log.add(String.format("%s - %s", LocalDateTime.now().format(formatter), phrase));
                System.out.println(phrase);
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            br.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/bot_log.txt", "./data/random_phrase.txt");
        cc.run();
    }
}
