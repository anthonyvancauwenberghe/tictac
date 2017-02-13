/**
 * Created by tony on 12/02/2017.
 */
public class Application {

    public void playVSAiBot(int boardsize) {
        System.out.println("");
        System.out.println("");
        System.out.println("STARTING GAME...");
        System.out.println("");
        System.out.println("");

        Player human = new Human();
        Player bot = new AIBot();
        GameLogic.createInstance(boardsize, human, bot, 1);
        GameLogic.getInstance().gameLoop();

    }

    public void playVSRandomBot(int boardsize) {
        System.out.println("");
        System.out.println("");
        System.out.println("STARTING GAME...");
        System.out.println("");
        System.out.println("");

        Player human = new Human();
        Player bot = new RandomBot();
        GameLogic.createInstance(boardsize, human, bot, 1);
        GameLogic.getInstance().gameLoop();

    }

    public void trainBotWithRandomness(int boardSize, int amountOfGamesToSimulate) {
        Player bot1 = new RandomBot(1);
        Player bot2 = new RandomBot(2);

        GameLogic.createInstance(boardSize, bot1, bot2, amountOfGamesToSimulate);
        GameLogic.getInstance().simulate();

    }

    public void trainBotWithIntelligence(int boardSize, int amountOfGamesToSimulate) {
        Player bot1 = new AIBot(1);
        Player bot2 = new AIBot(2);

        GameLogic.createInstance(boardSize, bot1, bot2, amountOfGamesToSimulate);
        GameLogic.getInstance().simulate();

    }

    public static void main(String args[]) {
        Application app = new Application();


        app.playVSAiBot(3);
        //app.playVSRandomBot(3);


        /* FOR TRAINING THE BOT WITH SIMULATIONS */

        //int amountOfGamesToSimulate = 10000;
        //app.trainBotWithIntelligence(3,amountOfGamesToSimulate);
        //app.trainBotWithRandomness(3,amountOfGamesToSimulate);
    }
}
