import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by tony on 10/02/2017.
 */
public class AIBot extends Bot {


    public Coordinate generateMove(){
        int x = ThreadLocalRandom.current().nextInt(0, getGrid().getSize());
        int y = ThreadLocalRandom.current().nextInt(0, getGrid().getSize());

        //System.out.println("bot Move: x: " + x + " y: " + y);
        Coordinate coord = new Coordinate(x,y);
        return coord;
    }



}
