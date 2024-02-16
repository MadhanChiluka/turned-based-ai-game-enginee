package com.lowleveldesign.turnedbasedaigameenginee.boards;

import java.util.function.BiFunction;
import java.util.function.Function;

import com.lowleveldesign.turnedbasedaigameenginee.api.Rule;
import com.lowleveldesign.turnedbasedaigameenginee.api.RuleSet;
import com.lowleveldesign.turnedbasedaigameenginee.game.Board;
import com.lowleveldesign.turnedbasedaigameenginee.game.Cell;
import com.lowleveldesign.turnedbasedaigameenginee.game.GameState;
import com.lowleveldesign.turnedbasedaigameenginee.game.Move;

public class TicTacToeBoard implements Board {
    String cells[][] = new String[3][3];

    public String getSymbol(int row, int col) {
        return cells[row][col];
    }

    public void setCell(Cell cell, String symbol) {
        if (cells[cell.getRow()][cell.getCol()] == null) {
            cells[cell.getRow()][cell.getCol()] = symbol;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result += cells[i][j] != null ? cells[i][j] : "-";
            }
            result += "\n";
        }
        return result;
    }

    @Override
    public void move(Move move) {
        setCell(move.getCell(), move.getPlayer().symbol());
    }

    @Override
    public TicTacToeBoard copy() {
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ticTacToeBoard.cells[i][j] = cells[i][j];
            }
        }
        return ticTacToeBoard;
    }

    public static RuleSet<TicTacToeBoard> getRules() {
        RuleSet<TicTacToeBoard> rules = new RuleSet<>();
        rules.add(new Rule<TicTacToeBoard>(board -> outerTraversal((row, col) -> board.getSymbol(row, col))));
        rules.add(new Rule<TicTacToeBoard>(board -> outerTraversal((row, col) -> board.getSymbol(col, row))));
        rules.add(new Rule<TicTacToeBoard>(board -> traverse(i -> board.getSymbol(i, i))));
        rules.add(new Rule<TicTacToeBoard>(board -> traverse(i -> board.getSymbol(i, 2 - i))));
        rules.add(new Rule<TicTacToeBoard>(board -> {
            int countOfFilledCells = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getSymbol(i, j) != null) {
                        countOfFilledCells++;
                    }
                }
            }
            if (countOfFilledCells == 9) {
                return new GameState(true, "-");
            }
            return new GameState(false, "-");
        }));
        return rules;
    }

    private static GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
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