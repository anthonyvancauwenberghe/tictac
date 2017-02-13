import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by tony on 10/02/2017.
 */

public class GameHistory {

    private ArrayList<Game> games;
    private String filePath = "./Training_Data/History";

    public static GameHistory getInstance() {
        return InstanceHolder.instance;
    }

    public ArrayList<Game> getGames() {

        if (games == null)
            return null;

        return games;
    }

    public File getFile() {
        File file = new File(filePath + GameLogic.getInstance().getGrid().getSize() + "x" + GameLogic.getInstance().getGrid().getSize() + ".json");
        try {
            if (!file.exists())
                file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


    public void load() {
        System.out.println("Please Be patient Loading Training Data");

        try (BufferedReader reader = new BufferedReader(new FileReader(getFile()))) {
            final ArrayList<Game> games = new Gson().fromJson(new JsonParser().parse(reader), new TypeToken<ArrayList<Game>>() {
            }.getType());
            reader.close();
            this.games = games;
        } catch (IOException ex) {
            ex.printStackTrace();
            this.games = new ArrayList<>();
        }
    }

    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFile()))) {
            writer.write(new GsonBuilder().create().toJson(games, new TypeToken<ArrayList<Game>>() {
            }.getType()));
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
