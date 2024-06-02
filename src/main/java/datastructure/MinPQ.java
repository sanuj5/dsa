package datastructure;


public class MinPQ<Key extends Comparable<Key>> {
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
