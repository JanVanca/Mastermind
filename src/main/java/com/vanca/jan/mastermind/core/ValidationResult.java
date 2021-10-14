package com.vanca.jan.mastermind.core;

public class ValidationResult {

    private int correct = 0;
    private int correctOnWrongPlace = 0;
    private int wrong = 0;

    public ValidationResult(int correct, int correctOnWrongPlace, int wrong) {
        this.correct = correct;
        this.correctOnWrongPlace = correctOnWrongPlace;
        this.wrong = wrong;
    }

    /**
     * @return The number of correct answers.
     */
    public int getCorrect() {
        return correct;
    }

    /**
     * @return The number of correct answers but on wrong place.
     */
    public int getCorrectOnWrongPlace() {
        return correctOnWrongPlace;
    }

    /**
     * @return The number of wrong answers.
     */
    public int getWrong() {
        return wrong;
    }

    /**
     * @return Result of the game.
     */
    public boolean isWon() {
        return wrong == 0 && correctOnWrongPlace == 0;
    }
}
