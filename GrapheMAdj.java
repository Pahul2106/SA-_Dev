package graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//écrivez une classe GrapheMAdj qui représente un graphe à l’aide d’une matrice d’adjacence. 
//private int[][] matrice;
//private Map<String, Integer> indices;

public class GrapheMAdj extends groupe {
	private int[][] matrice;
	private Map<String, Integer> indices;
	
	public GrapheMAdj() {
		indices = new HashMap<>();
		matrice = new int[0][0];
	}
	@Override
	public void ajouterSommet(String noeud) {
		// TODO Auto-generated method stub
		// si noeud n'exite pas
		if (!contientSommet(noeud)){
			indices.put(noeud, indices.size());
			// définit les nouvelles relations du sommet
			int[][] newMatrice = new int[matrice.length + 1][matrice.length + 1];
			for (int i = 0; i < matrice.length; ++i) {
				System.arraycopy(matrice[i], 0, newMatrice[i], 0, matrice.length);
				newMatrice[i][matrice.length] = -1;
				newMatrice[matrice.length][i] = -1;
			}
			newMatrice[matrice.length][matrice.length] = -1;
			matrice = newMatrice;
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
				if (!contientSommet(source)) {
					ajouterSommet(source);
				}
				if (!contientSommet(destination)) {
					ajouterSommet(destination);
				}
				// matrice prend la nouvel valeur de la source à la destination
				try {
				    // code that may throw the IllegalArgumentException

					if(contientArc(source, destination)) {
						throw new IllegalArgumentException("Arc existe déjà");
					}
					else {
						matrice[indices.get(source)][indices.get(destination)] = valeur;
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
			indices.remove(noeud);
		}
	}
	@Override
	public void oterArc(String source, String destination) {
		try {
		    // code that may throw the IllegalArgumentException
			if (!contientArc(source, destination)) {
				throw new IllegalArgumentException("n'existe pas");
			}
			else {
				matrice[indices.get(source)][indices.get(destination)] = -1;
			}
		} catch (IllegalArgumentException e) {
		    throw e;
		}
		
	}
	@Override
	public List<String> getSommets() {
		// TODO Auto-generated method stub
		List<String> sommets = new ArrayList<>();
	    sommets.addAll(indices.keySet());
	    return sommets;
	}
	@Override
	public List<String> getSucc(String sommet) {
		// TODO Auto-generated method stub
		try {
		    // code that may throw the IllegalArgumentException
			if (!contientSommet(sommet)) {
				throw new IllegalArgumentException("sommet doit être exister");	
			}
			else {
				List<String> lis = new ArrayList<>();
				List<String> lis2 = getSommets();
				for (int i = 0; i < indices.size(); ++i) {
					if (contientArc(sommet, lis2.get(i))) {
						lis.add(lis2.get(i));
					}
				}
				return lis;
			}
		} catch (IllegalArgumentException e) {
		    throw e;
		}
	}
	@Override
	public int getValuation(String src, String dest) {
		// TODO Auto-generated method stub
		if (contientSommet(src) && contientSommet(dest)) {
			return matrice[indices.get(src)][indices.get(dest)];
		}
		return -1;
	}
	@Override
	public boolean contientSommet(String sommet) {
		// TODO Auto-generated method stub
		return indices.containsKey(sommet);
	}
	@Override
	public boolean contientArc(String src, String dest) {
		// TODO Auto-generated method stub
		if (contientSommet(src) && contientSommet(dest)) {
			return matrice[indices.get(src)][indices.get(dest)] != -1;
		}
		return false;
	}
	
}
