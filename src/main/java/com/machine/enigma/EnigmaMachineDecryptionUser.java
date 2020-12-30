package com.machine.enigma;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class EnigmaMachineDecryptionUser {

    public static void main(String[] args) throws IOException {
        Rotor r1 = new Rotor("QWERTYUIOPLKJHGFDSAZXCVBNM");
        Rotor r2 = new Rotor("ZAQWSXCDERFVBGTYHNMJUIKLOP");
        Rotor r3 = new Rotor("PLOKMIJNUHBYGVTFCRDXESZWAQ");
        Reflector rf = new Reflector("NPKMSLZTWQCFDAVBJYEHXOIURG");
        EnigmaMachine enigmaMachine = new EnigmaMachine(r1, r2, r3, rf);

        final String fileName = "src/main/resources/encrypted.txt";

        EnigmaMachineDecryptionUser user = new EnigmaMachineDecryptionUser();

        final List<Integer> rotorSettings = user.findRotorSettings(enigmaMachine, fileName, 2);
        System.out.println(user.decryptFile(enigmaMachine, rotorSettings, fileName));
    }

    String decryptFile(EnigmaMachine enigmaMachine, List<Integer> rotorSettings, String fileName)
            throws IOException {
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
