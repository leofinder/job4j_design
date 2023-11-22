package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                File file = source.toFile();
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
                zip.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName values = ArgsName.of(args);
        validateArgs(values);
        String exclude = values.get("e");
        List<Path> sources = Search.search(Path.of(values.get("d")), p -> !p.toFile().getName().endsWith(exclude));
        Zip zip = new Zip();
        zip.packFiles(sources, new File(values.get("o")));
    }

    private static void validateArgs(ArgsName values) {
        File directory = new File(values.get("d"));
        if (!directory.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", directory.getAbsoluteFile()));
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", directory.getAbsoluteFile()));
        }
        String ext = values.get("e");
        if (!ext.startsWith(".")) {
            throw new IllegalArgumentException("Not dot in the file extension");
        }
        if (ext.length() < 2) {
            throw new IllegalArgumentException("Length extension is less than 2");
        }
        String output = values.get("o");
        if (!output.endsWith(".zip")) {
            throw new IllegalArgumentException(String.format("'%s' is not ends with '.zip'", output));
        }
    }
}
