import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Connect4 class
 * @author Riya Gunda
 */
public class Connect4 {

    /**
     * This int represents the number 4 for matching requirements of rows, columns, or diagonals
     */
    public static final int NUMBER_4 = 4;

    /**
     * This int represents the amount of rows on game board
     */
    public static final int ROWS = 8;
    
    /**
     * This int represents the amount of columns on the
     * game board
     */
    public static final int COLUMNS = 8;
    
    /**
     * This char array represents where each game piece is
     * on the game board. Each index represents a spot.
     */
    public static char[][] board = new char[ROWS][COLUMNS];

    /**
     * This int represents the last playable row on the
     * game board
     */    
    public static int lastMoveRow = -1;

    /**
     * This int represents the last playable column
     * on the game board
     */
    public static int lastMoveCol = -1;

    /**
     * This method maintains the game's operation.
     * It initiallizes, updates, and displays the board, recieves
     * input from the players, and outputs player data.
     * @param args String for representation of board
     * @throws IndexOutOfBoundsException when the column input is not between 1-8
     * @throws InputMismatchException when the column input is not an int
     */
    public static void main(String[] args) {
        // Initialize the board with empty cells
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = '-';
            }
        }
        // Display the initial board
        System.out.println("Welcome to Connect 4!");
        printUpdatedGrid();

        // Play the game
        Scanner input = new Scanner(System.in);
        char currentPlayer = 'X';
        boolean gameOver = false;
        while (!gameOver) {
            // Ask the current player to input a column number
            System.out.print("Player " + currentPlayer + ", choose a column (1-8): ");
            int column;
            try {
                column = input.nextInt() - 1;
                if (column < 0 || column >= COLUMNS) {
                    throw new IndexOutOfBoundsException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number between 1 and 8.");
                input.nextLine(); // consume the invalid input
                continue;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid input, please enter a number between 1 and 8.");
                continue;
            }
            // Find the first empty cell in the chosen column
            int row = ROWS - 1;
            while (row >= 0 && board[row][column] != '-') {
                row--;
            }
            // Check if the column is full
            if (row < 0) {
                System.out.println("Column " + (column + 1) 
                    + " is full, please choose another column");
                continue;
            }
            // Update the board with the current player's move
            board[row][column] = currentPlayer;
            lastMoveRow = row;
            lastMoveCol = column;
            printUpdatedGrid();
            // Check if the game is over
            if (checkRow() || checkColumn() || checkDiagonal()) {
                System.out.println("Player " + currentPlayer + " won!");
                gameOver = true;
            } else if (isBoardFull()) {
                System.out.println("Game over, it's a tie!");
                gameOver = true;
            }
            // Switch to the other player
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

     /**
     * This method updates the grid's display to
     * its most recent version reflecting the
     * gameplay.
     */
    public static void printUpdatedGrid() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print("|" + board[i][j]);
            }
            System.out.println("|");
        }
        System.out.println();
    }

    /**
    * Checks the row in which the most recent move
    * was made to see if the game was won and returns a boolean
    *
    * @return Returns true if the game has been won, through 4
    * consecutive horizontal same pieces, otherwise returns false.
    *
    * @throws IndexOutOfBoundsException if the lastMoveRow or
    * lastMoveCol is less than or exceeds the amount of
    * rows on the board  
     */
    public static boolean checkRow() {
        if (lastMoveRow < 0 || lastMoveRow >= ROWS || 
            lastMoveCol < 0 || lastMoveCol >= COLUMNS) {
            throw new IndexOutOfBoundsException("Invalid last move position");
        }
        int count = 0;
        char player = board[lastMoveRow][lastMoveCol];
        for (int j = 0; j < COLUMNS; j++) {
            if (board[lastMoveRow][j] == player) {
                count++;
                if (count == NUMBER_4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    /**
     * This method checks the column in which the most recent move was
     * made to see if the game was won and returns a boolean.
     *
     * @return Returns true if the game has been won, through 4
     * consecutive vertical same pieces, otherwise returns false.
     *
     * @throws IndexOutOfBoundsException if the index exceeds the number of rows
     */
    public static boolean checkColumn() {
        if (lastMoveRow < 0 || lastMoveRow >= ROWS || 
            lastMoveCol < 0 || lastMoveCol >= COLUMNS) {
            throw new IndexOutOfBoundsException("Invalid last move position");
        }
        int count = 0;
        char player = board[lastMoveRow][lastMoveCol];
        for (int i = 0; i < ROWS; i++) {
            if (board[i][lastMoveCol] == player) {
                count++;
                if (count == NUMBER_4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }
    
    /**
     * This checks both the diagonals of the move that was
     * recently made to see if the game was won and returns a boolean.
     *
     * @return Returns true if the game has been won, through 4
     * consecutive diagonal same pieces, otherwise returns false.
     *
     *
     */
    public static boolean checkDiagonal() {
        int count1 = 0;
        int count2 = 0;
        char player = board[lastMoveRow][lastMoveCol];
        // Check the diagonal from top-left to bottom-right
        for (int i = lastMoveRow - Math.min(lastMoveRow, lastMoveCol), 
            j = lastMoveCol - Math.min(lastMoveRow, lastMoveCol); 
                i < ROWS && j < COLUMNS; i++, j++) {
            if (board[i][j] == player) {
                count1++;
                if (count1 == NUMBER_4) {
                    return true;
                }
            } else {
                count1 = 0;
            }
        }
        // Check the diagonal from bottom-left to top-right
        for (int i = lastMoveRow + Math.min(ROWS - 1 - lastMoveRow, lastMoveCol), 
            j = lastMoveCol - Math.min(ROWS - 1 - lastMoveRow, lastMoveCol); 
                i >= 0 && j < COLUMNS; i--, j++) {
            if (board[i][j] == player) {
                count2++;
                if (count2 == NUMBER_4) {
                    return true;
                }
            } else {
                count2 = 0;
            }
        }
        return false;
    }

    /**
     * This method checks if the board is full or
     * has any more indices left to place
     * game pieces on
     *
     * @return Returns false if the board has
     * indices with a -, otherwise returns true.
     */
    public static boolean isBoardFull() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
