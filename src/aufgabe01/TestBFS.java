package aufgabe01;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Pseudograph;
import org.junit.Test;

public class TestBFS {

	@Test
	public void test() throws Exception{
		List<String> kanten = new ArrayList<String>();
		ReadGraph rg = new ReadGraph();
		rg.einlesen("C:/Users/bendi/workspace02/GKAP/src/gkaDatein/graph01.gka", kanten);
		Graph<String, DefaultEdge> graph01;
		graph01 = rg.graphArt(kanten);
		BFS bfs = new BFS();
		System.out.println(" " + graph01.getEdgeWeight(graph01.getEdge("a", "b")) );
		assertEquals(bfs.bfs(graph01, "a", "e").size(), 2);
		assertEquals(bfs.bfs(graph01, "a", "e").get(0).toString(), "(a : b)" );
		assertEquals(bfs.bfs(graph01, "a", "e").get(1).toString(), "(b : e)" );
//		
		rg.safeGraph(graph01, "C:/Users/bendi/workspace02/GKAP/src/gkaDatein/graph011.gka");
		BufferedReader a = new BufferedReader(new FileReader("C:/Users/bendi/workspace02/GKAP/src/gkaDatein/graph01.gka"));
		BufferedReader b = new BufferedReader(new FileReader("C:/Users/bendi/workspace02/GKAP/src/gkaDatein/graph011.gka"));
		String s1;
		String s2;
		
		while(((s1 = a.readLine()) != null) && ((s2 = b.readLine()) != null)) {
			assertEquals(s1, s2);
		}
		a.close();
		b.close();
		
		rg.einlesen("C:/Users/bendi/workspace02/GKAP/src/gkaDatein/graph03.gka", kanten);
		graph01 = rg.graphArt(kanten);
		
		assertEquals(bfs.bfs(graph01, "Hannover", "Bremen").size(), 3);
		assertEquals(bfs.bfs(graph01, "Hannover", "Bremen").get(0).toString(), "(Hannover : Oldenburg)" );
		assertEquals(bfs.bfs(graph01, "Hannover", "Bremen").get(1).toString(), "(Oldenburg : Cuxhaven)" );
		assertEquals(bfs.bfs(graph01, "Hannover", "Bremen").get(2).toString(), "(Cuxhaven : Bremen)" );
		int k = new Double(graph01.getEdgeWeight(graph01.getEdge("Paderborn", "Hamburg"))).intValue();
		System.out.println(k + " " + graph01.getEdgeWeight(graph01.getEdge("Walsrode", "Hamburg")) );
		assertEquals(new Double(graph01.getEdgeWeight(graph01.getEdge("Walsrode", "Hamburg"))).intValue(), 101);
	
	}

}
