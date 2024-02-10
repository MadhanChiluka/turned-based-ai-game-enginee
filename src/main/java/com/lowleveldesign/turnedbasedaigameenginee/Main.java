package com.lowleveldesign.turnedbasedaigameenginee;

import java.util.Scanner;

import com.lowleveldesign.turnedbasedaigameenginee.api.AIEngine;
import com.lowleveldesign.turnedbasedaigameenginee.api.GameEnginee;
import com.lowleveldesign.turnedbasedaigameenginee.api.RuleEngine;
import com.lowleveldesign.turnedbasedaigameenginee.game.Board;
import com.lowleveldesign.turnedbasedaigameenginee.game.Cell;
import com.lowleveldesign.turnedbasedaigameenginee.game.Move;
import com.lowleveldesign.turnedbasedaigameenginee.game.Player;

public class Main {
    public static void main(String[] args) {
        GameEnginee gameEnginee = new GameEnginee();
        AIEngine aiEngine = new AIEngine();
        RuleEngine ruleEngine = new RuleEngine();
        Board board = gameEnginee.start("TicTacToe");
        Scanner scanner = new Scanner(System.in);
        int row, col;

        // make moves
        Player computer = new Player("O"), human = new Player("X");
        while (!ruleEngine.getState(board).isOver()) {
            // Opponent Move
            System.out.println("Make your move!");
            System.out.println(board);
            row = scanner.nextInt();
            col = scanner.nextInt();
            Move oppMove = new Move(new Cell(row, col), human);
            gameEnginee.move(board, oppMove);

            System.out.println(board);

            // Computer Move
            if (!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEnginee.move(board, computerMove);
            }
        }
        System.out.println("Game Result : " + ruleEngine.getState(board));
        System.out.println(board);
    }
}
