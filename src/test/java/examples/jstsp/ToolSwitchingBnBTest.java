package examples.jstsp;

import abstraction.OpenNodes;
import org.junit.Assert;
import org.junit.Test;
import solver.AnytimeColumnSearch;
import solver.AnytimeWeigthedAstar;
import solver.Astar;
import solver.BranchAndBound;
import util.BestFirstOpenNodes;
import util.Result;

public class ToolSwitchingBnBTest {

    @Test
    public void dummyTest() {
        long t0 = System.currentTimeMillis();
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/dummy.txt", "problem 1");
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 11);
    }

    @Test
    public void datA1_1Test() {
        long t0 = System.currentTimeMillis();
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA1", "problem 1");
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 14);
    }

    @Test
    public void datA1_2Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA1", "problem 2");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 11);
    }

    @Test
    public void datA1_3Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA1", "problem 3");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 12);
    }

    @Test
    public void datA1_4Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA1", "problem 4");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 13);
    }

    @Test
    public void datA1_5Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA1", "problem 5");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 13);
    }

    @Test
    public void datA1_6Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA1", "problem 6");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 14);
    }

    @Test
    public void datA1_7Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA1", "problem 7");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 12);
    }

    @Test
    public void datA1_8Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA1", "problem 8");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 11);
    }

    @Test
    public void datA1_9Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA1", "problem 9");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 13);
    }

    @Test
    public void datA1_10Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA1", "problem 10");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 12);

    }

    @Test
    public void datA2_1Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA2", "problem 1");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 11);
    }

    @Test
    public void datA2_2Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA2", "problem 2");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);
    }

    @Test
    public void datA2_3Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA2", "problem 3");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);
    }

    @Test
    public void datA2_4Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA2", "problem 4");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 11);
    }

    @Test
    public void datA2_5Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA2", "problem 5");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 11);
    }

    @Test
    public void datA2_6Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA2", "problem 6");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 12);
    }

    @Test
    public void datA2_7Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA2", "problem 7");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 11);
    }

    @Test
    public void datA2_8Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA2", "problem 8");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);
    }

    @Test
    public void datA2_9Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA2", "problem 9");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 11);
    }

    @Test
    public void datA2_10Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA2", "problem 10");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 11);

    }

    @Test
    public void datA3_1Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA3", "problem 1");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);
    }

    @Test
    public void datA3_2Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA3", "problem 2");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);
    }

    @Test
    public void datA3_3Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA3", "problem 3");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);
    }

    @Test
    public void datA3_4Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA3", "problem 4");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);
    }

    @Test
    public void datA3_5Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA3", "problem 5");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);
    }

    @Test
    public void datA3_6Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA3", "problem 6");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 11);
    }

    @Test
    public void datA3_7Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA3", "problem 7");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);
    }

    @Test
    public void datA3_8Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA3", "problem 8");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);
    }

    @Test
    public void datA3_9Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA3", "problem 9");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);
    }

    @Test
    public void datA3_10Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA3", "problem 10");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);

    }

    @Test
    public void datA4_1Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA4", "problem 1");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);
    }

    @Test
    public void datA4_2Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA4", "problem 2");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);
    }

    @Test
    public void datA4_3Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA4", "problem 3");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);
    }

    @Test
    public void datA4_4Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA4", "problem 4");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);
    }

    @Test
    public void datA4_5Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA4", "problem 5");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);
    }

    @Test
    public void datA4_6Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA4", "problem 6");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);
    }

    @Test
    public void datA4_7Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA4", "problem 7");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);
    }

    @Test
    public void datA4_8Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA4", "problem 8");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);
    }

    @Test
    public void datA4_9Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA4", "problem 9");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);
    }

    @Test
    public void datA4_10Test() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA4", "problem 10");
        long t0 = System.currentTimeMillis();
        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        BranchAndBound<ToolSwitchingState> bnb = new BranchAndBound<>();
        OpenNodes<ToolSwitchingState> openNodes = new BestFirstOpenNodes<>();
        Result res =  bnb.minimize(model, openNodes, pair -> {
            System.out.println("new best solution: " + pair.f);
        },false);
        Assert.assertEquals(res.getCost(), 10);

    }
}
