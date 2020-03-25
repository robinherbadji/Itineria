package fr.uha.ensisa.itineria.donnees;

import java.util.ArrayList;
/**
 * 
 * @author weber
 *
 */

public class Resultat {
	Parametres parametres;
	ArrayList<Route> trajet;
	int nbNoeudsExplores;
	long tempsDeCalcul;
	
	public Resultat(ArrayList<Route> trajet, int nbNoeudsExplores, long tempsDeCalcul, Parametres parametres)
	{
		this.trajet=trajet;
		this.nbNoeudsExplores=nbNoeudsExplores;
		this.tempsDeCalcul=tempsDeCalcul;
		this.parametres=parametres;
	}
	/**
	 * @author Robin
	 * 
	 * Renvoie la longueur en km du trajet résultat
	 * @return
	 */
	public int getDistance()
	{
		int dist = 0;
		for (Route r : trajet)
		{
			dist += r.getDistance();
		}
		return dist;
	}
	
	/**
	 * @author Robin
	 * 
	 * Renvoie la durée du trajet résultat
	 * @return
	 */
	public int getDuree()
	{
		int duree = 0;
		for (Route r : trajet)
		{
			duree += r.getDuree();
		}
		return duree;
	}
	
	
	/**
	 * @author Robin
	 * 
	 * Renvoie la profondeur du trajet résultat
	 * @return
	 */
	public int getProfondeur()
	{		
		return trajet.size();
	}
	
	/**
	 * @author Robin
	 * 
	 * Renvoie une présentation textuelle de Résultat pour un affichage dans la console par exemple
	 */
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		Ville v1 = parametres.getDepart();
		str.append("Trajet entre "+v1.getNom()+" et "+parametres.getArrivee().getNom()+":\n");		
		for (Route r : trajet)
		{
			str.append(v1.getNom()+" --> "+r.getAutreVille(v1).getNom()+"\n");
			v1 = r.getAutreVille(v1);
			
		}
		str.append("\nDistance : "+getDistance()+"km");
		str.append("\nDuree : "+getDuree()+" minutes");
		str.append("\nProfondeur de la solution : "+getProfondeur());
		str.append("\nTemps de calcul : "+tempsDeCalcul+"ms");
		str.append("\n# noeuds explores : "+nbNoeudsExplores);
		return str.toString();
	}
	
}
