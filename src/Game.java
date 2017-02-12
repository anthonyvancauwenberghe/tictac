import java.util.ArrayList;

/**
 * Created by tony on 10/02/2017.
 */
public class Game {
    private ArrayList<Integer> gameMoves = new ArrayList<>();
    private int weight;

    public Game(){

    }

    public Game(int[] array, int weight){
        this.weight = weight;

        for(int i=0; i<array.length; i++){
            gameMoves.add(array[i]);
        }

    }

    public void addMove(int number) {
        gameMoves.add(number);
    }

    public void reset() {
        gameMoves.clear();
    }

    public ArrayList<Integer> getGameMoves() {
        return gameMoves;
    }

    public int getWeight(){
        return weight;
    }

    public int getMove(int moveNumber) {

        try {
            return gameMoves.get(moveNumber);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR: The GameLogic does not contain that many moves! returning -1");
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    public String toString(){
        String string = getGameMoves() + " " + getWeight();
        return string;
    }

    public int[] toArray() {
        int size = gameMoves.size();
        int[] arraySequence = new int[size];

        if(size == 0)
            return arraySequence;

        int i = 0;
        for (int number : gameMoves) {
            arraySequence[0] = number;
            i++;
        }

        return arraySequence;
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
