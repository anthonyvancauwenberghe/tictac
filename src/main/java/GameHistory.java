import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel
 */
public class GameHistory {

    private List<Game> games = getGamesList();

    public static GameHistory getInstance() {
        return InstanceHolder.instance;
    }

    public void refresh() {
        games = getGamesList();
    }

    private List<Game> getGamesList() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.GAME_HISTORY))) {
            final List<Game> games = new Gson().fromJson(new JsonParser().parse(reader), new TypeToken<List<Game>>() {}.getType());
            reader.close();
            return games;
        } catch (IOException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void save() {
        Environment.createParentDirectories(Constants.GAME_HISTORY);
        Environment.createFiles(Constants.GAME_HISTORY);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.GAME_HISTORY))) {
            writer.write(new GsonBuilder().create().toJson(games, new TypeToken<List<Game>>() {}.getType()));
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            games = new ArrayList<>();
            save();
        }
    }

    public void add(Game game) {
        if (games == null) {
            games = new ArrayList<>();
        }
        if (!games.contains(game)) {
            games.add(game);
        }
    }

    private static class InstanceHolder {
        private static GameHistory instance = new GameHistory();
    }

}
