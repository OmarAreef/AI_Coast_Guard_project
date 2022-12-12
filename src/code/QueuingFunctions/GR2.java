package code.QueuingFunctions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import code.TreeNode;
import code.State;
import code.Cost;
import code.Ship;

public class GR2 extends QueuingFunction {
    public GR2() {
        this.previousStates = new HashSet<State>();
    }

    public static Cost generateHeuristicCost(State state) {
        int totalPassengers = 0;
        int increasedDeaths = 0;
        // int totalDeaths = 0;
        for (Ship ship : state.ships) {
            totalPassengers = totalPassengers + ship.passengers;
            if (ship.isWreck()) {
                increasedDeaths += 1;
            }
        }
        return new Cost(totalPassengers + increasedDeaths, 0);

    }

    public static int getIndex(List<TreeNode> list, TreeNode node) {

        int index = -1;
        for (int j = 0; j < list.size(); j++) {
            TreeNode current = list.get(j);
            if (current.compareTo(node) > 0) {
                continue;

            }
            if (current.compareTo(node) <= 0) {
                return j;

            }

        }
        return index;
    }

    @Override
    public ArrayList<TreeNode> queingFunction(TreeNode node, ArrayList<TreeNode> nodesList) {
        if (previousStates.contains(node.state)) {
          
            return nodesList;
        }
        previousStates.add(node.state);

        Cost costEst = generateHeuristicCost(node.state);
        node.pathCost = costEst;
        int index = getIndex(nodesList, node);
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