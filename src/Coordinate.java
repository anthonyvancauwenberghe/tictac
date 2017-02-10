/**
 * Created by tony on 10/02/2017.
 */
public class Coordinate {

    private int content;
    private int x;
    private int y;


    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        this.content = 0;
    }

    public void makeEmpty() {
        this.content = 0;
    }

    public void fillByComputer() {
        this.content = 1;
    }

    public void fillByPlayer() {
        this.content = 2;
    }

    public void output() {
        System.out.println("x: " + this.x + " y: " + y + " Cell: " + this.getContentByString());
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public int getContent() {
        return this.content;
    }

    public String getContentByString() {
        if (this.content == 1)
            return "Computer";

        if (this.content == 2)
            return "Player";

        return "Empty";
    }

}


