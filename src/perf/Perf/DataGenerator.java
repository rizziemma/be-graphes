package Perf;

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
	private static boolean type;
	private static int samples;

	public static void initAll() throws IOException, FileNotFoundException {
		map = "fractal";
		//String path = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/"+map+ ".mapgr";
		if(type) {
			fpath = "./TestFile/"+ map +"_distance_" + samples + "_data.txt";
		}
		else {
			fpath = "./TestFile/"+ map +"_temps_" + samples + "_data.txt";
		}
		f = new File(fpath);
		f.createNewFile();
		//GraphReader reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(fpath))));
		//G = reader.read();
		FileOutputStream fos = new FileOutputStream(fpath);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		samples = 100;
		bw.write((map));
		bw.newLine();
		bw.write((Boolean.toString(type)));
		bw.newLine();
		bw.write((Integer.toString(samples)));
		bw.newLine();
		int i;
		Random rand = new Random();
		for(i=0;i<samples;i++) {
			bw.write((rand.nextInt(20000)+ " "+rand.nextInt(20000)));
			bw.newLine();
		}
		bw.close();
		fos.close();
	}

	 public static void main(String[] args) throws Exception {
		 initAll();
		 if(f.exists() && ! f.isDirectory()) {
			 System.out.println("INIT OK");
		 }
	 }
}


