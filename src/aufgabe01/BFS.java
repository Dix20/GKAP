package aufgabe01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class BFS {

	public static void main(String[] args) throws Exception{
		List<String> kanten = new ArrayList<String>();
		ReadGraph rg = new ReadGraph();
		rg.einlesen("C:/Users/bendi/workspace02/GKAP/src/gkaDatein/graph01.gka", kanten);
		Graph<String, DefaultEdge> graph;
		graph = rg.graphArt(kanten);
//		bfs(graph, "a", "b");
//		for (int i = 0 ; i < graph.edgeSet().size(); i++) { 
//			System.out.println(s.);
//		}
		
		System.out.println("" + bfs(graph, "a", "l"));
	}
	
	
	public BFS() {
		Graph<String, DefaultEdge> g = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
		bfs(g, "", "");

	}

	// ja

	public static int bfs(Graph<String, DefaultEdge> graph, String start, String ziel) {

		int f = -1;
		int t = 0;
		
		Queue<String> queue = new LinkedList<String>();
		queue.add(start);
		List<String> bearbeitet = new ArrayList<>();
		

		while(t == 0) {
			Set set = graph.edgesOf(queue.peek());
//			String[] sa;
			
			if (queue.contains(ziel)) {
				t = 1;
			} else {
						set.forEach(l -> {
							String[] sa = l.toString().split(" ");
							sa = sa[2].split(";");
							if (!queue.contains(sa[0]) && !bearbeitet.contains(sa[0])) {
								queue.add(sa[0]);
							}
						});
			

				if (queue.size() > 0) {
					bearbeitet.add(queue.peek());
				} else {
					return 0;
				}
			}
			f ++;
		}
		return f;
	}

}