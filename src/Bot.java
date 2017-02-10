import java.util.Scanner;

/**
 * Created by tony on 10/02/2017.
 */
public class Bot extends Player {

    public Bot() {
        /* Bot has PlayerID 1! */
        this.playerID = 1;
    }

    public void move(){
        botIsThinking();
    }


    public void botIsThinking() {

        try {
            Thread.sleep(500);
            System.out.println("The bot is starting to think about his move:");
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

        try {
            Thread.sleep(700);
            System.out.print(".");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            Thread.sleep(1000);
            System.out.println("");
            System.out.println("FOUND A MOVE!");
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
