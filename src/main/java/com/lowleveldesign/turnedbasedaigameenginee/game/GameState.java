package com.lowleveldesign.turnedbasedaigameenginee.game;

import lombok.Data;

@Data
public class GameState {
    private boolean isOver;
    private String winner;

    public GameState(boolean isOver, String winner) {
        this.isOver = isOver;
        this.winner = winner;
    }

}