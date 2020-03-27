package fr.uha.ensisa.itineria.interfaces;

import java.util.Scanner;

import fr.uha.ensisa.itineria.moteur.Moteur;
import fr.uha.ensisa.itineria.util.Constantes;
/**
 * 
 * @author weber
 *
 */
public class Console extends Thread {
	
	public Console()
	{
		super("console");
	}
	
	/*
	public void run()
	{
		int departementDepart, departementArrivee, methode, heuristique=-1, profondeurLimite=0;	
		
		Moteur moteur = new Moteur();
		//Entr�e des diff�rents param�tres par l'utilisateur
		Scanner entry = new Scanner(System.in);
		do{
			System.out.println("Entrez le num�ro de d�partement de la ville de d�part");
			System.out.println(moteur.fromVillesToString());
			System.out.print("Votre choix : ");
			departementDepart = entry.nextInt();
		}while(departementDepart<1||departementDepart>95);
		do{
			System.out.println("Entrez le de num�ro de d�partement de la ville d'arriv�e");
			System.out.println(moteur.fromVillesToString());
			System.out.print("Votre choix : ");
			departementArrivee = entry.nextInt();
		}while(departementArrivee<1||departementArrivee>95);
		do{
			System.out.println("Indiquez la m�thode que vous souhaitez utiliser:");
			for(int i=0;i<Constantes.NOM_ALGO_RECHERCHE.length;i++)
				System.out.println((i+1)+") "+Constantes.NOM_ALGO_RECHERCHE[i]);
			System.out.print("Votre choix : ");
			methode = entry.nextInt();
		}while(methode<1||methode>7);
		if(methode==4)
		{
			do{
				System.out.println("Indiquez la profondeur maximum que vous souhaitez utiliser:");
				System.out.print("Votre choix : ");
				profondeurLimite= entry.nextInt();
			}while(profondeurLimite<1);
		}
		if(methode==6||methode==7)
		{
			do{
				System.out.println("Indiquez l'heuristique que vous souhaitez utiliser:");
				for(int i=0;i<Constantes.NOM_HEURISTIQUE.length;i++)
					System.out.println((i+1)+") "+Constantes.NOM_HEURISTIQUE[i]);
				System.out.print("Votre choix : ");
				heuristique= entry.nextInt();
			}while(heuristique!=1&&heuristique!=2);
		}
		entry.close();
		moteur.setParametres(departementDepart-1, departementArrivee-1, methode, heuristique, profondeurLimite);
		moteur.run();
		System.out.println(moteur.fromResultatToString());
	}
	*/
	
	public void run()
	{
		int departementDepart, departementArrivee, methode, heuristique=-1, profondeurLimite=0;	
		
		Moteur moteur = new Moteur();
		
		/*
		departementDepart = 67;
		departementArrivee = 64;
		methode = 2;
		*/
		
		departementDepart = 67;
		departementArrivee = 64;
		methode = 4;
		profondeurLimite = 95;
		//heuristique = 1;
		
		moteur.setParametres(departementDepart-1, departementArrivee-1, methode, heuristique, profondeurLimite);
		moteur.run();
		System.out.println(moteur.fromResultatToString());
	}

}
