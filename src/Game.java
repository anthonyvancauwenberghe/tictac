import java.util.Scanner;

/**
 * Created by tony on 10/02/2017.
 */
public class Game {
    private static int boardSize = 9;
    private static Game instance = null;

    private Grid grid;
    private Bot bot;
    private Human human;
    private boolean gameContinueing;

    private Game(int boardSize) {
        this.grid = new Grid(boardSize);
        this.bot = new Bot();
        this.human = new Human();
        this.gameContinueing = true;

        System.out.println(this.grid.toString());
    }

    public Grid getGrid() {
        return this.grid;
    }

    private void startGame() {
        while(true){

            while (gameContinueing) {
                if (!gameFinished()) {
                    human.move();
                } else {
                    //TODO: ADD PLAYSEQUENCE TO DECISSION TREE HERE
                    break;
                }

                if (!gameFinished()) {
                    bot.move();
                } else {
                    break;
                }

            }
            grid.resetGrid();
        }

    }

    private boolean horizontalRowSequence(int[][] grid){
        int size = boardSize;
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

    private int[][] transposeArray(int[][] matrix){
        for(int i = 0; i < boardSize; i++) {
            for(int j = i+1; j < boardSize; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        return matrix;
    }


    private boolean gameFinished() {
        int[][] grid = this.grid.generateGridArray();

        /* CHECK FOR HORIZONTAL SEQUENCE */
        if(horizontalRowSequence(grid)){
            System.out.println("Game Finished");
            return true;
        }

        /* CHECK FOR VERTICAL SEQUENCE */
        if(horizontalRowSequence(transposeArray(grid))){
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
