import java.util.ArrayList;
import java.util.List;

/**
 * Created by tony on 10/02/2017.
 */
public class Game {

    private final ArrayList<Integer> gameMoves;
    private int weight;

    public Game(){
        this.gameMoves = new ArrayList<Integer>();
        this.weight = 0;
    }

    public Game(ArrayList<Integer> gameMoves, int weight) {
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

    public int getMove(int moveNumber){
        return gameMoves.get(moveNumber);
    }

    public int[] toArray(){
            int size = gameMoves.size();
            int[] movesArray = new int[size];
            int i=0;
            for (Integer move : gameMoves) {
                movesArray[i]= move;
                i++;
            }

            return movesArray;
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
