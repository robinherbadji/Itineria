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
		System.out.println("Parcours en Largeur");
		tempsDeCalcul = System.currentTimeMillis();

		if (verifierObjectif(parametres.getDepart())) {
			resultat = new Resultat(new ArrayList<Route>(), explored.size(), System.currentTimeMillis() - tempsDeCalcul,
					parametres);
			return;
		}

		arbre = new ArbreDeRecherche(new Noeud(parametres.getDepart(), null, 0, 0));
		frontier.add(arbre.getRacine());

		parcoursEnLargeur(arbre.getRacine());
	}

	/**
	 * Cherche le Noeud solution de l'algorithme de Parcours en Largeur (BFS en
	 * anglais)
	 * 
	 * @param noeudCourant
	 * @return
	 * @author Robin
	 */
	public void parcoursEnLargeur(Noeud noeudCourant) {
		while (!frontier.isEmpty()) {
			noeudCourant = frontier.remove();
			explored.add(noeudCourant);
			nbNoeudsExplores++;
			Ville villeCourante = noeudCourant.getVille();

			for (Route route : villeCourante.getRoutesVersVoisins()) {
				Ville villeVoisine = route.getAutreVille(villeCourante);

				if (!villeDejaExplore(villeVoisine)) {
					Noeud noeudVoisin = new Noeud(villeVoisine, noeudCourant,
							noeudCourant.getCout() + route.getDistance(), noeudCourant.getProfondeur() + 1);

					if (verifierObjectif(villeVoisine)) {

						// Succès de l'Algo
						resultat = new Resultat(noeudVoisin.getTrajetFromRacine(), nbNoeudsExplores,
								System.currentTimeMillis() - tempsDeCalcul, parametres);
						return;
					}
					frontier.add(noeudVoisin);
				}
			}
		}
		// Echec de l'Algo
		resultat = new Resultat(new ArrayList<Route>(), nbNoeudsExplores, System.currentTimeMillis() - tempsDeCalcul,
				parametres);
	}

	/***
	 * Vérifie si la ville passée en paramètre a déjà été explorée
	 * 
	 * @param ville
	 * @return
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

}