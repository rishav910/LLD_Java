import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Tic Tac Toe
        // Entities - 2 players, Game board

        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        while (!game.isBoardFull() && !game.checkWinner()) {

            game.printBoard();
            System.out.println("Player " + game.getCurrentPlayer() + ": Enter (row, col): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (game.makeMove(row, col)) {
                System.out.println("Move successful");
            } else {
                System.out.println("Invalid move, try again!");
            }
        }

        if(game.checkWinner()) {
            System.out.println("Player " + (1-game.getCurrentPlayer()) + " wins the game");
        } else {
            System.out.println("Draw");
        }
    }
}