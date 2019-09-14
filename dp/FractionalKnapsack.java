package dp;

import java.util.Scanner;

public class FractionalKnapsack {

	double weight[];
	double profit[];
	double ratio[];
	double capacity;

	FractionalKnapsack() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of items in the store: ");
		int nItems = scan.nextInt();
		System.out.println("Enter the (weight and benefit) of items: ");
		weight = new double[nItems];
		profit = new double[nItems];
		ratio = new double[nItems];

		for (int i = 0; i < nItems; ++i) {
			weight[i] = scan.nextDouble();
			profit[i] = scan.nextDouble();
			ratio[i] = profit[i] / weight[i];
		}

		System.out.println("Enter the weight of the knapsack: ");
		capacity = scan.nextDouble();
	}

	int getNext() {
		double highest = 0;
		int index = -1;
		for (int i = 0; i < profit.length; ++i) {
			if (ratio[i] > highest) {
				highest = ratio[i];
				index = i;
			}
		}
		return index;
	}

	void fill() {
		double currentWeight = 0; // current weight
		double currentProfit = 0; // current benefit

		System.out.print("\nItems considered: ");
		while (currentWeight < capacity) {
			int item = getNext(); // next highest ratio
			if (item == -1) {
				// No items left
				break;
			}

			System.out.print((item + 1) + " ");
			if (currentWeight + weight[item] <= capacity) {
				currentWeight = currentWeight + weight[item];
				currentProfit = currentProfit + profit[item];
				// mark as used for the getNext() (ratio) function
				ratio[item] = 0;
			} else {
				currentProfit = currentProfit + (ratio[item] * (capacity - currentWeight));
				currentWeight = currentWeight + (capacity - currentWeight);
				break; // the knapsack is full
			}
		}
		System.out.println("\nMax Benefit = " + currentProfit + ", Max Weight = " + currentWeight);
	}

	public static void main(String[] args) {
		FractionalKnapsack fk = new FractionalKnapsack();
		fk.fill();
	}
}
