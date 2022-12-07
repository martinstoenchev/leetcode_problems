import java.util.*;

public class Main {

    public static boolean balancedBrackets(String x) {
        Deque<Character> deque = new ArrayDeque<>();
        char out;

        for (int i = 0; i < x.length(); i++) {
            char c = x.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                deque.addLast(c);
            } else if (!deque.isEmpty()) {
                if (c == ')') {
                    out = deque.pollLast();
                    if (out != '(') {
                        return false;
                    }
                } else if (c == ']') {
                    out = deque.pollLast();
                    if (out != '[') {
                        return false;
                    }
                } else {
                    out = deque.pollLast();
                    if (out != '{') {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }

        return deque.isEmpty();
    }

    static class StackQueue {

        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();

        void push(int x) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }

            s1.push(x);

            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }


        int pop() {
            return s1.isEmpty() ? -1 : s1.pop();
        }

    }

    static class QueueStack {

        Queue<Integer> q1 = new LinkedList<Integer>();
        Queue<Integer> q2 = new LinkedList<Integer>();

        void push(int a) {
            while (!q1.isEmpty()) {
                q2.add(q1.poll());
            }

            q1.add(a);

            while (!q2.isEmpty()) {
                q1.add(q2.poll());
            }
        }

        int pop() {
            return q1.isEmpty() ? -1 : q1.poll();
        }

    }

    static class StackWithMinnEl {

        int minEle;
        Stack<Integer> s;

        StackWithMinnEl() {
            minEle = -1;
            s = new Stack<>();
        }

        int getMin() {
            return minEle;
        }

        int pop() {
            if (!s.isEmpty()) {
                if (s.peek() == minEle) {
                    int result = s.pop();
                    minEle = s.isEmpty() ? -1 : s.peek();
                    Stack<Integer> s1 = new Stack<>();
                    while (!s.isEmpty()) {
                        minEle = Integer.min(minEle, s.peek());
                        s1.push(s.pop());
                    }
                    while (!s1.isEmpty()) {
                        s.push(s1.pop());
                    }
                    return result;
                } else {
                    return s.pop();
                }
            }

            return -1;
        }

        void push(int x) {
            if (s.isEmpty()) {
                minEle = x;
            }
            s.push(x);
            minEle = Integer.min(minEle, x);
        }

    }

    static class Pair<K, V>{

        K key;
        V value;

        public Pair(K key, V  value) {
            this.key = key;
            this.value = value;
        }

        public void setValue(V value) {
            this.value = value;
        }
        public V getValue() {
            return value;
        }

        public void setKey(K key) {
            this.key = key;
        }
        public K getKey() {
            return key;
        }

    }

    static class LRUCache {

        private static int capacity;
        private static Queue<Pair<Integer, Integer>> q;

        public LRUCache(int cap) {
            capacity = cap;
            q = new LinkedList<>();
        }

        public int get(int key) {
            if (q.isEmpty() || !hasKey(key)) {
                return -1;
            }

            int i = 0;
            int n = q.size();
            Pair<Integer, Integer> temp = q.poll();
            while (i < n - 1 && temp != null && key != temp.getKey()) {
                q.add(temp);
                i++;
                temp = q.poll();
            }

            if (i == n - 1) {
                q.add(temp);
                return temp.getKey() == key ? temp.getValue() : -1;
            }
            Pair<Integer, Integer> result = temp;

            while (i < n - 1) {
                temp = q.poll();
                q.add(temp);
                i++;
            }
            q.add(result);

            return result != null ? result.getValue() : -1;
        }

        public void set(int key, int value) {
            if (hasKey(key)) {
                int i = 0;
                int n = q.size();
                while (i < n) {
                    Pair<Integer, Integer> temp = q.poll();
                    if (temp != null && temp.getKey() != key) {
                        q.add(temp);
                    }
                    i++;
                }
            } else if (q.size() == capacity) {
                q.poll();
            }
            q.add(new Pair<>(key, value));
        }

        private boolean hasKey(int key) {
            for (Pair<Integer, Integer> pair : q) {
                if (pair.getKey() == key) {
                    return true;
                }
            }
            return false;
        }

    }

    static class Element {
        int x;
        int y;

        Element(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static boolean isDelimiter(Element e) {
        return e.x == -1 && e.y == -1;
    }

    private static boolean isValid(int i, int j, int n, int m) {
        return 0 <= i && i < n && 0 <= j && j < m;
    }

    private static boolean checkAll(int[][] grid) {
        for (int[] ints : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                if (ints[j] == 1) {
                    return true;
                }
            }
        }

        return false;
    }

