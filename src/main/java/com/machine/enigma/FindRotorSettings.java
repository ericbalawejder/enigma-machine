package com.machine.enigma;

import java.io.IOException;

class FindRotorSettings {

    public static void main(String[] args) {
        Rotor r1 = new Rotor("QWERTYUIOPLKJHGFDSAZXCVBNM");
        Rotor r2 = new Rotor("ZAQWSXCDERFVBGTYHNMJUIKLOP");
        Rotor r3 = new Rotor("PLOKMIJNUHBYGVTFCRDXESZWAQ");
        Reflector rf = new Reflector("NPKMSLZTWQCFDAVBJYEHXOIURG");
        EnigmaMachine enigmaMachine = new EnigmaMachine(r1, r2, r3, rf);
        English english = new English();
        String fileName = "src/main/resources/encrypted.txt";

        try {
            final int numberOfLinesToSample = 2;

            ReadFile file = new ReadFile(fileName);
            String encryptedFile = file.readFile(numberOfLinesToSample);

            for (int i = 0; i < Rotor.MAX; i++) {
                for (int j = 0; j < Rotor.MAX; j++) {
                    for (int k = 0; k < Rotor.MAX; k++) {
                        enigmaMachine.setRotors(i, j, k);
                        String result = enigmaMachine.encodeLine(encryptedFile);
                        english.countAllLetters(result);
                        final double multipler = 1.6;
                        final int errorsAllowedInDecryption = 15;

                        if (english.getErrorCount(multipler) <= errorsAllowedInDecryption) {
                            System.out.printf("Rotor 1: %d\nRotor 2: %d\nRotor 3: %d\n", i, j, k);
                            System.out.println(result);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
