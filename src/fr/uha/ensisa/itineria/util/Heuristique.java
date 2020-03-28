package fr.uha.ensisa.itineria.util;

import fr.uha.ensisa.itineria.donnees.Ville;
import fr.uha.ensisa.itineria.util.Constantes;

/**
 * Fichier permettant de calculer les différentes heuristiques
 * @author weber
 *
 */
public abstract class Heuristique {
	
	
	/**
	 * Renvoie la distance vol d'oiseau entre 2 villes
	 * 
	 * @param v1
	 * @param v2
	 * @return La distance vol d'oiseau entre 2 villes
	 * 
	 * @author Robin
	 * @see <a href="https://rosettacode.org/wiki/Haversine_formula#Java">Formule de Haversine</a>
	 */	
	public static double getVolOiseau(Ville v1, Ville v2) {		
		double dLat = Math.toRadians(v2.getLattitude() - v1.getLattitude());
	    double dLon = Math.toRadians(v2.getLongitude() - v1.getLongitude());
	    double lat1 = Math.toRadians(v1.getLattitude());
	    double lat2 = Math.toRadians(v2.getLattitude());
	    
	    double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
	    double c = 2 * Math.asin(Math.sqrt(a));
	    return Constantes.RAYON_TERRESTRE * c;
	}
	
	
	/**
	 * Renvoie une heuristique sur le temps de trajet entre deux villes
	 * 
	 * @param v1
	 * @param v2
	 * @return Une heuristique sur le temps de trajet entre deux villes
	 * 
	 * @author Robin
	 * @see Constantes.VITESSE_MOYENNE Vitesse Moyenne
	 */
	public static double getTempsRestant(Ville v1, Ville v2)
	{		
		double temps = 60*getVolOiseau(v1,v2)/Constantes.VITESSE_MOYENNE;
		return temps;
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
	
	public static double carre(double val) {
		return val*val;
	}
	
}
