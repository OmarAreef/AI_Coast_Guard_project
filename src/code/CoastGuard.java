package code;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CoastGuard extends searchProblem {

    @Override
    public boolean goalTest(State state) {
        // TODO Auto-generated method stub
        if (state.ships.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public int pathCost(ArrayList<String> actions) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public State expand(State state, String operator) {
        // TODO Auto-generated method stub
        State stateDeepCopy = state.deepCopy();
        Agent agent = stateDeepCopy.agent;
        ArrayList<Ship> ships = stateDeepCopy.ships;
        Ship ShipAtMyLocation = null;
        for (Ship ship : ships) {
            if (agent.xPos == ship.xPos && agent.yPos == ship.yPos) {
                ShipAtMyLocation = ship;
            }
        }

        ArrayList<Station> stations = stateDeepCopy.stations;
        Station stationAtMyLocation = null;
        for (Station station : stations) {
            if (agent.xPos == station.xPos && agent.yPos == station.yPos) {
                stationAtMyLocation = station;
            }
        }

        switch (operator) {
            case "pickup":
                if (ShipAtMyLocation == null) {
                    break;
                }
                int passengersToPickUp = Math.min(agent.remainingCapacity, ShipAtMyLocation.passengers);
                ShipAtMyLocation.pickUp(passengersToPickUp);
                agent.pickUp(passengersToPickUp);
                break;
            case "drop":
                if (stationAtMyLocation == null) {
                    break;
                }
                agent.drop();
                break;
            case "retrieve":
                if (ShipAtMyLocation == null) {
                    break;
                }
                ships.remove(ShipAtMyLocation);
                break;
            case "up":
                if (agent.yPos == agent.N - 1) {
                    break;
                }
                agent.moveY(1);

            case "down":
                if (agent.yPos == 0) {
                    break;
                }
                agent.moveY(-1);
            case "left":
                if (agent.xPos == 0) {
                    break;
                }
                agent.moveX(-1);
            case "right":
                if (agent.xPos == agent.M - 1) {
                    break;
                }
                agent.moveX(1);
        }

        if (state.equals(stateDeepCopy)) {
            return null;
        }
        return stateDeepCopy;
    }

    public static String genGrid() {
        int M = (int) (Math.random() * 5 + 1);
        int N = (int) (Math.random() * 15 + 1);

        int C = (int) (Math.random() * 70 + 30);

        int cgX = (int) (Math.random() * M);
        int cgY = (int) (Math.random() * N);

        ArrayList<String> stationLocations = generateLocations(M, N, cgX, cgY, new ArrayList<>());
        ArrayList<String> shipLocations = generateLocations(M, N, cgX, cgY, stationLocations);

        String shipLocationsAndPassengerNumbers = "";
        for (String shipString : shipLocations) {
            int numberOfPassengerNumbers = (int) (Math.random() * 100);
            shipLocationsAndPassengerNumbers = shipLocationsAndPassengerNumbers + shipString + ","
                    + numberOfPassengerNumbers + ",";
        }
        shipLocationsAndPassengerNumbers = shipLocationsAndPassengerNumbers.substring(0,
                shipLocationsAndPassengerNumbers.length() - 1) + ";";
        String stationStringFinal = "";
        for (String stationString : stationLocations) {
            stationStringFinal += stationString + ",";
        }
        stationStringFinal = stationStringFinal.substring(0,
                stationStringFinal.length() - 1);
        return ("" + M + "," + N + ";" + C + ";" + cgX + "," + cgY + ";" + stationStringFinal + ";"
                + shipLocationsAndPassengerNumbers);
    }

    private static ArrayList<String> generateLocations(int M, int N, int cgX, int cgY,
            ArrayList<String> occupiedLocations) {
        int numberOfShips = (int) ((Math.random() * (M - 1) * N) + 1);
        while (numberOfShips > (M * N)) {
            numberOfShips = (int) ((Math.random() * (M - 1) * N) + 1);
        }
        ArrayList<String> stationLocations = new ArrayList<String>();
        for (int i = 0; i < numberOfShips; i++) {
            int stationX = (int) (Math.random() * M);
            int stationY = (int) (Math.random() * N);
            while ((stationX == cgX && stationY == cgY) || stationLocations.contains(stationX + "," + stationY) ||
                    occupiedLocations.contains(stationX + "," + stationY)) {
                stationX = (int) (Math.random() * M);
                stationY = (int) (Math.random() * N);

            }
            stationLocations.add(stationX + "," + stationY);
        }
        return stationLocations;
    }

    public static String solve(String grid, String strategy, boolean visualize) {
        String[] girdParams = grid.split(";");

        String[] gridSize = girdParams[0].split(",");
        int M = Integer.parseInt(gridSize[0]);
        int N = Integer.parseInt(gridSize[1]);

        int C = Integer.parseInt(girdParams[1]);

        String[] agentStart = girdParams[2].split(",");
        int cgX = Integer.parseInt(agentStart[0]);
        int cgY = Integer.parseInt(agentStart[1]);
        return "";
    }

    public static void main(String[] args) {
        String grid = genGrid();
        solve(grid, "df", false);
    }

}
