package com.machine.enigma;

import java.io.IOException;

class Decrypt {

    public static void main(String[] args) {
        Rotor r1 = new Rotor("QWERTYUIOPLKJHGFDSAZXCVBNM");
        Rotor r2 = new Rotor("ZAQWSXCDERFVBGTYHNMJUIKLOP");
        Rotor r3 = new Rotor("PLOKMIJNUHBYGVTFCRDXESZWAQ");
        Reflector rf = new Reflector("NPKMSLZTWQCFDAVBJYEHXOIURG");

        EnigmaMachine enigmaMachine = new EnigmaMachine(r1, r2, r3, rf);

        // manually set rotors with FindRotorSettings.java result
        enigmaMachine.setRotors(18, 19, 14);

        try {
            String fileName = "src/main/resources/encrypted.txt";
            ReadFile file = new ReadFile(fileName);
            String encryptedFile = file.readFile();
            String decryptResult = enigmaMachine.encodeLine(encryptedFile);

            System.out.println(decryptResult);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
