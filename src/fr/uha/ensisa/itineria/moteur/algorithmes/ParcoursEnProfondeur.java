package fr.uha.ensisa.itineria.moteur.algorithmes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

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
public class ParcoursEnProfondeur extends Algorithme {
	private LinkedList<Noeud> frontier;
	private HashSet<Noeud> explored;

	public ParcoursEnProfondeur(Carte carte, Parametres parametres) {
		super(carte, parametres);
		frontier = new LinkedList<Noeud>();
		explored = new HashSet<Noeud>();
	}
	
	/***
	 * 
	 * @author Robin
	 *
	 */	
	public void launch() {
		System.out.println("Parcours en Profondeur");
		tempsDeCalcul = System.currentTimeMillis();
		
		arbre = new ArbreDeRecherche(new Noeud(parametres.getDepart(), null, 0, 0));
		frontier.add(arbre.getRacine());
		
		parcoursEnProfondeur();
		//DFS(arbre.getRacine());
	}
	
	/**
	 * Cherche le Noeud solution de l'algorithme de Parcours en Profondeur (DFS en anglais)
	 * 
	 * @return
	 * @author Robin
	 */
	public void parcoursEnProfondeur(/* Noeud noeudCourant */) {
		if (resultat != null) {
			return;
		}

		Noeud noeudCourant = frontier.peekLast();
		nbNoeudsExplores++;
		Ville villeCourante = noeudCourant.getVille();

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
				parcoursEnProfondeur();
			}
		}
		frontier.removeLast();
	}
	
	
	private void DFS(Noeud noeudCourant) {
		Ville villeCourante = noeudCourant.getVille();
		//explored.add(noeudCourant);
		System.out.println("Ville Courante : "+villeCourante);
		
		if (resultat != null) {			
			// Succès de l'Algo
			System.out.println("Succes");			
			return;
		}

		if (verifierObjectif(villeCourante)) {			
			// Succès de l'Algo
			resultat = new Resultat(noeudCourant.getTrajetFromRacine(), explored.size(),
					System.currentTimeMillis() - tempsDeCalcul, parametres);
			return;
		}

		for (Route route : villeCourante.getRoutesVersVoisins()) {
			if (resultat != null) {			
				// Succès de l'Algo
				System.out.println("crouroucour");				
				return;
			}

			Ville villeVoisine = route.getAutreVille(villeCourante);

			if (verifierObjectif(villeVoisine)) {			
				// Succès de l'Algo
				System.out.println("prout");
				Noeud noeudVoisin = new Noeud(villeVoisine, noeudCourant,
						noeudCourant.getCout() + route.getDistance(), noeudCourant.getProfondeur() + 1);
				explored.add(noeudVoisin);
				resultat = new Resultat(noeudVoisin.getTrajetFromRacine(), explored.size(),
						System.currentTimeMillis() - tempsDeCalcul, parametres);
				return;
			}

			if (!villeDejaExplore(villeVoisine)) {
				Noeud noeudVoisin = new Noeud(villeVoisine, noeudCourant,
						noeudCourant.getCout() + route.getDistance(), noeudCourant.getProfondeur() + 1);
				explored.add(noeudVoisin);
				if (verifierObjectif(villeVoisine)) {			
					// Succès de l'Algo
					resultat = new Resultat(noeudVoisin.getTrajetFromRacine(), explored.size(),
							System.currentTimeMillis() - tempsDeCalcul, parametres);
					return;
				}
				DFS(noeudVoisin);				
			}
		}		
		return;		
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
	
	
	private boolean villeDejaExplore(Ville ville) {
		for (Noeud noeud : explored) {
			if (noeud.getVille() == ville)
				return true;
		}
		/*
		for (Noeud noeud : frontier) {
			if (noeud.getVille() == ville)
				return true;
		}
		*/
		return false;
	}

}
