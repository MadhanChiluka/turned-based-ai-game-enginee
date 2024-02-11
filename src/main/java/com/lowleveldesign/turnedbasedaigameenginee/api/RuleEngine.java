package com.lowleveldesign.turnedbasedaigameenginee.api;

import java.util.function.BiFunction;
import java.util.function.Function;

import com.lowleveldesign.turnedbasedaigameenginee.boards.TicTacToeBoard;
import com.lowleveldesign.turnedbasedaigameenginee.game.Board;
import com.lowleveldesign.turnedbasedaigameenginee.game.GameState;

public class RuleEngine {
    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;

            GameState rowWin = outerTraversal((i, j) -> board1.getSymbol(i, j));
            if (rowWin.isOver())
                return rowWin;

            GameState colWin = outerTraversal((i, j) -> board1.getSymbol(j, i));
            if (colWin.isOver())
                return colWin;

            GameState diagWin = traverse(i -> board1.getSymbol(i, i));
            if (diagWin.isOver())
                return diagWin;

            GameState revDiagWin = traverse(i -> board1.getSymbol(i, 2 - i));
            if (revDiagWin.isOver()) {
                return revDiagWin;
            }

            int countOfFilledCells = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.getSymbol(i, j) != null) {
                        countOfFilledCells++;
                    }
                }
            }

            if (countOfFilledCells == 9) {
                return new GameState(true, "-");
            } else {
                return new GameState(false, "-");
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
        GameState result = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            final int finalI = i;
            GameState traversal = traverse(j -> next.apply(finalI, j));
            if (traversal.isOver()) {
                result = traversal;
                break;
            }
        }
        return result;
    }

    private static GameState traverse(Function<Integer, String> traversal) {
        GameState result = new GameState(false, "-");
        boolean possibleStreak = true;
        for (int i = 0; i < 3; i++) {
            if (traversal.apply(i) == null || !traversal.apply(0).equals(traversal.apply(i))) {
                possibleStreak = false;
                break;
            }
        }
        if (possibleStreak) {
            result = new GameState(possibleStreak, traversal.apply(0));
        }
        return result;
    }
}
