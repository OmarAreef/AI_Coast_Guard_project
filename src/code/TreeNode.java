package code;

import code.State;

public class TreeNode {
    public State state;
    public TreeNode parent;
    public String operator;
    public int depth;
    public int pathCost;

    public TreeNode(State state) {
        this.state = state;
    }

    public TreeNode(State state, TreeNode parent, String operator, int depth, int pathCost) {
        this.state = state;
        this.parent = parent;
        this.operator = operator;
        this.depth = depth;
        this.pathCost = pathCost;
    }

}
