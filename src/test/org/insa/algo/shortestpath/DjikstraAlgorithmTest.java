package org.insa.algo.shortestpath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.insa.algo.shortestpath.*;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import org.insa.graph.*;
import org.insa.graph.RoadInformation.RoadType;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;

import org.insa.algo.utils.ElementNotFoundException;
import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.ArcInspectorFactory;


public class DjikstraAlgorithmTest {

	//___________ ATTRIBUTS_______________ 

	private static ShortestPathSolution RESemptyPath, RESsingleNodePath, RESPathLength1, RESPathTime1, RESPathLength2, RESPathTime2, RESPathLength3, RESPathTime3, RESinfeasiblePath, BelRESPathLength1,  BelRESPathTime1,  BelRESPathLength2,  BelRESPathLength3, BelRESPathTime3;


	//____________CONSTRUCTOR______________
	@BeforeClass
	public static void Init() throws IOException{
		// Create a graph reader.
		String mapFractal = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/fractal.mapgr";
		String mapChile = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/chile.mapgr";
		String mapBelgium = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/belgium.mapgr";

		ShortestPathData emptyPath, singleNodePath, PathLength1, PathTime1, PathLength2, PathTime2, PathLength3, PathTime3, infeasiblePath;

		GraphReader readerFractal = new BinaryGraphReader(
				new DataInputStream(new BufferedInputStream(new FileInputStream(mapFractal))));
		GraphReader readerChile = new BinaryGraphReader(
				new DataInputStream(new BufferedInputStream(new FileInputStream(mapChile))));
		GraphReader readerBelgium = new BinaryGraphReader(
				new DataInputStream(new BufferedInputStream(new FileInputStream(mapBelgium))));

		Graph g1 = readerFractal.read();
		Graph g2 = readerChile.read();
		Graph g3 = readerBelgium.read();

		emptyPath = new ShortestPathData(g1,null,null,ArcInspectorFactory.getAllFilters().get(0));

		singleNodePath = new ShortestPathData(g1,g1.getNodes().get(531),g1.getNodes().get(531), ArcInspectorFactory.getAllFilters().get(0));
		PathLength1 = new ShortestPathData(g1,g1.getNodes().get(577631),g1.getNodes().get(883963), ArcInspectorFactory.getAllFilters().get(0));
		PathTime1 = new ShortestPathData(g1,g1.getNodes().get(577631),g1.getNodes().get(883963), ArcInspectorFactory.getAllFilters().get(2));
		PathLength2 = new ShortestPathData(g2,g2.getNodes().get(112948),g2.getNodes().get(647983), ArcInspectorFactory.getAllFilters().get(0));
		PathTime2 = new ShortestPathData(g2,g2.getNodes().get(112948),g2.getNodes().get(647983), ArcInspectorFactory.getAllFilters().get(2));
		PathLength3 = new ShortestPathData(g3,g3.getNodes().get(38048),g3.getNodes().get(679966), ArcInspectorFactory.getAllFilters().get(0));
		PathTime3 = new ShortestPathData(g3,g3.getNodes().get(38048),g3.getNodes().get(679966), ArcInspectorFactory.getAllFilters().get(2));
		infeasiblePath = new ShortestPathData(g2,g2.getNodes().get(642747),g2.getNodes().get(25215), ArcInspectorFactory.getAllFilters().get(0));

		DijkstraAlgorithm D1 = new DijkstraAlgorithm(emptyPath);
		DijkstraAlgorithm D2 = new DijkstraAlgorithm(singleNodePath);
		DijkstraAlgorithm D3 = new DijkstraAlgorithm(PathLength1);
		DijkstraAlgorithm D4 = new DijkstraAlgorithm(PathTime1);
		DijkstraAlgorithm D5 = new DijkstraAlgorithm(PathLength2);
		DijkstraAlgorithm D6 = new DijkstraAlgorithm(PathTime2);
		DijkstraAlgorithm D7 = new DijkstraAlgorithm(PathLength3);
		DijkstraAlgorithm D8 = new DijkstraAlgorithm(PathTime3);
		DijkstraAlgorithm D9 = new DijkstraAlgorithm(infeasiblePath);

		RESemptyPath = D1.doRun();
		RESsingleNodePath = D2.doRun();
		RESPathLength1 = D3.doRun();
		RESPathTime1 = D4.doRun();
		RESPathLength2 = D5.doRun();
		RESPathTime2 = D6.doRun();
		RESPathLength3 = D7.doRun();
		RESPathTime3 = D8.doRun();
		RESinfeasiblePath = D9.doRun();
		
		BellmanFordAlgorithm B3 = new BellmanFordAlgorithm(PathLength1);
		BellmanFordAlgorithm B4 = new BellmanFordAlgorithm(PathTime1);
		BellmanFordAlgorithm B5 = new BellmanFordAlgorithm(PathLength2);
		BellmanFordAlgorithm B7 = new BellmanFordAlgorithm(PathLength3);
		BellmanFordAlgorithm B8 = new BellmanFordAlgorithm(PathTime3);
		
		BelRESPathLength1 = B3.doRun();
		BelRESPathTime1 = B4.doRun();
		BelRESPathLength2 = B5.doRun() ;
		BelRESPathLength3 = B7.doRun() ;
		BelRESPathTime3 = B8.doRun() ;
		
	}


	//____________METHODES________________
	@Test
	public void testPathIsValid() {
		assertTrue(RESemptyPath.getPath().isValid());
		assertTrue(RESsingleNodePath.getPath().isValid());
		assertTrue(RESPathLength1.getPath().isValid());
		assertTrue(RESPathTime1.getPath().isValid());
		assertTrue(RESPathLength2.getPath().isValid());
		assertTrue(RESPathTime2.getPath().isValid());
		assertTrue(RESPathLength3.getPath().isValid());
		assertTrue(RESPathTime3.getPath().isValid());
		assertTrue(RESinfeasiblePath.getPath().isValid());
	}

	@Test
	public void testPathStatus() {
		assertFalse(RESemptyPath.isFeasible());
		assertTrue(RESsingleNodePath.isFeasible());
		assertTrue(RESPathLength1.isFeasible());
		assertTrue(RESPathTime1.isFeasible());
		assertTrue(RESPathLength2.isFeasible());
		assertFalse(RESPathTime2.isFeasible());
		assertTrue(RESPathLength3.isFeasible());
		assertTrue(RESPathTime3.isFeasible());
		assertFalse(RESinfeasiblePath.isFeasible());
	}
	
	@Test
	public void testPathOptimal() {
		assertEquals(RESPathLength1.getPath().getLength(),BelRESPathLength1.getPath().getLength(),1e-6);
		assertEquals(RESPathTime1.getPath().getMinimumTravelTime(),BelRESPathTime1.getPath().getMinimumTravelTime(),1e-6);
		assertEquals(RESPathLength2.getPath().getLength(),BelRESPathLength2.getPath().getLength(),1e-6);
		assertEquals(RESPathLength3.getPath().getLength(),BelRESPathLength3.getPath().getLength(),1e-2);
		assertEquals(RESPathTime3.getPath().getMinimumTravelTime(),BelRESPathTime3.getPath().getMinimumTravelTime(),1e-6);
		
	}
}


