package com.lowleveldesign.turnedbasedaigameenginee.api;

import com.lowleveldesign.turnedbasedaigameenginee.boards.TicTacToeBoard;
import com.lowleveldesign.turnedbasedaigameenginee.game.Board;
import com.lowleveldesign.turnedbasedaigameenginee.game.Cell;
import com.lowleveldesign.turnedbasedaigameenginee.game.Move;
import com.lowleveldesign.turnedbasedaigameenginee.game.Player;

public class AIEngine {
    public Move suggestMove(Player computer, Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard tBoard = (TicTacToeBoard) board;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (tBoard.getCell(i, j) == null) {
                        return new Move(new Cell(i, j), computer);
                    }
                }
            }
            throw new IllegalArgumentException();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
