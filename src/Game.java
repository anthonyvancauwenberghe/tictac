/**
 * Created by tony on 10/02/2017.
 */
public class Game {
    private static int boardSize = 3;
    private static Game instance = null;

    private Grid grid;
    private Bot bot;
    private Human human;
    private GameSequences sequence;
    private boolean gameContinueing;

    private Game(int boardSize) {
        this.grid = new Grid(boardSize);
        this.bot = new Bot();
        this.human = new Human();
        this.gameContinueing = true;
        this.sequence = new GameSequences();

        System.out.println(this.grid.toString());
    }

    public Grid getGrid() {
        return this.grid;
    }

    private void startGame() {
        while (true) {

            while (gameContinueing) {
                if (!gameFinished()) {
                    int move = human.move();
                    sequence.addDecission(move);
                } else {
                    sequence.addLostGameSequence();
                    break;
                }

                if(allCoordinatesFilled()){
                    System.out.println("It's a draw!");
                    break;
                }

                if (!gameFinished()) {
                    bot.isThinking();
                    int move = bot.move();
                    sequence.addDecission(move);
                } else {
                    sequence.addWonGameSequence();
                    break;
                }

                if(allCoordinatesFilled()){
                    System.out.println("It's a draw!");
                    break;
                }

            }
            grid.resetGrid();
            sequence.resetCurrentSequence();
        }

    }

    private boolean verticalRowSequence() {
        int size = boardSize;
        int[][] grid = transposeArray(this.grid.generateGridArray());

        for (int y = 0; y < size; y++) {
            int playerID = 0;
            int successfullRows = 0;

            for (int x = 0; x < size; x++) {
                if (grid[x][y] != 0) {


                    if (x == 0) {
                        playerID = grid[x][y];
                        successfullRows = 1;
                    } else {

                        if (playerID == grid[x][y]) {
                            successfullRows++;
                        } else {
                            playerID = grid[x][y];
                            successfullRows = 1;
                        }
                    }

                    if (successfullRows >= 3) {
                        return true;
                    }
                }

            }

        }
        return false;
    }

    private boolean horizontalRowSequence() {
        int size = boardSize;
        int[][] grid = this.grid.generateGridArray();

        for (int y = 0; y < size; y++) {
            int playerID = 0;
            int successfullRows = 0;

            for (int x = 0; x < size; x++) {
                if (grid[x][y] != 0) {


                    if (x == 0) {
                        playerID = grid[x][y];
                        successfullRows = 1;
                    } else {

                        if (playerID == grid[x][y]) {
                            successfullRows++;
                        } else {
                            playerID = grid[x][y];
                            successfullRows = 1;
                        }
                    }

                    if (successfullRows >= 3) {
                        return true;
                    }
                }

            }

        }
        return false;
    }

    private boolean diagonalRowSequence() {
        int size = boardSize;
        int[][] grid = this.grid.generateGridArray();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int successfullRowsUL = 0;
                int successfullRowsUR = 0;
                int successfullRowsDL = 0;
                int successfullRowsDR = 0;
                int playerID = grid[x][y];
                if (grid[x][y] != 0) {

                    for (int i = 0; i < 3; i++) {

                    /* CHECK LEFT UPPER SEQUENCE */
                        try {
                            if (grid[x - i][y - i] == playerID) {
                                successfullRowsUL++;
                            } else {
                                successfullRowsUL = 0;
                                break;
                            }
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            break;
                        }
                    }

                    for (int i = 0; i < 3; i++) {
                    /* CHECK RIGHT UPPER SEQUENCE */
                        try {
                            if (grid[x + i][y - i] == playerID) {
                                successfullRowsUR++;
                            } else {
                                successfullRowsUR = 0;
                                break;
                            }
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            break;
                        }
                    }

                    for (int i = 0; i < 3; i++) {
                    /* CHECK LEFT DOWN SEQUENCE */
                        try {
                            if (grid[x - i][y + i] == playerID) {
                                successfullRowsDL++;
                            } else {
                                successfullRowsDL = 0;
                                break;
                            }
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            break;
                        }
                    }

                    for (int i = 0; i < 3; i++) {
                    /* CHECK RIGHT DOWN SEQUENCE */
                        try {
                            if (grid[x + i][y + i] == playerID) {
                                successfullRowsDR++;
                            } else {
                                successfullRowsDR = 0;
                                break;
                            }
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            break;
                        }

                    }


                }
                if (successfullRowsUR >= 3 || successfullRowsUL >= 3 || successfullRowsDR >= 3 || successfullRowsDL >= 3) {
                    return true;
                }

            }

        }
        return false;
    }

    private boolean allCoordinatesFilled() {
        int size = boardSize;
        int[][] grid = transposeArray(this.grid.generateGridArray());

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (grid[x][y] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] transposeArray(int[][] matrix) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = i + 1; j < boardSize; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        return matrix;
    }

    private boolean gameFinished() {

        /* CHECK FOR 3 SEQUENCES */
        if (horizontalRowSequence() || verticalRowSequence() || diagonalRowSequence()) {
            System.out.println("Game Finished");
            return true;
        }

        return false;
    }


    public static Game getInstance() {
        if (instance == null) {
            instance = new Game(boardSize);
        }
        return instance;
    }

    public static void main(String args[]) {

        Game game = Game.getInstance();
        game.startGame();

    }
}
