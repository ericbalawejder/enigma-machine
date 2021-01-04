package com.machine.enigma;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EnglishTest {

    private English english;

    @BeforeEach
    void setUp() {
        english = new English();
    }

    @Test
    void countAllValidLetters() {
        final int expected = 35;

        String sequence = "HELLO WORLD. WELCOME TO THE ENIGMA MACHINE.";
        english.countAllLetters(sequence);

        assertEquals(expected, english.getTotalNumberOfLetters());
    }

    @Test
    void countAllValidLettersMixedCase() {
        final int expected = 3;

        String sequence = "Hello world. Welcome to the Enigma machine.";
        english.countAllLetters(sequence);

        assertEquals(expected, english.getTotalNumberOfLetters());
    }

    @Test
    void countAllValidLettersShouldBeZero() {
        final int expected = 0;

        String sequence = "hello world. welcome to the enigma machine.";
        english.countAllLetters(sequence);

        assertEquals(expected, english.getTotalNumberOfLetters());
    }

    @Test
    void testAlphabetLetterCountAllOne() {
        final int[] expected = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

        final String sequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        english.countAllLetters(sequence);

        assertArrayEquals(expected, english.getAlphabetLetterCount());
    }

    @Test
    void testAlphabetLetterCountAllZero() {
        final int[] expected = new int[26];
        Arrays.fill(expected, 0);

        final String sequence = "";
        english.countAllLetters(sequence);

        assertArrayEquals(expected, english.getAlphabetLetterCount());
    }

    @Disabled
    @Test
    void getErrorCount() {
        // TODO
    }

}