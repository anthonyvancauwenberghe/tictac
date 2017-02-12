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

    public ArrayList getGames() {
        return games;
    }

    public void addGame(Game game) {

        if (!gameExistsAlready(game))
            games.add(game);
    }

    public boolean gameExistsAlready(Game toAddGame) {
        for (Game game : games) {
            if (compareGames(toAddGame, game)) {
                System.out.println("Game Already in History");
                return true;
            }
        }
        return false;
    }

    public boolean compareGames(Game game1, Game game2) {
        int[] array1 = game1.toArray();
        int[] array2 = game2.toArray();

        boolean b = true;
        if (array1 != null && array2 != null) {
            if (array1.length != array2.length)
                b = false;
            else
                for (int i = 0; i < array2.length; i++) {
                    if (array2[i] != array1[i]) {
                        b = false;
                    }
                }
        } else {
            b = false;
        }

        return b;
    }


}
