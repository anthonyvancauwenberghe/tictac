import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by tony on 10/02/2017.
 */
public class GameHistory {
    private ArrayList<Game> games = new ArrayList<>();

    public void save() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream("gameHistory.txt"));
        for (Game game : games)
            pw.println(game.toString());
        pw.close();
    }

    public void load() throws FileNotFoundException {
        int[] array= new int[1000]; //TODO REPLACE NEW INT WITH THE VALUE FROM THE JSON
        int weight=0; //TODO REPLACE 0 WITH THE VALUE FROM THE JSON

        Game game = new Game(array,weight);
        games.add(game);
    }

    public ArrayList getGames() {
        return games;
    }

    public void addGame(Game game) {

        if (!gameExistsAlready(game)){
            games.add(game);
            System.out.println("ADDED GAME");
        }

    }

    public boolean gameExistsAlready(Game toAddGame) {
        for (Game game : games) {

            if (toAddGame.toString().equals(game.toString())) {
                System.out.println("Game Already in History");
                return true;
            }

        }
        return false;
    }

}
