package abstraction;

import java.util.List;

/**
 * Node interface to be used in Branch and Bound.
 * It assumes a minimization problem
 * (just take the opposite if you have a maximization problem)
 *
 *
 */
public interface Node<S extends State> {

    /**
     * Return true if the node is a complete solution, that is
     * a leaf node that is a feasible solution to the problem
     * (but not necessarily optimal).
     * @return true if the node is a complete solution (not necessarily optimal)
     */
    boolean isSolutionCandidate();

    /**
     * The objective function of the node.
     * @return The objective function of the node.
     *         It only makes sense to call this function
     *         when the node is a solution candidate
     */
    double objectiveFunction();

    /**
     * A lower bound on the objective function.
     * This function can be called on any-node, not only
     * for a solution candidate.
     * @return A lower bound on the objective function
     */
    double lowerBound();

    /**
     * Depth of the node
     * @return the depth of the node
     */
    int depth();

    List<Node<S>> children();

    /** @return the state associated with this current node */
    S getState();
}

