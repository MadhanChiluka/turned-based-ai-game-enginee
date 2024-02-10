package com.lowleveldesign.turnedbasedaigameenginee.api;

import com.lowleveldesign.turnedbasedaigameenginee.boards.TicTacToeBoard;
import com.lowleveldesign.turnedbasedaigameenginee.game.Board;
import com.lowleveldesign.turnedbasedaigameenginee.game.Cell;
import com.lowleveldesign.turnedbasedaigameenginee.game.GameResult;
import com.lowleveldesign.turnedbasedaigameenginee.game.Move;
import com.lowleveldesign.turnedbasedaigameenginee.game.Player;

// Turned based ai game enginee
public class GameEnginee {

    // start
    // move
    // result

    public Board start(String type) {
        if (type.equals("TicTacToe")) {
            return new TicTacToeBoard();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void move(Board board, Player player, Move move) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard tBoard = (TicTacToeBoard) board;
            tBoard.setCell(move.getCell(), player.symbol());
        } else {
            throw new IllegalArgumentException();
        }
    }

    public GameResult isComplete(Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            String firstCharacter = "-";

            boolean rowComplete = true;
            for (int i = 0; i < 3; i++) {
                firstCharacter = board1.getCell(i, 0);
                rowComplete = firstCharacter != null;
                if (rowComplete) {
                    for (int j = 1; j < 3; j++) {
                        if (!firstCharacter.equals(board1.getCell(i, j))) {
                            rowComplete = false;
                            break;
                        }
                    }
                }
                if (rowComplete) {
                    break;
                }
            }

            if (rowComplete) {
                return new GameResult(true, firstCharacter);
            }

            boolean colComplete = true;
            for (int i = 0; i < 3; i++) {
                firstCharacter = board1.getCell(0, i);
                colComplete = firstCharacter != null;
                if (firstCharacter != null) {
                    for (int j = 1; j < 3; j++) {
                        if (!firstCharacter.equals(board1.getCell(j, i))) {
                            colComplete = false;
                            break;
                        }
                    }
                }
                if (colComplete) {
                    break;
                }
            }
            if (colComplete) {
                return new GameResult(true, firstCharacter);
            }

            firstCharacter = board1.getCell(0, 0);
            boolean diagComplete = firstCharacter != null;
            for (int i = 0; i < 3; i++) {
                if (firstCharacter != null && !firstCharacter.equals(board1.getCell(i, i))) {
                    diagComplete = false;
                    break;
                }
            }
            if (diagComplete) {
                return new GameResult(diagComplete, firstCharacter);
            }

            firstCharacter = board1.getCell(0, 2);
            boolean revDiagComplete = firstCharacter != null;
            for (int i = 0; i < 3; i++) {
                if (firstCharacter != null && !firstCharacter.equals(board1.getCell(0 + i, 2 - i))) {
                    revDiagComplete = false;
                    break;
                }
            }
            if (revDiagComplete) {
                return new GameResult(revDiagComplete, firstCharacter);
            }

            int countOfFilledCells = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.getCell(i, j) != null) {
                        countOfFilledCells++;
                    }
                }
            }

            if (countOfFilledCells == 9) {
                return new GameResult(true, "-");
            } else {
                return new GameResult(false, "-");
            }
        }
        return new GameResult(false, "-");
    }

    public Move suggestMove(Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard tBoard = (TicTacToeBoard) board;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (tBoard.getCell(i, j) == null) {
                        return new Move(new Cell(i, j));
                    }
                }
            }
            throw new IllegalArgumentException();
        } else {
            throw new IllegalArgumentException();
        }
    }
}