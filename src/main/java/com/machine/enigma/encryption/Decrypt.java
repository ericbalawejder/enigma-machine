package com.machine.enigma.encryption;

import com.machine.enigma.io.ReadFile;
import com.machine.enigma.language.English;
import com.machine.enigma.cipher.EnigmaMachine;
import com.machine.enigma.cipher.Rotor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Decrypt {

    public static void main(String[] args) throws IOException {

        final String fileName = "src/main/resources/encrypt-file.txt";

        final Decrypt user = new Decrypt();

        //final List<Integer> rotorSettings = user.findRotorSettings(enigmaMachine, fileName, 10);

        //final String unencryptedFile = user.decryptFile(enigmaMachine, rotorSettings, fileName);

        //System.setOut(new PrintStream(new FileOutputStream("src/main/resources/test.txt")));
        //System.out.println(unencryptedFile);
    }

    String decryptFile(EnigmaMachine enigmaMachine, List<Integer> rotorSettings, String fileName)
            throws IOException {
        if (rotorSettings.isEmpty()) {
            throw new RuntimeException("No rotor settings satisfy language constraints");
        }
        enigmaMachine.setRotors(rotorSettings.get(0), rotorSettings.get(1), rotorSettings.get(2));
        final ReadFile file = new ReadFile(fileName);
        return enigmaMachine.encodeLine(file.read());
    }

    List<Integer> findRotorSettings(EnigmaMachine enigmaMachine, String fileName)
            throws IOException {

        final ReadFile file = new ReadFile(fileName);
        final English english = new English();
        final String encryptedFile = file.read();

        for (int i = 0; i < Rotor.MAX; i++) {
            for (int j = 0; j < Rotor.MAX; j++) {
                for (int k = 0; k < Rotor.MAX; k++) {
                    enigmaMachine.setRotors(i, j, k);
                    final String decrypt = enigmaMachine.encodeLine(encryptedFile);
                    english.countAllLetters(decrypt);

                    final double multiplier = 1.6;
                    final int errorsAllowedInDecryption = 15;
                    if (english.getErrorCount(multiplier) <= errorsAllowedInDecryption) {
                        return List.of(i, j, k);
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    List<Integer> findRotorSettings(EnigmaMachine enigmaMachine, String fileName, int numberOfLinesToSample)
            throws IOException {

        final ReadFile file = new ReadFile(fileName);
        final English english = new English();
        final String encryptedFile = file.read(numberOfLinesToSample);

        for (int i = 0; i < Rotor.MAX; i++) {
            for (int j = 0; j < Rotor.MAX; j++) {
                for (int k = 0; k < Rotor.MAX; k++) {
                    enigmaMachine.setRotors(i, j, k);
                    final String decrypt = enigmaMachine.encodeLine(encryptedFile);
                    english.countAllLetters(decrypt);

                    final double multiplier = 1.6;
                    final int errorsAllowedInDecryption = 15;
                    if (english.getErrorCount(multiplier) <= errorsAllowedInDecryption) {
                        return List.of(i, j, k);
                    }
                }
            }
        }
        return new ArrayList<>();
    }

}
