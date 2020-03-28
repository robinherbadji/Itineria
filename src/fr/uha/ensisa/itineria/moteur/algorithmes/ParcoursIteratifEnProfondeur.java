package fr.uha.ensisa.itineria.moteur.algorithmes;


import java.util.ArrayList;
import java.util.HashSet;

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
public class ParcoursIteratifEnProfondeur extends Algorithme {
	private HashSet<Noeud> explored;
	private Noeud result;

	
	public ParcoursIteratifEnProfondeur(Carte carte, Parametres parametres) {
		super(carte, parametres);
		explored = new HashSet<Noeud>();
		result = null;
	}
	
	public void launch()
	{
		System.out.println("Parcours Itératif en Profondeur");
		tempsDeCalcul = System.currentTimeMillis();
		
		arbre = new ArbreDeRecherche(new Noeud(parametres.getDepart(), null, 0, 0));
		explored.add(arbre.getRacine());
		
		//DFS(arbre.getRacine());
		
	}
	
	/*
	private Noeud DFS(Noeud noeudCourant) {
		Ville villeCourante = noeudCourant.getVille();
		//explored.add(noeudCourant);
		//System.out.println("Ville Courante : "+villeCourante);
		
		
		if (verifierObjectif(villeCourante)) {			
			// Succès de l'Algo
			//explored.add(noeudCourant);
			resultat = new Resultat(noeudCourant.getTrajetFromRacine(), explored.size(),
					System.currentTimeMillis() - tempsDeCalcul, parametres);
			return noeudCourant;
		}
		
		else if (noeudCourant.getProfondeur() > parametres.getProfondeurLimite()) {
			// Echec de l'algo
			resultat = new Resultat(new ArrayList<Route>(), explored.size(),
					System.currentTimeMillis() - tempsDeCalcul, parametres);
			//cutoff_occurred = true;
			return new Noeud(null,null,-1,parametres.getProfondeurLimite());
		}
		
		else {
			cutoff_occurred = false;
			
			for (Route route : villeCourante.getRoutesVersVoisins()) {
				Ville villeVoisine = route.getAutreVille(villeCourante);
				if (!villeDejaExplore(villeVoisine)) {
					Noeud noeudVoisin = new Noeud(villeVoisine, noeudCourant,
							noeudCourant.getCout()+route.getDistance(), noeudCourant.getProfondeur()+1);
					explored.add(noeudVoisin);					
					result = DFS(noeudVoisin);
					
					//if (result == null) {
					if (result.getProfondeur() == parametres.getProfondeurLimite()) {
						cutoff_occurred = true;
					}
					else {
						return result;
					}					
				}				
			}
			if (cutoff_occurred) {
				return new Noeud(null,null,-1,parametres.getProfondeurLimite());
			}
			return null;
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
		return false;
	}
	
}
