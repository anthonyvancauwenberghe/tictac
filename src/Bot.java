import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by tony on 10/02/2017.
 */
abstract public class Bot extends Player {

    public Bot() {

        /* Bot has PlayerID 1! */
        this.playerID = 1;
        this.name = "BOT";
    }

    public Bot(int id) {

        /* Bot has PlayerID 1! */
        this.playerID = id;
        this.name = "Bot"+id;
    }

    public void isThinking() {

        try {
            Thread.sleep(50);
            System.out.println("The bot is starting to think about his move:");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(200);
            System.out.print(".");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(200);
            System.out.print(".");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(200);
            System.out.print(".");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(200);
            System.out.println("");
            System.out.println("FOUND A MOVE!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
