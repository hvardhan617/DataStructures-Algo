package greedy;

//https://iq.opengenus.org/activity-selection-problem-greedy-algorithm/
//https://www.geeksforgeeks.org/activity-selection-problem-greedy-algo-1/
//The Activity Selection problem follows Greedy approach i.e.
//at every step, we can make a choice that looks best at the moment(which ever activity finishes early) to get the optimal solution of the complete problem
// If we sort elements based on their starting time, the activity with least starting time could take the maximum duration for completion,
//therefore we won't be able to maximise number of activities.
public class ActivitySelectionProblem {

	// Prints a maximum set of activities that can be done by a single
	// person, one at a time.
	// n --> Total number of activities
	// s[] --> An array that contains start time of all activities
	// f[] --> An array that contains finish time of all activities
	public static void printMaxActivities(int s[], int f[], int n) {
		int i, j;

		System.out.print("Following activities are selected : n");

		// The first activity always gets selected
		i = 0;
		System.out.print(i + " ");

		// Consider rest of the activities
		for (j = 1; j < n; j++) {
			// If this activity has start time greater than or
			// equal to the finish time of previously selected
			// activity, then select it
			if (s[j] >= f[i]) {
				System.out.print(j + " ");
				i = j;
			}
		}
	}

	// driver program to test above function
	public static void main(String[] args) {
		int s[] = { 1, 3, 0, 5, 8, 5 };
		int f[] = { 2, 4, 6, 7, 9, 9 };
		int n = s.length;

		printMaxActivities(s, f, n);
	}
}
