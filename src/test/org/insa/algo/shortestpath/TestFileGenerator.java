package org.insa.algo.shortestpath;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;

public class TestFileGenerator {
	
	
	public TestFileGenerator(int samples,Boolean type,String map) throws FileNotFoundException {
		
		String path = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/"+map+ ".mapgr";
		GraphReader reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(path))));
		Graph G = reader.read();
		
	}
}
