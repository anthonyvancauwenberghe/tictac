import java.util.Scanner;

/**
 * Created by tony on 10/02/2017.
 */
public class Human extends Player {

    public Human() {

        /* Human has PlayerID 2! */
        this.playerID = 2;
        this.name = "Human";
    }

    public Coordinate generateMove() {


        int x = -1;
        int y = -1;

        System.out.println("");

        while ((x < 0 || x >= getGrid().getSize())) {
            x = getXLocationFromInput();
        }

        while ((y < 0 || y >= getGrid().getSize())) {
            y = getYLocationFromInput();
        }

        return new Coordinate(x, y);
    }

    public int getXLocationFromInput() {
        int x;
        System.out.println("Enter X Location (0-" + (GameLogic.getInstance().getGrid().getSize() - 1) + "): ");
        try {
            Scanner reader = new Scanner(System.in);
            x = reader.nextInt();
        } catch (Exception e) {
            System.out.println("Please Enter A Valid X Location");
            x = -1;
        }

        return x;
    }

    public int getYLocationFromInput() {
        int y;
        System.out.println("Enter Y Location (0-" + (GameLogic.getInstance().getGrid().getSize() - 1) + "): ");
        try {
            Scanner reader = new Scanner(System.in);
            y = reader.nextInt();
        } catch (Exception e) {
            System.out.println("Please Enter A Valid Y Location");

            y = -1;
        }

        return y;
    }

    @Override
    public boolean moveIsValid(Coordinate coord) {
        if (!(getGrid().getCoordinate(coord).getContent() == 0)) {
            System.out.println("");
            System.out.println("********************* ERROR ************************ ");
            System.out.println("Location Already taken, Please Re-Enter Coordinates ");
            System.out.println("");
            return false;
        }
        return true;
    }
}