    public static int orangesRotting(int[][] grid) {
        Queue<Element> queue = new LinkedList<>();
        int count = 0;
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new Element(i, j));
                }
            }
        }

        queue.add(new Element(-1, -1));

        while (!queue.isEmpty()) {
            boolean flag = false;

            while (!isDelimiter(queue.peek())) {
                Element curr = queue.poll();

                if (isValid(curr.x - 1, curr.y, n, m) && grid[curr.x - 1][curr.y] == 1) {
                    if (!flag) {
                        count++;
                        flag = true;
                    }

                    grid[curr.x - 1][curr.y] = 2;
                    queue.add(new Element(curr.x - 1, curr.y));
                }
                if (isValid(curr.x + 1, curr.y, n, m) && grid[curr.x + 1][curr.y] == 1) {
                    if (!flag) {
                        count++;
                        flag = true;
                    }

                    grid[curr.x + 1][curr.y] = 2;
                    queue.add(new Element(curr.x + 1, curr.y));
                }
                if (isValid(curr.x, curr.y - 1, n, m) && grid[curr.x][curr.y - 1] == 1) {
                    if (!flag) {
                        count++;
                        flag = true;
                    }

                    grid[curr.x][curr.y - 1] = 2;
                    queue.add(new Element(curr.x, curr.y - 1));
                }
                if (isValid(curr.x, curr.y + 1, n, m) && grid[curr.x][curr.y + 1] == 1) {
                    if (!flag) {
                        count++;
                        flag = true;
                    }

                    grid[curr.x][curr.y + 1] = 2;
                    queue.add(new Element(curr.x, curr.y + 1));
                }

            }
            queue.remove();

            if (!queue.isEmpty()) {
                queue.add(new Element(-1, -1));
            }
        }

        return checkAll(grid) ? -1 : count;
    }

    public static ArrayList<Integer> maxOfSubArrays(int[] arr, int n, int k) {
        Queue<Integer> q = new LinkedList<>();
        int max = 0;
        ArrayList<Integer> result = new ArrayList<>();


        for (int i = 0; i < k; i++) {
            q.add(arr[i]);
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        result.add(max);

        for (int i = k; i < n; i++) {
            int el = q.poll();
            q.add(arr[i]);

            if (arr[i] > max) {
                max = arr[i];
            } else if (el == max) {
                max = Integer.MIN_VALUE;
                for (int j = 0; j < k; j++) {
                    el = q.poll();
                    if (el > max) {
                        max = el;
                    }
                    q.add(el);
                }
            }

            result.add(max);
        }

        return result;
    }

    public static String firstNonRepeating(String A) {
        boolean[] booleans = new boolean[26];
        List<Character> q = new LinkedList<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < A.length(); i++) {
            char c = A.charAt(i);
            if (!booleans[c - 97]) {
                if (!q.contains(c)) {
                    q.add(c);
                } else {
                    q.remove((Character)c);
                    booleans[c - 97] = true;
                }
            }

            if (q.size() > 0) {
                result.append(q.get(0));
            } else {
                result.append('#');
            }
        }

        return result.toString();
    }


    public static int tour(int[] petrol, int[] distance) {
        int n = petrol.length;
        int currentStartIndex = 0;

        while (currentStartIndex < n) {
            int petrolInTruck = petrol[currentStartIndex];
            int distanceBetweenPumps = distance[currentStartIndex];
            boolean broken = false;

            for (int j = currentStartIndex + 1; j < n; j++) {
                if (petrolInTruck < distanceBetweenPumps) {
                    broken = true;
                    currentStartIndex++;
                    break;
                } else {
                    petrolInTruck -= distanceBetweenPumps;
                    petrolInTruck += petrol[j];
                    distanceBetweenPumps = distance[j];
                }
            }

            if (!broken) {
                for (int j = 0; j < currentStartIndex; j++) {
                    if (petrolInTruck < distanceBetweenPumps) {
                        broken = true;
                        currentStartIndex++;
                        break;
                    } else {
                        petrolInTruck -= distanceBetweenPumps;
                        petrolInTruck += petrol[j];
                        distanceBetweenPumps = distance[j];
                    }
                }

                if (!broken && petrolInTruck >= distanceBetweenPumps) {
                    return currentStartIndex;
                } else {
                    currentStartIndex++;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        //System.out.println(balancedBrackets("[]"));

        /*LRUCache cache = new LRUCache(4);
        cache.set(94, 16);
        cache.set(93, 87);
        cache.set(63, 22);
        cache.set(60, 91);
        cache.set(41, 27);
        System.out.println(cache.get(73));
        System.out.println(cache.get(12));
        System.out.println(cache.get(68));
        cache.set(31, 83);
        System.out.println(cache.get(24));
        cache.set(30, 36);
        System.out.println(cache.get(23));
        System.out.println(cache.get(70));
        cache.set(57, 94);
        cache.set(30, 43);
        cache.set(20, 22);
        System.out.println(cache.get(38));
        System.out.println(cache.get(25));
        cache.set(14, 71);
        System.out.println(cache.get(92));
        System.out.println(cache.get(57));
        System.out.println();*/

        /*System.out.println(firstNonRepeating("abcbbac"));*/

        // 87 27 40 95 71 96 79 35 2 68 3 98 93 18 57 53 81 2 42 87 90 66 20 45 30 41
        // 87  40  71  79  2  3  93  57  81  42  90  20  30
        //  27  95  96  35  68  98  18  53  2  87  66  45  41

        int[] petrol = {87, 40, 71, 79, 2, 3, 93, 57, 81, 42, 90, 20, 30};
        int[] distance = {27, 95, 96, 35, 68, 98, 18, 53, 2, 87, 66, 45, 41};

        System.out.println(tour(petrol, distance));

    }

}
