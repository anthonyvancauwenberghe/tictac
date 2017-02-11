import java.util.ArrayList;
import java.util.List;

/**
 * Created by tony on 10/02/2017.
 */

public class Node
{
    private List<Node> children = null;
    private Node parent = null;
    private int weight;
    public int x;
    public int y;
    private int totalWeight;

    public Node(int weight, Node parent)
    {
        this.children = new ArrayList<>();
        this.parent = parent;
        this.weight = weight;
    }

    public void addChild()
    {
        Node currentNode = this;
        Node node = new Node(1, currentNode);
        children.add(node);
    }

    public Node getLastChild(){
        return children.get(children.size()-1);
    }

    public Node getParent(){
        return this.parent;
    }

    public Node getChild(int index){
        return children.get(index);
    }

    public List<Node> getChildren() {
        return children;
    }

    public int getChildrenAmount(){
        return children.size();
    }

    public void isWinner(){
        weight = 10;
        children.clear();
    }

    public void isTie(){
        weight = 0;
        children.clear();
    }

    public void isLoser(){
        weight = -1;
        children.clear();
    }

    public int getWeight() {
        return weight;
    }
}
