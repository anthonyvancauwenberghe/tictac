import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by tony on 10/02/2017.
 */
public class AIBot extends Bot {


    public AIBot() {
        super();
    }

    public AIBot(int id) {
        super(id);
    }

    /* The AI works with Training Data & 2 heuristics */
    /* 1. Machine Learning: It calculates weightings based on previous games that are stored in memory */
    /* 2. Heuristic 1: It calculates if the bot can do a winning move or if the bot can prevent the opponent from doing a winning move */
    /* 3. Heuristic 2: It checks if in a 3x3 match the middle of the field is taken  */

    public Coordinate generateMove() {
        isThinking();
        GameHistory gameHistory = GameLogic.getInstance().getGameHistory();

        Game currentGame = GameLogic.getInstance().getCurrentGame();


        int[] moves = currentGame.toArray();
        int currentMove;
        int historyMove;
        ArrayList<Game> possibleGames = new ArrayList<>();
        int[] weightArray = new int[GameLogic.getInstance().getGrid().getSize() * GameLogic.getInstance().getGrid().getSize()];
        int[] weightArrayCount = new int[GameLogic.getInstance().getGrid().getSize() * GameLogic.getInstance().getGrid().getSize()];
        int nextMove;

        if (gameHistory.getGames() != null) {
            ArrayList<Game> games = gameHistory.getGames();

        /* LOOP THROUGH GAME HISTORY AND PUT ALL GAMES THAT COULD BE PLAYED IN TO POSSIBLEGAMES LIST */
            for (Game historyGame : games) {
                for (int i = 0; i < moves.length; i++) {

                    if (moves.length > historyGame.toArray().length)
                        break;

                    currentMove = moves[i];
                    historyMove = historyGame.getMove(i);

                    if (currentMove != historyMove)
                        break;

                    if (i == moves.length - 1)
                        possibleGames.add(historyGame);
                }
            }

        /* INITIALIZE NEW ARRAY THAT WILL HOLD A SCORE FOR EACH NEW POSSIBLE MOVE */
        /* THE INDEX IS THE MOVE & THE ARRAYVALUE THE SCORE */

            for (int i = 0; i < weightArray.length; i++) {
                weightArray[i] = 0;
                weightArrayCount[i] = 0;
            }


        /* REMOVE THE MOVES THAT ARE ALREADY PLAYED FROM THE POSSIBLEGAMES LIST */
        /* GENERATE A SCORE PER MOVE BASED ON AMOUNT OF WIN/LOSS/DRAWS */

            for (Game possibleGame : possibleGames) {

                for (int i = 0; i < moves.length; i++) {

                    //System.out.println("i: " + i + " possibleGamemoves: " + possibleGame.toString());
                    if (possibleGame.getGameMoves().size() <= 1)
                        possibleGame.getGameMoves().clear();
                    else {
                        possibleGame.getGameMoves().remove(i);
                        nextMove = possibleGame.getMove(0);
                        weightArray[nextMove] = weightArray[nextMove] + possibleGame.getWeight();
                        weightArrayCount[nextMove] = weightArrayCount[nextMove]+1;
                    }

                }
            }
        }

        /* DIVIDE THE WEIGHT WITH THE AMOUNT OF GAMES PLAYED */
        for (int i = 0; i < weightArray.length; i++) {
            if(weightArrayCount[i] == 0)
                weightArrayCount[i]=1;

            weightArray[i] = Math.round(weightArray[i]/weightArrayCount[i]);  ;
        }

            /* GIVE THE MOVES THAT ARE ALREADY PLAYED AN EXTREMELY LOW WEIGHT SO THEY ARE NEVER PLAYED */
        for (int i = 0; i < weightArray.length; i++) {
            if (isInArray(moves, i))
                weightArray[i] = -1000000000;
        }

        /*HEURISTIC1: CHECK IF THERE IS A WINNING MOVE */
        Grid currentGrid;

        for (int i = 0; i < GameLogic.getInstance().getGrid().getSize() * GameLogic.getInstance().getGrid().getSize(); i++) {
            currentGrid = currentGame.toGrid();

            if (!isInArray(moves, i)) {
                Coordinate coord = new Coordinate(i);
                currentGrid.fillCoordinate(coord, GameLogic.getInstance().player2.getPlayerID());
                if (currentGrid.horizontalRowSequence() || currentGrid.verticalRowSequence() || currentGrid.diagonalRowSequence())
                    weightArray[i] = 1000000000;
            }
        }

        /*HEURISTIC1: CHECK IF THERE IS A LOSING MOVE */
        for (int i = 0; i < GameLogic.getInstance().getGrid().getSize() * GameLogic.getInstance().getGrid().getSize(); i++) {
            currentGrid = currentGame.toGrid();
            if (!isInArray(moves, i)) {
                Coordinate coord = new Coordinate(i);
                currentGrid.fillCoordinate(coord, GameLogic.getInstance().player1.getPlayerID());
                if (currentGrid.horizontalRowSequence() || currentGrid.verticalRowSequence() || currentGrid.diagonalRowSequence())
                    weightArray[i] = 900000000;
            }
        }



        /*LOOK FOR THE BEST MOVE BASED ON THE WEIGHTINGS */
        int bestMove = 0;
        ArrayList<Integer> bestMoves = new ArrayList<>();
        for (int i = 0; i < weightArray.length; i++) {

            int weight = weightArray[i];
            if ((weight > weightArray[bestMove])) {
                bestMove = i;
                bestMoves.clear();
                bestMoves.add(i);
            } else if (weight == weightArray[bestMove]) {
                bestMove = i;
                bestMoves.add(i);
            }
        }

        int amountOfBestMoves = bestMoves.size();
        int index;

        if (amountOfBestMoves > 1) {
            index = ThreadLocalRandom.current().nextInt(0, amountOfBestMoves - 1);

        } else {
            index = 0;
        }

        /* HEURISTIC 2: CHECK IF THE MIDDLE OF THE FIELD IS TAKEN */
        if (currentGame.toArray().length < 2 && GameLogic.getInstance().getGrid().getSize() == 3) {
            int move = (GameLogic.getInstance().getGrid().getSize()*GameLogic.getInstance().getGrid().getSize()-1) / 2;
            Coordinate moveCoordinate = new Coordinate(move);

            if (GameLogic.getInstance().getGrid().getCoordinate(moveCoordinate).getContent() == 0)
                bestMove = move;
            else
                bestMove = bestMoves.get(index);
        } else
            bestMove = bestMoves.get(index);



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
