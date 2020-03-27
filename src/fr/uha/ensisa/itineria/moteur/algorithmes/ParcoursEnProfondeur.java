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
	private HashSet<Noeud> explored;
	//private Queue<Noeud> frontier;
	private int limit;
	private Noeud noeudFinal;


	public ParcoursEnProfondeur(Carte carte, Parametres parametres) {
		super(carte, parametres);
		explored = new HashSet<Noeud>();
		//frontier = new LinkedList<Noeud>();
		limit = 95;
		noeudFinal = null;
	}
	
	/***
	 * 
	 * @author Robin
	 *
	 */	
	public void launch() {
		/*
		DFS-recursive(G, s):
	        mark s as visited
	        for all neighbours w of s in Graph G:
	            if w is not visited:
	                DFS-recursive(G, w)
	    */
		System.out.println("Parcours en Profondeur");
		tempsDeCalcul = System.currentTimeMillis();
		
		arbre = new ArbreDeRecherche(new Noeud(parametres.getDepart(), null, 0, 0));
		explored.add(arbre.getRacine());
		
		DFS(arbre.getRacine());
		
		/*
		while (!frontier.isEmpty()) {
			noeudCourant = frontier.remove();
			explored.add(noeudCourant);
			villeCourante = noeudCourant.getVille();
			
			for (Route route : villeCourante.getRoutesVersVoisins()) {
				Ville villeVoisine = route.getAutreVille(villeCourante);
				
				if (!villeDejaExplore(villeVoisine)) {
					Noeud noeudVoisin = new Noeud(villeVoisine, noeudCourant,
							noeudCourant.getCout() + route.getDistance(), noeudCourant.getProfondeur() + 1);
					
					
					frontier.add(noeudVoisin);
				}
			}
		}

		// Echec de l'Algo
		resultat = new Resultat(new ArrayList<Route>(), explored.size(), System.currentTimeMillis()-tempsDeCalcul, parametres);
		*/
	}
	
	private Noeud DFS(Noeud noeudCourant) {
		Ville villeCourante = noeudCourant.getVille();
		//explored.add(noeudCourant);
		System.out.println("Ville Courante : "+villeCourante);
		
		if (noeudFinal != null) {			
			// Succès de l'Algo
			System.out.println("crouroucour");
			resultat = new Resultat(noeudFinal.getTrajetFromRacine(), explored.size(),
					System.currentTimeMillis() - tempsDeCalcul, parametres);
			return noeudFinal;
		}
		
		if (verifierObjectif(villeCourante)) {			
			// Succès de l'Algo
			resultat = new Resultat(noeudCourant.getTrajetFromRacine(), explored.size(),
					System.currentTimeMillis() - tempsDeCalcul, parametres);
			return noeudCourant;
		}
		
		else if (noeudCourant.getProfondeur() > limit) {
			// Echec de l'algo
			resultat = new Resultat(new ArrayList<Route>(), explored.size(),
					System.currentTimeMillis() - tempsDeCalcul, parametres);
			return null;
		}
		
		for (Route route : villeCourante.getRoutesVersVoisins()) {
			if (noeudFinal != null) {			
				// Succès de l'Algo
				System.out.println("crouroucour");
				resultat = new Resultat(noeudFinal.getTrajetFromRacine(), explored.size(),
						System.currentTimeMillis() - tempsDeCalcul, parametres);
				return noeudFinal;
			}
			
			Ville villeVoisine = route.getAutreVille(villeCourante);
			System.out.println(villeVoisine);			
			
			
			if (verifierObjectif(villeVoisine)) {			
				// Succès de l'Algo
				System.out.println("prout");
				Noeud noeudVoisin = new Noeud(villeVoisine, noeudCourant,
						noeudCourant.getCout() + route.getDistance(), noeudCourant.getProfondeur() + 1);
				explored.add(noeudVoisin);
				resultat = new Resultat(noeudVoisin.getTrajetFromRacine(), explored.size(),
						System.currentTimeMillis() - tempsDeCalcul, parametres);
				return noeudVoisin;
			}
			
			if (verifierObjectif(villeVoisine)) {			
				// Succès de l'Algo
				System.out.println("qcaca");
				Noeud noeudVoisin = new Noeud(villeVoisine, noeudCourant,
						noeudCourant.getCout() + route.getDistance(), noeudCourant.getProfondeur() + 1);
				resultat = new Resultat(noeudVoisin.getTrajetFromRacine(), explored.size(),
						System.currentTimeMillis() - tempsDeCalcul, parametres);
				return noeudVoisin;
			}
					
			if (!villeDejaExplore(villeVoisine)) {
				
				System.out.println(villeVoisine);
				Noeud noeudVoisin = new Noeud(villeVoisine, noeudCourant,
						noeudCourant.getCout() + route.getDistance(), noeudCourant.getProfondeur() + 1);
				explored.add(noeudVoisin);
				if (verifierObjectif(villeVoisine)) {			
					// Succès de l'Algo
					resultat = new Resultat(noeudVoisin.getTrajetFromRacine(), explored.size(),
							System.currentTimeMillis() - tempsDeCalcul, parametres);
					return noeudVoisin;
				}
				
				noeudFinal = DFS(noeudVoisin);				
			}
		}		
		return null;		
	}
	
	/*
	private void DFS(Noeud noeudCourant) {
		Ville villeCourante = noeudCourant.getVille();
		//explored.add(noeudCourant);
		System.out.println("Ville Courante : "+villeCourante);
		
		if (verifierObjectif(villeCourante)) {			
			// Succès de l'Algo
			resultat = new Resultat(noeudCourant.getTrajetFromRacine(), explored.size(),
					System.currentTimeMillis() - tempsDeCalcul, parametres);
			return;
		}
		
		else if (noeudCourant.getProfondeur() > limit) {
			// Echec de l'algo
			resultat = new Resultat(new ArrayList<Route>(), explored.size(),
					System.currentTimeMillis() - tempsDeCalcul, parametres);
			return;
		}
		
		for (Route route : villeCourante.getRoutesVersVoisins()) {
			Ville villeVoisine = route.getAutreVille(villeCourante);
			System.out.println(villeVoisine);
			
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
			
			if (verifierObjectif(villeVoisine)) {			
				// Succès de l'Algo
				resultat = new Resultat(noeudVoisin.getTrajetFromRacine(), explored.size(),
						System.currentTimeMillis() - tempsDeCalcul, parametres);
				return;
			}
			
			if (!villeDejaExplore(villeVoisine)) {
				
				System.out.println(villeVoisine);
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
				
	}
	*/
	
	
	/***
	 * 
	 * @author Robin
	 * 
	 *         Vérifie si la ville passée en paramètre a déjà été explorée
	 * @param ville
	 * @return
	 */
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
