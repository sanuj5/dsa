package algorithm.graph;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DijsktraSPath {
	private final double[] distTo;
	private final Edge[] edgeTo;
	private final IndexMinPQ<Double> pq;

	private static class Stack<Item extends Comparable<Item>> implements Iterable<Item>{

		private Node lastAddedNode;

		private class Node{
			Item value;
			Node next;
		}

		public void push(final Item item){
			final Node currentNode = new Node();
			currentNode.value = item;
			currentNode.next = lastAddedNode;
			lastAddedNode = currentNode;
		}

		public Item pop(){
			final Node temp = lastAddedNode;
			if(temp == null) {
				return null;
			}
			lastAddedNode = temp.next;
			return temp.value;
		}

		public Iterator<Item> iterator() {
			return new stackIterator();
		}

		private class stackIterator implements Iterator<Item>{
			private Node currentNode;

			public stackIterator() {
				currentNode = lastAddedNode;
			}
			public boolean hasNext() {
				return currentNode!=null;
			}

			public Item next() {
				final Node temp = currentNode;
				currentNode = currentNode.next;
				return temp.value;
			}

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

		public double weight(){
			return weight;
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


	private static class IndexMinPQ<Key extends Comparable<Key>> {
		private final int capacity;
		private int N;
		private final int[] pq;
		private final int[] qp;
		private final Key[] keys;

		@SuppressWarnings("unchecked")
		public IndexMinPQ(final int capacity){
			this.capacity = capacity;
			pq = new int[capacity+1];
			qp = new int[capacity+1];
			keys = (Key[]) new Comparable[capacity+1];
			for(int i=1;i<=capacity;i++){
				qp[i] = -1;
			}
		}

		public void insert(final int i,final Key key){
			if(i>capacity) {
				throw new IndexOutOfBoundsException("Can not add more.");
			}
			keys[i] = key;
			qp[i] = ++N;
			pq[N] = i;
			swim(N);
		}

		public int deleteMin(){
			final int min = pq[1];
			exch(1,N--);
			sink(1);
			keys[pq[N+1]] = null;
			pq[N+1] = -1;
			qp[min] = -1;
			return min;
		}

		public boolean isEmpty(){
			return N==0;
		}
		private boolean greater(final int i,final int j){
			return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
		}

		public boolean contains(final int i){
			return qp[i] != -1;
		}

		private void swim(int k){
			while(k>1 && greater(k/2, k)){
				exch(k, k/2);
				k=k/2;
			}
		}

		public void decreaseKey(final int i,final Key key){
			keys[i] = key;
			swim(qp[i]);
		}
		private void exch(final int i, final int j){
			final int temp = pq[i];
			pq[i] = pq[j];
			pq[j] = temp;
			qp[pq[i]] = i;
			qp[pq[j]] = j;
		}

		private void sink(int k){
			while(2*k<=N){
				int j=k*2;
				if(j<N && greater(j, j+1)){
					j++;
				}
				if(!greater(k, j)){
					break;
				}
				exch(k, j);
				k=j;
			}
		}
	}

	public DijsktraSPath(final EdgeWeightedGraph G, final int s){
		pq = new IndexMinPQ<Double>(G.V());
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		for(int v=0;v<G.V();v++){
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0;
		pq.insert(s, 0.0);
		while(!pq.isEmpty()){
			final int v = pq.deleteMin();
			for(final Edge e: G.adj(v)){
				relax(e,v);
			}
		}
	}

	private void relax(final Edge e,final int v){
		final int w = e.other(v);
		System.out.println("Relaxed:"+v+" Other:"+w+" distTo[v]="+distTo[v]+" distTo[w]="+distTo[w]+" Weight:"+e.weight());
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

	public Iterable<Edge> pathTo(final int v){
		if(!hasPathTo(v)) {
			return null;
		}
		final Stack<Edge> path = new Stack<Edge>();
		for(Edge e = edgeTo[v]; e!=null; e=edgeTo[e.other(v)]){
			path.push(e);
		}
		return path;
	}

	public static void main(final String[] arg){
		final Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		while(T-->0){
			final int N = in.nextInt();
			int M = in.nextInt();
			final EdgeWeightedGraph G= new EdgeWeightedGraph(N+1);
			while(M-->0){
				G.addEdge(new Edge(in.nextInt(), in.nextInt(), in.nextDouble()));;
			}
			final int s= in.nextInt();
			final DijsktraSPath dsp = new DijsktraSPath(G, s);
			for(int i=1;i<G.V();i++){
				if(s!=i){
					if(dsp.hasPathTo(i)) {
						System.out.print((int)dsp.distTo[i]+" ");
					} else {
						System.out.print("-1 ");
					}
				}
			}
			System.out.println("");
		}
	}
}
