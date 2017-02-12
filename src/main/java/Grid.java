import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by tony on 10/02/2017.
 */
public class Grid {
    private ArrayList<Coordinate> grid = new ArrayList<>();
    private int size;

    public Grid(int size) {
        this.size = size;
        initGrid();
    }

    private void initGrid() {
        for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
                Coordinate coordinate = new Coordinate(x, y);
                this.grid.add(coordinate);
            }
        }
    }

    public void resetGrid(){
        this.grid.clear();
        initGrid();
    }

    public int[][] generateGridArray() {
        int size = getSize();
        int[][] grid = new int[size][size];
        for (Coordinate coordinate : this.grid) {
            grid[coordinate.getX()][coordinate.getY()] = coordinate.getContent();
        }

        return grid;
    }

    public int getSize() {
        return this.size;
    }

    public Coordinate getCoordinate(int x, int y) {
        return this.grid.get(x + getSize()*y);
    }

    public Coordinate getCoordinate(Coordinate coordinate) {
        return this.grid.get(coordinate.getX() + getSize()*coordinate.getY());
    }

    public String toString() {
        int[][] grid = generateGridArray();
        StringBuilder stringBuilder = new StringBuilder();
        int size = getSize();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                String character = getChar4Content(grid[x][y]);
                stringBuilder.append(character + "  ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private String getChar4Content(int content ){
        if(content == 1)
            return "X";
        if(content == 2)
            return "O";

            return "-";
    }
}
