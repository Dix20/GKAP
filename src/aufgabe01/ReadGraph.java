package aufgabe01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;

public class ReadGraph {

//	public static void main(String[] args) throws Exception {
//		List<String> kanten = new ArrayList<String>();
//		einlesen("C:/Users/bendi/workspace02/GKAP/src/gkaDatein/graph03.gka", kanten);
//		Graph<String, DefaultEdge> graph01;
//		graph01 = graphArt(kanten);
//		
//		graph01.edgeSet().forEach(p -> System.out.println(p));
//		System.out.println(graph01.getEdgeWeight(graph01.getEdge("Kiel", "Husum")));
//		
//		safeGraph(graph01, "C:/Users/bendi/workspace02/GKAP/src/gkaDatein/graph011.gka");
//		System.out.println();
//	}
	
	public void einlesen(String file, List<String> s) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		Scanner sc = new Scanner(br);
		while (sc.hasNext()) {
			s.add(sc.nextLine());
		}
	}

	public AbstractBaseGraph<String, DefaultEdge> graphArt(List<String> list) {
		AbstractBaseGraph<String, DefaultEdge> graph;

		if (list.get(0).contains("->") && !Character.isDigit(list.get(0).charAt(list.get(0).length() - 2))) {
			graph = new DirectedPseudograph(DefaultEdge.class);
		} else if (list.get(0).contains("--") && !Character.isDigit(list.get(0).charAt(list.get(0).length() - 2))) {
			graph = new Pseudograph<>(DefaultEdge.class);
		} else if (list.get(0).contains("--") && Character.isDigit(list.get(0).charAt(list.get(0).length() - 2))) {
			graph = new WeightedPseudograph<>(DefaultWeightedEdge.class);
		} else if (list.get(0).contains("->") && Character.isDigit(list.get(0).charAt(list.get(0).length() - 2))) {
			graph = new DirectedWeightedPseudograph<>(DefaultWeightedEdge.class);
		} else {
			return null;
		}

		// Knoten und Kanten zufügen
		for (int i = 0; i < list.size(); i++) {
			String v1 = knoten(list.get(i), 0);
			String v2 = knoten(list.get(i), 2);

			v2 = letztesElement(v2, ";");

			if (!graph.containsVertex(v1)) {
				graph.addVertex(v1);
			}
			if (!graph.containsVertex(v2)) {
				graph.addVertex(v2);
			}
			if (!graph.containsEdge(v1, v2)) {

				if (Character.isDigit(list.get(0).charAt(list.get(0).length() - 2))) {
					DefaultEdge e1 = graph.addEdge(v1, v2);
					System.out.println(knoten(list.get(i).substring(0, list.get(i).length()-1), 4));
					int weight = Integer.parseInt(knoten(list.get(i).substring(0, list.get(i).length()-1), 4));
					graph.setEdgeWeight(e1, weight);
				} else {
					graph.addEdge(v1, v2);
				}
			}
		}

		return graph;
	}

	// liefert String an der Position von position zurück
	public String knoten(String s, int position) {
		String[] sa = s.split(" ");
		return sa[position];
	}

	// liefert String ohne ende zurück, falls ende das Ende von string ist
	private String letztesElement(String string, String ende) {
		if (string.endsWith(ende)) {
			string = string.substring(0, string.length() - 1);
		}
		return string;
	}

	public void safeGraph(Graph<String, DefaultEdge> graph, String speicherort) throws IOException {
		File file = new File(speicherort);
		file.createNewFile();

		Set<DefaultEdge> set = graph.edgeSet();
		PrintWriter pWriter;

		pWriter = new PrintWriter(new BufferedWriter(new FileWriter(speicherort)));
		List<DefaultEdge> l = new ArrayList<>();
		set.forEach(p -> l.add(p));

		for (int i = 0; i < l.size(); i++) {
			pWriter.println(
					l.get(i).toString().substring(1, l.get(i).toString().length() - 1).replace(":", "->") + ";");
		}

		pWriter.flush();
		pWriter.close();
	}

	public void visual(Graph<String, DefaultEdge> graph) throws InterruptedException {

		JFrame frame = new JFrame();
		frame.setSize(400, 400);
		JGraph jgraph = new JGraph(new JGraphModelAdapter(graph));
		frame.getContentPane().add(jgraph);
		frame.setVisible(true);
		while (true) {
			Thread.sleep(2000);
		}
	}
}
