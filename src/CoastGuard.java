import java.util.ArrayList;
import java.util.Random;

public class CoastGuard extends searchProblem {

    @Override
    public boolean goalTest(State state) {
        // TODO Auto-generated method stub
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
        return null;
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

        
        return "";
    }

    public static void main(String[] args) {
        String grid = genGrid();
        solve(grid, "df", false);
    }

}
