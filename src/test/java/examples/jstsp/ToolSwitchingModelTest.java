package examples.jstsp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToolSwitchingModelTest {
    @Test
    public void test_lowerboundL2() {
        ToolSwitchingInstance instance = ToolSwitchingInstance.readFile("data/toolswitching/Catanzaro/datA1", "problem 1");

        ToolSwitchingModel model = new ToolSwitchingModel(instance, instance.getMin_cost());
        assertEquals(model.lowerBoundL2(new ToolSwitchingState(8,new FreeTools(instance.c),1652L)),6,0.001);

        assertEquals(model.lowerBoundL2(new ToolSwitchingState(7,new FreeTools(instance.c),1918L)),9,0.001);

        assertEquals(model.lowerBoundL2(new ToolSwitchingState(2,new FreeTools(instance.c),1754L)),7,0.001);


    }
}
