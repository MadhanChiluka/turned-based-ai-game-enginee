package com.lowleveldesign.turnedbasedaigameenginee.game;

import lombok.Data;

@Data
public class GameResult {
    private boolean isOver;
    private String winner;

    public GameResult(boolean isOver, String winner) {
        this.isOver = isOver;
        this.winner = winner;
    }

}