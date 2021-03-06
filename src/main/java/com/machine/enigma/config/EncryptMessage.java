package com.machine.enigma.config;

import com.machine.enigma.cipher.EnigmaMachine;
import com.machine.enigma.cipher.Reflector;
import com.machine.enigma.cipher.Rotor;
import com.machine.enigma.encryption.Encrypt;
import com.machine.enigma.io.ReadFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class EncryptMessage {

    public static void main(String[] args) throws IOException {

        Rotor r1 = new Rotor("JGDQOXUSCAMIFRVTPNEWKBLZYH");
        Rotor r2 = new Rotor("NTZPSFBOKMWRCJDIVLAEYUXHGQ");
        Rotor r3 = new Rotor("JVIUBHTCDYAKEQZPOSGXNRMWFL");
        Reflector rf = new Reflector("QYHOGNECVPUZTFDJAXWMKISRBL");
        EnigmaMachine enigmaMachine = new EnigmaMachine(r1, r2, r3, rf);

        final String file = ReadFile.read("src/main/resources/unencrypted.txt");

        final Encrypt encrypt = new Encrypt();
        final String encryptedFile = encrypt.encryptFile(enigmaMachine, file);

        System.setOut(new PrintStream(new FileOutputStream("src/main/resources/encrypted.txt")));
        System.out.println(encryptedFile);
    }

}
