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
		// TODO
		// QU'EST CE QUE LA PROFONDEUR ???
		//int profondeur = trajet.get(trajet.size()-1).getV2();
		int profondeur = 0;
		return profondeur;
	}
	
	/**
	 * @author Robin
	 * 
	 * Renvoie une présentation textuelle de Résultat pour un affichage dans la console par exemple
	 */
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		str.append("Trajet entre "+parametres.getDepart().getNom()+" et "+parametres.getArrivee().getNom()+":x\n");
		for (Route r : trajet)
		{
			str.append(r.getV1().getNom()+" --> "+r.getV2().getNom()+"\n");
		}
		str.append("\nDistance : "+getDistance()+"km");
		str.append("\nDuree : "+getDuree()+" minutes");
		str.append("\nProfondeur de la solution : "+getProfondeur());
		str.append("\nTemps de calcul : "+tempsDeCalcul+"ms");
		str.append("\n# noeuds explores : "+nbNoeudsExplores);
		return str.toString();
	}
	
}
