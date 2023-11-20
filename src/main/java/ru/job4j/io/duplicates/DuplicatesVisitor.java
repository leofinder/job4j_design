package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        files.computeIfAbsent(fileProperty, k -> new ArrayList<>()).add(file);
        return super.visitFile(file, attrs);
    }

    public Map<FileProperty, List<Path>> getDuplicates() {
        return files.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void printDuplicates() {
        for (Map.Entry<FileProperty, List<Path>> entry : getDuplicates().entrySet()) {
            System.out.println(entry.getKey());
            for (Path path : entry.getValue()) {
                System.out.printf("\t%s%n", path.toAbsolutePath());
            }
        }
    }
}
