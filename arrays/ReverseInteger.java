package arrays;

public class ReverseInteger {

	public static void main(String args[]) {
		int a = -1007432;

		System.out.println(reverseInteger(a));
	}

	private static int reverseInteger(int a) {
		int result = 0;
		boolean neg = false;
		if (a < 0) {
			neg = true;
			a = a * -1;
		}

		while (a > 0) {
			int lastDigit = a % 10;
			a = a / 10;
			result = lastDigit + (result * 10);
		}

		if (neg) {
			result = result * -1;
		}

		return result;
	}

}
