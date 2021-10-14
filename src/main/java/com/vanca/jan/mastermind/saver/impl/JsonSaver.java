package com.vanca.jan.mastermind.saver.impl;

import com.vanca.jan.mastermind.core.*;
import com.vanca.jan.mastermind.saver.Saver;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class JsonSaver implements Saver {

    private String directory = System.getProperty("user.dir");
    private String fileName = "save.txt";
    private String absolutePath = directory + File.separator + fileName;


    @Override
    public boolean saveGame(Game game) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Number of rounds", game.getGameConfig().getNumberOfRounds());
        jsonObject.put("Number of colors", game.getGameConfig().getNumberOfColors());
        jsonObject.put("Excepted", game.getExpected());
        jsonObject.put("Current round", game.getCurrentRounds());
        jsonObject.put("Attempts", game.getAttempts());
        jsonObject.put("Results", game.getResults());
        writeJsonToFile(jsonObject);
        return true;
    }

    /**
     * Write content to file
     *
     * @param jsonObject
     */
    private void writeJsonToFile(JSONObject jsonObject) {
        try {
            PrintWriter pw = new PrintWriter(absolutePath);
            pw.write(jsonObject.toString());
            pw.flush();
            pw.close();
        } catch (Exception e) {

        }
    }

    /**
     * @return Game with all settings.
     */
    @Override
    public Game loadGame() {
        StringBuilder result = new StringBuilder();
        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(absolutePath));

            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            bufferedReader.close();
        } catch (Exception e) {

        }

        Game game = jsonToGame(result);
        return game;
    }

    /**
     * Converts all json variables to game.
     *
     * @param result Entered variables.
     * @return Game with all settings.
     */
    private Game jsonToGame(StringBuilder result) {
        String jsonString = result.toString();
        JSONObject jsonObject = new JSONObject(jsonString);
        GameConfig gameConfig = new GameConfig();

        int numberOfRounds = jsonObject.getInt("Number of rounds");
        int numberOfColors = jsonObject.getInt("Number of colors");
        int currentRounds = jsonObject.getInt("Current round");
        List<Color> expected;
        ColorValidator colorValidator = new ColorValidator();
        List<ValidationResult> results = new ArrayList<>();

        gameConfig.setNumberOfRounds(numberOfRounds);
        gameConfig.setNumberOfColors(numberOfColors);

        String[] searchColors = new String[numberOfColors];
        JSONArray jsonArray = jsonObject.getJSONArray("Excepted");

        expected = jsonToList(searchColors, jsonArray);

        jsonArray = jsonObject.getJSONArray("Attempts");

        List<List<Color>> attempts = new ArrayList<>();

        for (int i = 0; i <= currentRounds - 2; i++) {
            JSONArray j = (JSONArray) jsonArray.get(i);
            List<Color> variable = new ArrayList<>();
            variable = jsonToList(searchColors, j);
            attempts.add(variable);
            results.add(colorValidator.validate(expected, variable));
        }

        Game game = Game.builder()
                .setAttempts(attempts)
                .setExcepted(expected)
                .setResults(results)
                .setCurrentRounds(currentRounds)
                .setGameConfig(gameConfig)
                .build();
        return game;
    }

    /**
     * Converts all json variables to list.
     *
     * @param searchColors
     * @param jsonArray
     * @return List of colors.
     */
    private List<Color> jsonToList(String[] searchColors, JSONArray jsonArray) {
        List<Color> results = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            String s = (String) jsonArray.get(i);
            searchColors[i] = s;
        }
        for (String string : searchColors) {
            Color color = Enum.valueOf(Color.class, string);
            results.add(color);
        }
        return results;
    }
}

