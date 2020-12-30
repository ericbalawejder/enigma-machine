package com.machine.enigma;

import java.util.stream.Stream;

class EnigmaMachine {

    private final Rotor r1;
    private final Rotor r2;
    private final Rotor r3;
    private final Reflector reflector;

    public static void main(String[] args) {
        Rotor r1 = new Rotor("QWERTYUIOPLKJHGFDSAZXCVBNM");
        Rotor r2 = new Rotor("ZAQWSXCDERFVBGTYHNMJUIKLOP");
        Rotor r3 = new Rotor("PLOKMIJNUHBYGVTFCRDXESZWAQ");
        Reflector rf = new Reflector("NPKMSLZTWQCFDAVBJYEHXOIURG");
        EnigmaMachine enigmaMachine = new EnigmaMachine(r1, r2, r3, rf);
        System.out.println(enigmaMachine.encodeCharacter('A'));
    }

    EnigmaMachine(Rotor r1, Rotor r2, Rotor r3, Reflector rf) {
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.reflector = rf;
    }

    String encodeLine(String sequence) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < sequence.length(); i++) {
            if (Character.isAlphabetic(sequence.charAt(i))
                    && Character.isUpperCase(sequence.charAt((i)))) {
                stringBuilder.append(encodeCharacter(sequence.charAt(i)));
                incrementRotors();
            } else {
                stringBuilder.append(sequence.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    void setRotors(int a, int b, int c) {
        r1.setRotorPosition(a);
        r2.setRotorPosition(b);
        r3.setRotorPosition(c);
    }

    void incrementRotors() {
        if (r1.increment()) {
            if (r2.increment()) {
                r3.increment();
            }
        }
    }

    public Rotor getR1() {
        return r1;
    }

    public Rotor getR2() {
        return r2;
    }

    public Rotor getR3() {
        return r3;
    }

    public Reflector getReflector() {
        return reflector;
    }

    private char encodeCharacter(char c) {
        return Stream.of(c)
                .map(r1::encodeLeftToRight)
                .map(r2::encodeLeftToRight)
                .map(r3::encodeLeftToRight)
                .map(reflector::reflect)
                .map(r3::encodeRightToLeft)
                .map(r2::encodeRightToLeft)
                .map(r1::encodeRightToLeft)
                .findFirst()
                .get();
    }

}
