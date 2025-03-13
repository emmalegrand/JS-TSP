package examples.jstsp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FreeToolsTest {
    @Test
    public void addTest(){
        FreeTools f = new FreeTools(5);
        f.add(4,5);
        assertEquals(f.bitsets[4], 0b100000);
        f.add(4,2);
        assertEquals(f.bitsets[4], 0b100100);
        f.add(4,3);
        assertEquals(f.bitsets[4], 0b101100);
    }
    @Test
    public void containsTest(){
        FreeTools f = new FreeTools(5);
        f.add(4,5);
        f.add(4,2);
        f.add(4,3);
        assertEquals(f.contains(2),4);
        assertEquals(f.contains(1),-1);
    }
    @Test
    public void removeTest(){
        FreeTools f = new FreeTools(5);
        f.add(4,5);
        f.add(4,2);
        f.add(4,3);
        f.add(1,4);
        f.remove(4,3);
        assertEquals(f.bitsets[4], 0b000000);
        assertEquals(f.bitsets[3], 0b100100);
        assertEquals(f.bitsets[1], 0b010000);
    }
    @Test
    public void removeFromTest(){
        FreeTools f = new FreeTools(5);
        f.add(4,5);
        f.add(4,2);
        f.add(4,3);
        f.add(1,4);
        f.removeFrom(1);
        assertEquals(f.bitsets[4], 0b000000);
        assertEquals(f.bitsets[3], 0b000000);
        assertEquals(f.bitsets[2], 0b000000);
        assertEquals(f.bitsets[1], 0b111100);
    }
    @Test
    public void resetTest(){
        FreeTools f = new FreeTools(5);
        f.add(4,5);
        f.add(4,2);
        f.add(4,3);
        f.add(1,4);
        f.reset();
        assertEquals(f.bitsets[4], 0b000000);
        assertEquals(f.bitsets[3], 0b000000);
        assertEquals(f.bitsets[2], 0b000000);
        assertEquals(f.bitsets[1], 0b000000);
        assertEquals(f.bitsets[0], 0b000000);
    }
}
