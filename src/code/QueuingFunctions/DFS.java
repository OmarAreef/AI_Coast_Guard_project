package code.QueuingFunctions;

import java.util.ArrayList;
import java.util.HashSet;

import code.State;
import code.TreeNode;

public class DFS extends QueuingFunction {

    public DFS() {
        this.previousStates = new HashSet<State>();

    }

    @Override
    public ArrayList<TreeNode> queingFunction(TreeNode node, ArrayList<TreeNode> nodesList) {
        // TODO Auto-generated method stub
        if (previousStates.contains(node.state)) {
            return nodesList;
        }
        previousStates.add(node.state);

        nodesList.add(0, node);
        return nodesList;
    }

}
