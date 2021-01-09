package com.machine.enigma.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFile {

    private final String path;

    public ReadFile(String filePath) {
        this.path = filePath;
    }

    public String read(int numberOfLines) throws IOException {
        final FileReader fileReader = new FileReader(path);
        final BufferedReader bufferReader = new BufferedReader(fileReader);
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

    public String read() throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

}
