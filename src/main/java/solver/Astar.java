package solver;

import abstraction.Model;
import abstraction.State;
import abstraction.Transition;
import examples.jstsp.FreeTools;
import examples.jstsp.ToolSwitchingState;
import util.Pair;
import util.PairComparator;
import util.Result;

import java.util.*;

import static java.lang.Math.max;

public class Astar<S extends State>  {

    public Result solve(Model<S> model){
        PriorityQueue<Pair> open = new PriorityQueue<>(1000,new PairComparator());
        int nb_nodes = 0;
        HashSet<S> closed = new HashSet<>();
        S rootState = model.initial_state();
        HashMap<S, Integer> g = new HashMap<>(1000);
        HashMap<S, Double> present = new HashMap();
        g.put(rootState, 0);
        double h0 = model.lowerBound(rootState);
        open.add(new Pair(rootState, h0));
        present.put(rootState, h0);
        HashMap<Pair,Pair> parent = new HashMap<>();
        parent.put(new Pair(rootState, h0), null);
        Result res = new Result();
        while (!open.isEmpty()){
            Pair<S> current = open.poll();
            present.remove(current.state);
            if (closed.contains(current.state)) {
                continue; // Ignore si déjà traité
            }
            nb_nodes++;
            closed.add( current.state);
            int valg_current = g.get(current.state);
            if (model.isGoal(current.state)){
                res.setNb_nodes(nb_nodes);
                res.setCost(valg_current);
                return res;
            }
            List<Integer> successors = model.next_variables( current.state,false);
            for (int succ : successors) {
                Transition<S> t = model.transition( current.state,succ);
                int gval = t.getValue() + valg_current;
                double hval =  model.lowerBound(t.getSuccessor());
                int valg_succ = g.getOrDefault(t.getSuccessor(),Integer.MAX_VALUE);
                double ghval = max(gval+hval,current.f);
                if (gval < valg_succ ){
                    Pair<S> sp = new Pair<>(t.getSuccessor(),ghval);
                    g.put(t.getSuccessor(),gval) ;
                    parent.put(sp, current);
                    if (!present.containsKey(t.getSuccessor())) {
                        open.add(sp);
                        present.put(t.getSuccessor(),ghval);
                    }else if (present.get(t.getSuccessor())>ghval) {
                        open.add(sp);
                    }
                    if (closed.contains(t.getSuccessor())) {
                        closed.remove(t.getSuccessor());
                    }
                }
            }
        }
        res.setNb_nodes(nb_nodes);
        res.setCost(-1);
        return res;

    }
}
