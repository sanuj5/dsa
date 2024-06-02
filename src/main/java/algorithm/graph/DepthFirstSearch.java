package algorithm.graph;

import java.util.Stack;
import datastructure.Graph;

public class DepthFirstSearch {
	private final boolean[] marked;
	private final int[] edgeTo;
	private int count;
	private final int s;

	public DepthFirstSearch(final Graph G, final int s) {
		this.s = s;
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		dfs(G, s);
	}

	public int count() {
		return count;
	}

	private void dfs(final Graph G, final int v) {
		count++;
		marked[v] = true;
		for (final int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
				edgeTo[w] = v;
			}
		}
	}

	public boolean hasPathTo(final int v) {
		return marked[v];
	}

	Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v)) {
			return null;
		}
		final Stack<Integer> path = new Stack<Integer>();
		path.push(v);
		while (v != s) {
			path.push(v = edgeTo[v]);
		}
		return path;
	}

	public static void main(final String[] args) {
		final Graph G = new Graph(10);
		final int s = 0;
		G.addEdgeTo(0, 3);
		G.addEdgeTo(3, 6);
		G.addEdgeTo(3, 4);
		G.addEdgeTo(4, 6);
		G.addEdgeTo(4, 5);
		G.addEdgeTo(2, 6);
		G.addEdgeTo(1, 6);
		G.addEdgeTo(6, 9);
		G.addEdgeTo(9, 8);

		final DepthFirstSearch dfs = new DepthFirstSearch(G, s);

		for (int v = 0; v < G.V(); v++) {
			if (dfs.hasPathTo(v)) {
				System.out.printf("%d to %d:  ", s, v);
				for (final int x : dfs.pathTo(v)) {
					if (x == v) {
						System.out.print(x);
					} else {
						System.out.print("-" + x);
					}
				}
				System.out.println();
			} else {
				System.out.printf("%d to %d:  not connected\n", s, v);
			}

		}
	}
}
