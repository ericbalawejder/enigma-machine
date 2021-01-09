package com.machine.enigma;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnigmaMachineTest {

    private EnigmaMachine enigmaMachine;

    @BeforeEach
    void setUp() {
        Rotor r1 = new Rotor("QWERTYUIOPLKJHGFDSAZXCVBNM");
        Rotor r2 = new Rotor("ZAQWSXCDERFVBGTYHNMJUIKLOP");
        Rotor r3 = new Rotor("PLOKMIJNUHBYGVTFCRDXESZWAQ");
        Reflector rf = new Reflector("NPKMSLZTWQCFDAVBJYEHXOIURG");

        enigmaMachine = new EnigmaMachine(r1, r2, r3, rf);
    }

    @Test
    void testEncodeLine() {
        String expected = "WGJOUNHUPLJRETLGWMNRPIIXUDM";

        enigmaMachine.setRotors(5, 9, 14);
        String actual = enigmaMachine.encodeLine("AAAAAAAAAAAAAAAAAAAAAAAAAAA");

        assertEquals(expected, actual);
    }

    @Test
    void testEncodeLineIsSymmetrical() {
        String expected = "AAAAAAAAAAAAAAAAAAAAAAAAAAA";

        enigmaMachine.setRotors(5, 9, 14);
        String actual = enigmaMachine.encodeLine("WGJOUNHUPLJRETLGWMNRPIIXUDM");

        assertEquals(expected, actual);
    }

    @Test
    void testSetRotors() {
        enigmaMachine.setRotors(17, 4, 9);
        assertEquals(enigmaMachine.getR1().getRotorPosition(), 17);
        assertEquals(enigmaMachine.getR2().getRotorPosition(), 4);
        assertEquals(enigmaMachine.getR3().getRotorPosition(), 9);
    }

    @Test
    void testSetRotorsRandom() {
        enigmaMachine.setRotorsRandom();
        assertTrue(enigmaMachine.getR1().getRotorPosition() >= 0
                && enigmaMachine.getR1().getRotorPosition() < 26);
        assertTrue(enigmaMachine.getR2().getRotorPosition() >= 0
                && enigmaMachine.getR2().getRotorPosition() < 26);
        assertTrue(enigmaMachine.getR3().getRotorPosition() >= 0
                && enigmaMachine.getR3().getRotorPosition() < 26);
    }

    @Test
    void testIncrementRotorsR1() {
        enigmaMachine.setRotors(0, 0, 0);
        enigmaMachine.incrementRotors();
        assertEquals(enigmaMachine.getR1().getRotorPosition(), 1);
        assertEquals(enigmaMachine.getR2().getRotorPosition(), 0);
        assertEquals(enigmaMachine.getR3().getRotorPosition(), 0);
    }

    @Test
    void testIncrementRotorsR2() {
        enigmaMachine.setRotors(25, 0, 0);
        enigmaMachine.incrementRotors();
        assertEquals(enigmaMachine.getR1().getRotorPosition(), 0);
        assertEquals(enigmaMachine.getR2().getRotorPosition(), 1);
        assertEquals(enigmaMachine.getR3().getRotorPosition(), 0);
    }

    @Test
    void testIncrementRotorsR3() {
        enigmaMachine.setRotors(25, 25, 0);
        enigmaMachine.incrementRotors();
        assertEquals(enigmaMachine.getR1().getRotorPosition(), 0);
        assertEquals(enigmaMachine.getR2().getRotorPosition(), 0);
        assertEquals(enigmaMachine.getR3().getRotorPosition(), 1);
    }

    @Test
    void testIncrementRotorsR1R2R3() {
        enigmaMachine.setRotors(25, 25, 25);
        enigmaMachine.incrementRotors();
        assertEquals(enigmaMachine.getR1().getRotorPosition(), 0);
        assertEquals(enigmaMachine.getR2().getRotorPosition(), 0);
        assertEquals(enigmaMachine.getR3().getRotorPosition(), 0);
    }

    @Test
    void testIncrementRotors5Times() {
        enigmaMachine.setRotors(23, 19, 13);

        IntStream.range(0, 5)
                .forEach(e -> enigmaMachine.incrementRotors());

        assertEquals(enigmaMachine.getR1().getRotorPosition(), 2);
        assertEquals(enigmaMachine.getR2().getRotorPosition(), 20);
        assertEquals(enigmaMachine.getR3().getRotorPosition(), 13);
    }

    @Test
    void testIncrementRotors78Times() {
        enigmaMachine.setRotors(2, 21, 13);

        IntStream.range(0, 78)
                .forEach(e -> enigmaMachine.incrementRotors());

        assertEquals(enigmaMachine.getR1().getRotorPosition(), 2);
        assertEquals(enigmaMachine.getR2().getRotorPosition(), 24);
        assertEquals(enigmaMachine.getR3().getRotorPosition(), 13);
    }

    @Test
    void testReflector() {
        char[] expected = "NPKMSLZTWQCFDAVBJYEHXOIURG".toCharArray();

        char[] actual = enigmaMachine.getReflector().getLeftToRightWiring();

        assertArrayEquals(expected, actual);
    }

}