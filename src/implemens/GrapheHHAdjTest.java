package implemens;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GrapheHHAdjTest {
	private GrapheHHAdj g;

	@BeforeEach
	void setUp() throws Exception {
	    g = new GrapheHHAdj();
	}

	@Test
	void testGetSommets() {
	    List<String> expectedSommets = new ArrayList<>();
	    assertTrue(g.getSommets().equals(expectedSommets));
	    
	    g.ajouterSommet("A");
	    expectedSommets.add("A");
	    assertTrue(g.getSommets().equals(expectedSommets));
	    
	    g.ajouterSommet("B");
	    expectedSommets.add("B");
	    assertTrue(g.getSommets().equals(expectedSommets));
	}

	@Test
	void testGetSucc() {
	    g.ajouterSommet("A");
	    g.ajouterSommet("B");
	    g.ajouterSommet("C");
	    g.ajouterArc("A", "B", 1);
	    g.ajouterArc("A", "C", 2);
	    
	    List<String> expectedSuccA = new ArrayList<>(Arrays.asList("B", "C"));
	    System.out.println(g.getSucc("A"));
	    assertTrue(g.getSucc("A").equals(expectedSuccA));
	    
	    List<String> expectedSuccB = new ArrayList<>();
	    assertTrue(g.getSucc("B").equals(expectedSuccB));
	    
	    List<String> expectedSuccD = null;
	    assertTrue(g.getSucc("D") == expectedSuccD);
	}

	@Test
	void testGetValuation() {
	    g.ajouterSommet("A");
	    g.ajouterSommet("B");
	    g.ajouterArc("A", "B", 1);
	    
	    int expectedValuation = 1;
	    assertTrue(g.getValuation("A", "B") == expectedValuation);
	}

	@Test
	void testContientSommet() {
	    assertFalse(g.contientSommet("A"));
	    
	    g.ajouterSommet("A");
	    assertTrue(g.contientSommet("A"));
	}

	@Test
	void testContientArc() {
	    assertFalse(g.contientArc("A", "B"));
	    
	    g.ajouterSommet("A");
	    g.ajouterSommet("B");
	    g.ajouterArc("A", "B", 1);
	    assertTrue(g.contientArc("A", "B"));
	    assertFalse(g.contientArc("B", "A"));
	}

	@Test
	void testAjouterSommet() {
	    List<String> expectedSommets = new ArrayList<>();
	    assertTrue(g.getSommets().equals(expectedSommets));
	    
	    g.ajouterSommet("A");
	    expectedSommets.add("A");
	    assertTrue(g.getSommets().equals(expectedSommets));
	    
	    g.ajouterSommet("A");
	    assertTrue(g.getSommets().equals(expectedSommets));
	}

	@Test
	void testAjouterArc() {
	    g.ajouterSommet("A");
	    g.ajouterSommet("B");
	    g.ajouterArc("A", "B", 1);
	    assertTrue(g.getValuation("A", "B") == 1);
	    
	    g.ajouterArc("B", "A", 2);
	    System.out.println(g.getValuation("B", "A"));
	    assertFalse(g.getValuation("B", "A") == 3);
	}

}
