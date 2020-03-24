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
	
	public Noeud(Ville ville)
	{
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

	public void setParentToNull()
	{
		parent=null;
		ville=null;
	}
	
	public Noeud(Ville ville, Noeud parent, double cout, int profondeur)
	{
		this.ville=ville;
		this.parent=parent;
		this.cout=cout;
		this.profondeur=profondeur;
	}
	
	/**
	 * Renvoie la liste des routes entre le noeud et la racine
	 * @return
	 */
	public ArrayList<Route> getTrajetFromRacine()
	{
		//À compléter
		return null;
	}
	
	/**
	 * Renvoie la liste des villes entre le noeud et la racine (les deux comprises)
	 * @return
	 */
	public ArrayList<Ville> getVillesFromRacine()
	{		
		//À compl�ter
		return null;
	}
	
	public void setCout(double cout) {
		this.cout=cout;
		
	}
	
}
