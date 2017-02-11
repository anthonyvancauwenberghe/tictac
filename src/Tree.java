import java.util.ArrayList;
import java.util.List;

/**
 * Created by tony on 10/02/2017.
 */

public class Tree {
    private List<Node> tree = null;

    public Tree() {
        this.tree = new ArrayList<>();
        initTree();

    }

    public Node getFirstNode() {
        return tree.get(0);
    }

    public Node getNode(ArrayList<Integer> sequence) {

        try{
            Node node = getFirstNode();
            for (int i = 0; i < sequence.size(); i++) {
                node = node.getChild(sequence.get(i));
        }
            return node;
        }catch(Exception e){
            Node node = getFirstNode();
            }

        return null;
    }

    private void initTree() {
        Node node = new Node(0, null);
        tree.add(node);

        int treeLevel = 0;
        node = this.getFirstNode();
        int gridSize = Game.getInstance().getGrid().getSize();
        for (int i = 0; i < gridSize*gridSize; i++) {
            node.addChild();
            node = node.getLastChild();
            treeLevel++;
        }

        while (node.getParent() != null) {
            node = node.getParent();
            treeLevel--;

            while(node.getChildrenAmount()< (gridSize*gridSize - treeLevel)){
                node.addChild();
                node = node.getLastChild();
                treeLevel++;
            }

        }

    }


}
