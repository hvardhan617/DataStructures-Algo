package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

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

		for (int i = 0; i <= N; i++) {
			adjList.add(i, new ArrayList<>());
		}

		// add edges to the undirected graph
		for (Edge edge : edges) {
			adjList.get(edge.source).add(edge.dest);
		}
	}
}

public class test {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int n = 0; n < testCases; n++) {
			int noOfStars = sc.nextInt();
			List<Edge> edges = new ArrayList<>();
//			ArrayList<Integer> sources = new ArrayList<>();
//			ArrayList<Integer> dest = new ArrayList<>();
			for (int i = 0; i < noOfStars - 1; i++) {
//				sources.add(sc.nextInt());
//				dest.add(sc.nextInt());
				edges.add(new Edge(sc.nextInt(),sc.nextInt()));

			}
			//edges = Arrays.asList(new Edge(sc.nextInt(), sc.nextInt()));
			Graph graph = new Graph(edges, noOfStars);
			int brightest = sc.nextInt();
			System.out.println(BFS(graph, brightest));
		}

//		List<Edge> edges = Arrays.asList(new Edge(7, 2), new Edge(7, 3), new Edge(7, 6), new Edge(7, 4),
//				new Edge(7, 10), new Edge(3, 5), new Edge(6, 8), new Edge(6, 1), new Edge(8, 9)
//		// vertex 0, 13 and 14 are single nodes
//		);
//
//		// Set number of vertices in the graph
//		final int N = 10;
//
//		// create a graph from edges
//		Graph graph = new Graph(edges, N);
//		System.out.println(graph.adjList);
//		System.out.println(BFS(graph, 7));

	}

	private static Integer BFS(Graph graph, int source) {

		Queue<Integer> q = new LinkedList<>();
		q.add(source);
		q.add(null);

//		int height = 0;

		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Integer> list1 = null;
		while (!q.isEmpty()) {
			Integer temp = q.poll();

			if (temp == null) {
				if (!q.isEmpty()) {
					q.add(null);
				} else {
					break;
				}

//				height = height + 1;
				list1 = (ArrayList<Integer>) list.clone();
				list = new ArrayList<>();

			} else {
				for (int u : graph.adjList.get(temp)) {
					q.add(u);
					list.add(u);
				}

			}
		}
//		System.out.println("list1 sizr:::" + list1.size());
		if (list1.size() > 0) {
			return Collections.min(list1);
		}
		return null;

	}
}
