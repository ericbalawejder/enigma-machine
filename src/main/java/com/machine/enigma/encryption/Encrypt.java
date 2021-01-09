package com.machine.enigma.encryption;

import com.machine.enigma.cipher.EnigmaMachine;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Encrypt {

    public String encryptFile(EnigmaMachine enigmaMachine, String file) {
        return Stream.of(file)
                .map(enigmaMachine::encodeLine)
                .collect(Collectors.joining());
    }

}
