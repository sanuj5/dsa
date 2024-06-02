package algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;
import datastructure.Edge;
import datastructure.EdgeWeightedGraph;
import datastructure.MinPQ;

public class LazyPrimMST {
	private final Queue<Edge> queue;
	private final boolean[] visited;
	private final MinPQ<Edge> pq;

	public LazyPrimMST(final EdgeWeightedGraph G) {
		queue = new LinkedList<Edge>();
		visited = new boolean[G.V()];
		pq = new MinPQ<Edge>(G.E());
		populatePQ(G,0);
		while(!pq.isEmpty()){
			final Edge e = pq.delMin();
			final int v = e.either();
			final int w = e.other(v);
			if(visited[v] && visited[w]) {
				continue;
			}
			queue.add(e);
			if(!visited[v]) {
				populatePQ(G, v);
			}
			if(!visited[w]) {
				populatePQ(G, w);
			}
		}
	}
	private void populatePQ(final EdgeWeightedGraph G,final int v){
		visited[v] =true;
		for(final Edge e : G.adj(v)){
			if(!visited[e.other(v)]){
				pq.insert(e);
			}
		}
	}
	public Iterable<Edge> edges(){
		return queue;
	}
}
