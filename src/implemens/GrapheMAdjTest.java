package implemens;



import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GrapheMAdjTest {
    @Test
    public void testAjouterSommet() {
        GrapheMAdj graphe = new GrapheMAdj();
        graphe.ajouterSommet("A");
        assertTrue(graphe.contientSommet("A"));
    }

    @Test
    public void testAjouterArc() {
        GrapheMAdj graphe = new GrapheMAdj();
        graphe.ajouterArc("A", "B", 1);
        assertTrue(graphe.contientArc("A", "B"));
        assertEquals(graphe.getValuation("A", "B"), 1);
    }

    @Test
    public void testOterSommet() {
        GrapheMAdj graphe = new GrapheMAdj();
        graphe.ajouterSommet("A");
        graphe.ajouterSommet("B");
        graphe.ajouterArc("A", "B", 1);
        graphe.oterSommet("A");
        assertFalse(graphe.contientSommet("A"));
        assertFalse(graphe.contientArc("A", "B"));
        assertTrue(graphe.getValuation("A", "B") == -1);
    }

    @Test
    public void testOterArc() {
        GrapheMAdj graphe = new GrapheMAdj();
        graphe.ajouterSommet("A");
        graphe.ajouterSommet("B");
        graphe.ajouterArc("A", "B", 1);
        graphe.oterArc("A", "B");
        assertFalse(graphe.contientArc("A", "B"));
        assertTrue(graphe.getValuation("A", "B") == -1);
    }

    @Test
    public void testGetSommets() {
        GrapheMAdj graphe = new GrapheMAdj();
        graphe.ajouterSommet("A");
        graphe.ajouterSommet("B");
        graphe.ajouterSommet("C");
        List<String> sommets = graphe.getSommets();
        assertTrue(sommets.containsAll(Arrays.asList("A", "B", "C")));
    }

    @Test
    public void testGetSucc() {
        GrapheMAdj graphe = new GrapheMAdj();
        graphe.ajouterSommet("A");
        graphe.ajouterSommet("B");
        graphe.ajouterSommet("C");
        graphe.ajouterSommet("D");
        graphe.ajouterSommet("E");
        graphe.ajouterSommet("F");
        graphe.ajouterSommet("G");
        graphe.ajouterSommet("Z");
        graphe.ajouterArc("A", "B", 1);
        graphe.ajouterArc("A", "C", 2);
        List<String> succ = graphe.getSucc("A");
        assertTrue(succ.containsAll(Arrays.asList("B", "C")));
        assertFalse(succ.containsAll(Arrays.asList("A")));
        assertFalse(graphe.getSucc("B") == Arrays.asList("B"));
        assertFalse(graphe.getSucc("C") == Arrays.asList("C"));
        assertFalse(graphe.getSucc("D") == Arrays.asList("D"));
        assertFalse(graphe.getSucc("E") == Arrays.asList("E"));
        assertFalse(graphe.getSucc("F") == Arrays.asList("F"));
        assertFalse(graphe.getSucc("G") == Arrays.asList("G"));
        assertFalse(graphe.getSucc("Z") == Arrays.asList("Z"));
    }

    @Test
    public void testGetValuation() {
        GrapheMAdj graphe = new GrapheMAdj();
        graphe.ajouterSommet("A");
        graphe.ajouterSommet("B");
        graphe.ajouterArc("A", "B", 1);
        assertEquals(graphe.getValuation("A", "B"), 1);
    }

    @Test
    public void testContientSommet() {
        GrapheMAdj graphe = new GrapheMAdj();
        graphe.ajouterSommet("A");
        assertTrue(graphe.contientSommet("A"));
        assertFalse(graphe.contientSommet("B"));
    }
}