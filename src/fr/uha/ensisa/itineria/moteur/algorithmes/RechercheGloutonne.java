package fr.uha.ensisa.itineria.moteur.algorithmes;


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
public class RechercheGloutonne extends Algorithme {
	private HashSet<Noeud> explored;
	private Queue<Noeud> frontier;

	public RechercheGloutonne(Carte carte, Parametres parametres) {
		super(carte, parametres);
		explored = new HashSet<Noeud>();
		frontier = new PriorityQueue<Noeud>(new ComparateurGlouton());		
	}
	
	/***
	 * 
	 * @author Robin
	 *
	 */
	private class ComparateurGlouton implements Comparator<Noeud> {

		@Override
		public int compare(Noeud n1, Noeud n2) {
			double h1 = Heuristique.calcul(parametres.getHeuristique(), n1.getVille(), parametres.getArrivee());
			double h2 = Heuristique.calcul(parametres.getHeuristique(), n2.getVille(), parametres.getArrivee());
			return (int)(h1 - h2);
		}
	}
	
	/***
	 * 
	 * @author Robin
	 *
	 */
	public void launch() {
		System.out.println("Recherche gloutonne :");
		tempsDeCalcul = System.currentTimeMillis();

		arbre = new ArbreDeRecherche(new Noeud(parametres.getDepart(), null, 0, 0));
		frontier.add(arbre.getRacine());
		
		rechercheGloutonne(arbre.getRacine());
	}
	
	
	/**
	 * Cherche le Noeud solution de l'algorithme de Recherche Gloutonne (Greedy Best First Search en anglais)
	 * 
	 * @param noeudCourant
	 * @return
	 * @author Robin
	 */
	public void rechercheGloutonne(Noeud noeudCourant) {
		while (!frontier.isEmpty()) {
			noeudCourant = frontier.remove();
			Ville villeCourante = noeudCourant.getVille();
			explored.add(noeudCourant);
			nbNoeudsExplores++;

			// Succ�s de l'Algo
			if (verifierObjectif(villeCourante)) {
				resultat = new Resultat(noeudCourant.getTrajetFromRacine(), nbNoeudsExplores,
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
	 * V�rifie si la ville pass�e en param�tre a d�j� �t� explor�e
	 * 
	 * @param ville
	 * @return - true si ville a d�j� �t� explor�e;</br>
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
	 * Renvoie le noeud correspondant � la ville pass�e en param�tre, s'il existe
	 * 
	 * @param ville
	 * @return le noeud correspondant � ville ou null
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
	 * Renvoie le cout pour aller � la ville voisine selon l'heuristique choisie
	 * 
	 * @param noeud
	 * @param route
	 * @return le cout en minutes ou en kilom�tres
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
