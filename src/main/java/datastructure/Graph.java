package datastructure;


public class Graph {
	private int E;
	private final int V;
	private Bag<Integer>[] adj;
	private static final String NEWLINE = System.getProperty("line.separator");

	@SuppressWarnings("unchecked")
	public Graph(int V) {
		if (V < 0)
			throw new IllegalArgumentException(
					"Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		adj = new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<Integer>();
		}
	}

	private void validateVertex(int v) {
		if (v < 0 || v >= V)
			throw new IndexOutOfBoundsException("vertex " + v
					+ " is not between 0 and " + (V - 1));
	}

	public int degree(int v) {
		validateVertex(v);
		return adj[v].size();
	}

	public int E() {
		return E;
	}

	public int V() {
		return V;
	}

	public void addEdgeTo(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		adj[w].add(v);
		adj[v].add(w);
		E++;
	}

	public Iterable<Integer> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj[v]) {
				s.append(w + " ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}
}
