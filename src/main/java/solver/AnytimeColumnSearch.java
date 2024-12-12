package solver;

import abstraction.Model;
import abstraction.State;
import abstraction.Transition;

import util.Pair;
import util.PairComparator;
import util.Result;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class AnytimeColumnSearch<S extends State> {

    boolean empty(ArrayList<PriorityQueue<Pair<S>>> open) {

        for (PriorityQueue<Pair<S>> pq : open) {
            if (!pq.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public Result solve(Model<S> model, int V, int B) {
        long t0 = System.currentTimeMillis();
        Result res = new Result();
        HashMap<S, Integer> g = new HashMap<>();
        HashSet<S> closed = new HashSet<>();
        ArrayList<PriorityQueue<Pair<S>>> open = new ArrayList<>();
        HashSet<S>[] present = new HashSet[V];
        int nb_nodes = 0;
        for (int i = 0; i < V; i++) {
            open.add(new PriorityQueue<>(new PairComparator()));
            present[i] = new HashSet<>();
        }
        S rootState = model.initial_state();
        int ub = Integer.MAX_VALUE;
        res.addUbTime(new double[]{ub,System.currentTimeMillis()-t0});
        double h0 = model.lowerBound(rootState);
        open.get(0).add(new Pair(rootState, h0));
        present[0].add(rootState);
        g.put(rootState, 0);

        while (!empty(open)) {
            for (int k = 0; k < V; k++) {
                if (!open.get(k).isEmpty()) {
                    ArrayList<Pair<S>> candidates = new ArrayList<>();
                    int l = min(B, open.get(k).size());
                    for (int i = 0; i < l; i++) {
                        Pair<S> current =  open.get(k).poll();
                        present[k].remove(current.state);
                        candidates.add(current);
                    }
                    for (Pair<S> pair : candidates) {
                        nb_nodes +=1;
                        S state = pair.state;
                        closed.add(state);
                        List<Integer> successors = model.next_variables(state,false);
                        int val_g_state = g.get(state);
                        for (int succ : successors) {
                            Transition<S> t = model.transition(state, succ);
                            double h =  model.lowerBound(t.getSuccessor());
                            double ghval = max(t.getValue() + val_g_state + h,pair.f );

                            if (((present[k+1].contains(t.getSuccessor()) | closed.contains(t.getSuccessor())) && (t.getValue() + val_g_state) > g.getOrDefault(t.getSuccessor(), Integer.MAX_VALUE)) || ghval >= ub) {
                                continue;
                            }
                            g.put(t.getSuccessor(), t.getValue() + val_g_state);
                            if (model.isGoal(t.getSuccessor())){
                                if (ub > (t.getValue() + val_g_state)) {
                                    ub = t.getValue() + val_g_state;
                                    res.addUbTime(new double[]{ub,System.currentTimeMillis()-t0});
                                    for (int j = 0; j < open.size(); j++) {
                                        Iterator it = open.get(j).iterator();
                                        while (it.hasNext()) {
                                            Pair p = (Pair) it.next();
                                            if (p.f >= ub) {
                                                it.remove();
                                                present[j].remove(p.state);
                                            }
                                        }
                                    }
                                    closed.add(t.getSuccessor());
                                }
                            }else {
                                if (!present[k+1].contains(t.getSuccessor())) {
                                    open.get(k + 1).add(new Pair(t.getSuccessor(), ghval));
                                    present[k+1].add(t.getSuccessor());
                                }
                                if (closed.contains(t.getSuccessor())) {
                                    closed.remove(t.getSuccessor());
                                }

                            }
                        }
                    }
                }
            }
        }
        res.setCost(ub);
        res.setNb_nodes(nb_nodes);
        return res;
    }
}
