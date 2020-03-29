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


/**
 * 
 * @author weber
 *
 */
public class ParcoursCoutUniforme extends Algorithme {
	private HashSet<Noeud> explored;
	private Queue<Noeud> frontier;

	public ParcoursCoutUniforme(Carte carte, Parametres parametres) {
		super(carte, parametres);
		explored = new HashSet<Noeud>();
		frontier = new PriorityQueue<Noeud>(new ComparateurDeCout());
	}

	/***
	 * 
	 * @author Robin
	 *
	 */
	private class ComparateurDeCout implements Comparator<Noeud> {

		@Override
		public int compare(Noeud n1, Noeud n2) {
			return (int) (n1.getCout() - n2.getCout());
		}
	}
	

	/***
	 * 
	 * @author Robin
	 *
	 */
	public void launch() {
		System.out.println("Parcours en Coût Uniforme");
		tempsDeCalcul = System.currentTimeMillis();

		arbre = new ArbreDeRecherche(new Noeud(parametres.getDepart(), null, 0, 0));
		frontier.add(arbre.getRacine());
		
		parcoursCoutUniforme(arbre.getRacine());
	}
	
	
	/**
	 * Cherche le Noeud solution de l'algorithme Cout Uniforme (UCS en anglais)
	 * 
	 * @param noeudCourant
	 * @return
	 * @author Robin
	 */
	public void parcoursCoutUniforme(Noeud noeudCourant) {
		while (!frontier.isEmpty()) {
			noeudCourant = frontier.remove();
			Ville villeCourante = noeudCourant.getVille();

			// Succès de l'Algo
			if (verifierObjectif(villeCourante)) {
				resultat = new Resultat(noeudCourant.getTrajetFromRacine(), explored.size(),
						System.currentTimeMillis() - tempsDeCalcul, parametres);
				return;
			}

			explored.add(noeudCourant);

			for (Route route : villeCourante.getRoutesVersVoisins()) {
				Ville villeVoisine = route.getAutreVille(villeCourante);
				if (!villeDejaExplore(villeVoisine)) {
					Noeud noeudVoisin = new Noeud(villeVoisine, noeudCourant,
							noeudCourant.getCout() + route.getDistance(), noeudCourant.getProfondeur() + 1);
					frontier.add(noeudVoisin);
				}

				else {
					Noeud noeudVoisin = new Noeud(villeVoisine, noeudCourant,
							noeudCourant.getCout() + route.getDistance(), noeudCourant.getProfondeur() + 1);
					Noeud noeudAncien = noeudDansFontiere(villeVoisine);
					if (noeudAncien != null && noeudAncien.getCout() > noeudVoisin.getCout()) {
						frontier.remove(noeudAncien);
						frontier.add(noeudVoisin);
					}
				}
			}
		}
		// Echec de l'Algo
		resultat = new Resultat(new ArrayList<Route>(), explored.size(), System.currentTimeMillis() - tempsDeCalcul,
				parametres);
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

}
