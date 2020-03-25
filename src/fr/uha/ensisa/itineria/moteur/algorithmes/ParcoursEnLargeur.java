package fr.uha.ensisa.itineria.moteur.algorithmes;


import java.util.ArrayList;

import fr.uha.ensisa.itineria.donnees.ArbreDeRecherche;
import fr.uha.ensisa.itineria.donnees.Carte;
import fr.uha.ensisa.itineria.donnees.Noeud;
import fr.uha.ensisa.itineria.donnees.Parametres;
import fr.uha.ensisa.itineria.donnees.Route;
import fr.uha.ensisa.itineria.donnees.Ville;


/**
 * 
 * @author weber
 *
 */
public class ParcoursEnLargeur extends Algorithme {
	


	public ParcoursEnLargeur(Carte carte, Parametres parametres) {
		super(carte, parametres);
		
	}
	
	/**
	 * 
	 * @author Robin
	 *
	 */
	public void launch()
	{
		// TODO
		
		
		Ville ville = parametres.getDepart();
		Noeud racine = new Noeud(ville); 
		arbre = new ArbreDeRecherche(racine);
		
		while(!verifierObjectif(ville)) {
			ArrayList<Route> routes = ville.getRoutesVersVoisins();
			// ..
		}
		
	}
	
	

}
