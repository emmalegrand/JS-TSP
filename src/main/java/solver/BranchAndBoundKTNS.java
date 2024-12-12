package solver;


import abstraction.Model;
import abstraction.OpenNodes;
import abstraction.State;
import abstraction.Transition;
import util.*;

import java.util.List;
import java.util.function.Consumer;

import static java.lang.Math.max;


public class BranchAndBoundKTNS<S extends State> {

    /**
     * Explore completely the search space
     * defined by the nodes initially
     * in the provided open-nodes.
     * using Branch and Bound
     * @param openNode a collection containing
     *                 initially the root node.
     *                 This collection during execution will contain
     *                 the fringe of the exploration.
     *                 The search might be explored in depth-first-search
     *                 or best-first-search depending on the specific implementation
     *                 for the openNode see {@link BestFirstOpenNodes} and {@link DepthFirstOpenNodes}.
     * @param onSolution a closure called each time a new improving
     *                   solution is found.
     *
     */
    public Result minimize(Model<S> model, OpenNodes<S> openNode, Consumer<Pair<S>> onSolution, boolean bnbHeuristic) {
        // compute the gap between the best solution found so far UB and the global lower bound in the open-nodes LB = min_{n in open-nodes} n.lowerbound
        // GAP = (UB - LB) / LB
        long t0 = System.currentTimeMillis();
        Result res = new Result();
        int nb_nodes = 0;
        S rootState = model.initial_state();
        double upperBound = Integer.MAX_VALUE;
        double hroot = model.lowerBound(rootState);
        res.addUbTime(new double[]{upperBound,System.currentTimeMillis()-t0});
        openNode.add(rootState, hroot,0);
        while (!openNode.isEmpty()) {
            Triplet<S> p = openNode.remove();
            if (model.isGoal(p.state)) {
                double objective = p.val;
                if (objective < upperBound) {
                    upperBound = objective;
                    res.addUbTime(new double[]{upperBound,System.currentTimeMillis()-t0});
//                    onSolution.accept(p);
                }
            } else if (p.h < upperBound) {
                nb_nodes++;
                List<Integer> nexts = model.next_variables(p.state,bnbHeuristic);
                for (Integer child : nexts) {
                    Transition<S> t = model.transition(p.state, child);
                    double h = model.lowerBound(t.getSuccessor());
                    double ghval = max(t.getValue() + h, p.h);
                    if ( ghval < upperBound) {
                        openNode.add(t.getSuccessor(), ghval,t.getValue());
                    }
                }
            }
        }
        res.setCost((int)upperBound);
        res.setNb_nodes(nb_nodes);
        return res;
    }
}

