package com.machine.enigma;

import java.util.Arrays;

class English {

    private double totalNumberOfLetters;
    private static final int MAX = Rotor.MAX;

    private static final double[] ENGLISH_LETTER_FREQUENCY = {8.1, 1.6, 3.2, 3.6, 12.3, 2.3, 1.6,
            5.1, 7.2, 0.1, 0.5, 4.0, 2.2, 7.2, 7.9, 2.3, 0.2, 6.0, 6.6, 9.6, 3.1, 0.9,
            2.0, 0.2, 1.9, 0.1};

    private static final int[] ENGLISH_LETTER_DEVIATION_PERCENTAGE = {10, 50, 30, 30, 10, 30, 50, 20, 15,
            100, 80, 30, 30, 20, 20, 30, 100, 30, 20, 15, 30, 60, 40, 100, 40, 100};

    private static final int[] alphabetLetterCount = new int[MAX];

    void countAllLetters(String s) {
        char characterA = 65;
        totalNumberOfLetters = 0;

        Arrays.fill(alphabetLetterCount, 0);

        for (int i = 0; i < s.length(); i++) {
            if (Character.isAlphabetic(s.charAt(i)) && Character.isUpperCase(s.charAt((i)))) {
                totalNumberOfLetters++;
                alphabetLetterCount[s.charAt(i) - characterA]++;
            }
        }
    }

    int getErrorCount(double multipler) {
        int errorCount = 0;

        for (int i = 0; i < alphabetLetterCount.length; i++) {
            double percentError = (alphabetLetterCount[i] / totalNumberOfLetters) * 100.0;
            double lowError = ENGLISH_LETTER_FREQUENCY[i] - (ENGLISH_LETTER_DEVIATION_PERCENTAGE[i] * (multipler / 100));
            double highError = ENGLISH_LETTER_FREQUENCY[i] + (ENGLISH_LETTER_DEVIATION_PERCENTAGE[i] * (multipler / 100));

            if (percentError < lowError || percentError > highError) {
                errorCount++;
            }
        }
        return errorCount;
    }

}
