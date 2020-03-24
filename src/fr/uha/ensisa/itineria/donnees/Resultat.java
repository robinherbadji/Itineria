package fr.uha.ensisa.itineria.donnees;

import java.util.ArrayList;
/**
 * 
 * @author weber
 *
 */
public class Resultat {
	Parametres parametres;
	ArrayList<Route> trajet;
	int nbNoeudsExplores;
	long tempsDeCalcul;
	
	public Resultat(ArrayList<Route> trajet, int nbNoeudsExplores, long tempsDeCalcul, Parametres parametres)
	{
		this.trajet=trajet;
		this.nbNoeudsExplores=nbNoeudsExplores;
		this.tempsDeCalcul=tempsDeCalcul;
		this.parametres=parametres;
	}
	/**
	 * @author Robin
	 * 
	 * Renvoie la longueur en km du trajet r�sultat
	 * @return
	 */
	public int getDistance()
	{
		//À compléter
		return 0;
	}
	
	/**
	 * @author Robin
	 * 
	 * Renvoie la dur�e du trajet r�sultat
	 * @return
	 */
	public int getDuree()
	{
		//À compléter
		return 0;
	}
	
	/**
	 * @author Robin
	 * 
	 * Renvoie la profondeur du trajet r�sultat
	 * @return
	 */
	public int getProfondeur()
	{
		//À compléter
		return 0;
	}
	
	/**
	 * @author Robin
	 * 
	 * Renvoie une pr�sentation textuelle de R�sultat pour un affichage dans la console par exemple
	 */
	public String toString()
	{
		return "";
	}
	
}
