package fr.uha.ensisa.itineria.moteur.algorithmes;

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
public class ParcoursEnProfondeur extends Algorithme {
	private LinkedList<Noeud> frontier;

	public ParcoursEnProfondeur(Carte carte, Parametres parametres) {
		super(carte, parametres);
		frontier = new LinkedList<Noeud>();
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
	}

	/**
	 * Cherche le Noeud solution de l'algorithme de Parcours en Profondeur (DFS en
	 * anglais)
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

		if (verifierObjectif(villeCourante)) {
			resultat = new Resultat(noeudCourant.getTrajetFromRacine(), nbNoeudsExplores,
					System.currentTimeMillis() - tempsDeCalcul, parametres);
			return;
		}

		for (Route route : villeCourante.getRoutesVersVoisins()) {
			Ville villeVoisine = route.getAutreVille(villeCourante);

			if (!villeDejaParcourue(villeVoisine, noeudCourant)) {
				Noeud noeudVoisin = new Noeud(villeVoisine, noeudCourant, noeudCourant.getCout() + route.getDistance(),
						noeudCourant.getProfondeur() + 1);
				frontier.add(noeudVoisin);
				parcoursEnProfondeur();
			}
		}
		frontier.removeLast();
	}

	/**
	 * Vérifie si la ville en paramètre a déjà été parcourue par un des parents du
	 * noeud
	 * 
	 * @param ville
	 * @param noeudCourant
	 * @return - true si ville a déjà été parcourue;</br>
	 *         - false sinon
	 * @author Robin
	 */
	private boolean villeDejaParcourue(Ville ville, Noeud noeudCourant) {
		for (Ville villeParcourue : noeudCourant.getVillesFromRacine()) {
			if (villeParcourue == ville) {
				return true;
			}
		}
		return false;
	}

}
