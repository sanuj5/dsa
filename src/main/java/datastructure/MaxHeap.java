package datastructure;

public class MaxHeap<Key extends Comparable<Key>>{
    private final Key[] heap;
    private int N;
    @SuppressWarnings("unchecked")
    public MaxHeap(int capacity){
        heap = (Key[]) new Comparable[capacity+1];
    }

    private Key getMax(){
        return heap[0];
    }

    private void insert(Key element){
        heap[++N] = element;
        swim(N);
    }

    private Key deleteMax(){
        Key max = heap[1];
        exchange(0,N--);
        sink(1);
        heap[N+1] = null;
        return max;
    }

    private void swim(int k){
        while(k >1 && heap[k/2].compareTo(heap[k]) < 0){
            exchange(k/2,k);
            k=k/2;
        }
    }

    private void sink(int k){
        while(2*k<=N){
            int j = k*2;
            if(j<N && heap[j].compareTo(heap[j+1]) < 0) j++;
            if(heap[j].compareTo(heap[k]) < 0) break;
            exchange(k,j);
            k=j;
        }
    }

    private void exchange(int parent, int n) {
        Key temp = heap[parent];
        heap[parent] = heap[n];
        heap[n] = temp;
    }
}
