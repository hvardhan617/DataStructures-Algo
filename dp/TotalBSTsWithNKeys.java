package dp;

//https://www.youtube.com/watch?v=YDf982Lb84o&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=34
//catalan number
public class TotalBSTsWithNKeys {

	public int countTrees(int n) {
		int T[] = new int[n + 1];
		T[0] = 1;
		T[1] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				T[i] += T[j] * T[i - j - 1];
			}
		}
		return T[n];
	}

	public static void main(String args[]) {
		TotalBSTsWithNKeys cnt = new TotalBSTsWithNKeys();
		System.out.println(cnt.countTrees(3));
		System.out.println(cnt.countTrees(4));
	}

}
