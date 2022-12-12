package code.QueuingFunctions;

import java.util.ArrayList;

import code.TreeNode;

import java.util.Set;
import code.State;

public abstract class QueuingFunction {
    public Set<State> previousStates;

    public abstract ArrayList<TreeNode> queingFunction(TreeNode node, ArrayList<TreeNode> nodesList);
}
