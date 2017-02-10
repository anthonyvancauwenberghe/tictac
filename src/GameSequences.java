import java.util.ArrayList;

/**
 * Created by tony on 10/02/2017.
 */
public class GameSequences {
    private ArrayList<ArrayList> lostGameSequences = new ArrayList<>();
    private ArrayList<ArrayList> wonGameSequences = new ArrayList<>();
    private ArrayList<Integer> currentSequence = new ArrayList<>();


    public ArrayList getCurrentSequence() {
        return currentSequence;
    }

    public ArrayList getLostGameSequences() {
        return lostGameSequences;
    }

    public ArrayList getWonGameSequences() {
        return wonGameSequences;
    }

    public void addWonGameSequence() {
        this.wonGameSequences.add(currentSequence);
    }

    public void addLostGameSequence() {
        this.lostGameSequences.add(currentSequence);
    }

    public void addDecission(int number){
        currentSequence.add(number);
    }

    public void resetCurrentSequence(){
        this.currentSequence.clear();
    }


}
