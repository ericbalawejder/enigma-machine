package com.machine.enigma;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReflectorTest {

    private Reflector reflector;

    @BeforeEach
    void setUp() {
        reflector = new Reflector("NPKMSLZTWQCFDAVBJYEHXOIURG");
    }

    @Test
    void testReflect() {
        char expected = 'A';

        char actual = reflector.reflect('N');

        assertEquals(expected, actual);
    }

    @Test
    void testReflect2() {
        char expected = 'Z';

        char actual = reflector.reflect('G');

        assertEquals(expected, actual);
    }

    @Test
    void testReflectGetLeftToRightWiring() {
        char[] expected = "NPKMSLZTWQCFDAVBJYEHXOIURG".toCharArray();

        char[] actual = reflector.getLeftToRightWiring();

        assertArrayEquals(expected, actual);
    }

}