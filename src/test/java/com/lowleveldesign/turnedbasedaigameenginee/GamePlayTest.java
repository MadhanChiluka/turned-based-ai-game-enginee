package com.lowleveldesign.turnedbasedaigameenginee;

import java.util.Scanner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lowleveldesign.turnedbasedaigameenginee.api.AIEngine;
import com.lowleveldesign.turnedbasedaigameenginee.api.GameEnginee;
import com.lowleveldesign.turnedbasedaigameenginee.api.RuleEngine;
import com.lowleveldesign.turnedbasedaigameenginee.game.Board;
import com.lowleveldesign.turnedbasedaigameenginee.game.Cell;
import com.lowleveldesign.turnedbasedaigameenginee.game.Move;
import com.lowleveldesign.turnedbasedaigameenginee.game.Player;

public class GamePlayTest {

    GameEnginee gameEnginee;
    AIEngine aiEngine;
    RuleEngine ruleEngine;

    @Before
    public void setup() {
        gameEnginee = new GameEnginee();
        aiEngine = new AIEngine();
        ruleEngine = new RuleEngine();
    }

    @Test
    public void checkForRowWin() {
        Board board = gameEnginee.start("TicTacToe");
        // make moves in loop
        int moves[][] = new int[][] { { 1, 0 }, { 1, 1 }, { 1, 2 } };
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForColWin() {
        Board board = gameEnginee.start("TicTacToe");
        // make moves in loop
        int moves[][] = new int[][] { { 1, 0 }, { 1, 1 }, { 1, 2 } };
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForDiagWin() {
        Board board = gameEnginee.start("TicTacToe");
        int[][] moves = new int[][] { { 0, 0 }, { 1, 1 }, { 2, 2 } };
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForRevDiagWin() {
        Board board = gameEnginee.start("TicTacToe");
        int[][] moves = new int[][] { { 0, 2 }, { 1, 1 }, { 2, 0 } };
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForComputerWin() {
        Board board = gameEnginee.start("TicTacToe");
        int[][] moves = new int[][] { { 1, 0 }, { 1, 1 }, { 2, 0 } };
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "O");
    }

    private void playGame(Board board, int[][] moves) {
        int row, col;
        int next = 0;
        while (!ruleEngine.getState(board).isOver()) {
            Player computer = new Player("O"), human = new Player("X");
            // Opponent Move
            row = moves[next][0];
            col = moves[next][1];
            next++;
            Move oppMove = new Move(new Cell(row, col), human);
            gameEnginee.move(board, oppMove);

            // Computer Move
            if (!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEnginee.move(board, computerMove);
            }
        }
    }
}
