package datastructure;

public class DirectedEdge implements Comparable<DirectedEdge>{
	private final int v,w;
	private final double weight;

	public DirectedEdge(final int v,final int w,final double weight) {
		this.v=v;
		this.w=w;
		this.weight = weight;
	}

	public int from(){
		return v;
	}

	public double weight(){
		return weight;
	}

	public int to(){
		return w;
	}

	@Override
	public String toString() {
		return v + "->" + w + " " + String.format("%5.2f", weight);
	}

	public int compareTo(final DirectedEdge that) {
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
