package algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;
import datastructure.Edge;
import datastructure.EdgeWeightedGraph;
import datastructure.MinPQ;

public class KruskalMST {
	private final Queue<Edge> mst = new LinkedList<Edge>();

	public KruskalMST(final EdgeWeightedGraph G) {
		final MinPQ<Edge> pq = new MinPQ<Edge>(G.E());
		for(final Edge e:G.edges()){
			pq.insert(e);
		}
		final UnionFind uf = new UnionFind(G.V());
		while(!pq.isEmpty() && mst.size() < G.V()-1){
			final Edge e = pq.delMin();
			final int v = e.either();
			final int w = e.other(v);
			if(!uf.connected(v, w)){
				mst.add(e);
				uf.connected(w, v);
			}
		}
	}

	public Iterable<Edge> edges(){
		return mst;
	}
}
