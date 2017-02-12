import java.util.List;

/**
 * Created by tony on 10/02/2017.
 */
public class Game {

    private final List<Integer> gameMoves;
    private int weight;

    public Game(List<Integer> gameMoves, int weight) {
        this.gameMoves = gameMoves;
        this.weight = weight;
    }

    public void addMove(int number) {
        gameMoves.add(number);
    }

    public String getFormattedName() {
        return getGameMoves() + " " + getWeight();
    }

    public List<Integer> getGameMoves() {
        return gameMoves;
    }

    public int getWeight() {
        return weight;
    }

    public void botIsWinner() {
        this.weight = 100;
    }

    public void isDraw() {
        this.weight = 0;
    }

    public void botIsLoser() {
        this.weight = -100;
    }

}
