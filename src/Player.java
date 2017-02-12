/**
 * Created by tony on 10/02/2017.
 */
abstract public class Player {

    /* If there are multiple players we could set PlayerID's so coded the structure like this */
    /* Since we are only using one player i'm not going to use it */
    protected int playerID;
    protected String name;

    abstract public Coordinate generateMove();

    public String getName(){
        return this.name;
    }

    public int move() {

        Coordinate coord = generateMove();

        while (!moveIsValid(coord))
            coord = generateMove();

        fillCoordinate(coord, getPlayerID());

        System.out.println(getGrid().toString());

        int move = coord.toInt();
        //System.out.println("Move: x: " + coord.getX() + " y: " + coord.getY());
        //System.out.println("move: " + move);
        return move;
    }

    protected void fillCoordinate(Coordinate coordinate, int playerID) {
        GameLogic.getInstance().getGrid().getCoordinate(coordinate).setContent(playerID);
    }

    protected Grid getGrid() {
        return GameLogic.getInstance().getGrid();
    }

    public int getPlayerID() {
        return playerID;
    }

    protected boolean moveIsValid(Coordinate coordinate) {
        return getGrid().getCoordinate(coordinate).getContent() == 0;

    }
}
