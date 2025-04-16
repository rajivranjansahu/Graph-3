// TC: O(n log n)
// SC: O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        // List to hold all edges (both well edges and pipe edges)
        List<Edge> edges = new ArrayList<>();
        
        // Virtual water source is node 0.
        // For each house (1-indexed in our union-find), add an edge from node 0 with cost wells[i].
        for (int i = 0; i < n; i++) {
            edges.add(new Edge(0, i + 1, wells[i]));
        }
        
        // Add all the pipe edges.
        for (int[] pipe : pipes) {
            // Each pipe is represented by [house1, house2, cost].
            // Houses are numbered from 1 to n.
            int u = pipe[0], v = pipe[1], cost = pipe[2];
            edges.add(new Edge(u, v, cost));
        }
        
        // Sort edges by cost (ascending order).
        Collections.sort(edges, (a, b) -> a.cost - b.cost);
        
        // Initialize union-find for n + 1 nodes (including virtual node 0).
        UnionFind uf = new UnionFind(n + 1);
        int totalCost = 0;
        
        // Process each edge in order; if it connects two components, add its cost.
        for (Edge edge : edges) {
            if (uf.union(edge.u, edge.v)) {
                totalCost += edge.cost;
            }
        }
        
        return totalCost;
    }
    
    // Edge class to store an edge in the graph.
    class Edge {
        int u, v, cost;
        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
    }
    
    // Union-Find (Disjoint Set) data structure.
    class UnionFind {
        int[] parent;
        
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return false;
            parent[rootX] = rootY;
            return true;
        }
    }
}
