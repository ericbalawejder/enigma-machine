package com.machine.enigma.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFile {

    public static String read(String path, int numberOfLines) throws IOException {
        final BufferedReader bufferReader = new BufferedReader(new FileReader(path));
        final StringBuilder file = new StringBuilder();
        String characterData;
        int fileLine = 0;

        while ((characterData = bufferReader.readLine()) != null && fileLine <= numberOfLines) {
            file.append(characterData);
            file.append("\n");
            fileLine++;
        }
        bufferReader.close();
        return file.toString();
    }

    public static String read(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    public static List<String> readMachineSettings(String path) {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            final String firstLine = stream.map(String::valueOf)
                    .findFirst()
                    .orElseThrow();

            return Arrays.stream(firstLine.split(" "))
                    .collect(Collectors.toUnmodifiableList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static String readFileContents(String path) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        final StringBuilder file = new StringBuilder();
        bufferedReader.readLine();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            file.append(line);
            file.append("\n");
        }
        bufferedReader.close();
        return file.toString();
    }

}
