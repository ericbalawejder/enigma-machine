package com.machine.enigma.config;

import com.machine.enigma.cipher.EnigmaMachine;
import com.machine.enigma.encryption.Encrypt;
import com.machine.enigma.io.ReadFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CreateMessage {

    private static final Map<UUID, EnigmaMachine> MACHINE_SETTINGS = new HashMap<>();

    public static void main(String[] args) throws IOException {

        final EnigmaMachine enigmaMachine = new EnigmaMachine();
        enigmaMachine.setRotorsRandom();
        MACHINE_SETTINGS.put(enigmaMachine.getUuid(), enigmaMachine);

        final ReadFile readFile = new ReadFile("src/main/resources/ring.txt");
        final String file = readFile.read().toUpperCase();

        final Encrypt encrypt = new Encrypt();
        final String encryptedFile = encrypt.encryptFile(enigmaMachine, file);

        System.setOut(new PrintStream(new FileOutputStream("src/main/resources/encrypt-file.txt")));
        System.out.println(enigmaMachine.getUuid());
        System.out.println();
        System.out.println(encryptedFile);
    }

}
