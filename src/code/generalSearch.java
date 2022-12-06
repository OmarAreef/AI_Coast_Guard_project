package code;

import java.util.ArrayList;

import code.QueuingFunctions.QueuingFunction;

public class generalSearch {
    public static TreeNode GeneralSearch(searchProblem problem, QueuingFunction searchMethod) {
        TreeNode rootNode = new TreeNode(problem.initialState);
        ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
        nodes.add(rootNode);
        while (true) {

            if (nodes.isEmpty()) {
                return null;
            }
            TreeNode first = nodes.remove(0);

            if (problem.goalTest(first.state)) {
                return first;
            }
            for (String operartor : problem.operators) {
                State expansionState = problem.expand(first.state, operartor);
                if (expansionState == null) {
                    continue;
                }
                
                TreeNode child = new TreeNode(expansionState, first, operartor, first.depth + 1, first.depth + 1);
                searchMethod.queingFunction(child, nodes);
            }
        }
    }
}
