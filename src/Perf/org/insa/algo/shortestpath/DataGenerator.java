package org.insa.algo.shortestpath;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.File;
import java.io.FileOutputStream;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;
import org.insa.graph.*;
import java.util.Random;

public class DataGenerator {
	private static String map;
	private static String fpath;
	private static File f;
	private static Graph G;
	private static int type;
	private static int samples;

	public static void initAll(String map) throws IOException, FileNotFoundException {
		String path = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/"+map+ ".mapgr";
		
		//verif input
		if(type==0) {
			fpath = "./TestFile/"+ map +"_distance_" + samples + "_data.txt";
		}
		else {
			fpath = "./TestFile/"+ map +"_temps_" + samples + "_data.txt";
		}
		f = new File(fpath);
		f.createNewFile();
		GraphReader reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(path))));
		G = reader.read();
		FileOutputStream fos = new FileOutputStream(fpath);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		samples = 100;
		bw.write((map));
		bw.newLine();
		bw.write((Integer.toString(type)));
		bw.newLine();
		bw.write((Integer.toString(samples)));
		bw.newLine();
		int i;
		Random rand = new Random();
		for(i=0;i<samples*2;i++) {
			bw.write((rand.nextInt(G.getNodes().size())+ " "+rand.nextInt(G.getNodes().size())));
			bw.newLine();
		}
		bw.close();
		fos.close();
	}

	 public static void main(String[] args) throws Exception {
		 initAll("belgium");
		 if(f.exists() && ! f.isDirectory()) {
			 System.out.println("INIT OK");
		 }
	 }
}


