package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//O(n+m)
//https://www.geeksforgeeks.org/check-given-graph-tree/
//An undirected graph is a tree if it has no cycle and the graph is connected
//To check for cycle - use BFS/DFS
// to check for connectivity - start with BFS/DFS from any vertex and check if all the vertices can be reached from it
//https://www.techiedelight.com/determine-undirected-graph-tree-acyclic-connected-graph/
public class CheckIfGraphIsTree {

	// data structure to store graph edges
	static class Edge {
		int source, dest;

		public Edge(int source, int dest) {
			this.source = source;
			this.dest = dest;
		}
	};

	// class to represent a graph object
	static class Graph {
		// A List of Lists to represent an adjacency list
		List<List<Integer>> adjList = null;

		// Constructor
		Graph(List<Edge> edges, int N) {
			adjList = new ArrayList<>(N);
			for (int i = 0; i < N; i++) {
				adjList.add(i, new ArrayList<>());
			}

			// add edges to the undirected graph
			for (int i = 0; i < edges.size(); i++) {
				int src = edges.get(i).source;
				int dest = edges.get(i).dest;

				adjList.get(src).add(dest);
				adjList.get(dest).add(src);
			}
		}
	}

	// Perform DFS on graph and returns true if any back-edge
	// is found in the graph
	public static boolean DFS(Graph graph, int v, boolean[] discovered, int parent) {
		// mark current node as discovered
		discovered[v] = true;

		// do for every edge (v -> w)
		for (int w : graph.adjList.get(v)) {
			// w is not discovered
			if (!discovered[w]) {
				if (!DFS(graph, w, discovered, v))
					return false;
			}

			// w is discovered and w is not a parent
			else if (w != parent) {
				// we found a back-edge (cycle)
				return false;
			}
		}

		// No back-edges found in the graph
		return true;
	}

	public static void main(String[] args) {
		// List of graph edges as per above diagram
		List<Edge> edges = Arrays.asList(new Edge(0, 1), new Edge(1, 2), new Edge(2, 3), new Edge(3, 4), new Edge(4, 5),
				new Edge(5, 0)
		// edge (5->0) introduces a cycle in the graph
		);

		// Number of vertices in the graph
		final int N = 6;

		// construct graph
		Graph graph = new Graph(edges, N);

		// stores vertex is discovered or not
		boolean[] discovered = new boolean[N];

		// boolean flag to store if the graph is tree or not
		boolean isTree = true;

		// Do DFS traversal from first vertex
		isTree = DFS(graph, 0, discovered, -1);

		for (int i = 0; isTree && i < N; i++) {
			// any undiscovered vertex means graph is not connected
			if (!discovered[i])
				isTree = false;
		}

		if (isTree)
			System.out.println("Graph is a Tree");
		else
			System.out.println("Graph is not a Tree");
	}
}
