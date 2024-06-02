package algorithm.graph;

import datastructure.DirectedGraph;

/**
 * https://leetcode.com/problems/course-schedule/submissions/
 * Create Directed graph
 * Detect a cycle in graph
 */
public class CourseSchedule {
    boolean[] visited;
    boolean[] stacked;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        DirectedGraph dg = new DirectedGraph(numCourses);
        for(int[] x: prerequisites){
            dg.addEdgeTo(x[1], x[0]);
        }
        visited = new boolean[dg.V()];
        stacked = new boolean[dg.V()];
        for (int i=0;i<dg.V();i++){
            if(dfs(dg, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(DirectedGraph graph, int vertex){
        if(stacked[vertex]){
            return true;
        }
        if(visited[vertex]){
            return false;
        }
        visited[vertex] = true;
        stacked[vertex] = true;
        for(int neighbour: graph.adj(vertex)){
            if(dfs(graph, neighbour)) {
                return true;
            }
        }
        stacked[vertex] = false;
        return false;
    }
    public static void main(String[] args) {
        CourseSchedule c = new CourseSchedule();
        System.out.println(c.canFinish(3, new int[][]{{1,0},{0,2},{2,1}}));
    }
}
