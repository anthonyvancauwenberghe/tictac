import java.util.Scanner;

/**
 * Created by tony on 10/02/2017.
 */
public class Game {
    private static int boardSize = 3;
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

        while (gameContinueing) {
            if(!gameFinished()){
                human.move();
            }


            if(!gameFinished())
            bot.move();
        }

    }


    private boolean gameFinished(){
        int[][] grid = this.grid.generateGridArray();
        int size = Game.boardSize;

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if(grid[x][y]==0)
                    break;
            }

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
