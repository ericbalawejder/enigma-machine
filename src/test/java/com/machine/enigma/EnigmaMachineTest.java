package com.machine.enigma;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void encodeLine() {
        String expected = "WGJOUNHUPLJRETLGWMNRPIIXUDM";

        enigmaMachine.setRotors(5, 9, 14);
        String actual = enigmaMachine.encodeLine("AAAAAAAAAAAAAAAAAAAAAAAAAAA");

        assertEquals(expected, actual);
    }

    @Test
    void encodeLineIsSymmetrical() {
        String expected = "AAAAAAAAAAAAAAAAAAAAAAAAAAA";

        enigmaMachine.setRotors(5, 9, 14);
        String actual = enigmaMachine.encodeLine("WGJOUNHUPLJRETLGWMNRPIIXUDM");

        assertEquals(expected, actual);
    }

}