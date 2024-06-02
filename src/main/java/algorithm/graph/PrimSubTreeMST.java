package algorithm.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;

public class PrimSubTreeMST {

	private static class ArrayUtils {

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public static boolean less(final Comparable[] a, final int k, final int j){
			return a[k].compareTo(a[j]) < 0;
		}
		@SuppressWarnings("rawtypes")
		public static void exch(final Comparable[] a,final int k,final int j){
			final Comparable temp = a[k];
			a[k] = a[j];
			a[j] = temp;
		}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public static boolean greater(final Comparable[] a, final int k, final int j){
			return a[k].compareTo(a[j]) > 0;
		}

	}

	private static class Bag<Item> implements Iterable<Item> {
		private int N;
		private Node<Item> first;

		private static class Node<Item> {
			private Item item;
			private Node<Item> next;
		}

		public Bag() {
			first = null;
			N = 0;
		}

		public void add(final Item item) {
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

			public void remove() {
			}

		}

	}

	private static class MinPQ<Key extends Comparable<Key>> {
		private final Key[] nodes;
		private int N;

		@SuppressWarnings("unchecked")
		public MinPQ(final int capacity){
			nodes = (Key[]) new Comparable[capacity+1];
		}

		public void insert(final Key v){
			nodes[++N]=v;
			swim(N);
		}

		private void swim(int k){
			while(k>1 && ArrayUtils.greater(nodes, k/2, k)){
				ArrayUtils.exch(nodes, k, k/2);
				k=k/2;
			}
		}

		public Key delMin(){
			final Key min= nodes[1];
			ArrayUtils.exch(nodes, 1, N--);
			sink(1);
			nodes[N+1]=null;
			return min;
		}

		public boolean isEmpty(){
			return N==0;
		}

		private void sink(int k){
			while(2*k<=N){
				int j=k*2;
				if(j<N && ArrayUtils.greater(nodes, j, j+1)){
					j++;
				}
				if(!ArrayUtils.greater(nodes, k, j)){
					break;
				}
				ArrayUtils.exch(nodes, k, j);
				k=j;
			}
		}
	}

	private static class Edge implements Comparable<Edge>{
		private final int v,w;
		private final double weight;

		public Edge(final int v,final int w,final double weight) {
			this.v=v;
			this.w=w;
			this.weight = weight;
		}

		public int either(){
			return v;
		}

		public int other(final int vertex){
			if(vertex==v) {
				return w;
			}
			else{
				return v;
			}
		}

		public int compareTo(final Edge that) {
			if(this.weight<that.weight) {
				return -1;
			}
			else if(this.weight>that.weight) {
				return 1;
			}
			else{
				return 0;
			}
		}

	}

	private static class EdgeWeightedGraph {
		private int E;
		private final int V;
		private final Bag<Edge>[] adj;
		private final Bag<Edge> edges;
		private static final String NEWLINE = System.getProperty("line.separator");

		@SuppressWarnings("unchecked")
		public EdgeWeightedGraph(final int V) {
			if (V < 0) {
				throw new IllegalArgumentException(
						"Number of vertices must be nonnegative");
			}
			this.V = V;
			this.E = 0;
			adj = new Bag[V];
			edges = new Bag<Edge>();
			for (int i = 0; i < V; i++) {
				adj[i] = new Bag<Edge>();
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


		public void addEdge(final Edge e){
			final int v = e.either();
			final int w=e.other(v);
			adj[v].add(e);
			adj[w].add(e);
			edges.add(e);
			E++;
		}

		public Iterable<Edge> edges(){
			return edges;
		}

		public Iterable<Edge> adj(final int v) {
			validateVertex(v);
			return adj[v];
		}

		@Override
		public String toString() {
			final StringBuilder s = new StringBuilder();
			s.append(V + " vertices, " + E + " edges " + NEWLINE);
			for (int v = 0; v < V; v++) {
				s.append(v + ": ");
				for (final Edge w : adj[v]) {
					s.append(w.other(v) + " ");
				}
				s.append(NEWLINE);
			}
			return s.toString();
		}
	}

	private static class LazyPrimMST {
		private final Queue<Edge> queue;
		private final boolean[] visited;
		private final MinPQ<Edge> pq;

		public LazyPrimMST(final EdgeWeightedGraph G, final int s) {
			queue = new LinkedList<Edge>();
			visited = new boolean[G.V()];
			pq = new MinPQ<Edge>(G.E());
			populatePQ(G,s);
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

	public static void main(final String[] args) {
		final Scanner in = new Scanner(System.in);
		final int N = in.nextInt();
		final int M = in.nextInt();
		final EdgeWeightedGraph G = new EdgeWeightedGraph(N);
		for(int i=0;i<M;i++){
			G.addEdge(new Edge(in.nextInt()-1,in.nextInt()-1,in.nextDouble()));
		}
		final int S = in.nextInt();
		final LazyPrimMST mst = new LazyPrimMST(G,S-1);
		double weight = 0;
		for(final Edge e : mst.edges()){
			weight += e.weight;
		}
		System.out.println((int) weight);
	}
}
