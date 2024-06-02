package datastructure;

public class ArrayUtils {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean less(final Comparable[] a, final int k, final int j){
		return a[k].compareTo(a[j]) < 0;
	}
	@SuppressWarnings("rawtypes")
	public static void exch(final Comparable[] a,final int k,final int j){
		final Comparable temp = a[k];
		a[k] = a[j];
		a[j] = temp;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean greater(final Comparable[] a, final int k, final int j){
		return a[k].compareTo(a[j]) > 0;
	}

}
