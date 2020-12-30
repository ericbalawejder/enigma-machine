package com.machine.enigma;

class EnigmaMachine {

    private final Rotor r1;
    private final Rotor r2;
    private final Rotor r3;
    private final Reflector reflector;

    EnigmaMachine(Rotor r1, Rotor r2, Rotor r3, Reflector rf) {
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.reflector = rf;
    }

    private char encodeCharacter(char character) {
        char currentCharacter;

        currentCharacter = r1.encodeLeftToRight(character);
        currentCharacter = r2.encodeLeftToRight(currentCharacter);
        currentCharacter = r3.encodeLeftToRight(currentCharacter);
        currentCharacter = reflector.reflect(currentCharacter);
        currentCharacter = r3.encodeRightToLeft(currentCharacter);
        currentCharacter = r2.encodeRightToLeft(currentCharacter);

        return r1.encodeRightToLeft(currentCharacter);
    }

    String encodeLine(String sequence) {
        StringBuilder stringResult = new StringBuilder();

        for (int i = 0; i < sequence.length(); i++) {
            if (Character.isAlphabetic(sequence.charAt(i)) && Character.isUpperCase(sequence.charAt((i)))) {
                stringResult.append(encodeCharacter(sequence.charAt(i)));
                incrementRotors();
            } else {
                stringResult.append(sequence.charAt(i));
            }
        }
        return stringResult.toString();
    }

    void setRotors(int a, int b, int c) {
        r1.setRotorPosition(a);
        r2.setRotorPosition(b);
        r3.setRotorPosition(c);
    }

    void incrementRotors() {
        if (r1.inc()) {
            if (r2.inc()) {
                r3.inc();
            }
        }
    }

}
