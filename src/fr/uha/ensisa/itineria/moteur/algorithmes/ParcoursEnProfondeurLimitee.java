package fr.uha.ensisa.itineria.moteur.algorithmes;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import fr.uha.ensisa.itineria.donnees.ArbreDeRecherche;
import fr.uha.ensisa.itineria.donnees.Carte;
import fr.uha.ensisa.itineria.donnees.Noeud;
import fr.uha.ensisa.itineria.donnees.Parametres;
import fr.uha.ensisa.itineria.donnees.Resultat;
import fr.uha.ensisa.itineria.donnees.Route;
import fr.uha.ensisa.itineria.donnees.Ville;



/**
 * 
 * @author weber
 *
 */
public class ParcoursEnProfondeurLimitee extends Algorithme {
	private LinkedList<Noeud> frontier;
	
	public ParcoursEnProfondeurLimitee(Carte carte, Parametres parametres) {
		super(carte, parametres);
		frontier = new LinkedList<Noeud>();
	}
	
	/***
	 * 
	 * @author Robin
	 *
	 */	
	public void launch() {
		System.out.println("Parcours en Profondeur Limitée");
		tempsDeCalcul = System.currentTimeMillis();
		
		arbre = new ArbreDeRecherche(new Noeud(parametres.getDepart(), null, 0, 0));
		frontier.add(arbre.getRacine());
		
		parcoursEnProfondeurLimitee();
	}
	
	/**
	 * Cherche le Noeud solution de l'algorithme de Parcours en Profondeur Limitée (DLS en anglais)
	 * 
	 * @return
	 * @author Robin
	 */
	public void parcoursEnProfondeurLimitee(/* Noeud noeudCourant */) {
		if (resultat != null) {
			return;
		}
		Noeud noeudCourant = frontier.peekLast();
		nbNoeudsExplores++;
		Ville villeCourante = noeudCourant.getVille();
		
		if (noeudCourant.getProfondeur() >= parametres.getProfondeurLimite()) {
			return;
		}

		for (Route route : villeCourante.getRoutesVersVoisins()) {
			Ville villeVoisine = route.getAutreVille(villeCourante);

			if (!villeDejaParcourue(villeVoisine, noeudCourant)) {
				Noeud noeudVoisin = new Noeud(villeVoisine, noeudCourant, noeudCourant.getCout() + route.getDistance(),
						noeudCourant.getProfondeur() + 1);

				if (verifierObjectif(villeVoisine)) {
					resultat = new Resultat(noeudVoisin.getTrajetFromRacine(), nbNoeudsExplores,
							System.currentTimeMillis() - tempsDeCalcul, parametres);
					return;
				}
				frontier.add(noeudVoisin);
				parcoursEnProfondeurLimitee();
			}
		}
		frontier.removeLast();
	}
	
	
	/**
	 * Vérifie si la ville en paramètre a déjà été parcourue par un des parents du noeud
	 * 
	 * @param ville
	 * @param noeudCourant
	 * @return - true si ville a déjà été parcourue;</br>
	 *         - false sinon
	 * @author Robin
	 */
	private boolean villeDejaParcourue(Ville ville, Noeud noeudCourant) {		
		for(Ville villeParcourue : noeudCourant.getVillesFromRacine()) {
			if(villeParcourue == ville) {
				return true;
			}				
		}
		return false;
	}
	
	
}
