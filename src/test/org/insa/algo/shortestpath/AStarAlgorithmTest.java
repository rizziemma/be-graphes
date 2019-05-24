package org.insa.algo.shortestpath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.insa.algo.ArcInspectorFactory;
import org.insa.graph.Graph;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;
import org.junit.BeforeClass;
import org.junit.Test;

public class AStarAlgorithmTest {

	//___________ ATTRIBUTS_______________ 

	private static ShortestPathSolution RESemptyPath, RESsingleNodePath, RESPathLength1, RESPathTime1, RESPathLength2, RESPathTime2, RESPathLength3, RESPathTime3, RESinfeasiblePath, BelRESPathLength1,  BelRESPathTime1,  BelRESPathLength2,  BelRESPathTime2, BelRESPathLength3, BelRESPathTime3;


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
		PathLength2 = new ShortestPathData(g2,g2.getNodes().get(504470),g2.getNodes().get(464588), ArcInspectorFactory.getAllFilters().get(0));
		PathTime2 = new ShortestPathData(g2,g2.getNodes().get(504470),g2.getNodes().get(464588), ArcInspectorFactory.getAllFilters().get(2));
		PathLength3 = new ShortestPathData(g3,g3.getNodes().get(38048),g3.getNodes().get(679966), ArcInspectorFactory.getAllFilters().get(0));
		PathTime3 = new ShortestPathData(g3,g3.getNodes().get(38048),g3.getNodes().get(679966), ArcInspectorFactory.getAllFilters().get(2));
		infeasiblePath = new ShortestPathData(g2,g2.getNodes().get(642747),g2.getNodes().get(25215), ArcInspectorFactory.getAllFilters().get(0));
		
		
		AStarAlgorithm D1 = new AStarAlgorithm(emptyPath);
		AStarAlgorithm D2 = new AStarAlgorithm(singleNodePath);
		AStarAlgorithm D3 = new AStarAlgorithm(PathLength1);
		AStarAlgorithm D4 = new AStarAlgorithm(PathTime1);
		AStarAlgorithm D5 = new AStarAlgorithm(PathLength2);
		AStarAlgorithm D6 = new AStarAlgorithm(PathTime2);
		AStarAlgorithm D7 = new AStarAlgorithm(PathLength3);
		AStarAlgorithm D8 = new AStarAlgorithm(PathTime3);
		AStarAlgorithm D9 = new AStarAlgorithm(infeasiblePath);

		
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
		BellmanFordAlgorithm B6 = new BellmanFordAlgorithm(PathTime2);
		BellmanFordAlgorithm B7 = new BellmanFordAlgorithm(PathLength3);
		BellmanFordAlgorithm B8 = new BellmanFordAlgorithm(PathTime3);
		
		System.out.println("Debut Bellman 1");
		BelRESPathLength1 = B3.doRun();
		System.out.println("Debut Bellman 2");
		BelRESPathTime1 = B4.doRun();
		System.out.println("Debut Bellman 3");
		BelRESPathLength2 = B5.doRun() ;
		System.out.println("Debut Bellman 4");
		BelRESPathTime2 = B6.doRun();
		System.out.println("Debut Bellman 5");
		BelRESPathLength3 = B7.doRun() ;
		System.out.println("Debut Bellman 6");
		BelRESPathTime3 = B8.doRun() ;
		System.out.println("Debut tests");
		
	}


	//____________METHODES________________
	@Test //VALIDE
	public void testPathIsValid() {
		System.out.println("Debut validité");
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

	@Test //FEASIBLE
	public void testPathStatus() {
		System.out.println("Debut feasible");
		assertFalse(RESemptyPath.isFeasible());
		assertTrue(RESsingleNodePath.isFeasible());
		assertTrue(RESPathLength1.isFeasible());
		assertTrue(RESPathTime1.isFeasible());
		assertTrue(RESPathLength2.isFeasible());
		assertTrue(RESPathTime2.isFeasible());
		assertTrue(RESPathLength3.isFeasible());
		assertTrue(RESPathTime3.isFeasible());
		assertFalse(RESinfeasiblePath.isFeasible());
	}
	
	
	@Test //CHEMIN EGAL
	public void testEqualPath11() {
		System.out.println("Debut path");
		int i;
		for(i=0;i<BelRESPathLength1.getPath().getArcs().size();i++) {
			assertEquals(RESPathLength1.getPath().getArcs().get(i),BelRESPathLength1.getPath().getArcs().get(i));
		}
		
		
	}
	
	@Test //CHEMIN EGAL
	public void testEqualPath12() {
		int i;
		for(i=0;i<BelRESPathTime1.getPath().getArcs().size();i++) {
			assertEquals(RESPathTime1.getPath().getArcs().get(i),BelRESPathTime1.getPath().getArcs().get(i));
		}
		
	}
	
	@Test //CHEMIN EGAL
	public void testEqualPath21() {
		int i;
		for(i=0;i<BelRESPathTime2.getPath().getArcs().size();i++) {
			assertEquals(RESPathTime2.getPath().getArcs().get(i),BelRESPathTime2.getPath().getArcs().get(i));
		}
		
	}
	
	@Test //CHEMIN EGAL
	public void testEqualPath22() {
		int i;
		for(i=0;i<BelRESPathLength2.getPath().getArcs().size();i++) {
			assertEquals(RESPathLength2.getPath().getArcs().get(i),BelRESPathLength2.getPath().getArcs().get(i));
		}
		
		
	}
	
	@Test //CHEMIN EGAL
	public void testEqualPath31() {
		int i;
		for(i=0;i<BelRESPathLength3.getPath().getArcs().size();i++) {
			assertEquals(RESPathLength3.getPath().getArcs().get(i),BelRESPathLength3.getPath().getArcs().get(i));
		}
		
		
	}
	
	@Test //CHEMIN EGAL
	public void testEqualPath32() {
		int i;
		for(i=0;i<BelRESPathTime3.getPath().getArcs().size();i++) {
			assertEquals(RESPathTime3.getPath().getArcs().get(i),BelRESPathTime3.getPath().getArcs().get(i));
		}
		
	}
	
	
	@Test //OPTIMAL
	public void testPathOptimal() {
		System.out.println("Debut optimal");
		assertEquals(RESPathLength1.getPath().getLength(),BelRESPathLength1.getPath().getLength(),1e-6);
		
		
		assertEquals(RESPathTime1.getPath().getMinimumTravelTime(),BelRESPathTime1.getPath().getMinimumTravelTime(),1e-6);
		
		
		assertEquals(RESPathLength2.getPath().getLength(),BelRESPathLength2.getPath().getLength(),1e-6);
		
		
		assertEquals(RESPathTime2.getPath().getMinimumTravelTime(),BelRESPathTime2.getPath().getMinimumTravelTime(),1e-6);
		
		
		assertEquals(RESPathLength3.getPath().getLength(),BelRESPathLength3.getPath().getLength(),1e-6);	
		
		
		assertEquals(RESPathTime3.getPath().getMinimumTravelTime(),BelRESPathTime3.getPath().getMinimumTravelTime(),1e-6);
		
	}
}
