package abstraction;

import java.util.List;

public interface Model<S extends State> {

    /**
     * Returns true if the state is a base case of the dynamic programming model
     *
     * @return true if the state is a base case of the dynamic programming model
     */
    public abstract boolean isBaseCase(S state);

    /**
     * Returns the value of the base case
     *
     * @return the value of the base case
     */
    public abstract double getBaseCaseValue(S state);

    /**
     * Returns true if the state is a goal case
     *
     * @return true if the state is a goal case
     */
    public abstract boolean isGoal(S state);

    /**
     * Returns the root state
     *
     * @return the root state
     */
    public abstract S initial_state();

    /**
     * Returns the Abstraction.Transition after taking the decision from source state
     *
     * @return the Abstraction.Transition after taking the decision from source state
     */
    public abstract Transition<S> transition(S source, int decision);

    /**
     * Returns the root state
     *
     * @return the root state
     */
    public abstract List<Integer> next_variables(S state, boolean bnb_heuristic);

    /**
     * Returns the lower bound corresponding to this state
     *
     * @return the lower bound corresponding to this state
     */
    public abstract double lowerBound(S state);


}
