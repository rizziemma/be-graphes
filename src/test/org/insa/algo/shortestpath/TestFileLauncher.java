package org.insa.algo.shortestpath;

import java.io.File;
import java.util.Arrays;

public class TestFileLauncher {

	//lecture des fichiers data
	//lancement des tests
	//écriture des résultats
	
	public static void main (String args[]) {
		//lecture de tous les file 
		File Dir  = new File(".");
		String[] files = Dir.list();
		ShortestPathAlgorithm algo;
		switch(args[0]) {
		case "B" :
		case "D" :
			algo = new DijkstraAlgorithm();
			break;
		case "A" :
			
		default : 
			System.out.println("Erreur algo pas reconnu");
		}
	}
}
