package fr.uha.ensisa.itineria.moteur.algorithmes;


import java.util.ArrayList;
import java.util.HashSet;
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
public class ParcoursEnLargeur extends Algorithme {
	


	public ParcoursEnLargeur(Carte carte, Parametres parametres) {
		super(carte, parametres);
		
	}
	
	/**
	 * 
	 * @author Robin
	 *
	 */
	public void launch()
	{
		// TODO
		
		long tempsDeCalcul = System.currentTimeMillis();
		
		LinkedList<Noeud> noeuds = new LinkedList<Noeud>();
		LinkedList<Noeud> fileAttente = new LinkedList<Noeud>();
		
		Noeud racine = new Noeud(parametres.getDepart());
		fileAttente.add(racine);
		
		
		Noeud noeudCourant = racine;
		Ville villeCourante = noeudCourant.getVille();
		//int i = 0;
		do {
			noeudCourant = fileAttente.pollFirst();
			noeuds.add(noeudCourant);
			
			
			
			villeCourante = noeudCourant.getVille();
			
			int i = 0;
			for (Route route : villeCourante.getRoutesVersVoisins()) {
				i += 1;
				System.out.println(i);
				//Ville villeVoisine = route.getAutreVille(villeCourante);
				//Noeud noeud = new Noeud(villeVoisine, noeudCourant, route.getDistance(), noeudCourant.getProfondeur()+1);
				Noeud noeudVoisin = new Noeud(route.getAutreVille(villeCourante), noeudCourant, route.getDistance(), noeudCourant.getProfondeur()+1);
				fileAttente.add(noeudVoisin);				
			}			
		}
		while (!verifierObjectif(villeCourante));
		tempsDeCalcul = System.currentTimeMillis() - tempsDeCalcul;
		
		System.out.println("Noeudkjbhkbjh :"+noeuds.size());
		resultat = new Resultat(noeudCourant.getTrajetFromRacine(), noeuds.size(), tempsDeCalcul, parametres);
			
			
			
			/*
			LinkedList<Noeud> noeuds= new LinkedList<Noeud>();
			
			Ville ville_parente = parametres.getDepart();
			Noeud racine = new Noeud(ville_parente); 
			arbre = new ArbreDeRecherche(racine);
			int profondeur = 0;
			Noeud noeudParent = racine;
			noeuds.add(noeudParent);
			
			HashSet<Ville> villesParcourues = new HashSet<Ville>();		
			//while(!verifierObjectif(ville_parente)) {
				villesParcourues.add(ville_parente);
				ArrayList<Route> routes = ville_parente.getRoutesVersVoisins();
				
				for (Route route:routes) {
					Ville villeVoisine = route.getAutreVille(ville_parente);
					if(villesParcourues.add(villeVoisine)) {
						System.out.println(route);
						//Noeud noeud = new Noeud(villeVoisine, noeudParent, route.getDistance(), profondeur+1);
						noeuds.add(new Noeud(villeVoisine, noeudParent, route.getDistance(), profondeur+1));
					}
					else {
						System.out.println("prout");
					}
				}
				
				if (noeuds.peekLast() != null)
			
			
		//}
		 
		 */
		
	}
	
	
	

}
