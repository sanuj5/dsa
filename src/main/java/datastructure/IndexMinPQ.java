package datastructure;



public class IndexMinPQ<Key extends Comparable<Key>> {
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
