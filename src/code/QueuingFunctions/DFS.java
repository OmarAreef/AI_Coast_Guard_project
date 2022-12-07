package code.QueuingFunctions;

import java.util.ArrayList;

import code.TreeNode;

public class DFS implements QueuingFunction {

    @Override
    public ArrayList<TreeNode> queingFunction(TreeNode node, ArrayList<TreeNode> nodesList) {
        // TODO Auto-generated method stub
        nodesList.add(0, node);
        return nodesList;
    }

}
