package strings;

//swapping characters with 2 pointers at both ends
public class ReverseString {

	public static String reverse(String s) {
		char c[] = s.toCharArray();

		int start = 0;
		int end = s.length() - 1;

		while (start < end) {
			char temp = c[start];
			c[start] = c[end];
			c[end] = temp;
			start = start + 1;
			end = end - 1;
		}

		return new String(c);
	}

	public static void main(String args[]) {
		String s = "hello";
		System.out.println(reverse(s));

	}

}
