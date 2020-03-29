package fr.uha.ensisa.itineria.moteur.algorithmes;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

import fr.uha.ensisa.itineria.donnees.ArbreDeRecherche;
import fr.uha.ensisa.itineria.donnees.Carte;
import fr.uha.ensisa.itineria.donnees.Noeud;
import fr.uha.ensisa.itineria.donnees.Parametres;
import fr.uha.ensisa.itineria.donnees.Resultat;
import fr.uha.ensisa.itineria.donnees.Route;
import fr.uha.ensisa.itineria.donnees.Ville;
import fr.uha.ensisa.itineria.util.Constantes;
import fr.uha.ensisa.itineria.util.Heuristique;


/**
 * 
 * @author weber
 *
 */
public class RechercheAStar extends Algorithme {
	private HashSet<Noeud> explored;
	private Queue<Noeud> frontier;

	public RechercheAStar(Carte carte, Parametres parametres) {
		super(carte, parametres);
		explored = new HashSet<Noeud>();
		frontier = new PriorityQueue<Noeud>(new ComparateurAStar());		
	}
	
	/***
	 * 
	 * @author Robin
	 *
	 */
	private class ComparateurAStar implements Comparator<Noeud> {

		@Override
		public int compare(Noeud n1, Noeud n2) {
			double h1 = Heuristique.calcul(parametres.getHeuristique(), n1.getVille(), parametres.getArrivee());
			double h2 = Heuristique.calcul(parametres.getHeuristique(), n2.getVille(), parametres.getArrivee());
			double g1 = n1.getCout();
			double g2 = n2.getCout();
			if (parametres.getHeuristique() == Constantes.HEURISTIQUE_TEMPS) {
				g1 = getDuree(n1.getTrajetFromRacine());
				g2 = getDuree(n2.getTrajetFromRacine());
			}			
			return (int) ((h1+g1) - (h2+g2));
		}
	}
	
	/***
	 * 
	 * @author Robin
	 *
	 */
	public void launch() {
		System.out.println("Recherche A* :");
		tempsDeCalcul = System.currentTimeMillis();

		arbre = new ArbreDeRecherche(new Noeud(parametres.getDepart(), null, 0, 0));
		frontier.add(arbre.getRacine());

		Noeud noeudCourant = arbre.getRacine();
		
		rechercheAStar(noeudCourant);
		
	}
	
	
	/**
	 * Cherche le Noeud solution de l'algorithme de Recherche A* (A Star Search en anglais)
	 * 
	 * @param noeudCourant
	 * @return
	 * @author Robin
	 */
	public void rechercheAStar(Noeud noeudCourant) {
		while (!frontier.isEmpty()) {
			noeudCourant = frontier.remove();
			Ville villeCourante = noeudCourant.getVille();
			explored.add(noeudCourant);

			// Succès de l'Algo
			if (verifierObjectif(villeCourante)) {
				resultat = new Resultat(noeudCourant.getTrajetFromRacine(), explored.size(),
						System.currentTimeMillis() - tempsDeCalcul, parametres);
				return;
			}			

			for (Route route : villeCourante.getRoutesVersVoisins()) {
				Ville villeVoisine = route.getAutreVille(villeCourante);
				if (!villeDejaExplore(villeVoisine)) {
					Noeud noeudVoisin = new Noeud(villeVoisine, noeudCourant,
							calculCout(noeudCourant, route), noeudCourant.getProfondeur() + 1);
					frontier.add(noeudVoisin);
				}

				else {
					Noeud noeudVoisin = new Noeud(villeVoisine, noeudCourant,
							calculCout(noeudCourant, route), noeudCourant.getProfondeur() + 1);
					Noeud noeudAncien = noeudDansFontiere(villeVoisine);
					if (noeudAncien != null && noeudAncien.getCout() > noeudVoisin.getCout()) {
						frontier.remove(noeudAncien);
						frontier.add(noeudVoisin);
					}
				}
			}
		}
	}
	
	/***
	 * Vérifie si la ville passée en paramètre a déjà été explorée
	 * 
	 * @param ville
	 * @return - true si ville a déjà été explorée;</br>
	 *         - false sinon
	 * @author Robin
	 */
	private boolean villeDejaExplore(Ville ville) {
		for (Noeud noeud : explored) {
			if (noeud.getVille() == ville)
				return true;
		}
		for (Noeud noeud : frontier) {
			if (noeud.getVille() == ville)
				return true;
		}
		return false;
	}

	/***
	 * Renvoie le noeud correspondant à la ville passée en paramètre, s'il existe
	 * 
	 * @param ville
	 * @return le noeud correspondant à ville ou null
	 * 
	 * @author Robin
	 */
	private Noeud noeudDansFontiere(Ville ville) {
		for (Noeud noeud : frontier) {
			if (noeud.getVille() == ville)
				return noeud;
		}
		return null;
	}
	
	/**
	 * Renvoie la duree en minutes depuis le depart 
	 * 
	 * @param trajet
	 * @return la duree en minutes depuis le départ
	 * @author Robin
	 */
	private int getDuree(ArrayList<Route> trajet) {
		int duree = 0;
		for(Route r: trajet) {
			duree+=r.getDuree();
		}
		return duree;
	}
	
	
	/**
	 * Renvoie le cout pour aller à la ville voisine selon l'heuristique choisie
	 * 
	 * @param noeud
	 * @param route
	 * @return le cout en minutes ou en kilomètres
	 * @author Robin
	 */
	private double calculCout(Noeud noeud, Route route) {
		double coutRoute;
		if (parametres.getHeuristique() == Constantes.HEURISTIQUE_TEMPS) {
			coutRoute = route.getDuree();
		}
		else {
			coutRoute = route.getDistance();
		}
		return noeud.getCout() + coutRoute;
	}

}
