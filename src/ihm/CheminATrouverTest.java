package ihm;

import core.IGraphe;
import implemens.GrapheLArcs;
import implemens.Arc;
import ihm.CheminATrouver;

import java.util.Arrays;
import java.util.List;

public class CheminATrouverTest {
    public static void main(String[] args) {
        // Création d'un graphe
        IGraphe graphe = new GrapheLArcs();
        graphe.ajouterSommet("A");
        graphe.ajouterSommet("B");
        graphe.ajouterSommet("C");
        graphe.ajouterArc("A", "B", 5);
        graphe.ajouterArc("B", "C", 3);

        // Création d'un arc de départ et d'arrivée
        Arc sdArc = new Arc("A", "C", 0);

        // Création d'une liste de sommets représentant un chemin possible
        List<Integer> cheminPossible = Arrays.asList(0, 1, 2);

        // Création d'un objet CheminATrouver
        CheminATrouver chemin = new CheminATrouver("graphe.txt", "reponse.txt", graphe,
                sdArc, 8, cheminPossible);

        // Affichage des informations du chemin à trouver
        System.out.println("Nom du fichier : " + chemin.getFileName());
        System.out.println("Nom du fichier de réponse : " + chemin.getRepFileName());
        System.out.println("Graphe : " + chemin.getGraph());
        System.out.println("Arc de départ et d'arrivée : " + chemin.getSD_arc());
        System.out.println("Distance attendue : " + chemin.getDistance_attendue());
        System.out.println("Chemin possible : " + chemin.getChemin_possible());
    }
}
