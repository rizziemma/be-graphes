package Perf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

import org.insa.algo.shortestpath.ShortestPathAlgorithm;

public class FileLauncher {

	//lecture des fichiers data
	//lancement des tests
	//écriture des résultats
	
	public static void main (String args[]) {
		//lecture de tous les file 
		File f;
		String map;
		Boolean type;
		int samples;
		int[] nodes;
		String[] nodesString;
		File Dir  = new File(".");
		String[] files = Dir.list();
		
		
		for(String fpath : files) {
			BufferedReader br = new BufferedReader(new FileReader(fpath));
			map = br.readLine();
			type = Boolean.parseBoolean(br.readLine());
			samples = Integer.parseInt(br.readLine());
			for(int i = 0; i<samples; i++) {
				nodesString = br.readLine().split(" ");
				nodes[0] = Integer.parseInt(nodesString[0]);
				nodes[1] = Integer.parseInt(nodesString[1]);
				//lancer le test
			}
		}
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
