import java.util.*;

public class Main {

    private static ArrayList<Integer> subarraySum(int[] arr, int n, int s) {
        ArrayList<Integer> result = new ArrayList<>();
        int sum = 0, i = 0;
        result.add(0);
        while (i < n) {
            sum += arr[i];

            if (sum == s) {
                result.add(i + 1);
                break;
            }

            if (sum > s) {
                int index = result.get(0);
                i = index + 1;
                result.remove(0);
                result.add(i);
                sum = 0;
            } else {
                i++;
            }
        }

        if (result.size() == 2) {
            i = result.get(0);
            result.remove(0);
            result.add(0, i + 1);
        } else {
            result.remove(0);
            result.add(-1);
        }

        return result;
    }

    private static int countTriplet(int[] arr, int n) {
        int count = 0;

        Arrays.sort(arr);

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (arr[i] + arr[j] == arr[k]) {
                        count++;
                    } else if (arr[i] + arr[j] < arr[k]) {
                        break;
                    }
                }
            }
        }

        return count;
    }

    private static long maxSubarraySum(int[] arr, int n) {
        int c = arr[0], m = arr[0];
        for (int i = 1; i < n; i++) {
            c = Math.max(arr[i] + c, arr[i]);
            if (m < c) {
                m = c;
            }
        }
        return m;
    }

    public static void mergeWithoutExtraSpace(long[] arr1, long[] arr2, int n, int m) {
        int i, j, k;
        long tmp;

        if (n <= m) {
            i = 0;
            j = 0;
            while (i < n) {
                if (arr1[i] > arr2[j]) {
                    tmp = arr1[i];
                    arr1[i] = arr2[j];
                    arr2[j] = tmp;
                    k = j;
                    while (k < m - 1 && arr2[k] > arr2[k + 1]) {
                        tmp = arr2[k];
                        arr2[k] = arr2[k + 1];
                        arr2[k + 1] = tmp;
                        k++;
                    }
                }
                i++;
            }
        } else {
            i = n - 1;
            j = m - 1;
            while (j >= 0) {
                if (arr1[i] > arr2[j]) {
                    tmp = arr1[i];
                    arr1[i] = arr2[j];
                    arr2[j] = tmp;
                    k = i;
                    while (k >= 1 && arr1[k] < arr1[k - 1]) {
                        tmp = arr1[k];
                        arr1[k] = arr1[k - 1];
                        arr1[k - 1] = tmp;
                        k--;
                    }
                }
                j--;
            }
        }

        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }

    public static void mergeWithoutExtraSpaceBetterSolution(long[] arr1, long[] arr2, int n, int m) {
        int gap = (int) Math.ceil((double) (m + n) / 2);
        long temp;

        while (gap > 0) {
            int i = 0;
            int j = gap;

            while (j < n + m) {
                if (j < n && arr1[i] > arr1[j]) {
                    temp = arr1[i];
                    arr1[i] = arr1[j];
                    arr1[j] = temp;
                } else if (j >= n && i < n && arr1[i] > arr2[j - n]) {
                    temp = arr1[i];
                    arr1[i] = arr2[j - n];
                    arr2[j - n] = temp;
                } else if (j >= n && i >= n && arr2[i - n] > arr2[j - n]) {
                    temp = arr2[i - n];
                    arr2[i - n] = arr2[j - n];
                    arr2[j - n] = temp;
                }
                i++;
                j++;
            }

            if (gap == 1) {
                gap = 0;
            } else {
                gap = (int) Math.ceil((double) gap / 2);
            }
        }

        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }

    public static void rearrange(long[] arr, int n){
        int i, j = 0, len = n;
        long max;

        while (len > 0) {
            i = n - 1;
            max = arr[i];
            while (i > j) {
                arr[i] = arr[i - 1];
                i--;
            }
            arr[j] = max;
            len -= 2;
            j += 2;
        }

        System.out.println(Arrays.toString(arr));
    }

    public static void rearrangeBetterComplexity(long[] arr, int n){
        int minIndex = 0;
        int maxIndex = n - 1;

        long mx = arr[n - 1] + 1;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                arr[i] += (arr[maxIndex] % mx) * mx;
                maxIndex--;
            } else {
                arr[i] += (arr[minIndex] % mx) * mx;
                minIndex++;
            }
        }

        for (int i = 0; i < n; i++) {
            arr[i] /= mx;
        }

        System.out.println(Arrays.toString(arr));
    }

    private static long count(int x, int[] y, int N, int[] freq) {
        if (x == 0) {
            return 0;
        }

        if (x == 1) {
            return freq[0];
        }

        int index = Arrays.binarySearch(y, x);
        if (index < 0) {
            index = Math.abs(index + 1);
        } else {
            while (index < N && y[index] == x) {
                index++;
            }
        }
        int count = N - index;

        count += (freq[0] + freq[1]);

        if (x == 2) {
            count -= (freq[3] + freq[4]);
        }

        if (x == 3) {
            count += freq[2];
        }

        return count;
    }

    public static long countPairs(int[] x, int[] y, int M, int N) {
        Arrays.sort(y);

        int[] freq = new int[5];
        for (int i = 0; i < N; i++) {
            if (y[i] < 5) {
                freq[y[i]]++;
            }
        }

        long totalNumber = 0;

        for (int i = 0; i < M; i++) {
            totalNumber += count(x[i], y, N, freq);
        }

        return totalNumber;
    }

    public static long inversionCount(long[] arr, long N) {
        throw new UnsupportedOperationException();
    }

    public static void sort012(int[] a, int n) {
        int[] arr012 = new int[3];

        for (int i = 0; i < n; i++) {
            arr012[a[i]]++;
        }

        int i = 0;
        while (arr012[0] > 0) {
            a[i++] = 0;
            arr012[0]--;
        }

        while (arr012[1] > 0) {
            a[i++] = 1;
            arr012[1]--;
        }

        while (arr012[2] > 0) {
            a[i++] = 2;
            arr012[2]--;
        }

        System.out.println(Arrays.toString(a));
    }

    public static int equilibriumPoint(long[] arr, int n) {
        if (n == 1) {
            return n;
        }

        if (n == 2) {
            return -1;
        }

        long sum1 = arr[0];
        int equilibriumPointIndex = 1;
        long sum2 = 0;

        for (int i = 2; i < n; i++) {
            sum2 += arr[i];
        }

        while(sum1 <= sum2) {
            if (sum1 == sum2) {
                return equilibriumPointIndex + 1;
            }

            sum1 += arr[equilibriumPointIndex++];
            sum2 -= arr[equilibriumPointIndex];
        }

        return -1;
    }

    public static ArrayList<Integer> leaders(int[] arr, int n) {
        int max = Integer.MIN_VALUE;
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] >= max) {
                list.addFirst(arr[i]);
                max = arr[i];
            }
        }

        return new ArrayList<>(list);
    }

    public static int smallestSubWithSum(int[] a, int n, int x) {
        int sum = 0, startSumIndex = 0, minLength = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            sum += a[i];

            if (sum > x) {
                while (sum - a[startSumIndex] > x) {
                    sum -= a[startSumIndex++];
                }
                minLength = Math.min(i - startSumIndex + 1, minLength);
                sum -= a[startSumIndex++];
            }
        }
        return minLength;
    }

    public static String printLargest(String[] arr) {
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String newFirst = o1 + o2;
                String newSecond = o2 + o1;

                return newFirst.compareTo(newSecond) <= 0 ? 1 : -1;
            }
        });

        StringBuilder result = new StringBuilder();
        for (String s : arr) {
            result.append(s);
        }

        return result.toString();
    }

    public static int findElement(int[] arr, int n) {
        int[] maxElements = new int[n];
        int[] minElements = new int[n];

        maxElements[0] = arr[0];
        minElements[n - 1] = arr[n - 1];

        for (int i = 1; i < n; i++) {
            maxElements[i] = Integer.max(arr[i], maxElements[i - 1]);
            minElements[n - i - 1] = Integer.min(arr[n - i - 1], minElements[n - i]);
        }

        for (int i = 1; i < n - 1; i++) {
            if (minElements[i] == maxElements[i]) {
                return minElements[i];
            }
        }

        return -1;
    }

    public static ArrayList<Integer> spirallyTraverse(int[][] matrix, int r, int c) {
        int upStart = 0, upEnd = c;
        int rightStart = 1, rightEnd = r;
        int downStart = c - 2, downEnd = 0;
        int leftStart = r - 2, leftEnd = 1;

        int row = 0, col;

        ArrayList<Integer> result = new ArrayList<>();

        while (result.size() < r * c) {

            for (int i = upStart; i < upEnd; i++) {
                result.add(matrix[row][i]);
            }
            if (result.size() == r * c) {
                break;
            }
            upStart++;
            upEnd--;
            col = upEnd;

            for (int i = rightStart; i < rightEnd; i++) {
                result.add(matrix[i][col]);
            }
            if (result.size() == r * c) {
                break;
            }
            rightStart++;
            rightEnd--;
            row = rightEnd;

            for (int i = downStart; i >= downEnd; i--) {
                result.add(matrix[row][i]);
            }
            if (result.size() == r * c) {
                break;
            }
            downStart--;
            downEnd++;
            col = upStart - 1;

            for (int i = leftStart; i >= leftEnd ; i--) {
                result.add(matrix[i][col]);
            }
            if (result.size() == r * c) {
                break;
            }
            leftStart--;
            leftEnd++;
            row = leftEnd - 1;
        }

        return result;
    }

    public static int lastIndex(String s) {
        int lastIndex = -1;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                lastIndex = i;
            }
        }

        return lastIndex;
    }

    public static void zigZag(int[] arr, int n) {
        int tmp;
        for (int i = 0; i < n - 1; i++) {
            if (i % 2 == 0) {
                if (arr[i] > arr[i + 1]) {
                    tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                }
            } else {
                if (arr[i] < arr[i + 1]) {
                    tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                }
            }
        }
    }

    public static ArrayList<ArrayList<Integer> > stockBuySell(int[] A, int n) {
        ArrayList<Integer> temp = new ArrayList<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        int firstBuy = 0;

        while (firstBuy < n - 1 && A[firstBuy] > A[firstBuy + 1]) {
            firstBuy++;
        }

        temp.add(firstBuy);

        for (int i = firstBuy + 1; i < n; i++) {
            if (A[i - 1] > A[i]) {
                temp.add(1, i - 1);
                result.add(new ArrayList<>(temp));
                temp.clear();
                temp.add(i);
            }
        }

        if (A[temp.get(0)] < A[n - 1]) {
            temp.add(1, n - 1);
            result.add(new ArrayList<>(temp));
        }

        return result;
    }

    public static long findMinDiff(ArrayList<Integer> a, int n, int m) {
        Collections.sort(a);
        long currentMin;
        long min = Long.MAX_VALUE;

        for (int i = 0; i < n - m + 1; i++) {
            List<Integer> temp = a.subList(i, i + m);

            currentMin = temp.get(m - 1) - temp.get(0);
            if (currentMin < min) {
                min = currentMin;
            }
        }

        return min;
    }

    public static boolean checkTriplet(int[] arr, int n) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        boolean[] booleans = new boolean[max + 1];

        for (int i = 0; i < n; i++) {
            booleans[arr[i]] = true;
        }

        for (int i = 3; i < max; i++) {
            for (int j = i + 1; j < max + 1; j++) {
                double k = Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2));
                if (booleans[i] && booleans[j] && k == Math.ceil(k) && (int)k < max + 1 && booleans[(int)k]) {
                    return true;
                }
            }
        }

        return false;
    }

    public static long trappingWater(int[] arr, int n) {
        int lastMax = arr[0];

        long[] units = new long[n];
        units[0] = 0;
        units[n - 1] = 0;

        for (int i = 1; i < n - 1; i++) {
            if (lastMax < arr[i]) {
                units[i] = 0;
                lastMax = arr[i];
            } else {
                units[i] = lastMax - arr[i];
            }
        }

        lastMax = arr[n - 1];

        for (int i = n - 2; i > 0 ; i--) {
            if (lastMax < arr[i]) {
                units[i] = 0;
                lastMax = arr[i];
            } else {
                units[i] = Long.min(lastMax - arr[i], units[i]);
            }
        }

        long sum = 0;

        for (int i = 1; i < n - 1; i++) {
            sum += units[i];
        }

        return sum;
    }

    public static void reverseInGroups(ArrayList<Integer> arr, int n, int k) {
        List<Integer> temp;

        for (int i = 0; i < n; i+=k) {
            temp = arr.subList(i, i + k);
            Collections.reverse(temp);

        }
    }

    private static int partition(int[] arr, int l, int r) {
        int x = arr[r];
        int i = l - 1;
        int tmp;

        for (int j = l; j < r; j++) {
            if (arr[j] < x) {
                i++;
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }

        tmp = arr[i + 1];
        arr[i + 1] = arr[r];
        arr[r] = tmp;

        return i + 1;
    }

    public static int kthSmallest(int[] arr, int l, int r, int k) {
        if (l == r) {
            return arr[l];
        }

        int position = partition(arr, l, r);
        int count = position - l + 1;

        if (count == k) {
            return arr[position];
        }

        if (count < k) {
            return kthSmallest(arr, position + 1, r, k - count);
        }

        return kthSmallest(arr, l, position - 1, k);
    }

    public static int kthSmallestWithMinHeap(int[] arr, int l, int r, int k) {
        Queue<Integer> q = new PriorityQueue<>();

        for (int i = l; i <= r; i++) {
            q.add(arr[i]);
        }

        for (int i = 0; i < k - 1; i++) {
            q.poll();
        }

        return q.peek() == null ? Integer.MIN_VALUE : q.peek();
    }

    private static class Trains {
        int arrivalTime;
        int departureTime;

        Trains(int arrivalTime, int departureTime) {
            this.arrivalTime = arrivalTime;
            this.departureTime = departureTime;
        }
    }

    private static class SortByArrivalTimeComparator implements Comparator<Trains> {

        @Override
        public int compare(Trains t1, Trains t2) {
            return t1.arrivalTime - t2.arrivalTime;
        }
    }

    public static int findPlatformMyVersion(int[] arr, int[] dep, int n) {
        Trains[] trains = new Trains[n];

        for (int i = 0; i < n; i++) {
            trains[i] = new Trains(arr[i], dep[i]);
        }

        Arrays.sort(trains, new SortByArrivalTimeComparator());

        int[] platforms = new int[n];
        platforms[0] = trains[0].departureTime;
        int count = 1;

        for (int i = 1; i < n; i++) {
            platforms[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < n; i++) {
            int j;
            for (j = 0; j < count; j++) {
                 if (platforms[j] < trains[i].arrivalTime) { // platforms[j] is the first free platform if it's less or equal to arr[i]
                     break;
                 }
            }
            if (j < count) {
                platforms[j] = trains[i].departureTime;
            } else {
                platforms[count++] = trains[i].departureTime;
            }
        }

        return count;
    }

    public static int findPlatform(int[] arr, int[] dep, int n) {
        Trains[] trains = new Trains[n];

        for (int i = 0; i < n; i++) {
            trains[i] = new Trains(arr[i], dep[i]);
        }

        Arrays.sort(trains, new SortByArrivalTimeComparator());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(trains[0].departureTime);
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (trains[i].arrivalTime <= pq.peek()) {
                count++;
            } else {
                pq.poll();
            }

            pq.add(trains[i].departureTime);
        }

        return count;
    }


    public static void main(String[] args) {



        // ###########################################################################################

        /*int[] arr = {1,2,3,4,5,6,7,8,9,10};
        int n = 10;
        int s = 15;

        System.out.println(Main.subarraySum(arr, n, s));*/

        // ###########################################################################################

        /*int[] arr = {12, 8, 2, 11, 5, 14, 10};
        int n = 7;

        System.out.println(Main.countTriplet(arr, n));*/

        // ###########################################################################################

        /*int[] arr = {1,-2,3,-2,5};
        int n = 5;

        System.out.println(Main.maxSubarraySum(arr, n));*/

        // ###########################################################################################

        /*long[] arr1 = {1,2,3,4,5,6,7,8,9,10};
        long[] arr2 = {0, 18};
        int n = 10;
        int m = 2;

        Main.mergeWithoutExtraSpaceBetterSolution(arr1, arr2, n, m);*/

        // ###########################################################################################

        /*long[] arr = {1,2,3,4,5,6,7,8,9,10};
        int n = 10;

        Main.rearrangeBetterComplexity(arr, n);*/

        // ###########################################################################################

        /*int[] x = {2, 1, 6};
        int[] y = {1, 5};

        System.out.println(Main.countPairs(x, y, x.length, y.length));*/

        // ###########################################################################################

        /*int[] arr = {0, 2, 1, 2, 0};
        Main.sort012(arr, arr.length);*/

        // ###########################################################################################

        /*long[] arr = {1, 2, 3, 4, 5, 100000, 150};
        System.out.println(Main.equilibriumPoint(arr, arr.length));*/

        // ###########################################################################################

        /*int[] arr = {16, 17, 4, 3, 5, 2};
        System.out.println(Main.leaders(arr, arr.length));*/

        // ###########################################################################################

        /*int[] arr = {6, 3, 4, 5, 4, 3, 7, 9};
        int X = 16;
        System.out.println(smallestSubWithSum(arr, arr.length, X));*/

        // ###########################################################################################

        /*//String[] arr = {"3", "30", "34", "5", "9"};
        //String[] arr = {"54", "546", "548", "60"};
        String[] arr = {"250", "74", "659", "931", "273", "545", "879", "924"};
        //String[] arr = {"905", "969", "9", "969", "963", "943"}; // 9 969 969 963 943 905
        System.out.println(printLargest(arr));*/

        // ###########################################################################################

        /*int[] arr = {11, 9, 12};
        System.out.println(findElement(arr, arr.length));*/

        // ###########################################################################################

        /*int[] A = {4,3,2,1};
        System.out.println(stockBuySell(A, A.length));*/

        // ###########################################################################################

        /*ArrayList<Integer> A = new ArrayList<>(List.of(7, 3, 2, 4, 9, 12, 56));
        System.out.println(findMinDiff(A, A.size(), 3));*/

        // ###########################################################################################

        /*int[] arr = {3, 2, 4, 6, 5};
        System.out.println(checkTriplet(arr, arr.length));*/

        // ###########################################################################################

        /*int[] arr = {7, 4, 0, 8, 0, 2};
        System.out.println(trappingWater(arr, arr.length));*/

        // ###########################################################################################

        /*int[] arr = {7, 10, 4, 20, 15};
        System.out.println(kthSmallestWithMinHeap(arr, 0, arr.length - 1, 4));*/

        // ###########################################################################################

        //int[] arr = {900, 940, 950, 1100, 1500, 1800};
        //int[] dep = {910, 1200, 1120, 1130, 1900, 2000};

        //int[] arr = { 200, 210, 300, 320, 350, 500 };
        //int[] dep = { 230, 340, 320, 430, 400, 520 };

        /*int[] arr = {900, 1100, 1235};
        int[] dep = {1000, 1200, 1240};

        System.out.println(findPlatform(arr, dep, arr.length));*/

        // ###########################################################################################
    }

}
