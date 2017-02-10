import java.util.Scanner;

/**
 * Created by tony on 10/02/2017.
 */
public class Human implements Player {

    /* If there are multiple players we could set PlayerID's so coded the structure like this */
    /* Since we are only using one player i'm not going to use it */
    private int playerID;

    public Human() {
        /* Human has PlayerID 2! */
        this.playerID = 2;
    }

    private Grid getGrid(){
        return Game.getInstance().getGrid();
    }

    @Override
    public void fillCoordinate(int x, int y) {
        getGrid().getCoordinate(x, y).setContent(getPlayerID());
    }

    private int getPlayerID() {
        return playerID;
    }

    public void move(){
        Scanner reader = new Scanner(System.in);
        System.out.println("");
        System.out.println("Enter X Location: ");
        int x = reader.nextInt();

        System.out.println("Enter Y location");
        int y = reader.nextInt();

        verifyMove(x,y);

    }

    private void verifyMove(int x, int y){
        if(getGrid().getCoordinate(x,y).getContent()==0)
            fillCoordinate(x,y);
        else {
            System.out.println("");
            System.out.println("********************* ERROR ************************ ");
            System.out.println("Location Already taken, Please Re-Enter Coordinates ");
            System.out.println("");
            move();
        }
        System.out.println(getGrid().toString());
    }
}
