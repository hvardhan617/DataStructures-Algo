package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

//https://www.techiedelight.com/kahn-topological-sort-algorithm/

//data structure to store Graph2 Edge22s
class Edge2 {
	int source, dest;

	public Edge2(int source, int dest) {
		this.source = source;
		this.dest = dest;
	}
};

//class to represent a Graph2 object
class Graph2 {
	// A List of Lists to represent an adjacency list
	List<List<Integer>> adjList = null;

	// stores indegree of a vertex
	List<Integer> indegree = null;

	// Constructor
	Graph2(List<Edge2> Edge2s, int N) {
		adjList = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			adjList.add(i, new ArrayList<>());
		}

		// initialize indegree of each vertex by 0
		indegree = new ArrayList<>(Collections.nCopies(N, 0));

		// add Edge22s to the undirected Graph2
		for (int i = 0; i < Edge2s.size(); i++) {
			int src = Edge2s.get(i).source;
			int dest = Edge2s.get(i).dest;

			// add an Edge22 from source to destination
			adjList.get(src).add(dest);

			// increment in-degree of destination vertex by 1
			indegree.set(dest, indegree.get(dest) + 1);
		}
	}
}

public class KahnsAlgoTopologicalSort {
	// performs Topological Sort on a given DAG
	public static List<Integer> doTopologicalSort(Graph2 graph, int N) {
		// list to store the sorted elements
		List<Integer> L = new ArrayList<>();

		// get indegree information of the graph
		List<Integer> indegree = graph.indegree;

		// Set of all nodes with no incoming Edge2s
		Stack<Integer> S = new Stack<>();
		for (int i = 0; i < N; i++) {
			if (indegree.get(i) == 0) {
				S.add(i);
			}
		}

		while (!S.isEmpty()) {
			// remove a node n from S
			int n = S.pop();

			// add n to tail of L
			L.add(n);

			for (int m : graph.adjList.get(n)) {
				// remove Edge2 from n to m from the graph
				indegree.set(m, indegree.get(m) - 1);

				// if m has no other incoming Edge2s then
				// insert m into S
				if (indegree.get(m) == 0) {
					S.add(m);
				}
			}
		}

		// if graph has Edge2s then graph has at least one cycle
		for (int i = 0; i < N; i++) {
			if (indegree.get(i) != 0) {
				return null;
			}
		}

		return L;
	}

	public static void main(String[] args) {
		// List of graph Edge2s as per above diagram
		List<Edge2> Edge2s = Arrays.asList(new Edge2(0, 6), new Edge2(1, 2), new Edge2(1, 4), new Edge2(1, 6),
				new Edge2(3, 0), new Edge2(3, 4), new Edge2(5, 1), new Edge2(7, 0), new Edge2(7, 1));

		// Set number of vertices in the graph
		final int N = 8;

		// create a graph from Edge2s
		Graph2 graph = new Graph2(Edge2s, N);

		// Perform Topological Sort
		List<Integer> L = doTopologicalSort(graph, N);

		if (L != null) {
			System.out.print(L); // print topological order
		} else {
			System.out.println("Graph has at least one cycle. " + "Topological sorting is not possible");
		}
	}
}
