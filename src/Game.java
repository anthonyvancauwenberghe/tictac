import java.util.ArrayList;

/**
 * Created by tony on 10/02/2017.
 */
public class Game {
    private ArrayList<Integer> sequence = new ArrayList<>();
    private int weight;

    public void addMove(int number) {
        sequence.add(number);
    }

    public void reset() {
        sequence.clear();
    }

    public ArrayList<Integer> getSequence() {
        return sequence;
    }

    public int getWeight(){
        return weight;
    }

    public int getMove(int moveNumber) {

        try {
            return sequence.get(moveNumber);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR: The GameLogic does not contain that many moves! returning -1");
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    public String toString(){
        String string = getSequence() + " " + getWeight();
        return string;
    }

    public int[] toArray() {
        int size = sequence.size();
        int[] arraySequence = new int[size];
        int i = 0;

        for (int number : sequence) {
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
