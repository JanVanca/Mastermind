package com.vanca.jan.mastermind.generator;

import com.vanca.jan.mastermind.core.Color;

import java.util.List;

public interface Generator {

    public List<Color> generate(int numberOfColors, boolean repeat);

}
