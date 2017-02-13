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

    public void fillCoordinate(Coordinate coord, int playerID){
        this.getCoordinate(coord).setContent(playerID);
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

    private int[][] transposeArray(int[][] matrix) {
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        return matrix;
    }

    public boolean verticalRowSequence() {

        int[][] grid = transposeArray(generateGridArray());

        for (int y = 0; y < size; y++) {
            int playerID = 0;
            int successfullRows = 0;

            for (int x = 0; x < size; x++) {
                if (grid[x][y] != 0) {


                    if (x == 0) {
                        playerID = grid[x][y];
                        successfullRows = 1;
                    } else {

                        if (playerID == grid[x][y]) {
                            successfullRows++;
                        } else {
                            playerID = grid[x][y];
                            successfullRows = 1;
                        }
                    }

                    if (successfullRows >= 3) {
                        return true;
                    }
                }

            }

        }
        return false;
    }

    public boolean horizontalRowSequence() {
        int[][] grid = generateGridArray();

        for (int y = 0; y < size; y++) {
            int playerID = 0;
            int successfullRows = 0;

            for (int x = 0; x < size; x++) {
                if (grid[x][y] != 0) {


                    if (x == 0) {
                        playerID = grid[x][y];
                        successfullRows = 1;
                    } else {

                        if (playerID == grid[x][y]) {
                            successfullRows++;
                        } else {
                            playerID = grid[x][y];
                            successfullRows = 1;
                        }
                    }

                    if (successfullRows >= 3) {
                        return true;
                    }
                }

            }

        }
        return false;
    }

    public boolean diagonalRowSequence() {
        int[][] grid = generateGridArray();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int successfullRowsUL = 0;
                int successfullRowsUR = 0;
                int successfullRowsDL = 0;
                int successfullRowsDR = 0;
                int playerID = grid[x][y];
                if (grid[x][y] != 0) {

                    for (int i = 0; i < 3; i++) {

                    /* CHECK LEFT UPPER SEQUENCE */
                        try {
                            if (grid[x - i][y - i] == playerID) {
                                successfullRowsUL++;
                            } else {
                                successfullRowsUL = 0;
                                break;
                            }
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            break;
                        }
                    }

                    for (int i = 0; i < 3; i++) {
                    /* CHECK RIGHT UPPER SEQUENCE */
                        try {
                            if (grid[x + i][y - i] == playerID) {
                                successfullRowsUR++;
                            } else {
                                successfullRowsUR = 0;
                                break;
                            }
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            break;
                        }
                    }

                    for (int i = 0; i < 3; i++) {
                    /* CHECK LEFT DOWN SEQUENCE */
                        try {
                            if (grid[x - i][y + i] == playerID) {
                                successfullRowsDL++;
                            } else {
                                successfullRowsDL = 0;
                                break;
                            }
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            break;
                        }
                    }

                    for (int i = 0; i < 3; i++) {
                    /* CHECK RIGHT DOWN SEQUENCE */
                        try {
                            if (grid[x + i][y + i] == playerID) {
                                successfullRowsDR++;
                            } else {
                                successfullRowsDR = 0;
                                break;
                            }
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            break;
                        }

                    }


                }
                if (successfullRowsUR >= 3 || successfullRowsUL >= 3 || successfullRowsDR >= 3 || successfullRowsDL >= 3) {
                    return true;
                }

            }

        }
        return false;
    }

    public boolean allCoordinatesFilled() {
        int[][] grid = transposeArray(generateGridArray());

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (grid[x][y] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
