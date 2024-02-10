package com.lowleveldesign.turnedbasedaigameenginee;

import java.util.Scanner;

import com.lowleveldesign.turnedbasedaigameenginee.api.GameEnginee;
import com.lowleveldesign.turnedbasedaigameenginee.game.Board;
import com.lowleveldesign.turnedbasedaigameenginee.game.Cell;
import com.lowleveldesign.turnedbasedaigameenginee.game.Move;
import com.lowleveldesign.turnedbasedaigameenginee.game.Player;

public class Main {
    public static void main(String[] args) {
        GameEnginee gameEnginee = new GameEnginee();
        Board board = gameEnginee.start("TicTacToe");
        Scanner scanner = new Scanner(System.in);
        int row, col;

        // make moves
        Player computer = new Player("O"), human = new Player("X");
        while (!gameEnginee.isComplete(board).isOver()) {
            // Opponent Move
            System.out.println("Make your move!");
            System.out.println(board);
            row = scanner.nextInt();
            col = scanner.nextInt();
            Move oppMove = new Move(new Cell(row, col));
            gameEnginee.move(board, human, oppMove);

            System.out.println(board);

            // Computer Move
            if (!gameEnginee.isComplete(board).isOver()) {
                Move computerMove = gameEnginee.suggestMove(board);
                gameEnginee.move(board, computer, computerMove);
            }
        }
        System.out.println("Game Result : " + gameEnginee.isComplete(board));
        System.out.println(board);
        ;
    }
}
