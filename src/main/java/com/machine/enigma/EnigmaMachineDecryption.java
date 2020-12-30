package com.machine.enigma;

import java.io.IOException;

class EnigmaMachineDecryption {

    public static void main(String[] args) {
        Rotor r1 = new Rotor("QWERTYUIOPLKJHGFDSAZXCVBNM");
        Rotor r2 = new Rotor("ZAQWSXCDERFVBGTYHNMJUIKLOP");
        Rotor r3 = new Rotor("PLOKMIJNUHBYGVTFCRDXESZWAQ");
        Reflector rf = new Reflector("NPKMSLZTWQCFDAVBJYEHXOIURG");
        EnigmaMachine enigmaMachine = new EnigmaMachine(r1, r2, r3, rf);
        English english = new English();
        String fileName = "src/main/resources/encrypted.txt";

        try {
            ReadFile file = new ReadFile(fileName);
            String encryptedFile = file.readFile();

            for (int i = 0; i < Rotor.MAX; i++) {
                for (int j = 0; j < Rotor.MAX; j++) {
                    for (int k = 0; k < Rotor.MAX; k++) {
                        enigmaMachine.setRotors(i, j, k);
                        String decrypt = enigmaMachine.encodeLine(encryptedFile);
                        english.countAllLetters(decrypt);

                        final double errorDevianceMultiplier = 1.5;
                        final int numberOfErrorsAllowed = 10;

                        if (english.getErrorCount(errorDevianceMultiplier) <= numberOfErrorsAllowed) {
                            System.out.printf("Rotor 1: %d%nRotor 2: %d%nRotor 3: %d%n", i, j, k);
                            System.out.println(decrypt);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
