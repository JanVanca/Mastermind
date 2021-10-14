package com.vanca.jan.mastermind.saver;

import com.vanca.jan.mastermind.core.Game;

public interface Saver {

    boolean saveGame(Game game);
    Game loadGame();
}
