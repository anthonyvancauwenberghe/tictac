import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by tony on 10/02/2017.
 */
public class GameLogic {

    private static int boardSize;
    private static GameLogic instance = null;

    private Grid grid;

    private Player player1;
    private Player player2;

    private GameHistory gameHistory;
    private Game currentGame;

    private int amountOfGamesPlayed;
    private int maxAmountOfGamesToPlay = 10000000;
    private int breakTimeBetweenGames = 0; //break in MS between games

    public GameLogic(int boardSize, Player player1, Player player2) {
        this.boardSize = boardSize;
        this.grid = new Grid(boardSize);

        this.player1 = player1;
        this.player2 = player2;

        this.gameHistory = new GameHistory();

        this.amountOfGamesPlayed = 0;

        System.out.println(this.grid.toString());
    }

    public Grid getGrid() {
        return this.grid;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public GameHistory getGameHistory() {
        return this.gameHistory;
    }



    public void run() {
        while (amountOfGamesPlayed < maxAmountOfGamesToPlay) {
            this.currentGame = new Game();
            System.out.println(amountOfGamesPlayed);

            while (true) {
            /* PROCESS HUMAN MOVES */
                int humanMove = player1.move();
                currentGame.addMove(humanMove);

                /* CHECK IF GAME HAS ENDED */
                if (gameFinished()) {
                    currentGame.botIsLoser();
                    gameHistory.add(currentGame);
                    break;
                } else if (allCoordinatesFilled()) {
                    currentGame.isDraw();
                    gameHistory.add(currentGame);
                    break;
                }

                /* PROCESS BOT MOVES */
                int botMove = player2.move();
                currentGame.addMove(botMove);

                /* CHECK IF GAME HAS ENDED */
                if (gameFinished()) {
                    currentGame.botIsWinner();
                    gameHistory.add(currentGame);

                    break;
                } else if (allCoordinatesFilled()) {
                    currentGame.isDraw();
                    gameHistory.add(currentGame);
                    break;
                }
            }

            amountOfGamesPlayed++;
            grid.resetGrid();

        }
            gameHistory.save();
    }

    public void simulate() {

        while (amountOfGamesPlayed < maxAmountOfGamesToPlay) {
            this.currentGame = new Game();

            if(amountOfGamesPlayed %1000 ==0)
                System.out.println(amountOfGamesPlayed);
            while (true) {
            /* PROCESS HUMAN MOVES */
                int humanMove = player1.move();
                currentGame.addMove(humanMove);

                /* CHECK IF GAME HAS ENDED */
                if (gameFinished()) {
                    currentGame.botIsLoser();
                    GameHistory.getInstance().add(currentGame);
                    break;
                } else if (allCoordinatesFilled()) {
                    currentGame.isDraw();
                    GameHistory.getInstance().add(currentGame);
                    break;
                }

                /* PROCESS BOT MOVES */
                int botMove = player2.move();
                currentGame.addMove(botMove);

                /* CHECK IF GAME HAS ENDED */
                if (gameFinished()) {
                    currentGame.botIsWinner();
                    GameHistory.getInstance().add(currentGame);
                    break;
                } else if (allCoordinatesFilled()) {
                    currentGame.isDraw();
                    GameHistory.getInstance().add(currentGame);
                    break;
                }
            }

            amountOfGamesPlayed++;
            grid.resetGrid();

        }
        GameHistory.getInstance().save();
    }


    public void gameLoop() {

        while (amountOfGamesPlayed < maxAmountOfGamesToPlay) {
            this.currentGame = new Game();

            while (true) {
            /* PROCESS HUMAN MOVES */
                int humanMove = player1.move();
                currentGame.addMove(humanMove);

                /* CHECK IF GAME HAS ENDED */
                if (gameFinished()) {
                    System.out.println(player1.getName() + " HAS WON");
                    currentGame.botIsLoser();
                    GameHistory.getInstance().add(currentGame);
                    break;
                } else if (allCoordinatesFilled()) {
                    System.out.println("It's a draw!");
                    currentGame.isDraw();
                    GameHistory.getInstance().add(currentGame);
                    break;
                }

                System.out.println(getGrid().toString());

                /* PROCESS BOT MOVES */
                int botMove = player2.move();
                currentGame.addMove(botMove);

                /* CHECK IF GAME HAS ENDED */
                if (gameFinished()) {
                    System.out.println(player2.getName() + " HAS WON");
                    currentGame.botIsWinner();
                    GameHistory.getInstance().add(currentGame);
                    break;
                } else if (allCoordinatesFilled()) {
                    System.out.println("It's a draw!");
                    currentGame.isDraw();
                    GameHistory.getInstance().add(currentGame);
                    break;
                }

                System.out.println(getGrid().toString());
            }
            System.out.println(currentGame.getFormattedName());

            amountOfGamesPlayed++;
            grid.resetGrid();

            if (breakTimeBetweenGames != 0) {
            /* add Break */
                try {
                    Thread.sleep(breakTimeBetweenGames);
                } catch (Exception e) {

                }
            }
        }
        GameHistory.getInstance().save();
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
            return true;
        }

        return false;
    }

    public static GameLogic getInstance() {
        return instance;
    }

    public static void createInstance(int boardSize, Player player1, Player player2) {
        instance = new GameLogic(boardSize, player1, player2);
    }

}
