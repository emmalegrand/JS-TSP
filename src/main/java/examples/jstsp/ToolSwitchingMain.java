package examples.jstsp;

import abstraction.OpenNodes;
import solver.*;
import util.BestFirstOpenNodes;
import util.DepthFirstOpenNodes;
import util.Result;
import org.apache.commons.cli.*;

import java.util.*;
import java.util.stream.Collectors;

public class ToolSwitchingMain {
    public enum SearchType {
        IBS,
        Astar,
        AWA,
        ACS,
        BnBdsf,
        KTNS
    }

    private static final Map<String, SearchType> searchMap = new HashMap<>(){
        {
            put("BnBdfs", SearchType.BnBdsf);
            put("Astar", SearchType.Astar);
            put("KTNS", SearchType.KTNS);
            put("ACS", SearchType.ACS);
            put("AWA", SearchType.AWA);
            put("IBS", SearchType.IBS);
        }
    };
    public static void main(String[] args) {

        String quotedValidSearch = searchMap.keySet().stream().sorted().map(x -> "\"" + x + "\"")
                .collect(Collectors.joining(",\n"));

        Option modelOpt = Option.builder("s").longOpt("solver").argName("SOLVER").required().hasArg()
                .desc("used search.\nValid searches value are : " + quotedValidSearch).build();

        Option inst = Option.builder("i").longOpt("instance").argName("INSTANCE").required().hasArg()
                .desc("path file").build();

        Option problem = Option.builder("p").longOpt("problem").argName("PROBLEM").required().hasArg()
                .desc("problem to solved").build();

        Options options = new Options();

        options.addOption(modelOpt);
        options.addOption(inst);
        options.addOption(problem);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        String fileName = null;
        String problemName = null;
        String searchName = null;
        try {
            cmd = parser.parse(options, args);
            fileName = cmd.getOptionValue("i");
            problemName = cmd.getOptionValue("p");
            searchName = cmd.getOptionValue("s");
            if (!searchMap.containsKey(searchName))
                throw new IllegalArgumentException("Unknown solver: " + searchName);

        } catch (ParseException exp) {

            System.err.println(exp.getMessage());
            new HelpFormatter().printHelp("ToolSwitchingProblem", options);
            System.exit(1);
        }

        try {
            long t0 = System.currentTimeMillis();
            ToolSwitchingInstance instance = ToolSwitchingInstance.readFile(fileName, problemName);
            if (instance == null){
                System.err.println("Problem reading file: " + fileName +" and problem: " + problemName);
            }
            ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());

            Result res = null;
            if (Objects.equals(searchName, "IBS")) {
                IterativeBeamSearch ibs = new IterativeBeamSearch();
                res = ibs.solve(model);
            }
            if (Objects.equals(searchName, "Astar")) {
                Astar astar = new Astar();
                res = astar.solve(model);
            }
            if (Objects.equals(searchName, "AWA")) {
                AnytimeWeigthedAstar awa = new AnytimeWeigthedAstar();
                res = awa.solve(model, 1.5);
            }
            if (Objects.equals(searchName, "ACS")) {
                AnytimeColumnSearch acs = new AnytimeColumnSearch();
                res = acs.solve(model, instance.n, 10);
            }
            if (Objects.equals(searchName, "BnBdfs")) {
                BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
                OpenNodes<ToolSwitchingState> openNodes = new DepthFirstOpenNodes<>();
                res = bnb.minimize(model, openNodes, pair -> {
                    System.out.println("new best solution: " + pair.f);
                }, true);
            }
            if (Objects.equals(searchName, "KTNS")) {
                ToolSwitchingModelKTNS modelktns = new ToolSwitchingModelKTNS(instance, instance.getMin_cost());
                BranchAndBoundKTNS<ToolSwitchingKTNSState> bnb = new BranchAndBoundKTNS<>();
                OpenNodes<ToolSwitchingKTNSState> openNodes = new DepthFirstOpenNodes<>();
                res = bnb.minimize(modelktns, openNodes, pair -> {
                    System.out.println("new best solution: " + pair.f);
                }, true);
            }
            long t1 = System.currentTimeMillis();
            System.out.print(fileName + ";" + problemName + ";" + searchName + ";" + (instance.n - 1) + ";" + instance.m + ";" + instance.c + ";" + Arrays.deepToString(res.getUb_time().toArray()) + ";" + res.getNb_nodes() + ";" + res.getCost() + ";" + (t1 - t0) + "\n");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }

    }
}
