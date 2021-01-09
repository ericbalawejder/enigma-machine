package com.machine.enigma.cipher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RotorTest {

    private Rotor rotor;

    @BeforeEach
    void setUp() {
        rotor = new Rotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }

    @Test
    void incrementIsFalse() {
        IntStream.rangeClosed(1, 19)
                .forEach(i -> rotor.increment());

        assertFalse(rotor.increment());
    }

    @Test
    void incrementIsTrue() {
        IntStream.rangeClosed(1, 25)
                .forEach(i -> rotor.increment());

        assertTrue(rotor.increment());
    }

    @Test
    void encodeLeftToRightWithoutRotor() {
        char expected = 'A';

        char actual = rotor.encodeLeftToRight('A');

        assertEquals(expected, actual);
    }

    @Test
    void encodeLeftToRightWithRotorAToD() {
        char expected = 'D';

        IntStream.rangeClosed(1, 3)
                .forEach(i -> rotor.increment());

        char actual = rotor.encodeLeftToRight('A');

        assertEquals(expected, actual);
    }

    @Test
    void encodeLeftToRightWithRotorAToZ() {
        char expected = 'Z';

        IntStream.rangeClosed(1, 25)
                .forEach(i -> rotor.increment());

        char actual = rotor.encodeLeftToRight('A');

        assertEquals(expected, actual);
    }

    @Test
    void encodeRightToLeftWithoutRotor() {
        char expected = 'A';

        char actual = rotor.encodeRightToLeft('A');

        assertEquals(expected, actual);
    }

    @Test
    void encodeRightToLeftWithRotorDToA() {
        char expected = 'A';

        IntStream.rangeClosed(1, 3)
                .forEach(i -> rotor.increment());

        char actual = rotor.encodeRightToLeft('D');

        assertEquals(expected, actual);
    }

    @Test
    void encodeRightToLeftWithRotorZToA() {
        char expected = 'A';

        IntStream.rangeClosed(1, 25)
                .forEach(i -> rotor.increment());

        char actual = rotor.encodeRightToLeft('Z');

        assertEquals(expected, actual);
    }

    @Test
    void encodeRightToLeftWithRotorLoopZToY() {
        char expected = 'Y';

        IntStream.rangeClosed(1, 27)
                .forEach(i -> rotor.increment());

        char actual = rotor.encodeRightToLeft('Z');

        assertEquals(expected, actual);
    }

}