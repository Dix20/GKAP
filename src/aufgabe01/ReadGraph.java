package aufgabe01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

public class ReadGraph {

	public static void main(String[] args) throws Exception{
		List<String> kanten = new ArrayList<String>();
		einlesen("C:/Users/bendi/workspace02/GKAP/src/gkaDatein/graph01.gka", kanten);
		
		if(Character.isDigit(kanten.get(0).charAt(kanten.get(0).length()-2))){
			Graph<String, DefaultWeightedEdge> graph = graphArtWeighted(kanten.get(0));
		} else {
			Graph<String, DefaultEdge> graph = graphArt(kanten.get(0));
		}
		
		if(graphArt(kanten.get(0)).containsEdge("h", "g")){
		System.out.println("ja");
		}
	}
	
	public static void einlesen(String file, List<String> s) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));
        Scanner sc = new Scanner(br);
        int i=0;
    while(sc.hasNext()){
            s.add(sc.nextLine());
            System.out.println(s.get(i));
            i++;
        }
	}
	
	public static Graph<String, DefaultEdge> graphArt(String s) {
		if(s.contains("->") && !Character.isDigit(s.charAt(s.length()-2))) {
			Graph<String, DefaultEdge> direct = new DefaultDirectedGraph<>(DefaultEdge.class);
			return direct;
		} else if(s.contains("--") && !Character.isDigit(s.charAt(s.length()-2))) {
			Graph<String, DefaultEdge> undirect = new SimpleGraph<>(DefaultEdge.class);
			return undirect;
		} else {
			return null;
		}
	}
	
	public static Graph<String, DefaultWeightedEdge> graphArtWeighted(String s) {
		if(s.contains("--") && Character.isDigit(s.charAt(s.length()-2))){
			Graph<String, DefaultWeightedEdge> undirectweight = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
			return undirectweight;
		} else if(s.contains("->") && Character.isDigit(s.charAt(s.length()-2))){
			Graph<String, DefaultWeightedEdge> directweight = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
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
	
	public static void knotenKantenHinzufuegenWeighted(SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph, List<String> l) {
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
