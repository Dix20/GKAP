package aufgabe01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class BFS {

	public List<DefaultEdge> bfs(Graph<String, DefaultEdge> graph, String start, String ziel) {
		Queue<String> queue = new LinkedList<String>();
		queue.add(start);
		List<String> bearbeitet = new ArrayList<>();
		List<DefaultEdge> weg = new ArrayList<>();

		while (true) {
			if (start.equals(ziel)) {
				return weg;
			}
			Set<DefaultEdge> set = graph.edgesOf(queue.peek());

			set.forEach(l -> {
				String[] sa = l.toString().split(" ");
				sa = sa[2].split(";");

				if (!bearbeitet.contains(ziel)) {
					if (sa[0].endsWith(")")) {
						sa[0] = sa[0].substring(0, sa[0].length() - 1);
					}
					queue.add(sa[0]);
				}
			});

			if (queue.contains(ziel)) {
				DefaultEdge edge = kante(set, ziel);
				if (!start.equals(ziel)) {
					String[] sa = edge.toString().split(" ");
					weg = bfs(graph, start, sa[0].substring(1));
					weg.add(edge);
					return weg;
				}
			}
			bearbeitet.add(queue.poll());
		}
	}

	// gibt eine Kante aus set zurück, welche nach ziel führt.
	public DefaultEdge kante(Set<DefaultEdge> set, String ziel) {
		List<DefaultEdge> nachfolger = new ArrayList<>();

		set.forEach(l -> {
			String[] sa = l.toString().split(" ");
			sa = sa[2].split(";");
			if (sa[0].endsWith(")")) {
				sa[0] = sa[0].substring(0, sa[0].length() - 1);
			}
			if (sa[0].equals(ziel)) {
				nachfolger.add(l);
			}
		});

		return nachfolger.get(0);
	}

}