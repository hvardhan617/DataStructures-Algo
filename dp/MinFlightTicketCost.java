package dp;

//https://www.techiedelight.com/calculate-minimum-cost-to-reach-destination-city-from-source-city/
public class MinFlightTicketCost {

	// N x N matrix
	private static int N;

	// DP function to calculate the minimum cost to reach the destination city n
	// from the source city 0
	public static int findMinCost(int[][] cost) {
		// lookup[i] stores the minimum cost to reach city i from city 0
		int[] lookup = new int[N];

		// Initialize lookup[] with the direct ticket price from the source city
		for (int i = 0; i < N; i++) {
			lookup[i] = cost[0][i];
		}

		// fill lookup[] in bottom-up manner
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// min of direct cost and cost to reach via j
				lookup[i] = Math.min(lookup[i], lookup[j] + cost[j][i]);
			}
		}

		// return the minimum cost to reach city N-1 from city 0
		return lookup[N - 1];
	}

	public static void main(String[] args) {
		int[][] cost = { { 0, 25, 20, 10, 105 }, { 20, 0, 15, 80, 80 }, { 30, 15, 0, 70, 90 }, { 10, 10, 50, 0, 100 },
				{ 40, 50, 5, 10, 0 } };

		N = cost.length;
		System.out.print("The minimum cost is " + findMinCost(cost) + "$");
	}
}
