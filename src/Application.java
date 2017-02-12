/**
 * Created by tony on 12/02/2017.
 */
public class Application {

    public void start(int boardsize, Player player1, Player player2) {
        System.out.println("");
        System.out.println("");
        System.out.println("STARTING GAME...");
        System.out.println("");
        System.out.println("");

        GameLogic.createInstance(boardsize, player1, player2);
        GameLogic.getInstance().getGameHistory().load();
        GameLogic.getInstance().gameLoop();

    }

    public void trainBot() {
        int boardsize = 3;
        Player bot1 = new RandomBot(1);
        Player bot2 = new RandomBot(2);

        GameLogic.createInstance(boardsize, bot1, bot2);
        GameLogic.getInstance().simulate();

    }

    public static void main(String args[]) {
        Application app = new Application();

        int boardsize = 3;
        Player human = new Human();
        Player bot = new AIBot();

        //app.start(boardsize, human, bot);
        app.trainBot();
    }
}
