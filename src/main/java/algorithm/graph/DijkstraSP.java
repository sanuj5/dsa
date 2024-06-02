package algorithm.graph;

import java.util.Scanner;
import datastructure.DirectedEdge;
import datastructure.EdgeWeightedDiGraph;
import datastructure.Stack;
import datastructure.IndexMinPQ;

public class DijkstraSP {
	private final double[] distTo;
	private final DirectedEdge[] edgeTo;
	private final IndexMinPQ<Double> pq;

	public DijkstraSP(final EdgeWeightedDiGraph G, final int s){
		pq = new IndexMinPQ<Double>(G.V());
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		for(int v=0;v<G.V();v++){
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0;
		pq.insert(s, 0.0);
		while(!pq.isEmpty()){
			final int v = pq.deleteMin();
			System.out.println("Del min"+v);
			for(final DirectedEdge e: G.adj(v)){
				relax(e);
			}
		}
	}

	private void relax(final DirectedEdge e){
		final int v = e.from();final int w = e.to();
		if(distTo[w] > distTo[v] + e.weight()){
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
			if(pq.contains(w)){
				pq.decreaseKey(w, distTo[w]);
			}
			else{
				pq.insert(w, distTo[w]);
			}
		}
	}

	public double distTo(final int v){
		return distTo[v];
	}

	public boolean hasPathTo(final int v){
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	public Iterable<DirectedEdge> pathTo(final int v){
		if(!hasPathTo(v)) {
			return null;
		}
		final Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for(DirectedEdge e = edgeTo[v]; e!=null; e=edgeTo[e.from()]){
			path.push(e);
		}
		return path;
	}

	public static void main(final String[] arg){
		final Scanner in = new Scanner(System.in);
		final int N = in.nextInt();
		int M = in.nextInt();
		final EdgeWeightedDiGraph G= new EdgeWeightedDiGraph(N);
		while(M-->0){
			G.addEdge(new DirectedEdge(in.nextInt(), in.nextInt(), in.nextDouble()));;
		}
		final DijkstraSP dsp = new DijkstraSP(G, 0);
		for(int i=0;i<G.V();i++){
			System.out.print(0+" to "+i+":"+dsp.distTo[i]+" :::");
			for(final DirectedEdge e : dsp.pathTo(i)){
				System.out.print(e+" ");
			}
			System.out.println("");
		}

	}
}
