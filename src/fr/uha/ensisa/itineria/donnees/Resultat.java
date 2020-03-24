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
	 * Renvoie la longueur en km du trajet résultat
	 * @return
	 */
	public int getDistance()
	{
		//Ã€ complÃ©ter
		return 0;
	}
	
	/**
	 * @author Robin
	 * 
	 * Renvoie la durée du trajet résultat
	 * @return
	 */
	public int getDuree()
	{
		//Ã€ complÃ©ter
		return 0;
	}
	
	/**
	 * @author Robin
	 * 
	 * Renvoie la profondeur du trajet résultat
	 * @return
	 */
	public int getProfondeur()
	{
		//Ã€ complÃ©ter
		return 0;
	}
	
	/**
	 * @author Robin
	 * 
	 * Renvoie une présentation textuelle de Résultat pour un affichage dans la console par exemple
	 */
	public String toString()
	{
		return "";
	}
	
}
