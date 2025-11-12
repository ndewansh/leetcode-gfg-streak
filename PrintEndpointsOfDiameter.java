// Agoda: Online Interview - https://cs.stackexchange.com/questions/144454/print-all-nodes-which-are-the-endpoint-of-the-diameter-of-a-tree?utm_source=chatgpt.com

package templates.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**

Input:

10
1
2
1 2
5
1 2
2 3
3 4
4 5
5
1 2
1 3
1 4
1 5
6
1 2
2 3
2 4
3 5
3 6
7
1 2
1 3
2 4
2 5
3 6
3 7
6
1 2
2 3
3 4
4 5
4 6
7
1 2
2 3
2 4
3 5
3 6
1 7
6
1 2
2 3
3 4
4 5
4 6
8
1 2
1 3
2 4
3 5
3 6
4 7
6 8



Output:

 1
 1 2
 1 5
 2 3 4 5
 1 4 5 6
 4 5 6 7
 1 5 6
 4 5 6 7
 1 5 6
 7 8


 */

public class PrintEndpointsOfDiameter {

    static int diameter;
    static List<Pair> nodes;

    public static void main(String[] args) {
        Scanner ob = new Scanner(System.in);
        int testcases = ob.nextInt();
        List<List<Integer>> output = new ArrayList<>();
        while(testcases-- > 0) {
            int n = ob.nextInt();
            List<Integer> from = new ArrayList<>();
            List<Integer> to = new ArrayList<>();
            for(int  i = 0; i < n-1; i++) {
                from.add(ob.nextInt());
                to.add(ob.nextInt());
            }
            output.add(findEndpointsOfDiameters(n, from, to));
        }
        for(List<Integer> op: output){
            for(int val: op) {
                System.out.print(val+" ");
            }
            System.out.println();
        }
    }

    // Time Complexity : O(n)
    public static List<Integer> twoSum(List<Pair> a, int n, List<List<Integer>> graph) {
        Set<Integer> ans = new HashSet<>();
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            Set<Integer> set = map.getOrDefault(a.get(i).level, new HashSet<>());
            set.add(a.get(i).index);
            map.put(a.get(i).level, set);
        }

        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(visited[a.get(i).index]) continue;
            visited[a.get(i).index] = true;
            int otherPart = diameter - a.get(i).level;
            Set<Integer> set = map.getOrDefault(otherPart, new HashSet<>());
            for(int val: set) {
                visited[val] = true;
                if(graph.get(val).size() <= 1) {
                    ans.add(val + 1);
                }
                if(graph.get(a.get(i).index).size() <= 1) {
                    ans.add(a.get(i).index + 1);
                }
            }
        }
        return new ArrayList<>(ans);
    }


    // Time Complexity : O(n)
    public static List<Integer> findEndpointsOfDiameters(int n, List<Integer> from, List<Integer> to) {
        // Create adjacency list here.
        List<List<Integer>> graph = createUndirectedGraph(n, from, to);
        Set<Integer> set = new HashSet<>();
        diameter = 0;
        // Find the diameter
        getDiameter(graph, 0, new boolean[n]); // O(n)
        System.out.println("\nDiameter = "+ diameter);
        // Assign a level to each.
        nodes = new ArrayList<>();
        bfs(graph, n); // O(n)
        System.out.println("Nodes = "+ nodes);

        // Now sort.
        nodes.sort((x, y) -> (x.level - y.level));
        System.out.println("Sorted node = "+ nodes);
        return twoSum(nodes, n, graph); // O(n)
    }

    // Time Complexity : O(n)
    public static void bfs(List<List<Integer>> graph, int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int level = 0;
        boolean[] visited = new boolean[n];
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int node = queue.poll();
                visited[node] = true;
                nodes.add(new Pair(node, level));
                for(int adj: graph.get(node)) {
                    if(visited[adj]) continue;
                    queue.add(adj);
                }
            }
            level++;
        }
    }

    // Time Complexity : O(n)
    public static int getDiameter(List<List<Integer>> graph, int cur, boolean visited[]) {
        visited[cur] = true;
        PriorityQueue<Integer> pr = new PriorityQueue<>((x, y) -> (y - x));
        for(int adj: graph.get(cur)) {
            if(visited[adj]) continue;
            int countNodes = getDiameter(graph, adj, visited);
            pr.add(countNodes);
        }
        int toReturn = 1;
        if(pr.isEmpty()) {
            // must be a leaf node.
            return toReturn;
        }
        toReturn += pr.peek();
        if(pr.size() == 1) {
            diameter = Math.max(diameter, pr.poll());
        }
        else {
            int one = pr.poll();
            int two = pr.poll();
            diameter = Math.max(diameter, one+two);
        }
        return toReturn;
    }

    static class Pair {
        int index;
        int level;
        public Pair(int index, int level) {
            this.index = index;
            this.level = level;
        }

        @Override
        public String toString() {
            return "{"+(index + 1)+", "+level+" }";
        }
    }

    // Time Complexity : O(n)
    public static List<List<Integer>> createUndirectedGraph(int n, List<Integer> from, List<Integer> to) {
        List<List<Integer>> list = new ArrayList<>();
        for(int i =0 ; i < n; i++)
            list.add(new ArrayList<>());
        for(int i = 0; i < n-1; i++) {
            int fromNode = from.get(i) - 1;
            int toNode = to.get(i) - 1;
            list.get(fromNode).add(toNode);
            list.get(toNode).add(fromNode);
        }
        return list;
    }
}
