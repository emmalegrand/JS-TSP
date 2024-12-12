package util;


import abstraction.OpenNodes;
import abstraction.State;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Implementation of a fringe
 * to get depth-first-search strategy
 */
public class DepthFirstOpenNodes<S extends State> implements OpenNodes<S> {

    Stack<Triplet<S>> stack;

    public DepthFirstOpenNodes() {
        stack = new Stack<>();
    }

    public void add(S n, double h, double val) {
        stack.push(new Triplet<>(n, h, val));
    }

    public Triplet<S> remove() {
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }

}
