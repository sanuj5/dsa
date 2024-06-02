package datastructure;

public class Edge implements Comparable<Edge>{
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
