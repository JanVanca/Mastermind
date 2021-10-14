package com.vanca.jan.mastermind.generator.impl;

import com.vanca.jan.mastermind.core.Color;
import com.vanca.jan.mastermind.generator.Generator;

import java.util.*;

public class RandomGenerator implements Generator {

    /**
     * Generates the number of colors according to the specified settings.
     *
     * @param numberOfColors The number of colors to be generated.
     * @param repeat boolean whether colors can be repeated.
     * @return List of generated colors.
     */
    @Override
    public List<Color> generate(int numberOfColors, boolean repeat) {
        Random random = new Random();
        int randomInt = 0;
        List<Integer> numbersList = new ArrayList<>(numberOfColors);
        Color[] colors = Color.values();
        Set<Integer> generated = new LinkedHashSet<Integer>();
        List<Color> colorList = new ArrayList<>();

        if (repeat) {
            //TODO refactor to for
            while (numbersList.size() < numberOfColors) {
                randomInt = random.nextInt((colors.length - 1) + 1) + 1;
                numbersList.add(randomInt);
            }
            addToColorList(numbersList, colors, colorList);

        } else {
            while (generated.size() < numberOfColors) {
                Integer next = random.nextInt((colors.length - 1) + 1) + 1;
                generated.add(next);
            }
            numbersList = new ArrayList<>(generated);
            addToColorList(numbersList, colors, colorList);
        }
        return colorList;
    }

    /**
     * Adds a color to the color List.
     *
     * @param numbersList Entered number of colors.
     * @param colors      Entered colors.
     * @param colorList   color list to which colors will be added.
     */
    private void addToColorList(List<Integer> numbersList, Color[] colors, List<Color> colorList) {
        int j = 0;

        //alternative one
        for (Integer i : numbersList) {
            colorList.add(Arrays.stream(colors).filter(color -> color.getNumber() == i).findFirst().orElseThrow(IllegalArgumentException::new));
        }

//        alternative two
//        for (Integer i : numbersList) {
//            for (com.vanca.jan.mastermind.core.Color color : colors) {
//                if (color.getNumber() == i) {
//                    colorList.add(color);
//                    break;
//                }
//            }
//        }

//        alternative three
//        while (j < numbersList.size()) {
//            if (colors[0].getNumber() == numbersList.get(j)) {
//                colorList.add(com.vanca.jan.mastermind.core.Color.RED);
//            }
//            if (colors[1].getNumber() == numbersList.get(j)) {
//                colorList.add(com.vanca.jan.mastermind.core.Color.GREEN);
//            }
//            if (colors[2].getNumber() == numbersList.get(j)) {
//                colorList.add(com.vanca.jan.mastermind.core.Color.BLUE);
//            }
//            if (colors[3].getNumber() == numbersList.get(j)) {
//                colorList.add(com.vanca.jan.mastermind.core.Color.BLACK);
//            }
//            if (colors[4].getNumber() == numbersList.get(j)) {
//                colorList.add(com.vanca.jan.mastermind.core.Color.WHITE);
//            }
//            if (colors[5].getNumber() == numbersList.get(j)) {
//                colorList.add(com.vanca.jan.mastermind.core.Color.YELLOW);
//            }
//            j++;
//        }
    }
}
