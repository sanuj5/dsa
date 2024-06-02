package algorithm.graph;

import datastructure.Graph;

import java.util.LinkedList;
import java.util.Queue;


public class BreadthFirstSearch {
	private final boolean[] marked;
	private final int[] edgeTo;
	private final int[] distTo;

	public BreadthFirstSearch(final Graph G, final int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		distTo = new int[G.V()];
		bfs(G,s);
	}
	private void bfs(final Graph G, final int s){
		final Queue<Integer> list = new LinkedList<Integer>();
		list.add(s);
		distTo[s] = 0;
		marked[s] = true;
		while(!list.isEmpty()){
			final int node = list.poll();
			for(final int x: G.adj(node)){
				if(!marked[x]) {
					marked[x] = true;
					edgeTo[x] = node;
					distTo[x] = distTo[node] + 1;
					list.offer(x);
				}
			}
		}
	}

	public boolean hasPathTo(final int v) {
		return marked[v];
	}

	public int distTo(final int v){
		return distTo[v];
	}

}
