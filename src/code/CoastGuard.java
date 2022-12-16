package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import code.QueuingFunctions.A1;
import code.QueuingFunctions.A2;
import code.QueuingFunctions.BFS;
import code.QueuingFunctions.DFS;
import code.QueuingFunctions.GR1;
import code.QueuingFunctions.GR2;
import code.QueuingFunctions.ID;
import code.QueuingFunctions.QueuingFunction;

import java.util.HashSet;

public class CoastGuard extends searchProblem {

    public CoastGuard(ArrayList<String> operators, State initialState, Set<State> stateSpace) {
        super(operators, initialState, stateSpace);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean goalTest(State state) {
        // TODO Auto-generated method stub
        if (state.ships.isEmpty() && state.agent.remainingCapacity == state.agent.totalCapacity) {
            // if (state.ships.isEmpty()) {

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
            if (agent.column == ship.xPos && agent.row == ship.yPos) {
                ShipAtMyLocation = ship;
            }
        }

        ArrayList<Station> stations = stateDeepCopy.stations;
        Station stationAtMyLocation = null;
        for (Station station : stations) {
            if (agent.column == station.xPos && agent.row == station.yPos) {
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
                if (stationAtMyLocation == null
                        || (stateDeepCopy.agent.remainingCapacity == stateDeepCopy.agent.totalCapacity)) {
                    break;
                }

                agent.drop();

                break;
            case "retrieve":
                if (ShipAtMyLocation == null || !(ShipAtMyLocation.isWreck())) {
                    break;
                }

                stateDeepCopy.retrievedBlackBoxes = stateDeepCopy.retrievedBlackBoxes + 1;
                ships.remove(ShipAtMyLocation);

                break;
            case "up":
                if (agent.row <= 0) {
                    break;
                }
                agent.moveY(-1);
                break;
            case "down":
                if (agent.row >= agent.rows - 1) {
                    break;
                }
                agent.moveY(1);
                break;
            case "left":
                if (agent.column <= 0) {
                    break;
                }

                agent.moveX(-1);

                break;
            case "right":
                if (agent.column >= agent.columns - 1) {
                    break;
                }
                agent.moveX(1);
                break;

        }

        if (state.equals(stateDeepCopy)) {
            return null;
        }

        stateDeepCopy.update();

        return stateDeepCopy;
    }

    public static String genGrid() {
        int MRows = (int) (Math.random() * 5 + 1);
        int NColumns = (int) (Math.random() * 15 + 1);

        int C = (int) (Math.random() * 70 + 30);

        int cgColumn = (int) (Math.random() * NColumns);
        int cgRow = (int) (Math.random() * MRows);

        ArrayList<String> stationLocations = generateLocations(MRows, NColumns, cgColumn, cgRow, new ArrayList<>());
        ArrayList<String> shipLocations = generateLocations(MRows, NColumns, cgColumn, cgRow, stationLocations);

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
        return ("" + NColumns + "," + MRows + ";" + C + ";" + cgRow + "," + cgColumn + ";" + stationStringFinal + ";"
                + shipLocationsAndPassengerNumbers);
    }

    private static ArrayList<String> generateLocations(int rows, int columns, int cgColumn, int cgRow,
            ArrayList<String> occupiedLocations) {
        int numberOfShips = (int) ((Math.random() * (rows - 1) * columns) + 1);
        while (numberOfShips > (rows * columns)) {
            numberOfShips = (int) ((Math.random() * (rows - 1) * columns) + 1);
        }
        ArrayList<String> stationLocations = new ArrayList<String>();
        for (int i = 0; i < numberOfShips; i++) {
            int stationColumn = (int) (Math.random() * columns);
            int stationRow = (int) (Math.random() * rows);
            while ((stationColumn == cgColumn && stationRow == cgRow)
                    || stationLocations.contains(stationRow + "," + stationColumn) ||
                    occupiedLocations.contains(stationRow + "," + stationColumn)) {
                stationColumn = (int) (Math.random() * columns);
                stationRow = (int) (Math.random() * rows);

            }
            stationLocations.add(stationRow + "," + stationColumn);
        }
        return stationLocations;
    }

    public static String solve(String grid, String strategy, boolean visualize) {
        System.out.println("Started solving");

        String[] girdParams = grid.split(";");

        String[] gridSize = girdParams[0].split(",");
        int Columns = Integer.parseInt(gridSize[0]);
        int Rows = Integer.parseInt(gridSize[1]);

        int C = Integer.parseInt(girdParams[1]);

        String[] agentStart = girdParams[2].split(",");
        int cgRow = Integer.parseInt(agentStart[0]);
        int cgColumn = Integer.parseInt(agentStart[1]);
        Agent agent = new Agent(cgColumn, cgRow, C, C, Rows, Columns);

        String[] stationLocations = girdParams[3].split(",");
        ArrayList<Station> stationsArray = new ArrayList<Station>();
        for (int i = 0; i < stationLocations.length - 1; i = i + 2) {
            Station station = new Station(Integer.parseInt(stationLocations[i + 1]),
                    Integer.parseInt(stationLocations[i]));
            stationsArray.add(station);
        }

        String[] shipsLocations = girdParams[4].split(",");
        ArrayList<Ship> shipsArray = new ArrayList<Ship>();
        for (int i = 0; i < shipsLocations.length - 2; i = i + 3) {
            Ship ship = new Ship(Integer.parseInt(shipsLocations[i + 1]), Integer.parseInt(shipsLocations[i]),
                    Integer.parseInt(shipsLocations[i + 2]), 100);
            shipsArray.add(ship);
        }

        State initialState = new State(shipsArray, stationsArray, agent, 0, 0);

        ArrayList<String> operators = new ArrayList<String>() {
            {

                add("pickup");
                add("retrieve");
                add("drop");
                add("left");
                add("right");
                add("up");
                add("down");

            }
        };

        CoastGuard problem = new CoastGuard(operators, initialState, new HashSet<State>());

        QueuingFunction Qfunc = null;
        switch (strategy) {
            case "BF":
                Qfunc = new BFS();
                break;
            case "DF":
                Qfunc = new DFS();
                break;
            case "ID":
                Qfunc = new ID(problem.initialState);
                break;
            case "GR1":
                Qfunc = new GR1();
                break;
            case "GR2":
                Qfunc = new GR2();
                break;
            case "AS1":
                Qfunc = new A1();
                break;
            case "AS2":
                Qfunc = new A2();
                break;
            default:
                Qfunc = new GR1();
        }
        // QueuingFunction Qfunc = new DFS();

        TreeNode solution = generalSearch.GeneralSearch(problem, Qfunc);
        if (solution == null) {
            return "No Solution found";
        }
        String solutionString = "";
        int retrievedBlackBoxes = solution.state.retrievedBlackBoxes;
        int deaths = solution.state.deaths;
        int expandedStates = solution.state.expandedStates;
        String visualizeString = "";
        while (solution.parent != null) {
            solutionString = solution.operator + "," + solutionString;
            if (visualize == true) {

                String[][] gridArray = new String[Rows][Columns];

                gridArray = generateString(gridArray, solution.state);
                System.out.println("-------------------" + solution.operator + " -----------------");
                printStringGrid(gridArray);

            }
            solution = solution.parent;

        }
        solutionString = solutionString.substring(0, solutionString.length() - 1);

        return solutionString + ";" + deaths + ";" + retrievedBlackBoxes + ";" + expandedStates;
    }

    public static String[][] generateString(String[][] grid, State state) {
        for (int i = 0; i < grid.length; i++) {
            String[] row = grid[i];
            Arrays.fill(row, "");
        }
        grid[state.agent.row][state.agent.column] = state.agent.toStringGrid();
        for (Ship ship : state.ships) {
            grid[ship.yPos][ship.xPos] = grid[ship.yPos][ship.xPos] + "_" + ship.toStringGrid();
        }
        for (Station ship : state.stations) {
            grid[ship.yPos][ship.xPos] = grid[ship.yPos][ship.xPos] + "_" +
                    ship.toStringGrid();
        }
        return grid;

    }

    public static void printStringGrid(String[][] array) {
        System.out.print("    |");
        for (int i = 0; i < array[0].length; i++) {
            System.out.print("        ");
            System.out.print((i));
            System.out.print("         |");
        }
        System.out.println();
        for (int i = 0; i < array.length; i++) {
            System.out.print("----+");
            for (int j = 0; j < array[0].length; j++) {
                System.out.print("------------------+");
            }
            System.out.println();
            System.out.print("  " + (i + 1) + " |");
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j].length() < 10) {
                    int spaces = (9 - array[i][j].length()) * 2;
                    for (int k = 0; k < spaces; k++) {
                        System.out.print(" ");
                    }
                    System.out.print(array[i][j]);
                    for (int k = 0; k < (9 - array[i][j].length()) - spaces; k++) {
                        System.out.print(" ");
                    }
                } else {
                    System.out.print(array[i][j]);
                }
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // String grid = genGrid();
        // String test_grid = "5,6;50;0,1;0,4,3,3;1,1,90;";
        String test_grid = "3,4;97;1,2;0,1;3,2,65;";
        // String test_grid = "6,6;52;2,0;2,4,4,0,5,4;2,1,19,4,2,6,5,0,8;";
        String grid3 = "8,5;60;4,6;2,7;3,4,37,3,5,93,4,0,40;";

        // String test_grid = "8,5;60;4,6;2,7;3,4,37,3,5,93,4,0,40;";
        String grid9 = "7,5;100;3,4;2,6,3,5;0,0,4,0,1,8,1,4,77,1,5,1,3,2,94,4,3,46;";
        String grid1 = "6,6;52;2,0;2,4,4,0,5,4;2,1,19,4,2,6,5,0,8;";

        Runtime runtime = Runtime.getRuntime();
        String grid2 = "7,5;40;2,3;3,6;1,1,10,4,5,90;";

        // String test_grid = "1,15;72;0,7;0,10;0,2,73;";
        long startTime = System.currentTimeMillis();
        runtime.gc();

        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Before Used memory is bytes: " + memory);
        System.out.println("Before Used memory is megabytes: "
                + memory / (1024L * 1024L));
        String sol = solve(grid2, "AS2", false);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("------------- Solution -------");
        System.out.println(sol);

        System.out.println("------------- Elapsed Time -------");
        System.out.println(((float) (elapsedTime)) / 1000);
        // Run the garbage collector
        // Calculate the used memory
        memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
        System.out.println("Used memory is megabytes: "
                + memory / (1024L * 1024L));

    }

}
