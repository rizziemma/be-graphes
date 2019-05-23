package org.insa.algo.shortestpath;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.insa.algo.ArcInspectorFactory;
import org.insa.algo.shortestpath.ShortestPathAlgorithm;
import org.insa.graph.Graph;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;

public class FileLauncher {

	//lecture des fichiers data
	//lancement des tests
	//écriture des résultats

	public static void main (String args[]) throws IOException, FileNotFoundException  {
		//lecture de tous les file 
		File f;
		String map;
		int type;
		int samples;
		int Ori,Dest;
		String[] nodesString;
		File Dir  = new File(".");
		String[] files = Dir.list();


		for(String fpath : files) {
			BufferedReader br = new BufferedReader(new FileReader(fpath));
			map = br.readLine();
			type = Integer.parseInt(br.readLine());
			samples = Integer.parseInt(br.readLine());
			for(int i = 0; i<samples; i++) {
				nodesString = br.readLine().split(" ");
				Ori = Integer.parseInt(nodesString[0]);
				Dest = Integer.parseInt(nodesString[1]);
				//Preparation du test
				String pathmap = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/"+map+".mapgr";
				GraphReader reader =new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(pathmap))));
				Graph G = reader .read();
				ShortestPathData data = new ShortestPathData(G,G.getNodes().get(Ori),G.getNodes().get(Dest),ArcInspectorFactory.getAllFilters().get(type*2));
				ShortestPathAlgorithm algo;
				switch(args[0]) {
				case "B" :
					algo = new BellmanFordAlgorithm(data);
					break;
				case "D" :
					algo = new DijkstraAlgorithm(data);
					break;
				case "A" :
					algo = new AStarAlgorithm(data);
					break;
				default : 
					System.out.println("Erreur algo pas reconnu: use is A of A* B for BellmanFord or D for Djikstra");
					throw new IOException();
				}
				//lancement du test
				//recup du temps
				algo.doRun();
				//recup du temps
				//campagne de test
			}
			br.close();
		}

	}
}
