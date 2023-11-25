package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        List<List<String>> inputList;
        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")))) {
            inputList = toList(scanner, delimiter);
        }
        List<Integer> order = indexOrder(inputList.get(0), argsName.get("filter"));
        List<String> outputList = convert(inputList, order, delimiter);
        if ("stdout".equals(out)) {
            outputList.forEach(System.out::println);
        } else {
            try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
                outputList.forEach(writer::println);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);
    }

    private static void validate(ArgsName argsName) {
        File file = new File(argsName.get("path"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException(String.format("Not file %s", file.getAbsoluteFile()));
        }
    }

    private static List<List<String>> toList(Scanner scanner, String delimiter) {
        List<List<String>> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            list.add(Arrays.asList(scanner.nextLine().split(delimiter)));
        }
        return list;
    }

    private static List<Integer> indexOrder(List<String> header, String filter) {
        String[] filters = filter.split(",");
        List<Integer> order = new ArrayList<>();
        for (String element : filters) {
            int index = header.indexOf(element);
            if (index != -1) {
                order.add(index);
            }
        }
        return order;
    }

    private static List<String> convert(List<List<String>> list, List<Integer> order, String delimiter) {
        List<String> output = new ArrayList<>();
        for (List<String> row : list) {
            StringJoiner sj = new StringJoiner(delimiter);
            for (Integer index : order) {
                sj.add(row.get(index));
            }
            output.add(sj.toString());
        }
        return output;
    }
}
