import java.util.*;

public class Main {

    static class Node {
        int data;
        Node left, right, nextRight;

        Node(int data) {
            this.data = data;
            left = right = nextRight = null;
        }
    }

    private static int maxLevel;

    private static void leftViewRecursive(Node root, int level, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }

        if (maxLevel < level) {
            result.add(root.data);
            maxLevel = level;
        }

        leftViewRecursive(root.left, level + 1, result);
        leftViewRecursive(root.right, level + 1, result);
    }

    public static ArrayList<Integer> leftView(Node root) {
        ArrayList<Integer> result = new ArrayList<>();

        maxLevel = Integer.MIN_VALUE;
        leftViewRecursive(root, 0, result);

        return result;
    }

    public static ArrayList<Integer> findSpiral(Node root) {
        Deque<Node> leftToRightStack = new ArrayDeque<>();
        Deque<Node> rightToLeftStack = new ArrayDeque<>();

        ArrayList<Integer> result = new ArrayList<>();
        if (root != null) {
            rightToLeftStack.add(root);
        }

        while (!leftToRightStack.isEmpty() || !rightToLeftStack.isEmpty()) {
            if (leftToRightStack.isEmpty()) {
                while (!rightToLeftStack.isEmpty()) {
                    Node node = rightToLeftStack.removeLast();
                    result.add(node.data);
                    if (node.right != null) {
                        leftToRightStack.add(node.right);
                    }
                    if (node.left != null) {
                        leftToRightStack.add(node.left);
                    }
                }
            } else {
                while (!leftToRightStack.isEmpty()) {
                    Node node = leftToRightStack.removeLast();
                    result.add(node.data);
                    if (node.left != null) {
                        rightToLeftStack.add(node.left);
                    }
                    if (node.right != null) {
                        rightToLeftStack.add(node.right);
                    }
                }
            }
        }

        return result;
    }

    public static Node lowestCommonAncestor(Node root, int n1, int n2) {
        if (n1 == root.data || n2 == root.data) {
            return root;
        }

        if (n1 < root.data && n2 < root.data) {
            return lowestCommonAncestor(root.left, n1, n2);
        } else if (n1 > root.data && n2 > root.data) {
            return lowestCommonAncestor(root.right, n1, n2);
        }

        return root;
    }

    private static void subtreeToList(Node subTree, List<Node> list) {
        if (subTree == null) {
            return;
        }

        subtreeToList(subTree.left, list);
        list.add(subTree);
        subtreeToList(subTree.right, list);
    }

    public static Node bToDLL(Node root) {
        List<Node> list = new ArrayList<>();
        subtreeToList(root.left, list);
        list.add(root);

        Node head = list.get(0);
        head.left = null;
        Node prev = head;
        Node curr;

        for (int i = 1; i < list.size(); i++) {
            curr = list.get(i);
            curr.left = prev;
            prev.right = curr;
            prev = curr;
        }

        list.clear();
        subtreeToList(root.right, list);

        for (Node node : list) {
            node.left = prev;
            prev.right = node;
            prev = node;
        }
        prev.right = null;

        return head;
    }

    boolean isIdentical(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        if (root1.data != root2.data) {
            return false;
        }

        return isIdentical(root1.left, root2.left) && isIdentical(root1.right, root2.right);
    }

    private static boolean isSymmetricRecursive(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        if (root1.data != root2.data) {
            return false;
        }

        return isSymmetricRecursive(root1.left, root2.right) && isSymmetricRecursive(root1.right, root2.left);
    }

    public static boolean isSymmetric(Node root) {
        return root == null || isSymmetricRecursive(root.left, root.right);
    }

    public static int height(Node node) {
        if (node == null) {
            return 0;
        }

        return 1 + Integer.max(height(node.left), height(node.right));
    }

    private static boolean isBalancedRecursive(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null) {
            return root2.left == null && root2.right == null;
        }

        if (root2 == null) {
            return root1.left == null && root1.right == null;
        }

        return isBalancedRecursive(root1.left, root2.right) && isBalancedRecursive(root2.left, root2.right);
    }

    public static boolean isBalanced1(Node root) {
        if (root == null) {
            return true;
        }

        int left = height(root.left);
        int right = height(root.right);

        return Math.abs(left - right) < 2 && isBalancedRecursive(root.left, root.right);
    }

    public static boolean isBalanced(Node root) {
        if (root == null) {
            return true;
        }

        int left = height(root.left);
        int right = height(root.right);

        return Math.abs(left - right) < 2 && isBalanced(root.left) && isBalanced(root.right);
    }

    public static int countLeaves(Node node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        return countLeaves(node.left) + countLeaves(node.right);
    }

    private static void fillHashMap(Node root, int hd, int level, Map<Integer, int[]> map) {
        if (root == null) {
            return;
        }

        if (map.containsKey(hd)) {
            int[] tmp = map.get(hd);
            if (tmp[1] <= level) {
                tmp[0] = root.data;
                tmp[1] = level;
                map.put(hd, tmp);
            }
        } else {
            map.put(hd, new int[]{root.data, level});
        }

        if (hd < map.get(Integer.MIN_VALUE)[0]) {
            map.put(Integer.MIN_VALUE, new int[]{hd, level});
        }

        if (hd > map.get(Integer.MAX_VALUE)[0]) {
            map.put(Integer.MAX_VALUE, new int[]{hd, level});
        }

        fillHashMap(root.left, hd - 1, level + 1, map);
        fillHashMap(root.right, hd + 1, level + 1, map);
    }

    public static ArrayList<Integer> bottomView(Node root) {
        Map<Integer, int[]> map = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();

        map.put(Integer.MIN_VALUE, new int[]{0, 0});
        map.put(Integer.MAX_VALUE, new int[]{0, 0});


        fillHashMap(root, 0, 0, map);

        int min = map.get(Integer.MIN_VALUE)[0];
        int max = map.get(Integer.MAX_VALUE)[0];

        for (int i = min; i <= max; i++) {
            if (map.containsKey(i)) {
                result.add(map.get(i)[0]);
            }
        }

        return result;
    }

    // int[0] = data, int[1] = level
    private static void fillVerticalOrderHashMap(Node root, int hd, int level, Map<Integer, ArrayList<int[]>> map) {
        if (root == null) {
            return;
        }

        if (map.containsKey(hd)) {
            ArrayList<int[]> temp = map.get(hd);
            int i;
            for (i = 0; i < temp.size(); i++) {
                if (temp.get(i)[1] > level) {
                    break;
                }
            }
            temp.add(i, new int[]{root.data, level});
            map.put(hd, temp);
        } else {
            map.put(hd, new ArrayList<>(List.of(new int[]{root.data, level})));
        }

        if (hd < map.get(Integer.MIN_VALUE).get(0)[0]) {
            map.put(Integer.MIN_VALUE, new ArrayList<>(List.of(new int[]{hd, level})));
        }

        if (hd > map.get(Integer.MAX_VALUE).get(0)[0]) {
            map.put(Integer.MAX_VALUE, new ArrayList<>(List.of(new int[]{hd, level})));
        }

        fillVerticalOrderHashMap(root.left, hd - 1, level + 1, map);
        fillVerticalOrderHashMap(root.right, hd + 1, level + 1, map);
    }

    public static ArrayList<Integer> verticalOrderSlower(Node root) {
        Map<Integer, ArrayList<int[]>> map = new HashMap<>();

        map.put(Integer.MIN_VALUE, new ArrayList<>(List.of(new int[]{0, 0})));
        map.put(Integer.MAX_VALUE, new ArrayList<>(List.of(new int[]{0, 0})));

        fillVerticalOrderHashMap(root, 0, 0, map);

        ArrayList<Integer> result = new ArrayList<>();

        int min = map.get(Integer.MIN_VALUE).get(0)[0];
        int max = map.get(Integer.MAX_VALUE).get(0)[0];

        for (int i = min; i <= max; i++) {
            if (map.containsKey(i)) {
                ArrayList<int[]> list = map.get(i);
                for (int[] arr : list) {
                    result.add(arr[0]);
                }
            }
        }

        return result;
    }

    public static ArrayList<Integer> verticalOrder(Node root) { // using breadth first search
        if (root == null) {
            return new ArrayList<>();
        }

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        Queue<Map.Entry<Node, Integer>> q = new LinkedList<>();

        int min = 0, max = 0;

        q.add(new AbstractMap.SimpleEntry<>(root, 0));

        while (!q.isEmpty()) {
            Map.Entry<Node, Integer> entry = q.poll();

            Node node = entry.getKey();
            int hd = entry.getValue();

            if (map.containsKey(hd)) {
                ArrayList<Integer> tmp = map.get(hd);
                tmp.add(node.data);
                map.put(hd, tmp);
            } else {
                map.put(hd, new ArrayList<>(List.of(node.data)));
            }

             if (node.left != null) {
                 q.add(new AbstractMap.SimpleEntry<>(node.left, hd - 1));
             }

             if (node.right != null) {
                 q.add(new AbstractMap.SimpleEntry<>(node.right, hd + 1));
             }

             if (hd > max) {
                 max = hd;
             } else if (hd < min) {
                 min = hd;
             }
        }

        ArrayList<Integer> result = new ArrayList<>();

        for (int i = min; i <= max; i++) {
            result.addAll(map.get(i));
        }

        return result;
    }

    public static int diameter(Node root) {
        if (root == null) {
            return 0;
        }

        int rootDiameter = height(root.left) + height(root.right) + 1;
        int leftDiameter = diameter(root.left);
        int rightDiameter = diameter(root.right);

        return Integer.max(Integer.max(rootDiameter, leftDiameter), rightDiameter);
    }

    public static void connect(Node root) {
        Queue<Node> q1 = new LinkedList<>();
        Queue<Node> q2 = new LinkedList<>();

        q1.add(root);
        Node currentNode;
        boolean q1in = true, q2in = false;

        while (q1in) {

            while (!q1.isEmpty()) {
                currentNode = q1.poll();
                currentNode.nextRight = q1.peek();

                if (currentNode.left != null) {
                    q2.add(currentNode.left);
                    q2in = true;
                }

                if (currentNode.right != null) {
                    q2.add(currentNode.right);
                    q2in = true;
                }
            }
            q1in = false;

            while (q2in && !q2.isEmpty()) {
                currentNode = q2.poll();
                currentNode.nextRight = q2.peek();

                if (currentNode.left != null) {
                    q1.add(currentNode.left);
                    q1in = true;
                }

                if (currentNode.right != null) {
                    q1.add(currentNode.right);
                    q1in = true;
                }
            }
            q2in = false;
        }
    }

    public static void main(String[] args) {
        Node n10 = new Node(10);
        Node n12 = new Node(12);
        Node n25 = new Node(25);
        Node n30 = new Node(30);
        Node n15 = new Node(15);
        Node n36 = new Node(36);
        Node n38 = new Node(38);
        Node n42 = new Node(42);
        Node n53 = new Node(53);
        Node n60 = new Node(60);

        n10.left = n12;
        n10.right = n15;
        n12.left = n25;
        n12.right = n30;
        n15.left = n36;
        n15.right = n38;
        n38.left = n42;
        n38.right = n53;
        n53.left = n60;

        /*Node n = bToDLL(n10);

        while (n != null) {
            System.out.print(n.data + " ");
            n = n.right;
        }*/

        /*Queue<Map.Entry<Node, Integer>> q = new LinkedList<>();
        q.add(new AbstractMap.SimpleEntry<>(new Node(3), 0));

        System.out.println(q.size());
        Map.Entry<Node, Integer> entry = q.poll();
        System.out.println(q.size());
        System.out.println(entry.getKey().data);
        System.out.println(entry.getValue());*/



    }

}
