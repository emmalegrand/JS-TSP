package solver;

import abstraction.Model;
import abstraction.State;
import abstraction.Transition;
import util.Pair;
import util.PairComparator;
import util.Result;

import java.util.*;

import static java.lang.Math.max;

public class AnytimeWeigthedAstar<S extends State> {
    public Result solve(Model<S> model, double w) {
        long t0 = System.currentTimeMillis();
        int nb_nodes = 0;
        Result res = new Result();
        HashMap<S, Integer> g = new HashMap<>();
        HashMap<S, Double> f = new HashMap<>();
        PriorityQueue<Pair> open = new PriorityQueue<>(new PairComparator());
        HashSet<S> closed = new HashSet<>();
        HashMap<S, Double> present = new HashMap();
        S rootState = model.initial_state();
        int ub = Integer.MAX_VALUE;
        res.addUbTime(new double[]{ub,System.currentTimeMillis()-t0});
        double h0 = model.lowerBound(rootState);
        open.add(new Pair(rootState, w * h0));
        present.put(rootState,h0);
        g.put(rootState, 0);
        f.put(rootState, h0);
        while (!open.isEmpty()) {
            Pair<S> current = open.poll();
            nb_nodes++;
            present.remove(current.state);
            closed.add( current.state);
            List<Integer> successors = model.next_variables( current.state,false);
            for (int succ : successors) {
                Transition<S> t = model.transition( current.state, succ);
                double h = model.lowerBound(t.getSuccessor());
                double ghval = max( t.getValue() + g.get(current.state) + h,f.get(current.state));
                if (((present.containsKey(t.getSuccessor()) | closed.contains(t.getSuccessor())) && (t.getValue() + g.get(current.state)) > g.getOrDefault(t.getSuccessor(), Integer.MAX_VALUE)) || (ghval) >= ub) {
                    continue;
                }
                g.put(t.getSuccessor(), t.getValue() + g.get(current.state));
                if (model.isGoal(t.getSuccessor()) &&  (t.getValue() + g.get(current.state))< ub) {
                    ub = t.getValue() + g.get(current.state);
                    res.addUbTime(new double[]{ub,System.currentTimeMillis()-t0});
                    Iterator it = open.iterator();
                    while (it.hasNext()) {
                        Pair p = (Pair) it.next();
                        if (f.get(p.state) >= ub) {
                            it.remove();
                            present.remove(p.state);
                        }
                    }
                    closed.add(t.getSuccessor());
                } else {
                    Pair sp = new Pair(t.getSuccessor(), (t.getValue() + g.get(current.state)) + w * h);
                    if (!present.containsKey(t.getSuccessor())) {
                        open.add(sp);
                        present.put(t.getSuccessor(),ghval);
                    }else if (present.get(t.getSuccessor())>ghval) {
                        open.add(sp);
                    }
                    if (closed.contains(t.getSuccessor())) {
                        closed.remove(t.getSuccessor());
                    }
                    f.put(t.getSuccessor(),max( t.getValue() + g.get(current.state) + h,f.get(current.state)));
                }
            }
        }
        res.setCost(ub);
        res.setNb_nodes(nb_nodes);
        return res;
    }
}
