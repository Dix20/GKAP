package aufgabe01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class BFS {
	
	public BFS() {
		DirectedGraph<String, DefaultEdge> g = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
		
	}

	
	// ja
	
	public int bfs(Graph<String, DefaultEdge> graph, String start, String ziel) {

		Queue<String> queue = new LinkedList<String>();
		String[] s = graph.edgesOf(start).toArray(new String[0]);
		List<String> bearbeitet = new ArrayList<>();

		if (queue.contains(ziel)) {
			return 1;
		} else {

			for (int i = 0; i < s.length; i++) {
				if (!queue.contains(s[0]) && !bearbeitet.contains(s[0])) {
					queue.add(s[i]);
				}
			}

			if (queue.size() > 0) {
				bearbeitet.add(queue.peek());
				return 1 + bfs(graph, bearbeitet.get(0), ziel);
			} else {
				return 0;
			}
		}
	}
}