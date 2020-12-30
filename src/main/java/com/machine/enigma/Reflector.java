package com.machine.enigma;

import java.util.Arrays;
import java.util.stream.IntStream;

class Reflector {

    private static final int MAX = Rotor.MAX;
    private static final int A = Rotor.A;
    private final char[] leftToRightWiring = new char[MAX];

    Reflector(String sequence) {
        IntStream.range(0, MAX)
                .forEach(i -> leftToRightWiring[i] = sequence.charAt(i));
    }

    char reflect(char c) {
        return leftToRightWiring[c - A];
    }

    char[] getLeftToRightWiring() {
        return Arrays.copyOf(leftToRightWiring, leftToRightWiring.length);
    }

}
