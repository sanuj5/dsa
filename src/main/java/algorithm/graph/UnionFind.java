package algorithm.graph;

import java.util.Scanner;

public class UnionFind {
	private final int[] parent;
	private final int[] rank;
	private int count;

	public UnionFind(final int N) {
		parent = new int[N];
		rank = new int[N];
		count = N;
		for(int i=0;i<N;i++){
			parent[i] = i;
			rank[i]=0;
		}
	}

	public int count() {
		return count;
	}

	public void union(final int i,final int j){
		final int rootI = root(i);
		final int rootJ = root(j);
		if (rootI == rootJ) {
			return;
		}
		if (rank[rootI] < rank[rootJ]) {
			parent[rootI] = rootJ;
		}
		else  if(rank[rootI] > rank[rootJ]){
			parent[rootJ] = rootI;
		}
		else{
			parent[rootJ] = rootI;
			rank[rootI]++;
		}
		count--;
	}

	private int root(int x){
		while(x!=parent[x]){
			parent[x] = parent[parent[x]];
			x=parent[x];
		}
		return x;
	}

	public boolean connected(final int i,final int j){
		return root(i) == root(j);
	}

	public static void main(final String[] args) {
		final Scanner in = new Scanner(System.in);
		final UnionFind uf = new UnionFind(in.nextInt());
		while (in.hasNextInt()) {
			final int i = in.nextInt();
			final int j = in.nextInt();
			if (uf.connected(i, j)) {
				continue;
			}
			uf.union(i, j);
			System.out.println(i + " " + j);
		}
		System.out.println(uf.count() + " components");
		in.close();
	}

}
