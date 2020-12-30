package com.machine.enigma;

import java.util.stream.IntStream;

class Reflector {

    private static final int MAX = Rotor.MAX;
    private static final int A = Rotor.A;
    private final char[] reflector = new char[MAX];

    Reflector(String sequence) {
        IntStream.range(0, MAX)
                .forEach(i -> reflector[i] = sequence.charAt(i));
    }

    char reflect(char c) {
        return reflector[c - A];
    }

}
