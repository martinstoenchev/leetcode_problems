import java.util.*;

public class GraphsMain {

    private static void dfsRecursive(int V, int ver, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> result) {
        if (result.size() == V || result.contains(ver)) {
            return;
        }
        result.add(ver);

        for (int i = 0; i < adj.get(ver).size(); i++) {
            if (!result.contains(adj.get(ver).get(i))) {
                dfsRecursive(V, adj.get(ver).get(i), adj, result);
            }
        }
    }

    public static ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            dfsRecursive(V, i, adj, result);
        }

        return result;
    }

    public static ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();
        Deque<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        q.add(0);
        visited.add(0);
        while (!q.isEmpty()) {
            Integer curr = q.pollFirst();
            for (int i = 0; i < adj.get(curr).size(); i++) {
                if (!visited.contains(adj.get(curr).get(i))) {
                    q.addLast(adj.get(curr).get(i));
                    visited.add(adj.get(curr).get(i));
                }
            }
            result.add(curr);
        }

        return result;
    }

    private static int isCycle(int ver, int k, ArrayList<ArrayList<Integer>> adj, int[] sigma, int comeFrom) {
        sigma[ver] = 1;

        for (int i = 0; i < adj.get(ver).size(); i++) {
            if (adj.get(ver).get(i) != comeFrom) {
                switch (sigma[adj.get(ver).get(i)]) {
                    case 1:
                        return -k;
                    case 0: {
                        k = isCycle(adj.get(ver).get(i), k, adj, sigma, ver);
                        if (k < 0) {
                            return k;
                        }
                    }
                }
            }
        }

        sigma[ver] = 2;
        return k - 1;
    }

    public static boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] sigma = new int[V]; // 0 - unvisited, 1 - open, 2 - closed

        for (int i = 0; i < V; i++) {
            sigma[i] = 0;
        }

        int k = V;

        for (int i = 0; i < V; i++) {
            if (sigma[i] != 0) {
                continue;
            }

            k = isCycle(i, k, adj, sigma, -1);

            if (k < 0) {
                return true;
            }
        }

        return false;
    }

    private static int isCyclic(int ver, int k, ArrayList<ArrayList<Integer>> adj, int[] sigma) {
        sigma[ver] = 1;

        for (int i = 0; i < adj.get(ver).size(); i++) {
            switch (sigma[adj.get(ver).get(i)]) {
                case 1:
                    return -k;
                case 0: {
                    k = isCyclic(adj.get(ver).get(i), k, adj, sigma);
                    if (k < 0) {
                        return k;
                    }
                }
            }
        }

        sigma[ver] = 2;
        return k - 1;
    }

    public static boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] sigma = new int[V]; // 0 - unvisited, 1 - open, 2 - closed

        int k = V;

        for (int i = 0; i < V; i++) {
            if (sigma[i] != 0) {
                continue;
            }

            k = isCyclic(i, k, adj, sigma);

            if (k < 0) {
                return true;
            }
        }

        return false;
    }

    private static int topoSort(int ver, int k, ArrayList<ArrayList<Integer>> adj, int[] topo, int[] sigma) {
        sigma[ver] = 1;

        for (int i = 0; i < adj.get(ver).size(); i++) {
            if (sigma[adj.get(ver).get(i)] == 0) {
                k = topoSort(adj.get(ver).get(i), k, adj, topo, sigma);
            }
        }

        sigma[ver] = 2;
        topo[k] = ver;
        return k - 1;
    }

    public static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] sigma = new int[V]; // 0 - unvisited, 1 - open, 2 - closed
        int[] topo = new int[V];

        int k = V - 1;

        for (int i = 0; i < V; i++) {
            if (sigma[i] != 0) {
                continue;
            }

            k = topoSort(i, k, adj, topo, sigma);
        }

        return topo;
    }

    private static boolean hasCycleDirectedGraph(int ver, ArrayList<ArrayList<Integer>> adj, int[] sigma) {
        sigma[ver] = 1;

        for (int i = 0; i < adj.get(ver).size(); i++) {
            switch (sigma[adj.get(ver).get(i)]) {
                case 1 : return true;
                case 0 : {
                    if (hasCycleDirectedGraph(adj.get(ver).get(i), adj, sigma)) {
                        return true;
                    }
                }
            }
        }

        sigma[ver] = 2;
        return false;
    }

    public static boolean hasCycleDirectedGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] sigma = new int[V]; // 0 - unvisited, 1 - open, 2 - closed

        for (int i = 0; i < V; i++) {
            if (sigma[i] != 0) {
                continue;
            }

            if (hasCycleDirectedGraph(i, adj, sigma)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Deque<Integer> q = new LinkedList<>();

        q.add(1);
        q.add(2);
        System.out.println(q.pollFirst());
        System.out.println(q);
    }

}
