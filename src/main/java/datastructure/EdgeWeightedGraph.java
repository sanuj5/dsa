package datastructure;


public class EdgeWeightedGraph {
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
