public class TicTacToe {
    private String[][] board = new String[3][3];
    private int currentPlayer;

    public TicTacToe () {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ".";
            }
        }
        currentPlayer = 1;
    }

    public void printBoard () {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + "\t|");
            }
            System.out.println("\n________________");
        }
    }

    public boolean isBoardFull () {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j].equals(".")) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean makeMove(int row, int col) {
        if (row < 0 || col < 0 || row >= 3 || col >= 3 || !board[row][col].equals(".")) {
            return false;
        }

        board[row][col] = (currentPlayer == 1) ? "X" : "0";
        currentPlayer = 1-currentPlayer;

        return true;
    }

    boolean checkWinner() {
        for(int i=0; i<3; i++) {
            if(!board[i][0].equals(".") && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                return true;
            }
            if(!board[0][i].equals(".") && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                return true;
            }
        }
        // Check 2 diagonals
        if(!board[0][0].equals(".") && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            return true;
        }
        if(!board[0][2].equals(".") && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            return true;
        }
        return false;
    }

    public int getCurrentPlayer () {
        return currentPlayer;
    }
}
