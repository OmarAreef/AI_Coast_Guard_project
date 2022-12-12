package code;

public class TreeNode implements Comparable<TreeNode> {
    public State state;
    public TreeNode parent;
    public String operator;
    public int depth;
    public Cost pathCost;

    public TreeNode(State state) {
        this.state = state;
        this.depth = 1;

    }

    public TreeNode(State state, TreeNode parent, String operator, int depth, Cost pathCost) {
        this.state = state;
        this.parent = parent;
        this.operator = operator;
        this.depth = depth;
        this.pathCost = pathCost;
    }

    @Override
    public String toString() {
        return "TreeNode [operator=" + operator + ", depth=" + depth + ", pathCost=" + pathCost + "]";
    }

    @Override
    public int compareTo(TreeNode o) {
        // TODO Auto-generated method stub
        return this.pathCost.compareTo(o.pathCost);
    }

}
