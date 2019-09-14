package arrays;

public class IsPowerOfTwo {

	public static boolean checkIfPowerOfTwo(int number) {
		int i = 1;
		// in a loop go to a number that is less than d given number by multipying by 2
		while (i < number) {
			i = i * 2;
		}
		return i == number;
	}

	public static void main(String args[]) {
		int number = 8;
		System.out.println(checkIfPowerOfTwo(number));
	}

}
