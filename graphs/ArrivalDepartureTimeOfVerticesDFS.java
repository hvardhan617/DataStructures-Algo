package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://www.techiedelight.com/arrival-departure-time-vertices-dfs/
//O(m+n) - number of edges + number of vertices
//Data structure to store graph edges
class Edge {
	int source, dest;

	public Edge(int source, int dest) {
		this.source = source;
		this.dest = dest;
	}
};

//Class to represent a graph object
class Graph {
	// A List of Lists to represent an adjacency list
	List<List<Integer>> adjList = null;

	// Constructor
	Graph(List<Edge> edges, int N) {
		adjList = new ArrayList<>(N);

		for (int i = 0; i < N; i++) {
			adjList.add(i, new ArrayList<>());
		}

		// add edges to the directed graph
		for (Edge edge : edges) {
			adjList.get(edge.source).add(edge.dest);
		}
	}
}

//similar to backtracking
public class ArrivalDepartureTimeOfVerticesDFS {
	// Function to perform DFS Traversal
	public static int DFS(Graph graph, int v, boolean[] discovered, int[] arrival, int[] departure, int time) {

		// set arrival time of vertex v
		time = time + 1;
		arrival[v] = time;

		// mark vertex as discovered
		discovered[v] = true;

		for (int i : graph.adjList.get(v)) {
			if (!discovered[i]) {
				time = DFS(graph, i, discovered, arrival, departure, time);
			}
		}

		// set departure time of vertex v
		departure[v] = ++time;

		return time;
	}

//	public static void DFSIterative(Graph graph, int v, boolean[] discovered, int[] arrival, int[] departure,
//			int time) {
//
//		discovered[v] = true;
//		time = time + 1;
//		arrival[v] = time;
//
//		Stack<Integer> stack = new Stack<>();
//
//		// push the source node into stack
//		stack.push(v);
//
//		// run till stack is not empty
//		while (!stack.empty()) {
//			// Pop a vertex from stack
//			v = stack.pop();
//
//			List<Integer> list = graph.adjList.get(v);
//			for (Integer i : list) {
////				List<Integer> u =  graph.adjList.get(i);
//				if (!discovered[i]) {
//					discovered[i] = true;
//					time = time + 1;
//					arrival[i] = time;
//				}
//			}
//			time = time + 1;
//			departure[v] = time;
//
//		}
//	}

	public static void main(String[] args) {
		// List of graph edges as per above diagram
		List<Edge> edges = Arrays.asList(new Edge(0, 1), new Edge(0, 2), new Edge(2, 3), new Edge(2, 4), new Edge(3, 1),
				new Edge(3, 5), new Edge(4, 5), new Edge(6, 7));

		// Set number of vertices in the graph
		final int N = 8;

		// create a graph from edges
		Graph graph = new Graph(edges, N);

		// array to store arrival time of vertex
		int[] arrival = new int[N];

		// array to store departure time of vertex
		int[] departure = new int[N];

		// Mark all the vertices as not discovered
		boolean[] discovered = new boolean[N];
		int time = -1;

		// Do DFS traversal from all undiscovered nodes to
		// cover all unconnected components of graph
		for (int i = 0; i < N; i++) {
			if (!discovered[i]) {
				time = DFS(graph, i, discovered, arrival, departure, time);
//				DFSIterative(graph, i, discovered, arrival, departure, time);
			}
		}

		// print arrival and departure time of each
		// vertex in DFS
		for (int i = 0; i < N; i++) {
			System.out.println("Vertex " + i + " (" + arrival[i] + ", " + departure[i] + ")");
		}
	}
}
