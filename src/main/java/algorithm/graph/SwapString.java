package algorithm.graph;

import java.math.BigInteger;
import java.util.Scanner;

public class SwapString {

	public static void main(final String[] args) {
		final Scanner in = new Scanner(System.in);
		final String A = in.next();
		final String B = in.next();
		final BigInteger number1 = new BigInteger(A,2);
		final BigInteger number2 = new BigInteger(B,2);
		BigInteger number3 = new BigInteger("0",2);
		if(A.length() != B.length()){
			System.out.println(-1);
		}
		else if(number1.bitCount() != number2.bitCount()){
			System.out.println(-1);
		}
		else{
			number3 = number1.xor(number2);
			System.out.println(number3.bitCount()/2);
		}
	}

}
