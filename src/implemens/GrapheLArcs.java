package implemens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import core.IGraphe;

public class GrapheLArcs implements IGraphe{
    private List<Arc> arcs;

    public GrapheLArcs() {
        arcs = new ArrayList<>();
    }

    public GrapheLArcs(String description) {
        arcs = new ArrayList<>();
        peupler(description);
    }

    @Override
    public List<String> getSommets() {
        Set<String> sommets = new HashSet<>();

        for (Arc arc : arcs) {
            sommets.add(arc.getSource());
            sommets.add(arc.getDestination());
        }

        List<String> sommetsList = new ArrayList<>(sommets);
        sommetsList.remove(""); // Supprimer la chaîne vide s'il est présent
        Collections.sort(sommetsList); // Tri des sommets par ordre alphabétique

        return sommetsList;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> successeurs = new ArrayList<>();

        for (Arc arc : arcs) {
            if (arc.getSource().equals(sommet) && !arc.getDestination().isEmpty()) {
                successeurs.add(arc.getDestination());
            }
        }

        return successeurs;
    }
    
    


    @Override
    public int getValuation(String src, String dest) {
        for (Arc arc : arcs) {
            if (arc.getSource().equals(src) && arc.getDestination().equals(dest)) {
                return arc.getValuation();
            }
        }

        return -1; // Pas d'arc trouvé
    }

    @Override
    public boolean contientSommet(String sommet) {
        for (Arc arc : arcs) {
            if (arc.getSource().equals(sommet) || arc.getDestination().equals(sommet)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean contientArc(String src, String dest) {
        for (Arc arc : arcs) {
            if (arc.getSource().equals(src) && arc.getDestination().equals(dest)) {
                return true;
            }
        }

        return false;
    }
    
   


    @Override
    public void ajouterSommet(String noeud) {
        if (!contientSommet(noeud)) {
            arcs.add(new Arc(noeud, "", 0));
        }
    }

    

    @Override
    public void oterSommet(String noeud) {
        arcs.removeIf(arc -> arc.getSource().equals(noeud) || arc.getDestination().equals(noeud));
    }

    @Override
    public void oterArc(String source, String destination) {
        boolean arcExiste = arcs.removeIf(arc -> arc.getSource().equals(source) && arc.getDestination().equals(destination));
        if (!arcExiste) {
            throw new IllegalArgumentException("L'arc spécifié n'existe pas dans le graphe.");
        }
    }

    
    


    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (valeur < 0) {
            throw new IllegalArgumentException("La valeur de l'arc doit être supérieure ou égale à zéro.");
        }
        
        if (!contientArc(source, destination)) {
            ajouterSommet(source);
            ajouterSommet(destination);
            arcs.add(new Arc(source, destination, valeur));
        } else {
            throw new IllegalArgumentException("L'arc (" + source + ", " + destination + ") existe déjà.");
        }
    }

}
