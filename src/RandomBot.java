import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by tony on 10/02/2017.
 */
public class RandomBot extends Bot {

    public RandomBot(){
        super();
    }

    public RandomBot(int id){
        super();
    }

    public Coordinate generateMove() {
        int x = ThreadLocalRandom.current().nextInt(0, getGrid().getSize());
        int y = ThreadLocalRandom.current().nextInt(0, getGrid().getSize());

        //System.out.println("bot Move: x: " + x + " y: " + y);

        return new Coordinate(x, y);
    }

}
