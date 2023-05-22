package implems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrapheHHAdj extends groupe {
	private Map<String, Map<String, Integer>> hhadj;
	public GrapheHHAdj() {
		hhadj = new HashMap<String, Map<String, Integer>>();
	}
	
	@Override
	public List<String> getSommets() {
		// TODO Auto-generated method stub
		return new ArrayList<>(hhadj.keySet());
	}

	@Override
	public List<String> getSucc(String sommet) {
		// TODO Auto-generated method stub
	    if (hhadj.containsKey(sommet)) { // check if the key exists in the map
	        return new ArrayList<String>(hhadj.get(sommet).keySet());
	    }
		return Collections.emptyList();
	}

	@Override
	public int getValuation(String src, String dest) {
		// TODO Auto-generated method stub
		if (contientSommet(src) && contientSommet(dest)) {
			if (hhadj.get(src).get(dest) != null) {
				return hhadj.get(src).get(dest);
            }
		}
		return -1;
	}

	@Override
	public boolean contientSommet(String sommet) {
		// TODO Auto-generated method stub
		return hhadj.containsKey(sommet);
	}

	@Override
	public boolean contientArc(String src, String dest) {
		// TODO Auto-generated method stub
		if (contientSommet(src) && contientSommet(dest)) {
			return hhadj.get(src).containsKey(dest);
		}
		return false;
	}

	@Override
	public void ajouterSommet(String noeud) {
		// TODO Auto-generated method stub
		if (!contientSommet(noeud)) {
			hhadj.put(noeud, new HashMap<>());
		}
	}

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) {
		// TODO Auto-generated method stub
		try {
		    // code that may throw the IllegalArgumentException
			if ( valeur < 0) {
				throw new IllegalArgumentException("valeur doit être positive");
			}
			
			if(contientArc(source, destination)) {
				throw new IllegalArgumentException("Arc existe déjà");
			}
			else {
				ajouterSommet(source);
				ajouterSommet(destination);
				hhadj.get(source).put(destination, valeur);
			}
			
		} catch (IllegalArgumentException e) {
		    throw e;
		}
	}

	@Override
	public void oterSommet(String noeud) {
		if (contientSommet(noeud)) {
			List<String> lis = getSucc(noeud);
			//supprime tous les arcs du sommet
			for (int i = 0; i < lis.size(); ++i){
				oterArc(noeud, lis.get(i));
			}
			//suprime l'indices noeuds
			hhadj.remove(noeud);
		}
	}

	@Override
	public void oterArc(String source, String destination) {
		try {
		    // code that may throw the IllegalArgumentException
			if (!contientArc(source, destination)) {
				throw new IllegalArgumentException("l'arc n'existe pas");
			}
			else {
				hhadj.get(source).remove(destination);				
			}
		} catch (IllegalArgumentException e) {
		    throw e;
		}
	}
	
	
}
