import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author Tia Burmi
 * @author Akhil Mitta
 * @author Lavan Aditya Kakarla Kirankumar
 * @author Riya Gunda
 * @author Siddhant Joshi
 * @author Carmella Holloway
 */

class Connect4Test {

    /**
    * Sets up the game board with '-' in every cell before each test case.
    */
    @BeforeEach
    void setUp() {
        for (int i = 0; i < Connect4.ROWS; i++) {
            for (int j = 0; j < Connect4.COLUMNS; j++) {
                Connect4.board[i][j] = '-';
            }
        }
    }

    /**
    * Tests if the checkRow method returns true when there is a win in a row.
    */
    @Test
    void checkRow() {
        Connect4.board[7][0] = 'X';
        Connect4.board[7][1] = 'X';
        Connect4.board[7][2] = 'X';
        Connect4.board[7][3] = 'X';
        Connect4.lastMoveRow = 7;
        Connect4.lastMoveCol = 3;
        assertTrue(Connect4.checkRow());

        setUp();

        Connect4.board[7][0] = 'X';
        Connect4.board[7][1] = 'X';
        Connect4.board[7][2] = '-';
        Connect4.board[7][3] = 'X';
        Connect4.lastMoveRow = 7;
        Connect4.lastMoveCol = 3;
        assertFalse(Connect4.checkRow());
    }

    /**
    * Tests if the checkColumn method returns true when there is a win in a column.
    */
    @Test
    void checkColumn() {
        Connect4.board[7][0] = 'X';
        Connect4.board[6][0] = 'X';
        Connect4.board[5][0] = 'X';
        Connect4.board[4][0] = 'X';
        Connect4.lastMoveRow = 4;
        Connect4.lastMoveCol = 0;
        assertTrue(Connect4.checkColumn());

        setUp();

        Connect4.board[7][0] = 'X';
        Connect4.board[6][0] = 'X';
        Connect4.board[5][0] = '-';
        Connect4.board[4][0] = 'X';
        Connect4.lastMoveRow = 4;
        Connect4.lastMoveCol = 0;
        assertFalse(Connect4.checkColumn());
    }

    /**
    * Tests if the checkDiagonal method returns true when there is a win in a diagonal.
    */
    @Test
    void checkDiagonal() {
        Connect4.board[7][0] = 'X';
        Connect4.board[6][1] = 'X';
        Connect4.board[5][2] = 'X';
        Connect4.board[4][3] = 'X';
        Connect4.lastMoveRow = 4;
        Connect4.lastMoveCol = 3;
        assertTrue(Connect4.checkDiagonal());

        setUp();

        Connect4.board[7][0] = 'X';
        Connect4.board[6][1] = 'X';
        Connect4.board[5][2] = '-';
        Connect4.board[4][3] = 'X';
        Connect4.lastMoveRow = 4;
        Connect4.lastMoveCol = 3;
        assertFalse(Connect4.checkDiagonal());
    }

    /**
    * Test whether the isBoardFull() method returns true when all positions on the
    * game board are filled with 'X' characters, and false when there is at least
    * one empty position on the board.
    */
    @Test
    void isBoardFull() {
        for (int i = 0; i < Connect4.ROWS; i++) {
            for (int j = 0; j < Connect4.COLUMNS; j++) {
                Connect4.board[i][j] = 'X';
            }
        }
        assertTrue(Connect4.isBoardFull());

        setUp();

        Connect4.board[7][0] = 'X';
        Connect4.board[6][0] = 'X';
        Connect4.board[5][0] = '-';
        Connect4.board[4][0] = 'X';
        assertFalse(Connect4.isBoardFull());
    }

    /**
    * Test whether the checkColumn() method correctly identifies a vertical win
    * when there are four consecutive 'O' characters in a column on the game board.
    */
    @Test
    void testGameplay1() {
        Connect4.board[7][0] = 'X';
        Connect4.board[7][1] = 'X';
        Connect4.board[7][2] = 'X';
        Connect4.board[7][3] = 'X';
        Connect4.lastMoveRow = 7;
        Connect4.lastMoveCol = 3;
        assertTrue(Connect4.checkRow());
    }

    /**
    * Test whether the checkDiagonal() method correctly identifies a horizontal win
    * when there are four consecutive 'X' characters in a horizontal line on the
    * game board.
 */
    @Test
    void testGameplay2() {
        Connect4.board[7][0] = 'O';
        Connect4.board[6][0] = 'O';
        Connect4.board[5][0] = 'O';
        Connect4.board[4][0] = 'O';
        Connect4.lastMoveRow = 4;
        Connect4.lastMoveCol = 0;
        assertTrue(Connect4.checkColumn());
    }

    /**
    * Test whether the checkDiagonal() method correctly identifies a diagonal win
    * when there are four consecutive 'X' characters in a diagonal line on the
    * game board.
    */
    @Test
    void testGameplay3() {
        Connect4.board[7][0] = 'X';
        Connect4.board[6][1] = 'X';
        Connect4.board[5][2] = 'X';
        Connect4.board[4][3] = 'X';
        Connect4.lastMoveRow = 4;
        Connect4.lastMoveCol = 3;
        assertTrue(Connect4.checkDiagonal());
    }
}