package aufgabe01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.AsWeightedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

public class ReadGraph {

	public static void main(String[] args) throws Exception{
//		List<String> kanten = new ArrayList<String>();
//		einlesen("C:/Users/bendi/workspace02/GKAP/src/gkaDatein/graph01.gka", kanten);
//		Graph<String, DefaultEdge> graph;
//		
//		graph = graphArt(kanten);
		
//		for (int i = 0 ; i < graph.edgeSet().size(); i++) { 
//			System.out.println(graph.edgeSet().toArray()[i]);
//		}
	}
	
	public void einlesen(String file, List<String> s) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));
        Scanner sc = new Scanner(br);
        int i=0;
    while(sc.hasNext()){
            s.add(sc.nextLine());
            i++;
        }
	}
	
	
	public Graph<String, DefaultEdge> graphArt(List<String> list) {
		Graph<String, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);
		
		if(list.get(0).contains("->") && !Character.isDigit(list.get(0).charAt(list.get(0).length()-2))) {
			graph = new DefaultDirectedGraph<>(DefaultEdge.class);
		} else if(list.get(0).contains("--") && !Character.isDigit(list.get(0).charAt(list.get(0).length()-2))) {
			graph = new SimpleGraph<>(DefaultEdge.class);
		} else if(list.get(0).contains("--") && Character.isDigit(list.get(0).charAt(list.get(0).length()-2))){
			graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		} else if(list.get(0).contains("->") && Character.isDigit(list.get(0).charAt(list.get(0).length()-2))){
			graph = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);	
		} else {
			return null;
		}
		
		// Knoten und Kanten zufügen
		for(int i = 0; i < list.size() ; i++) {
			String v1 = knoten(list.get(i), 0);
			String v2 = knoten(list.get(i), 2);
			
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
		
		return graph;
	}
	
	public void knotenKantenHinzufuegenWeighted(WeightedGraph<String, DefaultWeightedEdge> graph, List<String> l) {
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
	
	// liefert String an der Position von position zurück
	public String knoten(String s, int position) {
		String[] sa = s.split(" ");
		return sa[position];
	}
	
}
