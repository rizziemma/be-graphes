package perf;

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

	public static void initAll(String map,int type) throws IOException, FileNotFoundException {
		samples = 100;
		String ProjectDirectory = "D:/Projets/";
		String mapDirectory = ProjectDirectory + "Maps/";
		String path = mapDirectory +map+ ".mapgr";
		//verif input
		if(type==0) {
			fpath =  ProjectDirectory + "TestMap/" +  map +"_distance_" + samples + "_data.txt";
		}
		else {
			fpath = ProjectDirectory + "TestMap/" +  map +"_temps_" + samples + "_data.txt";
		}
		System.out.println(fpath);
		f = new File(fpath);
		f.createNewFile();
		GraphReader reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(path))));
		G = reader.read();
		FileOutputStream fos = new FileOutputStream(fpath);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
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
		 initAll("belgium",0);
		 initAll("reunion",0);
		 initAll("carre-dense",0);
		 initAll("haute-garonne",0);
		 initAll("belgium",1);
		 initAll("reunion",1);
		 initAll("carre-dense",1);
		 initAll("haute-garonne",1);
	 }
}


