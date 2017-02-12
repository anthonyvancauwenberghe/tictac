import java.io.File;

/**
 * @author Daniel
 */
public class Constants {

    public static final File MAIN_DIRECTORY = new File("./", "Files");

    public static final File JSON_DIRECTORY = new File(MAIN_DIRECTORY, "JSON");

    public static final File GAME_HISTORY = new File(JSON_DIRECTORY, "History.json");

}
