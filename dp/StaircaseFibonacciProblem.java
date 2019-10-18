package dp;

//https://www.youtube.com/watch?v=CFQk7OQO_xM&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=31
//given n stairs,find no of ways u can reach nth stair with constrain u can go 1/2 steps at a time
//similar to total bsts wd n keys
//ways to reach 4 = ways bto reach step 3 + 2 
public class StaircaseFibonacciProblem {

	/**
	 * DP version where we do not recalculate values but just keep last 2 calculate
	 * values
	 */
	public int fibonacciSeries(int n) {
		int n1 = 0, n2 = 1;
		int sum;

		if (n == n1 || n == n2) {
			return n;
		}

		for (int i = 2; i <= n; i++) {
			sum = n1 + n2;
			n1 = n2;
			n2 = sum;
		}
		return n2;
	}

	/**
	 * Recursive and slow version. Recalculates same value over and over again.
	 * Chokes for n greater than 60
	 */
	public int fibonacciSeriesRecursive(int n) {
		if (n == 1 || n == 0) {
			return n;
		}
		return fibonacciSeriesRecursive(n - 1) + fibonacciSeriesRecursive(n - 2);
	}

	public static void main(String args[]) {
		StaircaseFibonacciProblem fs = new StaircaseFibonacciProblem();
		System.out.println(fs.fibonacciSeries(15));
		System.out.println(fs.fibonacciSeriesRecursive(15));
	}
}
