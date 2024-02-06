public class RecursionMain {

    private static void floodFill(int[][] image, int sr, int sc, int oldColor, int newColor) {
        if (sr < 0 || sr >= image.length) {
            return;
        }

        if (sc < 0 || sc >= image[sr].length) {
            return;
        }

        if (image[sr][sc] == oldColor) {
            image[sr][sc] = newColor;
            floodFill(image, sr - 1, sc, oldColor, newColor);
            floodFill(image, sr + 1, sc, oldColor, newColor);
            floodFill(image, sr, sc - 1, oldColor, newColor);
            floodFill(image, sr, sc + 1, oldColor, newColor);
        }
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (newColor != image[sr][sc]) {
            floodFill(image, sr, sc, image[sr][sc], newColor);
        }
        return image;
    }

    private static long numberOfPaths(int i, int j, int m, int n) {
        if (i == m || j == n) {
            return 1;
        }

        return numberOfPaths(i + 1, j, m, n) + numberOfPaths(i, j + 1, m, n);

    }

    public static long numberOfPaths(int m, int n) {
        return numberOfPaths(1, 1, m, n);
    }

    private static int optimalKeys(int pressed, int N, int As, boolean copied, int copiedAs) {
        if (pressed > N) {
            return 0;
        }

        if (pressed == N) {
            return As;
        }

        if (copied) {
            int tmp =  Integer.max(optimalKeys(pressed + 1, N, As + 1, false, 0),
                    optimalKeys(pressed + 1, N, As + copiedAs, true, copiedAs));
            return Integer.max(tmp, optimalKeys(pressed + 3, N, As + As, true, As));
        } else {
            return Integer.max(optimalKeys(pressed + 1, N, As + 1, false, 0),
                    optimalKeys(pressed + 3, N, As + As, true, As));
        }
    }

    private static int optimalKeys(int N, int As, boolean copied, int copiedAs) {
        if (N < 0) {
            return 0;
        }

        if (N == 0) {
            return As;
        }

        if (copied) {
            int tmp =  Integer.max(optimalKeys(N - 1, As + 1, false, 0),
                    optimalKeys(N - 1, As + copiedAs, true, copiedAs));
            return Integer.max(tmp, optimalKeys(N - 3, As + As, true, As));
        } else {
            return Integer.max(optimalKeys( N - 1, As + 1, false, 0),
                    optimalKeys(N - 3, As + As, true, As));
        }
    }

    public static int optimalKeys(int N){
        //return optimalKeys(1, N, 1, false, 0);
        return optimalKeys(N - 1, 1, false, 0);
    }

    public static void main(String[] args) {

        /*int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
        System.out.println(Arrays.deepToString(floodFill(image, 1, 1, 2)));*/

        /*System.out.println(numberOfPaths(2, 8));*/

        System.out.println(optimalKeys(20));

    }

}
