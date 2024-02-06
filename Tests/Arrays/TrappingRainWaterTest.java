import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrappingRainWaterTest {

    @Test
    public void testSolution() {
        TrappingRainWater trappingRainWater = new TrappingRainWater();

        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        assertEquals(6, trappingRainWater.trap(height));

        height = new int[]{4, 2, 0, 3, 2, 5};
        assertEquals(9, trappingRainWater.trap(height));

        height = new int[]{0,1,0,3,0,2,1,4,0,1};
        assertEquals(8, trappingRainWater.trap(height));

        height = new int[]{2,0,2};
        assertEquals(2, trappingRainWater.trap(height));

        height = new int[]{3, 0, 0, 2, 0, 4};
        assertEquals(10, trappingRainWater.trap(height));

        height = new int[]{7,4,0,9};
        assertEquals(10, trappingRainWater.trap(height));

        height = new int[]{6,9,9};
        assertEquals(0, trappingRainWater.trap(height));
    }

}
