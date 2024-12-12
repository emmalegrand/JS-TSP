package util;

import abstraction.OpenNodes;
import abstraction.State;

import java.util.PriorityQueue;

/**
 * Implementation of a fringe
 * to get best-first-search strategy
 *
 */
public class BestFirstOpenNodes<S extends State> implements OpenNodes<S> {

    PriorityQueue<Triplet<S>> queue;

    public BestFirstOpenNodes() {
        queue = new PriorityQueue<Triplet<S>>(new TripletComparator());
    }

    public void add(S n, double h, double val) {
        queue.add(new Triplet<S>(n,h, val));
    }

    public Triplet<S> remove() {
        return queue.remove();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int size() {
        return queue.size();
    }



}
