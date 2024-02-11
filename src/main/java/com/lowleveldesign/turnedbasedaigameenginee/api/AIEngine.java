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
            Move suggestion;
            int threshold = 4;
            if (countMoves(tBoard) < threshold) {
                suggestion = getBasicMove(computer, tBoard);
            } else {
                suggestion = getSmartMove(computer, tBoard);
            }
            if (suggestion != null)
                return suggestion;
            throw new IllegalArgumentException();
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Move getSmartMove(Player player, TicTacToeBoard tBoard) {
        RuleEngine ruleEngine = new RuleEngine();

        // Victorious Moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tBoard.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player);
                    TicTacToeBoard boardCopy = tBoard.copy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return move;
                    }
                }
            }
        }

        // Defensive moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tBoard.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player.flip());
                    TicTacToeBoard boardCopy = tBoard.copy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return new Move(new Cell(i, j), player);
                    }
                }
            }
        }

        return null;
    }

    private int countMoves(TicTacToeBoard tBoard) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tBoard.getSymbol(i, j) != null) {
                    count++;
                }
            }
        }
        return count;
    }

    public Move getBasicMove(Player computer, TicTacToeBoard tBoard) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tBoard.getSymbol(i, j) == null) {
                    return new Move(new Cell(i, j), computer);
                }
            }
        }
        return null;
    }
}
