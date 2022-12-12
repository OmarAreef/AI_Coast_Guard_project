package code.QueuingFunctions;

import java.util.ArrayList;
import java.util.HashSet;

import code.State;
import code.TreeNode;

public class BFS extends QueuingFunction {
    public BFS() {
        this.previousStates = new HashSet<State>();

    }

    @Override
    public ArrayList<TreeNode> queingFunction(TreeNode node, ArrayList<TreeNode> nodesList) {
        // TODO Auto-generated method stub
        if (previousStates.contains(node.state)) {
            System.out.println(previousStates.size());
            return nodesList;
        }
        previousStates.add(node.state);
        nodesList.add(node);
        return nodesList;
    }

}
