package code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import code.QueuingFunctions.GR1;
import code.QueuingFunctions.GR2;
import code.QueuingFunctions.QueuingFunction;

public class generalSearch {

    public static TreeNode GeneralSearch(searchProblem problem, QueuingFunction searchMethod) {
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
                return first;
            }
            for (String operartor : problem.operators) {
                State expansionState = problem.expand(first.state, operartor);
                if (expansionState == null) {
                    continue;
                }

                if (first.operator != null) {
                    if (operartor.equals("up") && (first.operator.equals("down") ||
                            first.operator.equals("right")
                            || first.operator.equals("left"))) {
                        continue;
                    }
                    if (operartor.equals("down") && (first.operator.equals("up") ||
                            first.operator.equals("left")
                            || first.operator.equals("right"))) {
                        continue;
                    }
                    // if (operartor.equals("up") && first.operator.equals("down")) {

                    // continue;
                    // }
                    // if (operartor.equals("down") && first.operator.equals("up")) {

                    // continue;
                    // }

                    if (operartor.equals("right") && first.operator.equals("left")) {
                        continue;
                    }
                    if (operartor.equals("left") && first.operator.equals("right")) {
                        continue;
                    }

                }
                Cost cost = new Cost(-expansionState.deaths, expansionState.retrievedBlackBoxes);

                TreeNode child = new TreeNode(expansionState, first, operartor, first.depth + 1, cost);
                searchMethod.queingFunction(child, nodes);
            }
        }
    }
}
