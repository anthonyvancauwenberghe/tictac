import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by tony on 10/02/2017.
 */
public class Bot extends Player {

    public Bot() {
        /* Bot has PlayerID 1! */
        this.playerID = 1;
    }


    public int move(){

        int x = ThreadLocalRandom.current().nextInt(0, getGrid().getSize());
        int y = ThreadLocalRandom.current().nextInt(0, getGrid().getSize());
        System.out.println("bot Move: x: " + x + " y: " + y);
        verifyMove(x,y);

        System.out.println(getGrid().toString());
        return x + getGrid().getSize()*y;
    }


    public void isThinking() {

        try {
            Thread.sleep(50);
            System.out.println("The bot is starting to think about his move:");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(100);
            System.out.print(".");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(300);
            System.out.print(".");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(500);
            System.out.print(".");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            Thread.sleep(200);
            System.out.println("");
            System.out.println("FOUND A MOVE!");
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
