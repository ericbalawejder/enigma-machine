package com.machine.enigma;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class FindRotorSettings {

    List<Integer> get(EnigmaMachine enigmaMachine, String fileName) throws IOException {
        ReadFile file = new ReadFile(fileName);
        English english = new English();
        String encryptedFile = file.readFile();

        for (int i = 0; i < Rotor.MAX; i++) {
            for (int j = 0; j < Rotor.MAX; j++) {
                for (int k = 0; k < Rotor.MAX; k++) {
                    enigmaMachine.setRotors(i, j, k);
                    String decrypt = enigmaMachine.encodeLine(encryptedFile);
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

    List<Integer> get(EnigmaMachine enigmaMachine, String fileName, int numberOfLinesToSample) throws IOException {
        ReadFile file = new ReadFile(fileName);
        English english = new English();
        String encryptedFile = file.readFile(numberOfLinesToSample);

        for (int i = 0; i < Rotor.MAX; i++) {
            for (int j = 0; j < Rotor.MAX; j++) {
                for (int k = 0; k < Rotor.MAX; k++) {
                    enigmaMachine.setRotors(i, j, k);
                    String decrypt = enigmaMachine.encodeLine(encryptedFile);
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
