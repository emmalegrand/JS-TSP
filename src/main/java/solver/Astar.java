package solver;

import abstraction.Model;
import abstraction.State;
import abstraction.Transition;
import util.Pair;
import util.PairComparator;
import util.Result;

import java.util.*;

import static java.lang.Math.max;

public class Astar<S extends State>  {

    public Result solve(Model<S> model){
        PriorityQueue<Pair> open = new PriorityQueue<>(1000,new PairComparator());
        HashSet<S> present = new HashSet<>();
        int nb_nodes = 0;
        HashSet<S> closed = new HashSet<>();
        S rootState = model.initial_state();
        HashMap<S, Integer> g = new HashMap<>(1000);
        g.put(rootState, 0);
        open.add(new Pair(rootState, model.lowerBound(rootState)));
        present.add(rootState);
        HashMap<S,S> parent = new HashMap<>();
        parent.put(rootState, null);
        Result res = new Result();
        while (!open.isEmpty()){
            Pair<S> current = open.poll();
            nb_nodes++;
            int valg_current = g.get(current.state);
            closed.add( current.state);
            if (model.isGoal( current.state)){
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
                    parent.put(t.getSuccessor(), current.state);
                    if (!present.contains(sp)){
                        open.add(sp);
                        present.add((S) current.state);
                    }
                }

            }
        }
        res.setNb_nodes(nb_nodes);
        res.setCost(-1);
        return res;

    }
}
