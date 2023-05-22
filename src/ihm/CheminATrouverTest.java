package ihm;

import core.IGraphe;
import implemens.GrapheLArcs;
import implemens.Arc;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheminATrouverTest {
    @Test
    public void testCheminATrouver() {
        String fileName = "graphes/g-10-1.txt";
        String repFileName = "reponses/r-10-1.txt";
        IGraphe g = createTestGraph();
        Arc sd_arc = new Arc("A", "D", 10);
        int distance_attendue = 20;
        List<Integer> chemin_possible = createTestPath();

        CheminATrouver cheminATrouver = new CheminATrouver(fileName, repFileName, g, sd_arc,
                distance_attendue, chemin_possible);

        assertEquals(fileName, cheminATrouver.getFileName());
        assertEquals(repFileName, cheminATrouver.getRepFileName());
        assertEquals(g, cheminATrouver.getGraph());
        assertEquals(sd_arc, cheminATrouver.getSD_arc());
        assertEquals(distance_attendue, cheminATrouver.getDistance_attendue());
        assertEquals(chemin_possible, cheminATrouver.getChemin_possible());
    }

    private IGraphe createTestGraph() {
        IGraphe g = new GrapheLArcs();
        
        // Ajoutez les sommets et les arcs nécessaires pour le test
        g.ajouterSommet("A");
        g.ajouterSommet("B");
        g.ajouterSommet("C");
        g.ajouterSommet("D");
        g.ajouterArc("A", "B", 5);
        g.ajouterArc("B", "C", 10);
        g.ajouterArc("C", "D", 15);

        return g;
    }

    private List<Integer> createTestPath() {
        // Créez et retournez un chemin de test
        List<Integer> chemin_possible = new ArrayList<>();
        chemin_possible.add(1);
        chemin_possible.add(2);
        chemin_possible.add(3);
        return chemin_possible;
    }
}
