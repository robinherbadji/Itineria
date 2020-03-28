package fr.uha.ensisa.itineria.moteur.algorithmes;


import fr.uha.ensisa.itineria.donnees.ArbreDeRecherche;
import fr.uha.ensisa.itineria.donnees.Carte;
import fr.uha.ensisa.itineria.donnees.Noeud;
import fr.uha.ensisa.itineria.donnees.Parametres;
import fr.uha.ensisa.itineria.donnees.Ville;
import fr.uha.ensisa.itineria.util.Heuristique;


/**
 * 
 * @author weber
 *
 */
public class RechercheGloutonne extends Algorithme {
	


	public RechercheGloutonne(Carte carte, Parametres parametres) {
		super(carte, parametres);
		
	}
	
	public void launch()
	{
		System.out.println("Recherche gloutonne :");
		tempsDeCalcul = System.currentTimeMillis();

		arbre = new ArbreDeRecherche(new Noeud(parametres.getDepart(), null, 0, 0));
		//frontier.add(arbre.getRacine());

		Noeud noeudCourant = null;
		Ville villeCourante = null;
		
		Ville ville1 = carte.getVille(58);
		Ville ville2 = carte.getVille(63);
		
		System.out.println(ville1.getNom()+"-"+ville2.getNom()+" : "+Heuristique.getVolOiseau(ville1,ville2));
		
	}
	
	

}
