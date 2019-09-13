package BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

//https://www.techiedelight.com/breadth-first-search/
class Edge {
	int source, dest;

	public Edge(int source, int dest) {
		this.source = source;
		this.dest = dest;
	}
};

class Graph {
	// A List of Lists to represent an adjacency list
	List<List<Integer>> adjList = null;

	// Constructor
	Graph(List<Edge> edges, int N) {
		adjList = new ArrayList<>(N);

		for (int i = 0; i < N; i++) {
			adjList.add(i, new ArrayList<>());
		}

		// add edges to the undirected graph
		for (Edge edge : edges) {
			adjList.get(edge.source).add(edge.dest);
		}
	}
}

public class BFSImpl {

	// Perform BFS on graph starting from vertex v
	public static void BFS(Graph graph, int v, boolean[] discovered) {
		// create a queue used to do BFS
		Queue<Integer> q = new ArrayDeque<>();

		// mark source vertex as discovered
		discovered[v] = true;

		// push source vertex into the queue
		q.add(v);

		// run till queue is not empty
		while (!q.isEmpty()) {
			// pop front node from queue and print it
			v = q.poll();
			System.out.print(v + " ");

			// do for every edge (v -> u)
			for (int u : graph.adjList.get(v)) {
				if (!discovered[u]) {
					// mark it discovered and push it into queue
					discovered[u] = true;
					q.add(u);
				}
			}
		}

	}

	// https://www.techiedelight.com/depth-first-search/
	// Perform iterative DFS on graph g starting from vertex v
	// only after popping mark it as discovered
	public static void iterativeDFS(Graph graph, int v, boolean[] discovered) {
		// create a stack used to do iterative DFS
		Stack<Integer> stack = new Stack<>();

		// push the source node into stack
		stack.push(v);

		// run till stack is not empty
		while (!stack.empty()) {
			// Pop a vertex from stack
			v = stack.pop();

			// if the vertex is already discovered yet, ignore it
			if (discovered[v])
				continue;

			// we will reach here if the popped vertex v
			// is not discovered yet. We print it and process
			// its undiscovered adjacent nodes into stack
			discovered[v] = true;
			System.out.print(v + " ");

			// do for every edge (v -> u)
			List<Integer> adj = graph.adjList.get(v);
			for (int i = adj.size() - 1; i >= 0; i--) {
				int u = adj.get(i);
				if (!discovered[u]) {
					stack.push(u);
				}
			}
		}
	}

	public static void main(String[] args) {
		// List of graph edges as per above diagram
//		List<Edge> edges = Arrays.asList(new Edge(1, 2), new Edge(1, 3), new Edge(1, 4), new Edge(2, 5), new Edge(2, 6),
//				new Edge(5, 9), new Edge(5, 10), new Edge(4, 7), new Edge(4, 8), new Edge(7, 11), new Edge(7, 12)
		// vertex 0, 13 and 14 are single nodes
//		);

		List<Edge> edges = Arrays.asList(new Edge(7, 2), new Edge(7, 3), new Edge(7, 6), new Edge(7, 4),
				new Edge(7, 10), new Edge(3, 5), new Edge(6, 8), new Edge(6, 1), new Edge(8, 9)
		// vertex 0, 13 and 14 are single nodes
		);

		// Set number of vertices in the graph
		final int N = 10;

		// create a graph from edges
		Graph graph = new Graph(edges, N);

		// stores vertex is discovered or not
		boolean[] discovered = new boolean[N];

		// Do BFS traversal from all undiscovered nodes to
		// cover all unconnected components of graph
		for (int i = 0; i < N; i++) {
			if (discovered[i] == false) {
				// start BFS traversal from vertex i
				BFS(graph, i, discovered);
			}
		}
	}
}
