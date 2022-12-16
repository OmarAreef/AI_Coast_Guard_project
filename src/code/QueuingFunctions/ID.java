package code.QueuingFunctions;

import java.util.ArrayList;
import java.util.HashSet;

import code.TreeNode;
import code.State;

public class ID extends QueuingFunction {
    public TreeNode root;
    public int depth;

    public ID(State initState) {
        this.depth = 2;
        this.root = new TreeNode(initState);
        this.previousStates = new HashSet<State>();
    }

    @Override
    public ArrayList<TreeNode> queingFunction(TreeNode node, ArrayList<TreeNode> nodesList) {
        if (node.depth > this.depth || previousStates.contains(node.state)) {

            return nodesList;
        }
        if (nodesList.size() == 0) {
            this.depth = this.depth + 1;
            nodesList.add(root);

            this.previousStates.clear();

            previousStates.add(root.state);
            return nodesList;
        }
        previousStates.add(node.state);
        nodesList.add(0, node);
        return nodesList;
    }

}
