package searching;

public class DivideNumbers {

	// https://www.techiedelight.com/division-two-numbers-using-binary-search-algorithm/
	// Function to perform division of two numbers using
	// Binary Search Algorithm
	public static double divide(double x, double y) {
		// handle divisibility by 0
		if (y == 0) {
			return Double.MAX_VALUE; // return infinity
		}

		// set range for result [left, right]
		// right is set to infinity to handle the case
		// when y < 1, x < result < Double.MAX_VALUE
		double left = 0.0, right = Double.MAX_VALUE;

		// set accuracy of the result
		double precision = 0.001;

		// store sign of the result
		int sign = 1;
		if (x * y < 0) {
			sign = -1;
		}

		// make both input numbers positive
		x = Math.abs(x);
		y = Math.abs(y);

		while (true) {
			// calculate mid
			double mid = left + ((right - left) / 2);

			// if y*mid is almost equal to x, return mid
			if (Math.abs(y * mid - x) <= precision) {
				return mid * sign;
			}

			// if y*mid is less than x, update left to mid
			if (y * mid < x) {
				left = mid;
			} else {
				// if y*mid is more than x, update right to mid
				right = mid;
			}
		}
	}
}
