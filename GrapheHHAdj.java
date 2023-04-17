package graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrapheHHAdj extends groupe {
	private Map<String, Map<String, Integer>> hhadj;
	private List<String> LisSom;
	public GrapheHHAdj() {
		hhadj = new HashMap<String, Map<String, Integer>>();
		LisSom = new ArrayList<>();
	}
	
	@Override
	public List<String> getSommets() {
		// TODO Auto-generated method stub
		return this.LisSom;
	}

	@Override
	public List<String> getSucc(String sommet) {
		// TODO Auto-generated method stub
	    if (hhadj.containsKey(sommet)) { // check if the key exists in the map
	        return new ArrayList<String>(hhadj.get(sommet).keySet());
	    }
		return null;
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
			LisSom.add(noeud);
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
			else {
				if(!contientSommet(source)) {
					ajouterSommet(source);
				}
				if(!contientSommet(destination)) {
					ajouterSommet(destination);
				}
				// matrice prend la nouvel valeur de la source à la destination
				try {
				    // code that may throw the IllegalArgumentException

					if(contientArc(source, destination)) {
						throw new IllegalArgumentException("Arc existe déjà");
					}
					else {
						hhadj.get(source).put(destination, valeur);
					}
				} catch (IllegalArgumentException e) {
				    throw e;
				}
			}
		} catch (IllegalArgumentException e) {
		    throw e;
		}
	}

	@Override
	public void oterSommet(String noeud) {
		// TODO Auto-generated method stub
		if (contientSommet(noeud)) {
			List<String> lis = getSucc(noeud);
			//supprime tous les arcs du sommet
			for (int i = 0; i < lis.size(); ++i){
				oterArc(noeud, lis.get(i));
			}
			//suprime l'indices noeuds
			LisSom.remove(noeud);
			hhadj.remove(noeud);
		}
	}

	@Override
	public void oterArc(String source, String destination) {
		// TODO Auto-generated method stub
		try {
		    // code that may throw the IllegalArgumentException
			if (!contientArc(source, destination)) {
				throw new IllegalArgumentException("valeur doit être positive");
			}
			else {
				hhadj.get(source).remove(destination);				
			}
		} catch (IllegalArgumentException e) {
		    throw e;
		}
	}
	
}
