package com.machine.enigma;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class ReadFile {

    private final String path;

    ReadFile(String filePath) {
        this.path = filePath;
    }

    String read(int numberOfLines) throws IOException {
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

    String read() throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

}
