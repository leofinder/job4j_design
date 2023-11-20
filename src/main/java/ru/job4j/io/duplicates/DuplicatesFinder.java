package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), visitor);
        Map<FileProperty, List<Path>> duplicates = visitor.getDuplicates();
        for (Map.Entry<FileProperty, List<Path>> entry : duplicates.entrySet()) {
            System.out.println(entry.getKey());
            for (Path path : entry.getValue()) {
                System.out.printf("\t%s%n", path.toAbsolutePath());
            }
        }
    }
}
