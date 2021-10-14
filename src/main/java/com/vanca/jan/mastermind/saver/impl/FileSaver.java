package com.vanca.jan.mastermind.saver.impl;

import com.vanca.jan.mastermind.core.*;
import com.vanca.jan.mastermind.saver.Saver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileSaver implements Saver {

    private String directory = System.getProperty("user.dir");
    private String fileName = "fileSaver.txt";
    private String absolutePath = directory + File.separator + fileName;

    /**
     * Starts saving the game.
     *
     * @param game Current game to be saved.
     * @return True if game was successfully saved.
     */
    @Override
    public boolean saveGame(Game game) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(absolutePath))) {
            bufferedWriter.write("" + game.getGameConfig().getNumberOfRounds());
            bufferedWriter.newLine();
            bufferedWriter.write("" + game.getGameConfig().getNumberOfColors());
            bufferedWriter.newLine();
            bufferedWriter.write("" + game.getCurrentRounds());
            bufferedWriter.newLine();
            bufferedWriter.write("" + game.getExpected());
            bufferedWriter.newLine();
            bufferedWriter.write("" + game.getAttempts());
            bufferedWriter.newLine();
            for (int i = 0; i <= game.getCurrentRounds() - 2; i++) {
                bufferedWriter.write("" + game.getResults().get(i).getCorrect());
                bufferedWriter.write("" + game.getResults().get(i).getCorrectOnWrongPlace());
                bufferedWriter.write("" + game.getResults().get(i).getWrong());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Starts loading the game.
     *
     * @return Loaded game.
     */
    @Override
    public Game loadGame() {
        List<String> listString = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(absolutePath))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                listString.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Game game;
        game = loadAllVariables(listString);
        return game;
    }

    /**
     * Loads all variables.
     *
     * @param listString All variables in string loaded from file.
     * @return loaded game with all variables.
     */
    private Game loadAllVariables(List<String> listString) {
        int numberOfRounds = 0;
        int numberOfColors = 0;
        int currentRound = 0;
        String s;
        String[] e = new String[numberOfColors];
        int j = 0;
        List<Color> expected = new ArrayList<>();
        ColorValidator colorValidator = new ColorValidator();
        List<ValidationResult> results = new ArrayList<>();
        GameConfig gameConfig = new GameConfig();

        // Setting values into variables
        numberOfRounds = Integer.parseInt(listString.get(0));
        numberOfColors = Integer.parseInt(listString.get(1));
        currentRound = Integer.parseInt(listString.get(2));
        gameConfig.setNumberOfRounds(numberOfRounds);
        gameConfig.setNumberOfColors(numberOfColors);

        //Setting values into variables and split by regex.
        s = listString.get(3);
        String[] excepted = new String[numberOfColors];
        e = s.split("[,\\[\\]\\ ]");

        //Setting values into varieble String[] excepted.
        for (int i = 1; i < e.length; i = i + 2) {
            excepted[j] = e[i];
            j++;
        }

        //Setting value into variable List<Color> Excepted.
        expected = stringToList(excepted);

        //Setting value into variables and split by regex.
        s = listString.get(4);
        e = s.split("[\\[\\]\\,\\ ]");
        String[] searchColors = new String[e.length];
        j = 0;

        // Setting values into variable String [] searchColors.
        for (int i = 2; i < e.length; i = i + 2) {
            searchColors[j] = e[i];
            j++;
        }

        //Add variables into List<List<Color>> attempts and into List<ValidationResult> results
        int i = 0;
        List<String> a = new ArrayList<String>();
        List<List<Color>> attempts = new ArrayList<>();
        while (searchColors[i] != null) {
            String k = searchColors[i];
            String[] stringList;
            if (k.isEmpty()) {
                stringList = new String[i];
                for (int l = 0; l < a.size(); l++) {
                    stringList = a.toArray(new String[l]);
                }
                i++;
                attempts.add(stringToList(stringList));
                results.add(colorValidator.validate(expected, stringToList(stringList)));
                a.removeAll(a);
            } else {
                a.add(k);
                i++;
            }
        }

        // Add last round variables into List<List<Color>> attempts and into List<ValidationResult> results
        String[] stringList = new String[i];
        for (int l = 0; l < a.size(); l++) {
            stringList = a.toArray(new String[l]);
        }
        attempts.add(stringToList(stringList));
        results.add(colorValidator.validate(expected, stringToList(stringList)));

        //Creates and return game
        Game game = Game.builder().setGameConfig(gameConfig).setExcepted(expected).build();
        for (i = 0; i < currentRound - 1; i++) {
            game.accept(attempts.get(i));
        }

//        Creates and return game using Builder pattern
//        Game game = Game.builder()
//                .setAttempts(attempts)
//                .setExcepted(expected)
//                .setResults(results)
//                .setCurrentRounds(currentRound)
//                .setGameConfig(gameConfig)
//                .build();
        return game;
    }

    /**
     * Add String[] into List<Color>.
     *
     * @param StringList Entered string.
     * @return List of color result.
     */
    private List<Color> stringToList(String[] StringList) {
        List<Color> results = new ArrayList<>();

        for (String string : StringList) {
            Color color = Enum.valueOf(Color.class, string);
            results.add(color);
        }
        return results;
    }
}