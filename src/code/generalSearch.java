package code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import code.QueuingFunctions.QueuingFunction;

public class generalSearch {

    public static TreeNode GeneralSearch(searchProblem problem, QueuingFunction searchMethod) {
        Set<State> previousStates = new HashSet<State>();
        int expandedStates = 0;
        TreeNode rootNode = new TreeNode(problem.initialState);
        ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
        nodes.add(rootNode);
        while (true) {

            if (nodes.isEmpty()) {
                return null;
            }
            TreeNode first = nodes.remove(0);
            expandedStates = expandedStates + 1;

            if (problem.goalTest(first.state)) {
                first.state.expandedStates = expandedStates;
                System.out.println(previousStates.size());
                System.out.println(first.depth);

                return first;
            }
            for (String operartor : problem.operators) {
                State expansionState = problem.expand(first.state, operartor);
                if (expansionState == null) {
                    continue;
                }
                if (previousStates.contains(expansionState)) {
                    // System.out.println("---------------------- Repeated ----------------");
                    // System.out.println(expansionState.toString());
                    // System.out.println(previousStates.size());

                    continue;
                }
                if (first.operator != null) {
                    if (operartor.equals("up") && first.operator.equals("down")) {
                        continue;
                    }
                    if (operartor.equals("down") && first.operator.equals("up")) {
                        continue;
                    }
                    if (operartor.equals("right") && first.operator.equals("left")) {
                        continue;
                    }
                    if (operartor.equals("left") && first.operator.equals("right")) {
                        continue;
                    }
                }

                previousStates.add(expansionState);

                TreeNode child = new TreeNode(expansionState, first, operartor, first.depth + 1, first.depth + 1);
                searchMethod.queingFunction(child, nodes);
            }
        }
    }
}
