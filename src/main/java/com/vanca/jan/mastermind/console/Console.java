package com.vanca.jan.mastermind.console;

import com.vanca.jan.mastermind.core.Color;
import com.vanca.jan.mastermind.core.Game;
import com.vanca.jan.mastermind.core.GameConfig;
import com.vanca.jan.mastermind.generator.Generator;
import com.vanca.jan.mastermind.generator.impl.ConsoleGenerator;
import com.vanca.jan.mastermind.generator.impl.RandomGenerator;
import com.vanca.jan.mastermind.saver.Saver;
import com.vanca.jan.mastermind.saver.impl.FileSaver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {
    private Scanner input = new Scanner(System.in);
    private String[] searchColors;
    private Game game;
    private boolean load = false;

    public Console() {
    }

    /**
     * Console turns on and creates a game.
     *
     * @param gameConfig Specified game settings.
     */
    public void start(GameConfig gameConfig) {
        Generator generator;

        if (gameConfig.getOwnResult() == true) {
            generator = new ConsoleGenerator();
        } else {
            generator = new RandomGenerator();
        }
        game = Game.builder().setGenerator(generator).setGameConfig(gameConfig).build();
        System.out.println("The correct answers are: " + game.getExpected());
        while (game.isTryLeft() && !game.isWon()) {
            System.out.println("Number of rounds: " + game.getCurrentRounds() + " / " + gameConfig.getNumberOfRounds());
            guessTheColors(gameConfig.getNumberOfColors());
            game.accept(transformSearchColorsToEnum());
            print();
            gameMenu();
            if (load) {
                print();
                load = false;
            }
        }
        if (game.isWon()) {
            System.out.println("You won");
        } else {
            System.out.println("You lost");
        }
    }

    /***
     * Prints the results to the console.
     */
    private void print() {

        for (int i = 0; i <= game.getCurrentRounds() - 2; i++) {
            System.out.print(game.getAttempts().get(i));
            System.out.print(" | ");
            System.out.print(game.getResults().get(i).getCorrect() + "S");
            System.out.print(" " + game.getResults().get(i).getCorrectOnWrongPlace() + "N");
            System.out.println(" " + game.getResults().get(i).getWrong() + "Z");
        }
    }

    /**
     * Enter values in the console.
     *
     * @param numberOfColors The number of colors that can be specified.
     */
    public void guessTheColors(int numberOfColors) {
        searchColors = new String[numberOfColors];
        int i = 0;
        int j = 1;
        System.out.println("Zadaj farby v poradi");
        while (i < numberOfColors) {
            System.out.println(j + ". farba: ");
            searchColors[i] = input.nextLine().toUpperCase();
            i++;
            j++;
        }
    }

    /**
     * Transform string into List<Color>.
     *
     * @return Transformed List<Color>.
     */
    public List<Color> transformSearchColorsToEnum() {
        List<Color> given = new ArrayList<>();
        for (String string : searchColors) {
            Color color = Enum.valueOf(Color.class, string);
            given.add(color);
        }
        return given;
    }

    /**
     * Game menu for selecting options.
     */
    private void gameMenu() {
        //Saver saver = new JsonSaver();
        Saver saver = new FileSaver();
        Scanner nacitaj = new Scanner(System.in);
        int choice = 0;
        System.out.println("1. Save game");
        System.out.println("2. Continue");
        System.out.println("3. Load game");
        System.out.println("4. Exit game");
        //Integer.parseInt(Scanner.nextLine());
        choice = nacitaj.nextInt();
        //Scanner.nextLine();
        switch (choice) {
            case 1:
                saver.saveGame(game);
                break;
            case 2:
                break;
            case 3:
                game = saver.loadGame();
                load = true;

                break;
            case 4:
                game.exitGame();
                break;
        }
    }
}