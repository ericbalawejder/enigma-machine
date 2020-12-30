package com.machine.enigma;

class Reflector {

    static final int MAX = Rotor.MAX;
    static final int A = Rotor.A;
    private static final char[] reflector = new char[MAX];

    Reflector(String sequence) {
        for (int i = 0; i < MAX; i++) {
            reflector[i] = sequence.charAt(i);
        }
    }

    char reflect(char c) {
        return reflector[c - A];
    }

}
