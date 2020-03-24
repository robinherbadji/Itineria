package fr.uha.ensisa.itineria.donnees;
/**
 * 
 * @author weber
 *
 */
public class Route {

	private Ville v1;
	private Ville v2;
	
	private int distance;
	private int duree;
	
	public Route(Ville v1, Ville v2, int distance, int duree)
	{
		this.v1 = v1;
		this.v2 = v2;
		this.distance = distance;
		this.duree = duree;
		v1.addRoutes(this);
		v2.addRoutes(this);
	}

	public Ville getV1() {
		return v1;
	}

	public Ville getV2() {
		return v2;
	}

	public int getDistance() {
		return distance;
	}

	public int getDuree() {
		return duree;
	}
	
	/**
	 * Renvoie la ville � l'autre extr�mit� de la route par rapport � la ville donn�e en argument
	 * @param v
	 * @return
	 */
	public Ville getAutreVille(Ville v)
	{
		//À compléter
		return null;
	}
	
}
