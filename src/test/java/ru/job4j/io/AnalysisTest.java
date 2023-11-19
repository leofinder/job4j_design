package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {

    @Test
    void whenOneDowntime(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("server.log").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("300 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        File target = tempDir.resolve("target.csv").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                rsl.append(line);
                rsl.append(System.lineSeparator());
            }
        }
        StringBuilder expected = new StringBuilder();
        expected.append("10:57:01;10:59:01;");
        expected.append(System.lineSeparator());
        expected.append("11:01:02;11:02:02;");
        expected.append(System.lineSeparator());

        assertThat(expected.toString()).isEqualTo(rsl.toString());
    }

    @Test
    void whenTwoDowntime(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("server.log").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("300 11:02:02");
        }
        File target = tempDir.resolve("target.csv").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                rsl.append(line);
                rsl.append(System.lineSeparator());
            }
        }
        StringBuilder expected = new StringBuilder();
        expected.append("10:57:01;11:02:02;");
        expected.append(System.lineSeparator());

        assertThat(expected.toString()).isEqualTo(rsl.toString());
    }
}