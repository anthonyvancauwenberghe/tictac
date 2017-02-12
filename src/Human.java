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
        Scanner reader = new Scanner(System.in);

        System.out.println("");

        System.out.println("Enter X Location: ");
        int x = reader.nextInt();

        System.out.println("Enter Y location");
        int y = reader.nextInt();

        return new Coordinate(x, y);
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
