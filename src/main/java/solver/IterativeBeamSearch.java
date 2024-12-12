package solver;

import abstraction.Model;
import abstraction.State;
import abstraction.Transition;
import examples.jstsp.ToolSwitchingState;

import util.Pair;
import util.PairComparator;
import util.Result;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class IterativeBeamSearch<S extends State> {
    public Result solve(Model<S> model) {
        long t0 = System.currentTimeMillis();
        Result res = new Result();
        HashMap<S, Integer> g = new HashMap<>();
        ArrayList<Pair> current = new ArrayList<>(2 ^ 5);
        PriorityQueue<Pair> next = new PriorityQueue<>(new PairComparator());
        int nb_nodes = 0;
        S rootState = model.initial_state();
        g.put(rootState, 0);
        int B = 1;
        int ub = Integer.MAX_VALUE;
        res.addUbTime(new double[]{ub,System.currentTimeMillis()-t0});
        boolean inadmissible = true;
        while (inadmissible) {
            inadmissible = false;
            current.add(new Pair(rootState, model.lowerBound(rootState)));
            while (!current.isEmpty()) {
                next.clear();
                for (Pair c : current) {
                    if (c.f >= ub) {
                        continue;
                    }
                    S s = (S) c.state;
                    nb_nodes++;
                    List<Integer> successors = model.next_variables(s,false);
                    int val_s = g.get(s);
                    for (int succ : successors) {
                        Transition<S> t = model.transition(s, succ);
                        S succ_state = t.getSuccessor();
                        int h = (int) model.lowerBound(succ_state);
                        double ghval = max(t.getValue() + val_s + h,c.f);
                        int val_g = g.getOrDefault(succ_state, Integer.MAX_VALUE);
                        if ((val_g != Integer.MAX_VALUE && (t.getValue() + val_s) > val_g) || ghval >= ub) {
                            continue;
                        }
                        g.put(succ_state, t.getValue() + val_s);
                        if (model.isGoal(succ_state)){
                            if ((t.getValue() + val_s )< ub) {
                                ub = t.getValue() + val_s;
                                res.addUbTime(new double[]{ub,System.currentTimeMillis()-t0});
                            }
                        } else {
                            next.add(new Pair(t.getSuccessor(), ghval));
                        }
                    }
                }
                if (next.size() <= B) {
                    current.clear();

                    while (!next.isEmpty()) {
                        current.add(next.poll());
                    }
                } else {
                    current.clear();
                    for (int i = 0; i < B; i++) {
                        current.add(next.poll());
                    }
                    inadmissible = true;
                }
            }
            B = B * 2;

        }
        res.setCost(ub);
        res.setNb_nodes(nb_nodes);
        return res;


    }
}
