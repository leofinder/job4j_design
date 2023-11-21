package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        checkValue(key);
        return values.get(key);
    }

    private void checkValue(String key) {
        if (values.get(key) == null) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
    }

    private void parse(String[] args) {
        for (String arg : args) {
            String[] entry = arg.substring(1).split("=", 2);
            values.put(entry[0], entry[1]);
        }
    }

    public static ArgsName of(String[] args) {
        validate(args);
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }

    private static void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String arg : args) {
            if (arg.isEmpty()) {
                throw new IllegalArgumentException("Argument is empty");
            }
            if (arg.charAt(0) != '-') {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not start with a '-' character", arg));
            }
            String[] entry = arg.substring(1).split("=", 2);
            if (entry.length == 1) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain an equal sign", arg));
            }
            if (entry[0].isEmpty()) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a key", arg));
            }
            if (entry[1].isEmpty()) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a value", arg));
            }
        }
    }
}
