package examples.jstsp;

import abstraction.Model;
import abstraction.Transition;
import heuristic.Edge;
import util.UF;

import java.util.*;

import static java.lang.Math.max;

public class ToolSwitchingModel implements Model<ToolSwitchingState> {

    ToolSwitchingInstance instance;

    double[][] distanceMatrix;

    ArrayList<Edge> edges;

    HashMap<Long, Integer> kruskal_value;

    boolean[] ret;

    UF unionFind;

    public ToolSwitchingModel(ToolSwitchingInstance instance, double[][] distanceMatrix) {
        this.instance = instance;
        this.distanceMatrix = distanceMatrix;
        this.edges = new ArrayList<>(this.instance.n * this.instance.n);
        for (int i = 1; i < this.instance.n; i++) {
            for (int j = i + 1; j < this.instance.n; j++) {
                edges.add(new Edge(i, j, this.distanceMatrix[i][j]));

            }
        }
        edges.sort(Edge::compareTo);
        this.kruskal_value = new HashMap<Long, Integer>();
        this.ret = new boolean[this.instance.n];
        this.unionFind = new UF(this.instance.n);
    }

    @Override
    public boolean isBaseCase(ToolSwitchingState state) {
        return state.remaining == 0;
    }

    @Override
    public double getBaseCaseValue(ToolSwitchingState state) {
        return 0;
    }

    @Override
    public boolean isGoal(ToolSwitchingState state) {
        return state.remaining == 0;
    }

    @Override
    public ToolSwitchingState initial_state() {
        long remaining = 0L;
        for (int i = 1; i < this.instance.n; i++) {
            remaining |= 1L << i;
        }
        return new ToolSwitchingState(0, new FreeTools(this.instance.c), remaining);
    }

    @Override
    public Transition<ToolSwitchingState> transition(ToolSwitchingState source, int decision) {
        FreeTools fnew = new FreeTools(source.f);
        int cost = 0;
        for (int t : this.instance.tools[decision]) {
            // if the tool is not in the current
            if ((this.instance.jobs[source.currentJobIdx] & (1L << t)) == 0L) {
                int i = fnew.contains(t);
                if (i != -1) {
                    fnew.remove(i, t);
                } else {
                    cost++;
                }
            }
        }
        int slack = this.instance.c - this.instance.tools[decision].size();
        long free = this.instance.jobs[source.currentJobIdx] & ~this.instance.jobs[decision];
        if (slack > 0) {
            if (free != 0L) {
                if (slack < fnew.size) {
                    fnew.removeFrom(slack);
                }
                fnew.add(slack, free);
            }
        } else {
            fnew.reset();
        }
        return new Transition<>(new ToolSwitchingState(decision, fnew, source.remaining & ~(1L << decision)), decision, cost);
    }

    @Override
    public List<Integer> next_variables(ToolSwitchingState state, boolean bnb_heuristic) {
        List<Integer> ret = new ArrayList<>();
        long active_tools = 0L;
        for (int i = 0; i < this.instance.n; i++) {
            if ((state.remaining & (1L << i)) == 1L << i) {
                ret.add(i);
            }
            else{
                active_tools = active_tools | this.instance.jobs[i];
            }
        }
        if (Long.bitCount(active_tools)<= this.instance.c){
            for (int i:ret){
                if ((active_tools |this.instance.jobs[i]) == active_tools ){
                    return Collections.singletonList(i);
                }
            }
        }
        if (bnb_heuristic) {
            if (state.currentJobIdx==0){
                ret.sort(new SortByNumberOfTools());
            }
            else{
                ret.sort(new SortByMostSimilar(state));
            }

        }
        return ret;
    }

    @Override
    public double lowerBound(ToolSwitchingState state) {
        double L2 = lowerBoundL2(state);

        return L2;
    }

    double lowerBoundL1(ToolSwitchingState state) {
        long toolsneeded = this.instance.jobs[state.currentJobIdx];
        for (int i = 0; i < this.instance.n; i++) {
            if ((state.remaining & (1L << i)) == 1L << i) {
                toolsneeded |= this.instance.jobs[i];
            }
        }
        int cost = 0;
        for (int i = 0; i < this.instance.m; i++) {
            if ((toolsneeded & (1L << i)) == 1L << i) {
                cost += 1;
            }
        }
        return cost - this.instance.c;

    }

    double lowerBoundL2(ToolSwitchingState state) {
        double min_c = Integer.MAX_VALUE;
        long toolactive = 0L;
        int n = 0;
        for (int i = 0; i < this.instance.n; i++) {
            if ((state.remaining & (1L << i)) == 1L << i) {
                ret[i] = true;
                n++;
                if (min_c > this.distanceMatrix[state.currentJobIdx][i]) {
                    min_c = this.distanceMatrix[state.currentJobIdx][i];
                }
            } else {
                ret[i] = false;
                toolactive |= this.instance.jobs[i];
            }
        }
        if (min_c == Integer.MAX_VALUE) {
            min_c = 0;

        }

        Integer cost = this.kruskal_value.get(state.remaining);
        if (cost == null) {
            cost = kruskal(ret,Long.bitCount(toolactive),n);
            this.kruskal_value.put(state.remaining, cost);
        }
        return cost + min_c ;
    }

    double easyLowerBound(ToolSwitchingState state) {
        long toolsneeded = 0L;
        long toolactive = 0L;
        for (int i = 0; i < this.instance.n; i++) {
            if ((state.remaining & (1L << i)) == 1L << i) {
                toolsneeded |= this.instance.jobs[i];
            }
            else{
                toolactive |= this.instance.jobs[i];
            }
        }

        int cost = Long.bitCount(toolsneeded);

        return max (cost - this.instance.c + max(this.instance.c - Long.bitCount(toolactive), 0),0);
    }

