import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by tony on 10/02/2017.
 */
public class AIBot extends Bot {


    public Coordinate generateMove() {
        GameHistory gameHistory = GameLogic.getInstance().getGameHistory();
        Game currentGame = GameLogic.getInstance().getCurrentGame();

        ArrayList<Game> games = gameHistory.getGames();
        int[] moves = currentGame.toArray();
        int currentMove;
        int historyMove;
        ArrayList<Game> possibleGames = new ArrayList<>();

        for (Game historyGame : games) {
            for (int i = 0; i < moves.length; i++) {
                currentMove = moves[i];
                historyMove = historyGame.getMove(i);

                if (currentMove != historyMove)
                    break;

                if (i == moves.length - 1)
                    possibleGames.add(historyGame);
            }
        }

        int[] weightArray = new int[GameLogic.getInstance().getGrid().getSize() * GameLogic.getInstance().getGrid().getSize()];
        int nextMove;
        for (int i = 0; i < weightArray.length; i++) {
            weightArray[i] = 0;
        }

        for (Game possibleGame : possibleGames) {

            for (int i = 0; i < moves.length; i++) {
                possibleGame.getGameMoves().remove(i);
            }


            nextMove = possibleGame.getMove(0);
            weightArray[nextMove] = weightArray[nextMove] + possibleGame.getWeight();

        }

        for (int i = 0; i < weightArray.length; i++) {
            if (isInArray(moves, i))
                weightArray[i] = -1000000000;
        }

        int bestMove = 0;
        for (int i = 1; i < weightArray.length; i++) {

            int newnumber = weightArray[i];
            if ((newnumber > weightArray[bestMove])) {
                bestMove = i;
            }
        }


        Coordinate coord = new Coordinate(bestMove);
        return coord;
    }

    public boolean isInArray(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value)
                return true;
        }
        return false;
    }


}
