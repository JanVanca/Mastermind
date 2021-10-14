package com.vanca.jan.mastermind.console;

import com.vanca.jan.mastermind.core.GameConfig;

import java.util.Scanner;

public class Menu {

    private Console console = new Console();
    private GameConfig gameConfig = new GameConfig();
    private Scanner input = new Scanner(System.in);
    private ServiceConsole serviceConsole = new ServiceConsole();

    public Menu() {

    }

    /**
     * Starts the console.
     */
    public void startGame() {
        console.start(gameConfig);
    }

    /**
     * Starts service console.
     */
    private void startServiceConsole() {
        serviceConsole.start();
    }

    /**
     * Starts the game.
     */
    public void start() {
        int choice = 0;

        System.out.println("MASTERMIND");
        while (choice != 5) {
            System.out.println("Enter your choice:");
            System.out.println("1. New Game");
            System.out.println("2. Settings");
            System.out.println("3. Load Game");
            System.out.println("4. Score");
            System.out.println("5. Exit Game");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    startGame();
                    break;
                case 2:
                    settingGame();
                    break;
                case 3:
                    //load();
                    break;
                case 4:
                    startServiceConsole();
                    break;
            }
        }
    }

    /**
     * Game settings.
     */
    public void settingGame() {
        int numberOfColors;
        int rounds = 0;
        char inputRepeat;
        char ownResult;
        System.out.println("Enter the number of colors (default 4): ");
        numberOfColors = input.nextInt();
        gameConfig.setNumberOfColors(numberOfColors);
        System.out.println("Enter the number of rounds: ");
        rounds = input.nextInt();
        gameConfig.setNumberOfRounds(rounds);
        System.out.println("Should the colors be repeated? A - N");
        inputRepeat = input.next().charAt(0);
        gameConfig.setRepeatColors(repeatColors(inputRepeat));
        System.out.println("Do you want to enter the correct answers? A - N");
        ownResult = input.next().charAt(0);
        gameConfig.setOwnResult(repeatColors(ownResult));
    }

    /**
     * Repeating colors.
     *
     * @param value Entered value.
     * @return choice.
     */
    private boolean repeatColors(char value) {
        return value == 'A';
    }
}