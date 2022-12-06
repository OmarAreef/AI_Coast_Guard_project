package code.QueuingFunctions;

import java.util.ArrayList;

import code.TreeNode;

public class BFS implements QueuingFunction {

    @Override
    public ArrayList<TreeNode> queingFunction(TreeNode node, ArrayList<TreeNode> nodesList) {
        // TODO Auto-generated method stub
        nodesList.add(node);
        return nodesList;
    }

}
