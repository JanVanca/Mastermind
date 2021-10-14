package com.vanca.jan.mastermind.generator.impl;

import com.vanca.jan.mastermind.core.Color;
import com.vanca.jan.mastermind.generator.Generator;

import java.util.*;

public class ConsoleGenerator implements Generator {

    private Scanner input = new Scanner(System.in);

    /**
     * Launches the console for entering custom colors.
     *
     * @param numberOfColors The number of colors to be entered.
     * @param repeat boolean whether colors can be repeated.
     * @return List of generated colors.
     */
    @Override
    public List<Color> generate(int numberOfColors, boolean repeat) {
        List<Color> colorList = new ArrayList<>();
        List<Integer> numbersList = new ArrayList<>(numberOfColors);
        Set<Integer> generated = new LinkedHashSet<Integer>();
        Color[] colors = Color.values();
        int randomNumber = 0;
        System.out.println("RED - 1, GREEN - 2, BLUE - 3, BLACK - 4, WHITE - 5, YELLOW - 6");
        if (repeat) {
            while (numbersList.size() < numberOfColors) {
                System.out.println("Enter a number to select a color: ");
                randomNumber = input.nextInt();
                numbersList.add(randomNumber);
            }

        } else {
            while (generated.size() < numberOfColors) {
                System.out.println("Enter a number to select a color: {You must not enter twice}");
                randomNumber = input.nextInt();
                generated.add(randomNumber);
            }
            numbersList = new ArrayList<>(generated);
        }
        addToColorList(numbersList, colors, colorList);
        return colorList;
    }

    /**
     * Adds a color to the color List.
     *
     * @param numbersList Entered number of colors.
     * @param colors Entered colors.
     * @param colorList Color list to which colors will be added.
     */
    private void addToColorList(List<Integer> numbersList, Color[] colors, List<Color> colorList) {
        for (Integer i : numbersList) {
            colorList.add(Arrays.stream(colors).filter(color -> color.getNumber() == i).findFirst().orElseThrow(IllegalArgumentException::new));
        }
    }
}
