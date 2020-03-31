package fr.uha.ensisa.itineria.donnees;

import java.util.ArrayList;

/**
 * 
 * @author weber
 *
 */
public class Noeud {

	private Ville ville;
	private Noeud parent;
	private double cout;
	private int profondeur;

	public Noeud(Ville ville) {
		this(ville, null, 0.0, 0);
	}

	public Ville getVille() {
		return ville;
	}

	public Noeud getParent() {
		return parent;
	}

	public double getCout() {
		return cout;
	}

	public int getProfondeur() {
		return profondeur;
	}

	public void setParentToNull() {
		parent = null;
		ville = null;
	}

	public Noeud(Ville ville, Noeud parent, double cout, int profondeur) {
		this.ville = ville;
		this.parent = parent;
		this.cout = cout;
		this.profondeur = profondeur;
	}

	/**
	 * Renvoie la liste des routes entre le noeud et la racine
	 * 
	 * @return
	 * @author Robin
	 */
	public ArrayList<Route> getTrajetFromRacine() {
		ArrayList<Route> trajet = new ArrayList<Route>();
		if (parent != null) {
			trajet.addAll(parent.getTrajetFromRacine());
			trajet.add(parent.getVille().getRouteTo(ville));
		}
		return trajet;
	}

	/**
	 * Renvoie la liste des villes entre le noeud et la racine (les deux comprises)
	 * 
	 * @return
	 * @author Robin
	 */
	public ArrayList<Ville> getVillesFromRacine() {
		ArrayList<Ville> villes = new ArrayList<Ville>();
		if (parent != null) {
			villes.addAll(parent.getVillesFromRacine());
		}
		villes.add(ville);
		return villes;
	}

	public void setCout(double cout) {
		this.cout = cout;
	}

}
