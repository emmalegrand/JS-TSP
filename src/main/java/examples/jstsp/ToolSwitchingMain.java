package examples.jstsp;

import abstraction.OpenNodes;
import solver.*;
import util.BestFirstOpenNodes;
import util.DepthFirstOpenNodes;
import util.Result;

import java.util.*;

public class ToolSwitchingMain {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.print("Please specify three arguments: path_to_file problem_to_solve search_type");
            return;
        }

        try {
            long t0 = System.currentTimeMillis();
            ToolSwitchingInstance instance = ToolSwitchingInstance.readFile(args[0], args[1]);
            ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());

            Result res = null;
            if (Objects.equals(args[2], "IBS")) {
                IterativeBeamSearch ibs = new IterativeBeamSearch();
                res = ibs.solve(model);
            }
            if (Objects.equals(args[2], "A*")) {
                Astar astar = new Astar();
                res = astar.solve(model);
            }
            if (Objects.equals(args[2], "AWA")) {
                AnytimeWeigthedAstar awa = new AnytimeWeigthedAstar();
                res = awa.solve(model, 1.5);
            }
            if (Objects.equals(args[2], "ACS")) {
                AnytimeColumnSearch acs = new AnytimeColumnSearch();
                res = acs.solve(model, instance.n, 10);
            }
            if (Objects.equals(args[2], "BnBdfs")) {
                BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
                OpenNodes<ToolSwitchingState> openNodes = new DepthFirstOpenNodes<>();
                res = bnb.minimize(model, openNodes, pair -> {
                    System.out.println("new best solution: " + pair.f);
                },true);
            }
            if (Objects.equals(args[2], "KTNS")) {
                ToolSwitchingModelKTNS modelktns = new ToolSwitchingModelKTNS(instance, instance.getMin_cost());
                BranchAndBoundKTNS<ToolSwitchingKTNSState> bnb = new BranchAndBoundKTNS<>();
                OpenNodes<ToolSwitchingKTNSState> openNodes = new DepthFirstOpenNodes<>();
                res = bnb.minimize(modelktns, openNodes, pair -> {
                    System.out.println("new best solution: " + pair.f);
                },true);
            }
            long t1 = System.currentTimeMillis();
            System.out.print(args[0] + ";" + args[1] + ";" + args[2] + ";" + (instance.n - 1) + ";" + instance.m + ";" + instance.c + ";" + Arrays.deepToString(res.getUb_time().toArray()) + ";" + res.getNb_nodes() + ";"+res.getCost()  + ";" + (t1 - t0) + "\n");
        }
        catch (Exception e) {
            System.out.print("error with "+args[0] + ";" + args[1] + ";" + args[2]+ "\n");
        }
    }
}
