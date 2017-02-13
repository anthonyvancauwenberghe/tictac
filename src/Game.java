import java.util.ArrayList;

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

    public String toString() {
        return getGameMoves() + " " + getWeight();
    }

    public ArrayList<Integer> getGameMoves() {
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

    public Grid toGrid(){
        int [] moves = toArray();
        Grid grid = new Grid(GameLogic.getInstance().getGrid().getSize());

        for(int i=0; i<moves.length; i++){
            Coordinate coord = new Coordinate(moves[i]);
            if(i%2 == 0){
                grid.fillCoordinate(coord,GameLogic.getInstance().player1.getPlayerID());
            }
            else{
                grid.fillCoordinate(coord,GameLogic.getInstance().player2.getPlayerID());
            }
        }
        return grid;

    }

}
