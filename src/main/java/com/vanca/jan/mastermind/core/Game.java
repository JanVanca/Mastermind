package com.vanca.jan.mastermind.core;

import com.vanca.jan.mastermind.generator.Generator;
import com.vanca.jan.mastermind.generator.impl.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private GameConfig gameConfig;
    private List<Color> expected;
    private ColorValidator colorValidator;
    private List<List<Color>> attempts;
    private List<ValidationResult> results;
    private Generator generator;
    private int currentRounds;

    /**
     * Private constructor for use from the builder.
     *
     * @param builder
     */
    private Game(Builder builder) {
        this.gameConfig = builder.gameConfig;
        this.colorValidator = new ColorValidator();
        this.attempts = builder.attempts;
        this.results = builder.results;
        this.currentRounds = builder.currentRounds;
        this.generator = builder.generator;

        if (builder.expected == null || builder.expected.isEmpty()) {
            this.expected = builder.generator.generate(builder.gameConfig.getNumberOfColors(), builder.gameConfig.isRepeatColors());
        } else {
            this.expected = builder.expected;
        }
    }

    /**
     * Creates builder.
     *
     * @return new builder.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * @return Current rounds.
     */
    public int getCurrentRounds() {
        return currentRounds;
    }

    /**
     * @return Current attempts.
     */
    public List<List<Color>> getAttempts() {
        return attempts;
    }

    /**
     * @return Current results.
     */
    public List<ValidationResult> getResults() {
        return results;
    }

    /**
     * Adds the specified colors.
     *
     * @param colors Entered colors.
     */
    public void accept(List<Color> colors) {
        results.add(colorValidator.validate(expected, colors));
        currentRounds++;
        attempts.add(colors);
    }

    /**
     * Restart the game.
     *
     * @param gameConfig Entered game config with settings.
     */
    public void restart(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
        currentRounds = 1;
        attempts = new ArrayList<>();
        results = new ArrayList<>();
        expected = generator.generate(gameConfig.getNumberOfColors(), gameConfig.isRepeatColors());
        colorValidator = new ColorValidator();
    }

    /**
     * @return Excepted colors.
     */
    public List<Color> getExpected() {
        return expected;
    }

    /**
     * @return Number of rounds remaining.
     */
    public boolean isTryLeft() {
        return currentRounds <= gameConfig.getNumberOfRounds();
    }

    /**
     * @return Game result.
     */
    public boolean isWon() {
//        first alternative
//        for (com.vanca.jan.mastermind.core.ValidationResult result : results) {
//            if (result.isWon()) {
//                return true;
//            }
//        }
//        return false;

//        second
        return results.stream().anyMatch(ValidationResult::isWon);
    }

    /**
     * Exit game.
     */
    public void exitGame() {

    }

    /**
     * @return Current game config.
     */
    public GameConfig getGameConfig() {
        return gameConfig;
    }

    public static class Builder {
        private GameConfig gameConfig = new GameConfig(false);
        private Generator generator = new RandomGenerator();
        private List<List<Color>> attempts = new ArrayList<>();
        private List<ValidationResult> results = new ArrayList<>();
        private int currentRounds = 1;
        private List<Color> expected = new ArrayList<>();

        /**
         * Private constructor in order to that the builder cannot be created other than via the static builder () method in the Game class.
         */
        private Builder() {

        }

        /**
         * Game config to set.
         *
         * @param gameConfig Entered game config.
         * @return Game config.
         */
        public Builder setGameConfig(GameConfig gameConfig) {
            this.gameConfig = gameConfig;
            return this;
        }

        /**
         * Attempts to set.
         *
         * @param attempts Entered game attempts.
         * @return Attempts.
         */
        public Builder setAttempts(List<List<Color>> attempts) {
            this.attempts = attempts;
            return this;
        }

        /**
         * Results to set.
         *
         * @param results List of results.
         * @return Results.
         */
        public Builder setResults(List<ValidationResult> results) {
            this.results = results;
            return this;
        }

        /**
         * Excepted colors to set.
         *
         * @param expected List of excepted colors.
         * @return Excepted colors.
         */
        public Builder setExcepted(List<Color> expected) {
            this.expected = expected;
            return this;
        }

        /**
         * Generator to set.
         *
         * @param generator Selected generator.
         * @return Generator.
         */
        public Builder setGenerator(Generator generator) {
            this.generator = generator;
            return this;
        }

        /**
         * Current round to set.
         *
         * @param currentRounds The number of the current round.
         * @return Current round.
         */
        public Builder setCurrentRounds(int currentRounds) {
            this.currentRounds = currentRounds;
            return this;
        }

        /**
         * creating a Game object.
         *
         * @return new Game object.
         */
        public Game build() {
            return new Game(this);
        }
    }
}