package algorithm.sort;

import datastructure.DirectedGraph;

import java.util.Stack;

public class TopologicalSort {
    Stack<Integer> stack = new Stack<>();
    boolean[] visited;
    public int[] sort(DirectedGraph graph){
        visited = new boolean[graph.V()];
        for (int i=0;i<graph.V();i++){
            if(!visited[i]) {
                dfs(graph, i);
            }
        }
        int[] result = new int[graph.V()];
        int i = 0;
        while(!stack.empty()){
            result[i++] = stack.pop();
        }
        return result;
    }

    private void dfs(DirectedGraph graph, int vertex){
        visited[vertex] = true;
        for(int neighbour: graph.adj(vertex)){
            if(!visited[neighbour]) {
                dfs(graph, neighbour);
            }
        }
        stack.push(vertex);
    }

    public static void main(String[] args) {
        TopologicalSort sort = new TopologicalSort();
        DirectedGraph g = new DirectedGraph(6);
        g.addEdgeTo(0,3);
        g.addEdgeTo(0,2);
        g.addEdgeTo(2,3);
        g.addEdgeTo(2,1);
        g.addEdgeTo(3,1);
        g.addEdgeTo(1,4);
        g.addEdgeTo(5,4);
        g.addEdgeTo(5,1);
        int[] result = sort.sort(g);
        for (int i: result){
            System.out.print(i);
        }
    }
}

