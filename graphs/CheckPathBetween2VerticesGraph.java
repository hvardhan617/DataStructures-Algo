package graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

//https://www.techiedelight.com/find-path-between-vertices-directed-graph/
//use bFS to check for path connectivity & DFS for printing the path
//O(n+m) n- vertices & m - edges
public class CheckPathBetween2VerticesGraph {

	static class Edge {
		public final int source, dest;

		private Edge(int source, int dest) {
			this.source = source;
			this.dest = dest;
		}

		// Factory method for creating a Edge immutable instance
		public static Edge of(int a, int b) {
			return new Edge(a, b); // calls private constructor
		}
	};

	// Class to represent a graph object
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
			}
		}
	}

	// Function to perform BFS traversal from the source vertex in the graph to
	// determine if the destination vertex is reachable from the source or not
	public static boolean isConnected(Graph graph, int src, int dest, boolean[] discovered) {
		// create a queue used to do BFS
		Queue<Integer> q = new ArrayDeque<>();

		// mark source vertex as discovered
		discovered[src] = true;

		// push source vertex into the queue
		q.add(src);

		// run till queue is not empty
		while (!q.isEmpty()) {
			// pop front node from queue and print it
			int v = q.poll();

			// if destination vertex is found
			if (v == dest) {
				return true;
			}

			// do for every edge (v -> u)
			for (int u : graph.adjList.get(v)) {
				if (!discovered[u]) {
					// mark it discovered and push it into queue
					discovered[u] = true;
					q.add(u);
				}
			}
		}

		return false;
	}

	public static void main(String[] args) {
		// List of graph edges as per above diagram
		List<Edge> edges = Arrays.asList(Edge.of(0, 3), Edge.of(1, 0), Edge.of(1, 2), Edge.of(1, 4), Edge.of(2, 7),
				Edge.of(3, 4), Edge.of(3, 5), Edge.of(4, 3), Edge.of(4, 6), Edge.of(5, 6), Edge.of(6, 7));

		// Number of nodes in the graph (labelled from 0 to N-1)
		int N = 8;

		// create a graph from given edges
		Graph graph = new Graph(edges, N);

		// stores vertex is discovered or not
		boolean[] discovered = new boolean[N];

		// source and destination vertex
		int src = 0, dest = 7;

		// perform BFS traversal from the source vertex to check the connectivity
		if (isConnected(graph, src, dest, discovered)) {
			System.out.println("Path exists from vertex " + src + " to vertex " + dest);
		} else {
			System.out.println("No path exists between vertices " + src + " and " + dest);
		}
	}
}
