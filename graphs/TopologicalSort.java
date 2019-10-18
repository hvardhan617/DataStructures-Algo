package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://www.youtube.com/watch?v=Q9PIxaNGnig&list=PLqM7alHXFySGqCvcwfqqMrteqWukz9ZoE&index=5
//Data structure to store Graph1 Edge1s
class Edge1 {
	int source, dest;

	public Edge1(int source, int dest) {
		this.source = source;
		this.dest = dest;
	}
};

//Class to represent a Graph1 object
class Graph1 {
	// A List of Lists to represent an adjacency list
	List<List<Integer>> adjList = null;

	// Constructor
	Graph1(List<Edge1> Edge1s, int N) {

		// allocate memory
		adjList = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			adjList.add(i, new ArrayList<>());
		}

		// add Edge1s to the undirected Graph1
		for (int i = 0; i < Edge1s.size(); i++) {
			int src = Edge1s.get(i).source;
			int dest = Edge1s.get(i).dest;

			// add an Edge1 from source to destination
			adjList.get(src).add(dest);
		}
	}
}

public class TopologicalSort {
	// Perform DFS on graph and set departure time of all
	// vertices of the graph
	static int DFS(Graph graph, int v, boolean[] discovered, int[] departure, int time) {
		// mark current node as discovered
		discovered[v] = true;

		// set arrival time
		time++;

		// do for every edge (v -> u)
		for (int u : graph.adjList.get(v)) {
			// u is not discovered
			if (!discovered[u]) {
				time = DFS(graph, u, discovered, departure, time);
			}
		}

		// ready to backtrack
		// set departure time of vertex v
		departure[time] = v;//(so that is filled in a sorted order)
		time++;

		return time;
	}

	// performs Topological Sort on a given DAG
	public static void doTopologicalSort(Graph graph, int N) {
		// departure[] stores the vertex number using departure time as index
		int[] departure = new int[2 * N];
		Arrays.fill(departure, -1);

		// Note if we had done the other way around i.e. fill the
		// array with departure time by using vertex number
		// as index, we would need to sort the array later

		// stores vertex is discovered or not
		boolean[] discovered = new boolean[N];
		int time = 0;

		// perform DFS on all undiscovered vertices
		for (int i = 0; i < N; i++) {
			if (!discovered[i]) {
				time = DFS(graph, i, discovered, departure, time);
			}
		}

		// Print the vertices in order of their decreasing
		// departure time in DFS i.e. in topological order
		for (int i = 2 * N - 1; i >= 0; i--) {
			if (departure[i] != -1) {
				System.out.print(departure[i] + " ");
			}
		}
	}

	// Topological Sort Algorithm for a DAG using DFS
	public static void main(String[] args) {
		// List of graph edges as per above diagram
		List<Edge> edges = Arrays.asList(new Edge(0, 6), new Edge(1, 2), new Edge(1, 4), new Edge(1, 6), new Edge(3, 0),
				new Edge(3, 4), new Edge(5, 1), new Edge(7, 0), new Edge(7, 1));

		// Set number of vertices in the graph
		final int N = 8;

		// create a graph from edges
		Graph graph = new Graph(edges, N);

		// perform Topological Sort
		doTopologicalSort(graph, N);
	}
}
