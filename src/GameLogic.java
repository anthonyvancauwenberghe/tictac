/**
 * Created by tony on 10/02/2017.
 */

public class GameLogic {

    private int boardSize;
    private static GameLogic instance = null;

    private Grid grid;

    public Player player1;
    public Player player2;

    private GameHistory gameHistory;
    private Game currentGame;

    private int amountOfGamesPlayed;
    private int amountOfGamesToSimulate;
    private int breakTimeBetweenGames = 0; //break in MS between games

    public GameLogic(int boardSize, Player player1, Player player2, int amountOfGamesToSimulate) {
        this.boardSize = boardSize;
        this.grid = new Grid(boardSize);

        this.player1 = player1;
        this.player2 = player2;
        this.amountOfGamesToSimulate = amountOfGamesToSimulate;
        this.amountOfGamesPlayed = 0;
         this.gameHistory = GameHistory.getInstance();
    }

    public Grid getGrid() {
        return this.grid;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public GameHistory getGameHistory() {
        return GameHistory.getInstance();
    }



    public void simulate() {
        getGameHistory().load();
        while (amountOfGamesPlayed < amountOfGamesToSimulate) {
            this.currentGame = new Game();

            if(amountOfGamesPlayed %10 ==0)
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
                } else if (grid.allCoordinatesFilled()) {
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
                } else if (grid.allCoordinatesFilled()) {
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
        getGameHistory().load();
        while (amountOfGamesPlayed < amountOfGamesToSimulate) {
            this.currentGame = new Game();
            System.out.println(this.grid.toString());
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
                } else if (grid.allCoordinatesFilled()) {
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
                } else if (grid.allCoordinatesFilled()) {
                    System.out.println("It's a draw!");
                    currentGame.isDraw();
                    GameHistory.getInstance().add(currentGame);
                    break;
                }

                System.out.println(getGrid().toString());
            }
            System.out.println(getGrid().toString());
            System.out.println(currentGame.toString());

            amountOfGamesPlayed++;
            grid.resetGrid();
            GameHistory.getInstance().save();
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

    private boolean gameFinished() {

        /* CHECK FOR 3 SEQUENCES */
        if (grid.horizontalRowSequence() || grid.verticalRowSequence() || grid.diagonalRowSequence()) {
            return true;
        }

        return false;
    }

    public static GameLogic getInstance() {
        return instance;
    }

    public static void createInstance(int boardSize, Player player1, Player player2, int amountOfGamesToSimulate) {
        instance = new GameLogic(boardSize, player1, player2, amountOfGamesToSimulate);
    }

}
