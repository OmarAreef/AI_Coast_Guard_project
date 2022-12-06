package code;
import java.util.ArrayList;
import java.util.Set;

public abstract class searchProblem {
    public Set<String> operators;
    public State initialState;
    public Set<State> stateSpace;

    public searchProblem(Set<String> operators, State initialState, Set<State> stateSpace) {
        this.operators = operators;
        this.initialState = initialState;
        this.stateSpace = stateSpace;
    }

    public abstract boolean goalTest(State state);

    public abstract int pathCost(ArrayList<String> actions);

    public abstract State expand(State state, String operator);
}
