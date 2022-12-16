package code.QueuingFunctions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import code.Cost;
import code.State;
import code.TreeNode;

public class A2 extends QueuingFunction {
    public A2() {
        this.previousStates = new HashSet<State>();
    }

    @Override
    public ArrayList<TreeNode> queingFunction(TreeNode node, ArrayList<TreeNode> nodesList) {
        // TODO Auto-generated method stub
        if (previousStates.contains(node.state)) {

            return nodesList;
        }
        previousStates.add(node.state);
        Cost costEst = GR2.generateHeuristicCost(node.state);
        node.pathCost.addCost(costEst);
        int index = GR1.getIndex(nodesList, node);
        if (index == -1) {
            nodesList.add(node);
            return nodesList;
        }

        nodesList.add(index, node);
        // System.out.println(nodesList.toString());
        // TODO Auto-generated method stub
        return nodesList;
    }

}
