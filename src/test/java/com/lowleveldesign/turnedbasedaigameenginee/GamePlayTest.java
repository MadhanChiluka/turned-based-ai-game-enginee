package com.lowleveldesign.turnedbasedaigameenginee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lowleveldesign.turnedbasedaigameenginee.api.GameEnginee;
import com.lowleveldesign.turnedbasedaigameenginee.api.RuleEngine;
import com.lowleveldesign.turnedbasedaigameenginee.game.Board;
import com.lowleveldesign.turnedbasedaigameenginee.game.Cell;
import com.lowleveldesign.turnedbasedaigameenginee.game.Move;
import com.lowleveldesign.turnedbasedaigameenginee.game.Player;

public class GamePlayTest {

    GameEnginee gameEnginee;
    RuleEngine ruleEngine;

    @Before
    public void setup() {
        gameEnginee = new GameEnginee();
        ruleEngine = new RuleEngine();
    }

    @Test
    public void checkForRowWin() {
        Board board = gameEnginee.start("TicTacToe");
        // make moves in loop
        int[][] firstPlayerMoves = new int[][] { { 1, 0 }, { 1, 1 }, { 1, 2 } };
        int[][] secondPlayerMoves = new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 } };
        playGame(board, firstPlayerMoves, secondPlayerMoves);

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForColWin() {
        Board board = gameEnginee.start("TicTacToe");
        // make moves in loop
        int[][] firstPlayerMoves = new int[][] { { 0, 0 }, { 1, 0 }, { 2, 0 } };
        int[][] secondPlayerMoves = new int[][] { { 0, 1 }, { 0, 2 }, { 1, 1 } };
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForDiagWin() {
        Board board = gameEnginee.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][] { { 0, 0 }, { 1, 1 }, { 2, 2 } };
        int[][] secondPlayerMoves = new int[][] { { 0, 1 }, { 0, 2 }, { 1, 0 } };
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForRevDiagWin() {
        Board board = gameEnginee.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][] { { 0, 2 }, { 1, 1 }, { 2, 0 } };
        int[][] secondPlayerMoves = new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 } };
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForComputerWin() {
        Board board = gameEnginee.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][] { { 1, 0 }, { 1, 1 }, { 2, 0 } };
        int[][] secondPlayerMoves = new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 } };
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "O");
    }

    private void playGame(Board board, int[][] firstPlayerMoves, int[][] secondPlayerMoves) {
        int row, col;
        int next = 0;
        while (!ruleEngine.getState(board).isOver()) {
            Player computer = new Player("O"), human = new Player("X");
            // Opponent Move
            row = firstPlayerMoves[next][0];
            col = firstPlayerMoves[next][1];
            Move oppMove = new Move(new Cell(row, col), human);
            gameEnginee.move(board, oppMove);

            // Computer Move
            if (!ruleEngine.getState(board).isOver()) {
                int sRow = secondPlayerMoves[next][0];
                int sCol = secondPlayerMoves[next][1];

                Move computerMove = new Move(new Cell(sRow, sCol), computer);
                gameEnginee.move(board, computerMove);
            }
            next++;
        }
    }
}
