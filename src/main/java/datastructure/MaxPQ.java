package datastructure;

public class MaxPQ<Key extends Comparable<Key>>{
	private int N;
	private final Key[] nodes;
	@SuppressWarnings("unchecked")
	public MaxPQ(final int capacity){
		nodes = (Key[]) new Comparable[capacity+1];
	}
	public void insert(final Key v){
		nodes[++N] = v;
		swim(N);
	}
	public Key delMax(){
		final Key max= nodes[1];
		ArrayUtils.exch(nodes,1,N--);
		sink(1);
		nodes[N+1] = null;
		return max;
	}
	public boolean isEmpty(){
		return N==0;
	}
	private void swim(int k){
		while(k>1 && ArrayUtils.less(nodes,k/2,k)){
			ArrayUtils.exch(nodes,k,k/2);
			k=k/2;
		}
	}
	private void sink(int k){
		while(2*k<=N){
			int j=k*2;
			if(j<N	 && ArrayUtils.less(nodes,j,j+1)) {
				j++;
			}
			if(!ArrayUtils.less(nodes,k,j)) {
				break;
			}
			ArrayUtils.exch(nodes,k,j);
			k=j;
		}

	}
}
