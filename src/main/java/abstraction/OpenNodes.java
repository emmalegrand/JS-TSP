package abstraction;

import util.Triplet;

/**
 * ADT for representing the fringe (open-nodes)
 * in the implementation of a BnB
 *
 */
public interface OpenNodes<S extends State> {
    /**
     * Add a node to the fringe
     *
     * @param n the node
     */
    void add(S n, double h, double val);

    /**
     * Remove a node from the fringe
     *
     * @return a node
     */
    Triplet<S> remove();

    /**
     * Verify if the fringe is empty
     *
     * @return true if the fringe is empty
     */
    boolean isEmpty();

    /**
     * The number of nodes in the fringe
     *
     * @return the number of nodes in the fringe
     */
    int size();

}

