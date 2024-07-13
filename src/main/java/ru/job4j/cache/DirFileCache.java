package ru.job4j.cache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String value = null;
        Path path = Path.of(cachingDir, key);
        if (!Files.exists(path)) {
            System.out.printf("Файл %s не существует\n", key);
        } else if (!Files.isRegularFile(path)) {
            System.out.printf("%s не является файлом\n", path);
        } else if (!Files.isReadable(path)) {
            System.out.printf("Файл %s не доступен для чтения\n", key);
        } else {
            try {
                value = Files.readString(path);
                put(key, value);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }
}