```java
package graphe;

import java.util.ArrayList;
import java.util.List;

public class GrapheLArcs implements IGraphe {
    private List<Arc> arcs;

    public GrapheLArcs() {
        arcs = new ArrayList<Arc>();
    }

    public GrapheLArcs(String str) {
        this();
        peupler(str);
    }

    @Override
    public void ajouterSommet(String noeud) {
        // rien à faire si le sommet est déjà présent
        if (!getSommets().contains(noeud)) {
            // ajoute un arc factice vers le sommet vide
            arcs.add(new Arc(noeud, "", 0));
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        // vérifie que l'arc n'existe pas déjà
        if (existeArc(source, destination)) {
            throw new IllegalArgumentException("L'arc (" + source + "->" + destination + ") existe déjà.");
        }
        ajouterSommet(source);
        ajouterSommet(destination);
        arcs.add(new Arc(source, destination, valeur));
    }

    @Override
    public void oterSommet(String noeud) {
        // supprime tous les arcs qui ont ce sommet comme source ou destination
        List<Arc> arcsASupprimer = new ArrayList<Arc>();
        for (Arc arc : arcs) {
            if (arc.getSource().equals(noeud) || arc.getDestination().equals(noeud)) {
                arcsASupprimer.add(arc);
            }
        }
        arcs.removeAll(arcsASupprimer);
    }

    @Override
    public void oterArc(String source, String destination) {
        // vérifie que l'arc existe
        if (!existeArc(source, destination)) {
            throw new IllegalArgumentException("L'arc (" + source + "->" + destination + ") n'existe pas.");
        }
        arcs.remove(new Arc(source, destination, 0));
    }

    @Override
    public List<String> getSommets() {
        List<String> sommets = new ArrayList<String>();
        for (Arc arc : arcs) {
            if (!sommets.contains(arc.getSource())) {
                sommets.add(arc.getSource());
            }
            if (!sommets.contains(arc.getDestination())) {
                sommets.add(arc.getDestination());
            }
        }
        return sommets;
    }

    @Override
    public List<Arc> getArcs() {
        return arcs;
    }

    @Override
    public boolean existeArc(String source, String destination) {
        return arcs.contains(new Arc(source, destination, 0));
    }
}```