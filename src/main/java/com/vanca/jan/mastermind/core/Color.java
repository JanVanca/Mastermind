package com.vanca.jan.mastermind.core;

public enum Color {
    RED(1), GREEN(2), BLUE(3), BLACK(4), WHITE(5), YELLOW(6);

    private int number;

    private Color(int number) {
        this.number = number;
    }

    /**
     * @return Current number.
     */
    public int getNumber() {
        return this.number;
    }
}
