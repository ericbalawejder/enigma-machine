package com.machine.enigma;

import java.io.IOException;

class EnigmaMachineDecryption {

    public static void main(String[] args) throws IOException {
        FindRotorSettings findRotorSettings = new FindRotorSettings();
        Rotor r1 = new Rotor("QWERTYUIOPLKJHGFDSAZXCVBNM");
        Rotor r2 = new Rotor("ZAQWSXCDERFVBGTYHNMJUIKLOP");
        Rotor r3 = new Rotor("PLOKMIJNUHBYGVTFCRDXESZWAQ");
        Reflector rf = new Reflector("NPKMSLZTWQCFDAVBJYEHXOIURG");
        EnigmaMachine enigmaMachine = new EnigmaMachine(r1, r2, r3, rf);

        System.out.println(findRotorSettings
                .get(enigmaMachine, "src/main/resources/encrypted.txt", 2));
    }

}
