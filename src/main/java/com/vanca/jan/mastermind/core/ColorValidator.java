package com.vanca.jan.mastermind.core;

import java.util.ArrayList;
import java.util.List;

public class ColorValidator {

    /**
     * The method receives the correct colors and a search  colors and evaluate them.
     *
     * @param expected Correct colors (answers)
     * @param given Entered colors by user.
     * @return Result of validation. (the number of correct, incorrect, and correct but on incorrect place).
     */
    public ValidationResult validate(List<Color> expected, List<Color> given) {
        int correct = 0;
        int wrongPlace = 0;
        int wrong = 0;
        if (expected.equals(given)) {
            return new ValidationResult(expected.size(), 0, 0);
        }
        List<Color> expectedNew = new ArrayList<>(expected);
        List<Color> givenNew = new ArrayList<>(given);
        for (int i = 0; i < expectedNew.size(); i++) {
            if (expectedNew.get(i) == givenNew.get(i)) {
                correct++;
                givenNew.remove(i);
                expectedNew.remove(i);
                i--;
            }
        }
        for (int i = 0; i < givenNew.size(); i++) {
            if (expectedNew.contains(givenNew.get(i))) {
                wrongPlace++;
                expectedNew.remove(givenNew.get(i));
            } else {
                wrong++;
            }
        }
        return new ValidationResult(correct, wrongPlace, wrong);
    }
}
