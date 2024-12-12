package examples.jstsp;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToolSwitchingInstanceTest {
    @Test
    public void getMin_costTest() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA1", "problem 1");
        double[][] minCostExpected = {{Double.MAX_VALUE,0,0,0,0,0,0,0,0,0,0},
            {0,Double.MAX_VALUE,0,0,1,1,1,1,0,2,2},
            {0,0,Double.MAX_VALUE,1,1,1,1,2,1,2,1},
            {0,0,1,Double.MAX_VALUE,0,2,1,1,2,2,2},
            {0,1,1,0,Double.MAX_VALUE,1,2,1,1,1,2},
            {0,1,1,2,1,Double.MAX_VALUE,2,1,0,1,2},
            {0,1,1,1,2,2,Double.MAX_VALUE,2,2,2,3},
            {0,1,2,1,1,1,2,Double.MAX_VALUE,1,2,4},
            {0,0,1,2,1,0,2,1,Double.MAX_VALUE,1,3},
            {0,2,2,2,1,1,2,2,1,Double.MAX_VALUE,2},
            {0,2,1,2,2,2,3,4,3,2,Double.MAX_VALUE}};
        double[][] minCost = instance.getMin_cost();
        for (int i = 0; i < minCostExpected.length; i++) {
            Assert.assertTrue(Arrays.equals(minCostExpected[i], minCost[i]));
        }
    }

}
