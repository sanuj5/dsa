package algorithm.sort;

public class MergeSort
{

	private static void sort( Comparable[ ] a , Comparable[ ] aux , int lo ,
			int hi )
	{
		if ( hi <= lo )
			return;
		int mid = lo + ( hi - lo ) / 2;
		sort( a , aux , lo , mid );
		sort( a , aux , mid + 1 , hi );
		merge( a , aux , lo , mid , hi );
	}

	private static void exchange( Comparable[ ] a , int p , int q )
	{
		Comparable temp = a[ p ];
		a[ p ] = a[ q ];
		a[ q ] = temp;
	}

	private static boolean less( Comparable p , Comparable q )
	{
		return p.compareTo( q ) < 0;
	}

	public static void main( String arg[] )
	{
		String[ ] list = { "sanuj" , "pathik" , "sanujs" , "w" , "z" , "b" , "a" ,
				"c" };
		sort( list );
		for ( String string : list )
		{
			System.out.println( string );
		}
	}
	
	public static void sort( Comparable[] a )
	{
		sort(a , new Comparable[a.length] , 0 , a.length-1);
	}

	private static void merge( Comparable[ ] a , Comparable[ ] aux , int lo ,
			int mid , int hi )
	{
		for ( int k = lo ; k <= hi ; k++ )
		{
			aux[ k ] = a[ k ];
		}
		int i = lo;
		int j = mid + 1;
		for ( int k = lo ; k <= hi ; k++ )
		{
			if ( i > mid )
				a[ k ] = aux[ j++ ];
			else if ( j > hi )
				a[ k ] = aux[ i++ ];
			else if ( less( aux[ j ] , aux[ i ] ) )
				a[ k ] = aux[ j++ ];
			else
				a[ k ] = aux[ i++ ];
		}

	}

}
