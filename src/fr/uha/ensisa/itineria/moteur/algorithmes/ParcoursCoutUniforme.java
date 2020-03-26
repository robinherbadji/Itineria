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
			if (n1.getCout() < n2.getCout()) {
				return -1;
			}
			else if (n1.getCout() > n2.getCout()) {
				return 1;
			}
			else {
				return 0;
			}
		}		
	}
	
	public void launch()
	{
		tempsDeCalcul = System.currentTimeMillis();
		
		arbre = new ArbreDeRecherche(new Noeud(parametres.getDepart(), null, 0, 0));
		frontier.add(arbre.getRacine());

		Noeud noeudCourant = null;
		Ville villeCourante = null;
		
		while (!frontier.isEmpty()) {
			noeudCourant = frontier.remove();
			villeCourante = noeudCourant.getVille();
			
			// Succès de l'Algo
			if (verifierObjectif(villeCourante)) {
				resultat = new Resultat(noeudCourant.getTrajetFromRacine(), explored.size(), System.currentTimeMillis() - tempsDeCalcul,
						parametres);
				return;
			}
			
			explored.add(noeudCourant);
			
			System.out.println("Ville Courante : "+villeCourante);
			
			for (Route route : villeCourante.getRoutesVersVoisins()) {
				Ville villeVoisine = route.getAutreVille(villeCourante);				
				System.out.println(villeVoisine);
				if (!villeDejaExplore(villeVoisine)) {
					Noeud noeudVoisin = new Noeud(villeVoisine, noeudCourant,
							noeudCourant.getCout() + route.getDistance(), noeudCourant.getProfondeur() + 1);
					/*
					// Succès de l'Algo
					if (verifierObjectif(parametres.getDepart())) {
						resultat = new Resultat(noeudVoisin.getTrajetFromRacine(), explored.size(), System.currentTimeMillis() - tempsDeCalcul,
								parametres);
						return;
					}
					*/
					frontier.add(noeudVoisin);
				}
				
				else {
					Noeud noeudAncien = noeudDansFontiere(villeVoisine);
					if (noeudAncien != null && noeudAncien.getCout() > noeudCourant.getCout()+route.getDistance()) {
						frontier.remove(noeudAncien);
						noeudAncien.setCout(noeudCourant.getCout()+route.getDistance());
						frontier.add(noeudAncien);
					}
				}
					
			}
		}

		// Echec de l'Algo
		resultat = new Resultat(new ArrayList<Route>(), explored.size(), System.currentTimeMillis()-tempsDeCalcul, parametres);
		
	}
	
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
		for (Noeud noeud : frontier) {
			if (noeud.getVille() == ville)
				return true;
		}
		return false;
	}
	
	private Noeud noeudDansFontiere(Ville ville) {
		for (Noeud noeud : frontier) {
			if (noeud.getVille() == ville)
				return noeud;
		}
		return null;
	}

}
