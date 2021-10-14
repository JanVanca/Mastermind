package com.vanca.jan.mastermind.core;

public class GameConfig {

    private int numberOfColors = 0;
    private int numberOfRounds = 0;
    private boolean repeat = false;
    private boolean ownResult = false;

    public GameConfig() {
        this(false);
    }

    public GameConfig(boolean repeat) {
        this.repeat = repeat;
        numberOfColors = 4;
        numberOfRounds = 10;
    }

    /**
     * @return current number of colors.
     */
    public int getNumberOfColors() {
        return numberOfColors;
    }

    /**
     * @param numberOfColors Number of colors to set.
     */
    public void setNumberOfColors(int numberOfColors) {
        if (numberOfColors < 1) {
            return;
        }
        this.numberOfColors = numberOfColors;
    }

    /**
     * @return Number of rounds.
     */
    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    /**
     * @param numberOfRounds Number of rounds to set.
     */
    public void setNumberOfRounds(int numberOfRounds) {
        if (numberOfRounds <= 0) {
            return;
        }
        this.numberOfRounds = numberOfRounds;
    }

    /**
     * @return whether to repeat the colors.
     */
    public boolean isRepeatColors() {
        return repeat;
    }

    /**
     * @param repeat Repeat colors to set.
     */
    public void setRepeatColors(boolean repeat) {
        this.repeat = repeat;
    }

    /**
     * @return current onw results.
     */
    public boolean getOwnResult() {
        return ownResult;
    }

    /**
     * @param ownResult Own results to set.
     */
    public void setOwnResult(boolean ownResult) {
        this.ownResult = ownResult;
    }
}
