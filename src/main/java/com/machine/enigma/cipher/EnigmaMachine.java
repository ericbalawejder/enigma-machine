package com.machine.enigma.cipher;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnigmaMachine {

    private final Rotor r1;
    private final Rotor r2;
    private final Rotor r3;
    private final Reflector reflector;
    private final UUID uuid;

    public EnigmaMachine() {
        this.r1 = new Rotor(randomLetters(), randomNumbers());
        this.r2 = new Rotor(randomLetters(), randomNumbers());
        this.r3 = new Rotor(randomLetters(), randomNumbers());
        this.reflector = new Reflector(randomLetters());
        this.uuid = UUID.randomUUID();
    }

    public EnigmaMachine(Rotor r1, Rotor r2, Rotor r3, Reflector reflector) {
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.reflector = reflector;
        this.uuid = UUID.randomUUID();
    }

    public EnigmaMachine(String uuid, String r1, String r2, String r3, String reflector,
                         int position1, int position2, int position3) {

        this.r1 = new Rotor(r1, position1);
        this.r2 = new Rotor(r2, position2);
        this.r3 = new Rotor(r3, position3);
        this.reflector = new Reflector(reflector);
        this.uuid = UUID.fromString(uuid);
    }

    public String encodeLine(String sequence) {
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

    public void setRotors(int a, int b, int c) {
        r1.setRotorPosition(a);
        r2.setRotorPosition(b);
        r3.setRotorPosition(c);
    }

    public void setRotorsRandom() {
        r1.setRotorPosition(randomNumbers());
        r2.setRotorPosition(randomNumbers());
        r3.setRotorPosition(randomNumbers());
    }

    public void incrementRotors() {
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

    public UUID getUuid() {
        return uuid;
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

    private String randomLetters() {
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final List<Character> sequence = alphabet.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());

        Collections.shuffle(sequence, new SecureRandom());

        return sequence.stream()
                .map(String::valueOf)
                .collect(Collector.of(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append,
                        StringBuilder::toString));
    }

    private int randomNumbers() {
        final Random random = new SecureRandom();
        return random.ints(0, 26)
                .limit(1)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnigmaMachine that = (EnigmaMachine) o;
        return r1.equals(that.r1) && r2.equals(that.r2) && r3.equals(that.r3)
                && reflector.equals(that.reflector) && uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(r1, r2, r3, reflector, uuid);
    }

    @Override
    public String toString() {
        return uuid + " "
                + r1.toString() + " "
                + r2.toString() + " "
                + r3.toString() + " "
                + reflector.toString() + " "
                + r1.getRotorPosition() + " "
                + r2.getRotorPosition() + " "
                + r3.getRotorPosition();
    }

}
