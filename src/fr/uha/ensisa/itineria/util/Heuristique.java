package fr.uha.ensisa.itineria.util;

import fr.uha.ensisa.itineria.donnees.Ville;

/**
 * Fichier permettant de calculer les diff�rentes heuristiques
 * @author weber
 *
 */
public abstract class Heuristique {
	
	/**
	 * Renvoie la distance vol d'oiseau entre 2 villes
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double getVolOiseau(Ville v1, Ville v2)
	{
		//À compléter
		return 0;
	}
	
	/**
	 * Renvoie une heuristique sur le temps de trajet entre deux villes
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double getTempsRestant(Ville v1, Ville v2)
	{		
		//À compléter
		return 0;
	}
	
	/**
	 * Calcule une heuristique entre deux villes
	 * @param heuristique
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double calcul(int heuristique, Ville v1, Ville v2)
	{
		double h;
		switch(heuristique)
		{
			case Constantes.HEURISTIQUE_VOL_OISEAU :
				h=getVolOiseau(v1,v2);
				break;
			case Constantes.HEURISTIQUE_TEMPS :
				h=getTempsRestant(v1,v2);
				break;
			default :
				h=getVolOiseau(v1,v2);
				break;
		}
		return h;
	}
	
	
	
}