    public Integer kruskal(boolean[] mask, int toolactive,int n) {
        int explored = 0;
        int global_cost = 0;
        HashMap<Integer, PairLi> tools = new HashMap<>();
        for (Edge edge : edges) {
            if (mask[edge.v1()] && mask[edge.v2()]) {
                int find_v1 = unionFind.find(edge.v1());
                int find_v2 = unionFind.find(edge.v2());
                if (find_v1 != find_v2) {
                    explored++;
                    if (unionFind.getRank(find_v1) == 0 && unionFind.getRank(find_v2) == 0) {
                        unionFind.union(edge.v1(), edge.v2());
                        int new_find = unionFind.find(edge.v1());
                        long tools_req = this.instance.jobs[edge.v1()] | this.instance.jobs[edge.v2()];
                        int nb_tools_req = Long.bitCount(tools_req);
                        double cost = edge.cost();
                        if (explored==n-1){
                            cost = max(cost,max(nb_tools_req - this.instance.c + max(this.instance.c-toolactive, 0),0));
                        }
                        else if (cost < nb_tools_req - this.instance.c) {
                            cost = nb_tools_req - this.instance.c;
                        }
                        tools.put(new_find, new PairLi(tools_req, cost));
                    } else if (unionFind.getRank(find_v1) == 0) {
                        unionFind.union(edge.v1(), edge.v2());
                        PairLi p = tools.get(find_v2);
                        p.tools_req = p.tools_req | this.instance.jobs[edge.v1()];
                        int nb_tools_req = Long.bitCount(p.tools_req);
                        p.cost += edge.cost();
                        if (explored==n-1){
                            p.cost = max(p.cost,max(nb_tools_req - this.instance.c + max(this.instance.c-toolactive, 0),0));
                        }
                        else if (p.cost < nb_tools_req - this.instance.c) {
                            p.cost = nb_tools_req - this.instance.c;
                        }
                        tools.replace(find_v2, p);
                    } else if (unionFind.getRank(find_v2) == 0) {
                        unionFind.union(edge.v1(), edge.v2());
                        PairLi p = tools.get(find_v1);
                        p.tools_req = p.tools_req | this.instance.jobs[edge.v2()];
                        int nb_tools_req = Long.bitCount(p.tools_req);
                        p.cost += edge.cost();
                        if (explored==n-1){
                            p.cost = max(p.cost,max(nb_tools_req - this.instance.c + max(this.instance.c-toolactive, 0),0));
                        }
                        else if (p.cost < nb_tools_req - this.instance.c) {
                            p.cost = nb_tools_req - this.instance.c;
                        }
                        tools.replace(find_v1, p);
                    } else {
                        unionFind.union(edge.v1(), edge.v2());
                        int new_find = unionFind.find(edge.v1());
                        PairLi p1 = tools.get(find_v1);
                        PairLi p2 = tools.get(find_v2);


                        p1.tools_req = p1.tools_req | p2.tools_req;
                        int nb_tools_req = Long.bitCount(p1.tools_req);
                        p1.cost += edge.cost() + p2.cost;
                        if (explored==n-1){
                            p1.cost = max(p1.cost,max(nb_tools_req - this.instance.c + max(this.instance.c-toolactive, 0),0));
                        }
                        else if (p1.cost < nb_tools_req - this.instance.c) {
                            p1.cost = nb_tools_req - this.instance.c;
                        }
                        tools.replace(new_find, p1);
                    }

                    if (unionFind.getRank(edge.v1()) >= unionFind.getRank(edge.v2())) {
                        global_cost = (int) tools.get(unionFind.find(edge.v1())).cost;
                    } else {
                        global_cost = (int) tools.get(unionFind.find(edge.v2())).cost;
                    }
                }
            }
        }
        this.unionFind.reset(this.instance.n);

        return global_cost;
    }

   

    class PairLi {
        public long tools_req;
        public double cost;

        public PairLi(long tools_req, double cost) {
            this.tools_req = tools_req;
            this.cost = cost;
        }
    }
    class SortByNumberOfTools implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            int res = Long.bitCount(instance.jobs[o1])-Long.bitCount(instance.jobs[o2]);
            if (res != 0) return res;
            else {
                int sum_1 = 0;
                int sum_2 = 0;
                for(int i=0;i<instance.n;i++){
                    if ((instance.jobs[o1]&1L<<i) !=0L){
                        sum_1+= instance.tools[i].size();
                    }
                    if ((instance.jobs[o2]&1L<<i) !=0L){
                        sum_2+= instance.tools[i].size();
                    }
                }
                return sum_1-sum_2;
            }
        }
    }
    class SortByMostSimilar implements Comparator<Integer> {
        private ToolSwitchingState current;
        public SortByMostSimilar(ToolSwitchingState current){
            this.current = current;
        }
        @Override
        public int compare(Integer o1, Integer o2) {
            int common_1 = 0;
            int common_2 = 0;
            for(int i=0;i<instance.m;i++){
                if ((instance.jobs[o1]&1L<<i) !=0L && (instance.jobs[this.current.currentJobIdx]&1L<<i)!=0L ){
                    common_1+= 1;
                }
                if ((instance.jobs[o2]&1L<<i) !=0L && (instance.jobs[this.current.currentJobIdx]&1L<<i)!=0L){
                    common_2+= 1;
                }
            }
            if (common_1==common_2){
                return Long.bitCount(instance.jobs[o2]|instance.jobs[this.current.currentJobIdx])-Long.bitCount(instance.jobs[o1]|instance.jobs[this.current.currentJobIdx]);
            }
            return common_1-common_2;
        }
    }


}
