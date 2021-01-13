package com.machine.enigma.cipher;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Rotor {

    public static final int MAX = 26;
    public static final int A = 65;
    private int position;
    private final char[] leftToRightWiring;
    private final char[] rightToLeftWiring;

    public Rotor(String sequence) {
        this.leftToRightWiring = new char[MAX];
        this.rightToLeftWiring = new char[MAX];
        for (int i = 0; i < MAX; i++) {
            leftToRightWiring[i] = sequence.charAt(i);
            rightToLeftWiring[sequence.charAt(i) - A] = (char) (A + i);
        }
    }

    public Rotor(String sequence, int position) {
        this.leftToRightWiring = new char[MAX];
        this.rightToLeftWiring = new char[MAX];
        this.position = position;
        for (int i = 0; i < MAX; i++) {
            leftToRightWiring[i] = sequence.charAt(i);
            rightToLeftWiring[sequence.charAt(i) - A] = (char) (A + i);
        }
    }

    public Rotor(Rotor rotor) {
        this.position = rotor.getRotorPosition();
        this.leftToRightWiring = rotor.getLeftToRightWiring();
        this.rightToLeftWiring = rotor.getRightToLeftWiring();
    }

    public boolean increment() {
        position++;
        if (position == MAX) {
            position = 0;
            return true;
        }
        return false;
    }

    public char encodeLeftToRight(char c) {
        return leftToRightWiring[((c - A) + position) % MAX];
    }

    public char encodeRightToLeft(char c) {
        char character = rightToLeftWiring[c - A];
        character -= position;

        if (character < A) {
            character += MAX;
        }
        return character;
    }

    public void setRotorPosition(int n) {
        this.position = n % MAX;
    }

    public int getRotorPosition() {
        return this.position;
    }

    public char[] getLeftToRightWiring() {
        return Arrays.copyOf(leftToRightWiring, leftToRightWiring.length);
    }

    public char[] getRightToLeftWiring() {
        return Arrays.copyOf(rightToLeftWiring, rightToLeftWiring.length);
    }

    @Override
    public String toString() {
        return IntStream.range(0, leftToRightWiring.length)
                .mapToObj(index -> leftToRightWiring[index])
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

}
