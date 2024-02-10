package com.lowleveldesign.turnedbasedaigameenginee.game;

import lombok.Data;

@Data
public class Cell {
    int row, col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

}
