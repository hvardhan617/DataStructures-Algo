package arrays;

//https://www.youtube.com/watch?v=hySR1exD5PE&list=PLqM7alHXFySGqCvcwfqqMrteqWukz9ZoE&index=17
//https://www.geeksforgeeks.org/find-the-number-occurring-odd-number-of-times/
//xor & hm & binary search
public class OddOccurance {

	int getOddOccurrence(int ar[], int ar_size) {
		int i;
		int res = 0;
		for (i = 0; i < ar_size; i++) {
			res = res ^ ar[i];
		}
		return res;
	}

	public static void main(String[] args) {
		OddOccurance occur = new OddOccurance();
		int ar[] = new int[] { 2, 3, 5, 4, 5, 2, 4, 3, 5, 2, 4, 4, 2 };
		int n = ar.length;
		System.out.println(occur.getOddOccurrence(ar, n));
	}
}
