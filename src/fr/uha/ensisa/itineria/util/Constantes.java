package fr.uha.ensisa.itineria.util;

/**
 * Fichier contenant toutes les constantes de l'application
 * @author weber
 *
 */
public abstract class Constantes {
	
	public static final int MODE_CONSOLE = 0;
	public static final int MODE_LIGNE_DE_COMMANDE = 1;
	public static final int MODE_INTERFACE_GRAPHIQUE = 2;
	
	public static final int RECHERCHE_LARGEUR = 1;
	public static final int RECHERCHE_COUT_UNIFORME = 2;
	public static final int RECHERCHE_PROFONDEUR = 3;
	public static final int RECHERCHE_PROFONDEUR_LIMITEE = 4;
	public static final int RECHERCHE_PROFONDEUR_ITERATIF = 5;
	public static final int RECHERCHE_GLOUTONNE = 6;
	public static final int RECHERCHE_A_STAR = 7;
	
	public static final String NOM_ALGO_RECHERCHE[] = {"Parcours en largeur",
			"Parcours � co�t uniforme", "Parcours en profondeur", "Parcours en profondeur limit�e",
			"Parcours it�ratif en profondeur", "Recherche gloutonne", "Recherche A*"};
	
	public static final int HEURISTIQUE_VOL_OISEAU = 1;
	public static final int HEURISTIQUE_TEMPS = 2;
	
	public static final String NOM_HEURISTIQUE[] = {"Vol d'oiseau", "Temps restant"};
	
	public static final double RAYON_TERRESTRE =  6372.8; // km
	public static final double VITESSE_MOYENNE =  80; // km/h
}
