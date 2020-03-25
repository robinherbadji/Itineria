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
public class ParcoursEnLargeur extends Algorithme {
	private HashSet<Noeud> explored;
	private Queue<Noeud> frontier;

	public ParcoursEnLargeur(Carte carte, Parametres parametres) {
		super(carte, parametres);
		explored = new HashSet<Noeud>();
		frontier = new LinkedList<Noeud>();
	}

	/**
	 * 
	 * @author Robin
	 *
	 */
	public void launch() {
		tempsDeCalcul = System.currentTimeMillis();

		if (verifierObjectif(parametres.getDepart())) {
			resultat = new Resultat(new ArrayList<Route>(), explored.size(), System.currentTimeMillis() - tempsDeCalcul,
					parametres);
			return;
		}

		arbre = new ArbreDeRecherche(new Noeud(parametres.getDepart(), null, 0, 0));
		frontier.add(arbre.getRacine());

		Noeud noeudCourant = null;
		Ville villeCourante = null;
		while (!frontier.isEmpty()) {
			noeudCourant = frontier.remove();
			explored.add(noeudCourant);
			villeCourante = noeudCourant.getVille();
			for (Route route : villeCourante.getRoutesVersVoisins()) {
				Ville villeVoisine = route.getAutreVille(villeCourante);
				if (!villeDejaExplore(villeVoisine)) {
					Noeud noeudVoisin = new Noeud(villeVoisine, noeudCourant,
							noeudCourant.getCout() + route.getDistance(), noeudCourant.getProfondeur() + 1);
					if (verifierObjectif(villeVoisine)) {
						// Succ�s de l'Algo
						resultat = new Resultat(noeudVoisin.getTrajetFromRacine(), explored.size(),
								System.currentTimeMillis() - tempsDeCalcul, parametres);
						return;
					}
					frontier.add(noeudVoisin);
				}
			}
		}

		// Echec de l'Algo
		tempsDeCalcul = System.currentTimeMillis() - tempsDeCalcul;
		resultat = new Resultat(new ArrayList<Route>(), explored.size(), tempsDeCalcul, parametres);

	}

	/***
	 * 
	 * @author Robin
	 * 
	 *         V�rifie si la ville pass�e en param�rtre a d�j� �t� explor�e
	 * @param ville
	 * @return
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
	
}