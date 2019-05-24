package org.insa.algo.shortestpath;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

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
		String map;
		int type,i;
		int samples;
		int Ori,Dest;
		String[] nodesString;
		File Dir  = new File("./TestFile");
		
		File[] files = Dir.listFiles();

		for(File f : files) {
			
			BufferedReader br = new BufferedReader(new FileReader(f.getPath()));
			map = br.readLine();
			type = Integer.parseInt(br.readLine());
			samples = Integer.parseInt(br.readLine());
			i=0;
			System.out.println("creation des fichiers de resultats");
			String fpathR1 = f.getName() + "R1";
			String fpathR2 = f.getName() + "R2";
			File fR1 = new File("./ResTest/"+fpathR1);
			File fR2 = new File("./ResTest/"+fpathR2);
			fR1.createNewFile();
			fR2.createNewFile();
			FileOutputStream fosR1 = new FileOutputStream("./ResTest/"+fpathR1);
			BufferedWriter bwR1 = new BufferedWriter(new OutputStreamWriter(fosR1));
			FileOutputStream fosR2 = new FileOutputStream("./ResTest/"+fpathR2);
			BufferedWriter bwR2 = new BufferedWriter(new OutputStreamWriter(fosR2));
			
			String pathmap = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/"+map+".mapgr";
			GraphReader reader =new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(pathmap))));
			Graph G = reader .read();
			
			while(i<samples) {
				nodesString = br.readLine().split(" ");
				Ori = Integer.parseInt(nodesString[0]);
				Dest = Integer.parseInt(nodesString[1]);
				System.out.println("Preparation du test");
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
				System.out.println("lancement du test" +" "+ i);
				long deb = System.currentTimeMillis();
				ShortestPathSolution sol = algo.doRun();
				long fin = System.currentTimeMillis();
				System.out.println("campagne de test");
				long temps = fin - deb;
				if(sol.isFeasible()) {
					//temps / nb nodes chemin (R1)
					bwR1.write(temps + " " + sol.getPath().size());
					bwR1.newLine();
					//nb node visité (entré dasn le tas) par nb nodes chemin
					bwR2.write( sol.getVisited() + " " + sol.getPath().size());
					bwR2.newLine();
					i++;
				}
				else {
					//do nothing
				}
			}
			bwR1.close();
			bwR2.close();
			fosR1.close();
			fosR2.close();
			br.close();
			System.out.println("Closed");
		}
	}
}
