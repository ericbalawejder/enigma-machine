package com.machine.enigma.cipher;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Reflector {

    private static final int MAX = Rotor.MAX;
    private static final int A = Rotor.A;
    private final char[] leftToRightWiring;

    public Reflector(String sequence) {
        this.leftToRightWiring = new char[MAX];
        IntStream.range(0, MAX)
                .forEach(i -> leftToRightWiring[i] = sequence.charAt(i));
    }

    public Reflector(Reflector reflector) {
        this.leftToRightWiring = reflector.getLeftToRightWiring();
    }

    public char reflect(char c) {
        return leftToRightWiring[c - A];
    }

    public char[] getLeftToRightWiring() {
        return Arrays.copyOf(leftToRightWiring, leftToRightWiring.length);
    }

    @Override
    public String toString() {
        return IntStream.range(0, leftToRightWiring.length)
                .mapToObj(index -> leftToRightWiring[index])
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

}
