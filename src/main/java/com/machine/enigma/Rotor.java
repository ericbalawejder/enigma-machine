package com.machine.enigma;

import java.util.Arrays;

class Rotor {

    static final int MAX = 26;
    static final int A = 65;
    private int position;
    private final char[] leftToRightWiring = new char[MAX];
    private final char[] rightToLeftWiring = new char[MAX];

    Rotor(String sequence) {
        for (int i = 0; i < MAX; i++) {
            leftToRightWiring[i] = sequence.charAt(i);
            rightToLeftWiring[sequence.charAt(i) - A] = (char) (A + i);
        }
    }

    boolean increment() {
        position++;
        if (position == MAX) {
            position = 0;
            return true;
        }
        return false;
    }

    char encodeLeftToRight(char c) {
        return leftToRightWiring[((c - A) + position) % MAX];
    }

    char encodeRightToLeft(char c) {
        char character = rightToLeftWiring[c - A];
        character -= position;

        if (character < A) {
            character += MAX;
        }
        return character;
    }

    void setRotorPosition(int n) {
        this.position = n % MAX;
    }

    int getRotorPosition() {
        return this.position;
    }

    char[] getLeftToRightWiring() {
        return Arrays.copyOf(leftToRightWiring, leftToRightWiring.length);
    }

    char[] getRightToLeftWiring() {
        return Arrays.copyOf(rightToLeftWiring, rightToLeftWiring.length);
    }
}
