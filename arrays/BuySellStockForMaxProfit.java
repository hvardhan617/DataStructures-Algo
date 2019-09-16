package arrays;

import java.util.ArrayList;

//https://www.geeksforgeeks.org/stock-buy-sell/
//https://www.geeksforgeeks.org/maximum-difference-between-two-elements/

//Solution structure 
class Interval {
	int buy, sell;
}

//can use maxdiffbetween2 elements in case only one transaction is allowed
//incase of multiple transactions, use pair of localMax & localMin
public class BuySellStockForMaxProfit {

	public static void main(String args[]) {

//		int stockPrices[] = { 7, 6, 5, 4, 3 };
		// int stockPrices[] = { 7, 1, 5, 3, 6, 4 };
		int stockPrices[] = { 100, 180, 260, 310, 40, 535, 695 };
		System.out.println(maxProfit(stockPrices));
	}

	// buy only when stock price is least,hence we need a min variable & while
	// selling compare existing profit
	private static int buySellStock(int stockPrices[]) {
		int maxProfit = 0;
		int buyPrice = Integer.MAX_VALUE;

		for (int i = 0; i < stockPrices.length; i++) {
			if (stockPrices[i] < buyPrice) {
				buyPrice = stockPrices[i];
			} else {
				maxProfit = Math.max(maxProfit, stockPrices[i] - buyPrice);
			}
		}
		return maxProfit;
	}

	// In this method, instead of taking difference of the picked element with every
	// other element, we take the difference with the minimum element found so far.
	// So we need to keep track of 2 things:
	// 1) Maximum difference found so far (max_diff).
	// 2) Minimum number visited so far (min_element).

	int maxDiff(int arr[], int arr_size) {

		int max_diff = arr[1] - arr[0];
		int min_element = arr[0];
		int i;
		for (i = 1; i < arr_size; i++) {

			// update max_diff in case if diff is greater than curr max_diff
			if (arr[i] - min_element > max_diff)
				max_diff = arr[i] - min_element;

			// update min_element if a[i] is smaller than current min_element
			if (arr[i] < min_element)
				min_element = arr[i];
		}
		return max_diff;
	}

	// Function to find maximum profit that can be earned by buying and
	// selling shares any number of times
	// https://www.techiedelight.com/maximum-profit-earned-buying-and-selling-shares/
	public static int maxProfit(int[] price) {
		// store maximum profit gained
		int profit = 0;

		// track minimum price day
		int j = 0;

		// start from second element
		for (int i = 1; i < price.length; i++) {
			// update local minimum if decreasing sequence is found
			if (price[i - 1] > price[i]) {
				j = i;
			}

			// sell shares if current element is peak
			// i.e. (previous <= current > next)
			if (price[i - 1] <= price[i] && (i + 1 == price.length || price[i] > price[i + 1])) {
				profit += (price[i] - price[j]);
				System.out.printf("Buy on day %d and sell on day %d\n", j + 1, i + 1);
			}
		}

		return profit;
	}

}
