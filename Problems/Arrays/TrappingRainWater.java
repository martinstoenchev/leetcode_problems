public class TrappingRainWater {

    private int computeTrappedWaterForRange(int startIndex, int endIndex, int minEl, int[] height) {
        int result = 0;

        for (int i = startIndex; i < endIndex; i++) {
            result += (minEl - height[i]);
        }

        return result;
    }

    public int trap(int[] height) {
        int i = 0;
        int result = 0;

        for (int j = i + 1; j < height.length; j++) {
            if (height[i] <= height[j]) {
                result += computeTrappedWaterForRange(i + 1, j, height[i], height);
                i = j;
            }
        }

        i = height.length - 1;
        for (int j = i - 1; j >= 0 ; j--) {
            if (height[i] < height[j]) {
                result += computeTrappedWaterForRange(j + 1, i, height[i], height);
                i = j;
            }
        }

        return result;
    }

}
