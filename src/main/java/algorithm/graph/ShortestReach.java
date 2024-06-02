package algorithm.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;

public class ShortestReach {

	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}
	static class Bag<Item> implements Iterable<Item> {
		private int N;
		private Node<Item> first;


		public Bag() {
			first = null;
			N = 0;
		}

		public void addItem(final Item item) {
			final Node<Item> oldFirst = first;
			first = new Node<Item>();
			first.item = item;
			first.next = oldFirst;
			N++;
		}

		public boolean isEmpty() {
			return first == null;
		}

		public int size() {
			return N;
		}

		public Iterator<Item> iterator() {
			return new ListIterator<Item>(first);
		}

		private class ListIterator<T> implements Iterator<T> {
			private Node<T> current;

			public ListIterator(final Node<T> first) {
				current = first;
			}

			public boolean hasNext() {
				return current != null;
			}

			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				final T item = current.item;
				current = current.next;
				return item;
			}
		}
	}

	static class Graph {
		private int E;
		private final int V;
		private final Bag<Integer>[] adj;
		private final String NEWLINE = System.getProperty("line.separator");

		@SuppressWarnings("unchecked")
		public Graph(final int V) {
			if (V < 0) {
				throw new IllegalArgumentException(
						"Number of vertices must be nonnegative");
			}
			this.V = V;
			this.E = 0;
			adj = new Bag[V];
			for (int i = 0; i < V; i++) {
				adj[i] = new Bag<Integer>();
			}
		}

		private void validateVertex(final int v) {
			if (v < 0 || v >= V) {
				throw new IndexOutOfBoundsException("vertex " + v
						+ " is not between 0 and " + (V - 1));
			}
		}

		public int degree(final int v) {
			validateVertex(v);
			return adj[v].size();
		}

		public int E() {
			return E;
		}

		public int V() {
			return V;
		}

		public void addEdgeTo(final int v, final int w) {
			validateVertex(v);
			validateVertex(w);
			adj[v].addItem(w);
			adj[w].addItem(v);
			E++;
		}

		public Iterable<Integer> adj(final int v) {
			validateVertex(v);
			return adj[v];
		}

		@Override
		public String toString() {
			final StringBuilder s = new StringBuilder();
			s.append(V + " vertices, " + E + " edges " + NEWLINE);
			for (int v = 0; v < V; v++) {
				s.append(v + ": ");
				for (final int w : adj[v]) {
					s.append(w + " ");
				}
				s.append(NEWLINE);
			}
			return s.toString();
		}
	}

	static class BreadthFirstSearch {
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

	public static void main(final String[] args) {
		final Scanner in = new Scanner(System.in);
		final int T = in.nextInt();
		int N,M,S;
		StringBuilder sb;
		for(int i=0;i<T;i++){
			sb = new StringBuilder();
			N = in.nextInt();
			M = in.nextInt();
			final Graph G = new Graph(N);
			for(int j=0;j<M;j++){
				G.addEdgeTo(in.nextInt()-1, in.nextInt()-1);
			}
			S = in.nextInt()-1;
			final BreadthFirstSearch bfs = new BreadthFirstSearch(G, S);
			for(int k=0;k<N;k++){
				if(k!=S){
					if(bfs.hasPathTo(k)){
						sb.append(bfs.distTo(k)*6);
					}
					else{
						sb.append(-1);
					}
					if(k!=N){
						sb.append(" ");
					}
				}
			}
			System.out.println(sb.toString());
		}
	}
}
