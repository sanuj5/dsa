package algorithm.graph;

import datastructure.Graph;

public class ConnectedComponents {
	private final boolean[] marked;
	private final int[] id;
	private int count;

	public ConnectedComponents(final Graph G){
		marked = new boolean[G.V()];
		id = new int[G.V()];
		for(int i=0;i<G.V();i++){
			if(!marked[i]){
				dfs(G,i);
				count++;
			}
		}
	}

	private void dfs(final Graph G, final int v) {
		marked[v] = true;
		id[v] = count;
		for (final int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
			}
		}
	}

	public int count(){
		return count;
	}

	public int id(final int v){
		return id[v];
	}
}
