package com.machine.enigma;

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

    boolean inc() {
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
        c = rightToLeftWiring[c - A];
        c -= position;

        if (c < A) {
            c += MAX;
        }
        return c;
    }

    void setRotorPosition(int n) {
        this.position = n % MAX;
    }

}
