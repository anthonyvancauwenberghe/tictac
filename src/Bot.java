import java.util.Scanner;

/**
 * Created by tony on 10/02/2017.
 */
public class Bot implements Player {

    /* If there are multiple players we could set PlayerID's so coded the structure like this */
    /* Since we are only using one player i'm not going to use it */
    private int playerID;

    public Bot() {
        /* Bot has PlayerID 1! */
        this.playerID = 1;
    }

    private Grid getGrid(){
        return Game.getInstance().getGrid();
    }

    @Override
    public void fillCoordinate(int x, int y) {
        Game.getInstance().getGrid().getCoordinate(x, y).setContent(getPlayerID());
    }

    public int getPlayerID() {
        return playerID;
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
