package com.lowleveldesign.turnedbasedaigameenginee.game;

public class Player {

    private User id;

    private int timeUsedInMillis;

    private String playerSymbol;

    public Player(String playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public String symbol() {
        return playerSymbol;
    }

    public Player flip() {
        return new Player(playerSymbol.equals("X") ? "O" : "X");
    }

    public void setTimeUsedInMillis(int timeUsedInMillis) {
        this.timeUsedInMillis += timeUsedInMillis;
    }

    public int getTimeUsedInMillis() {
        return timeUsedInMillis;
    }

}
