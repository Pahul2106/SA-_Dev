package implemens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrapheLAdj extends groupe {
	private Map<String, List<Arc>> ladj;
	
	public GrapheLAdj() {
		ladj = new HashMap<>();
	}
	@Override
	public List<String> getSommets() {
	    List<String> sommets = new ArrayList<>();
	    for (String sommet : ladj.keySet()) {
	        sommets.add(sommet);
	    }
	    return sommets;
	}

	@Override
	public List<String> getSucc(String sommet) {
	    List<String> successeurs = new ArrayList<>();
	    List<Arc> arcsSortants = ladj.get(sommet);
	    if (arcsSortants != null) {
	        for (Arc arc : arcsSortants) {
	            successeurs.add(arc.getDestination());
	        }
	    }
	    return successeurs;

	}

	@Override
	public int getValuation(String src, String dest) {
	    List<Arc> arcsSortants = ladj.get(src);
	    if (arcsSortants != null) {
	        for (Arc arc : arcsSortants) {
	            if (arc.getDestination().equals(dest)) {
	                return arc.getValuation();
	            }
	        }
	    }
	    return -1; // arc non trouvé
	}

	@Override
	public boolean contientSommet(String sommet) {
		return ladj.containsKey(sommet);
	}

	@Override
	public boolean contientArc(String src, String dest) {
	    List<Arc> arcsSortants = ladj.get(src);
	    if (arcsSortants != null) {
	        for (Arc arc : arcsSortants) {
	            if (arc.getDestination().equals(dest)) {
	                return true;
	            }
	        }
	    }
	    return false;

	}

	@Override
	public void ajouterSommet(String noeud) {
		if (!ladj.containsKey(noeud)) {
			ladj.put(noeud, new ArrayList<>());
		}
		
	}

	@Override
    public void ajouterArc(String source, String destination, Integer valeur) {
		try {
		    // code that may throw the IllegalArgumentException
			if ( valeur < 0) {
				throw new IllegalArgumentException("valeur doit être positive");
			}
			else {

		        ajouterSommet(source);
		        ajouterSommet(destination);
				// matrice prend la nouvel valeur de la source à la destination
				try {
				    // code that may throw the IllegalArgumentException

					if(contientArc(source, destination)) {
						throw new IllegalArgumentException("Arc existe déjà");
					}
					else {
				        ladj.get(source).add(new Arc(source, destination, valeur));
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
	    // Supprimer les arcs qui partent du sommet
	    List<Arc> arcsSortants = ladj.get(noeud);
	    if (arcsSortants != null) {
	        ladj.remove(noeud);
	        for (Arc arc : arcsSortants) {
	            // Supprimer les arcs qui arrivent au sommet
	            String destination = arc.getDestination();
	            List<Arc> arcsEntrants = ladj.get(destination);
	            if (arcsEntrants != null) {
	                arcsEntrants.removeIf(a -> a.getDestination().equals(noeud));
	            }
	        }
	    }
	}
	    
	@Override
	public void oterArc(String source, String destination) {
		
		try {
		    // code that may throw the IllegalArgumentException
			if (!contientArc(source, destination)) {
				throw new IllegalArgumentException(source + "n'as pas d'arc de " + destination);
			}
			else {
				List<Arc> arcsSortants = ladj.get(source);
			    if (arcsSortants != null) {
			        int index = -1;
			        for (int i = 0; i < arcsSortants.size(); i++) {
			            if (arcsSortants.get(i).getDestination().equals(destination)) {
			                index = i;
			                break;
			            }
			        }
			        if (index != -1) {
			            arcsSortants.remove(index);
			        }
			    }
			}
		} catch (IllegalArgumentException e) {
		    throw e;
		}
	    
	}
}