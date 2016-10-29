package aufgabe01;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

public class GraphTyp {

	
	
	public <T> T graphArt(String s) {
		if(s.contains("->") && !Character.isDigit(s.charAt(s.length()-2))) {
			Graph<String, DefaultEdge> direct = new DefaultDirectedGraph<>(DefaultEdge.class);
			return e;
			direct.
		} else if(s.contains("--") && !Character.isDigit(s.charAt(s.length()-2))) {
			Graph<String, DefaultEdge> undirect = new SimpleGraph<>(DefaultEdge.class);
			return undirect;
		} else {
			return null;
		}
	}
	
	public static WeightedGraph<String, DefaultWeightedEdge> graphArtWeighted(String s) {
		if(s.contains("--") && Character.isDigit(s.charAt(s.length()-2))){
			WeightedGraph<String, DefaultWeightedEdge> undirectweight = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
			return undirectweight;
		} else if(s.contains("->") && Character.isDigit(s.charAt(s.length()-2))){
			WeightedGraph<String, DefaultWeightedEdge> directweight = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
			return directweight;
		} else {
			return null;
		}
	}
	
	
	public static void knotenKantenHinzufuegen(Graph<String, DefaultEdge> graph, List<String> l) {
		for(int i = 0; i < l.size() ; i++) {
			String v1 = knoten(l.get(i), 0);
			String v2 = knoten(l.get(i), 2);
			
			if(!graph.containsVertex(v1)) {
				graph.addVertex(v1);
			}
			if(!graph.containsVertex(v2)) {
				graph.addVertex(v2);
			}
			if(!graph.containsEdge(v1, v2)) {
				graph.addEdge(v1, v2);
			}
			
		}
	}
	
	public static void knotenKantenHinzufuegenWeighted(WeightedGraph<String, DefaultWeightedEdge> graph, List<String> l) {
		for(int i = 0; i < l.size() ; i++) {
			String v1 = knoten(l.get(i), 0);
			String v2 = knoten(l.get(i), 2);
			
			if(!graph.containsVertex(v1)) {
				graph.addVertex(v1);
			}
			if(!graph.containsVertex(v2)) {
				graph.addVertex(v2);
			}
			if(!graph.containsEdge(v1, v2)) {
				DefaultWeightedEdge e1 = graph.addEdge(v1, v2);
				int weight = Integer.parseInt(knoten(l.get(i), 5));
				graph.setEdgeWeight(e1, weight);
			}
		}
	}
	
	public static String knoten(String s, int position) {
		String[] sa = s.split(" ");
		return sa[position];
	}
}
