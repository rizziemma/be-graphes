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

	private static ShortestPathSolution RESemptyPath, RESsingleNodePath, RESinfeasiblePath, RESPathLength_1, RESPathLengthCars_1, RESPathTime_1, RESPathTimeCars_1, RESPathTimePedestrian_1, RESPathLength_2, RESPathLengthCars_2, RESPathTime_2, RESPathTimeCars_2, RESPathTimePedestrian_2, RESPathLength_3, RESPathLengthCars_3, RESPathTime_3, RESPathTimeCars_3, RESPathTimePedestrian_3, BelRESPathLength_1, BelRESPathLengthCars_1, BelRESPathTime_1, BelRESPathTimeCars_1, BelRESPathTimePedestrian_1, BelRESPathLength_2, BelRESPathLengthCars_2, BelRESPathTime_2, BelRESPathTimeCars_2, BelRESPathTimePedestrian_2, BelRESPathLength_3, BelRESPathLengthCars_3, BelRESPathTime_3, BelRESPathTimeCars_3, BelRESPathTimePedestrian_3;


	//____________CONSTRUCTOR______________
	@BeforeClass
	public static void Init() throws IOException{
		//CONSTRUCTION GRAPHES
		String mapFractal = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/fractal.mapgr";
		String mapChile = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/chile.mapgr";
		String mapBelgium = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/belgium.mapgr";

		ShortestPathData emptyPath, singleNodePath, infeasiblePath, PathLength_1, PathLengthCars_1, PathTime_1, PathTimeCars_1, PathTimePedestrian_1, PathLength_2, PathLengthCars_2, PathTime_2, PathTimeCars_2, PathTimePedestrian_2, PathLength_3, PathLengthCars_3, PathTime_3, PathTimeCars_3, PathTimePedestrian_3;

		GraphReader readerFractal = new BinaryGraphReader(
				new DataInputStream(new BufferedInputStream(new FileInputStream(mapFractal))));
		GraphReader readerChile = new BinaryGraphReader(
				new DataInputStream(new BufferedInputStream(new FileInputStream(mapChile))));
		GraphReader readerBelgium = new BinaryGraphReader(
				new DataInputStream(new BufferedInputStream(new FileInputStream(mapBelgium))));

		Graph g1 = readerFractal.read();
		Graph g2 = readerChile.read();
		Graph g3 = readerBelgium.read();
		
		//CONSTRUCTION DATA

		emptyPath = new ShortestPathData(g1,null,null,ArcInspectorFactory.getAllFilters().get(0));
		singleNodePath = new ShortestPathData(g1,g1.getNodes().get(531),g1.getNodes().get(531), ArcInspectorFactory.getAllFilters().get(0));
		infeasiblePath = new ShortestPathData(g2,g2.getNodes().get(642747),g2.getNodes().get(25215), ArcInspectorFactory.getAllFilters().get(0));

		PathLength_1 = new ShortestPathData(g1,g1.getNodes().get(577631),g1.getNodes().get(883963), ArcInspectorFactory.getAllFilters().get(0));
		PathLengthCars_1 = new ShortestPathData(g1,g1.getNodes().get(577631),g1.getNodes().get(883963), ArcInspectorFactory.getAllFilters().get(1));
		PathTime_1 = new ShortestPathData(g1,g1.getNodes().get(577631),g1.getNodes().get(883963), ArcInspectorFactory.getAllFilters().get(2));
		PathTimeCars_1 = new ShortestPathData(g1,g1.getNodes().get(577631),g1.getNodes().get(883963), ArcInspectorFactory.getAllFilters().get(3));
		PathTimePedestrian_1 = new ShortestPathData(g1,g1.getNodes().get(577631),g1.getNodes().get(883963), ArcInspectorFactory.getAllFilters().get(4));
		
		PathLength_2 = new ShortestPathData(g2,g2.getNodes().get(504470),g2.getNodes().get(464588), ArcInspectorFactory.getAllFilters().get(0));
		PathLengthCars_2 = new ShortestPathData(g2,g2.getNodes().get(504470),g2.getNodes().get(464588), ArcInspectorFactory.getAllFilters().get(1));
		PathTime_2 = new ShortestPathData(g2,g2.getNodes().get(504470),g2.getNodes().get(464588), ArcInspectorFactory.getAllFilters().get(2));
		PathTimeCars_2 = new ShortestPathData(g2,g2.getNodes().get(504470),g2.getNodes().get(464588), ArcInspectorFactory.getAllFilters().get(3));
		PathTimePedestrian_2 = new ShortestPathData(g2,g2.getNodes().get(504470),g2.getNodes().get(464588), ArcInspectorFactory.getAllFilters().get(4));
		
		PathLength_3 = new ShortestPathData(g3,g3.getNodes().get(38048),g3.getNodes().get(679966), ArcInspectorFactory.getAllFilters().get(0));
		PathLengthCars_3 = new ShortestPathData(g3,g3.getNodes().get(38048),g3.getNodes().get(679966), ArcInspectorFactory.getAllFilters().get(1));
		PathTime_3 = new ShortestPathData(g3,g3.getNodes().get(38048),g3.getNodes().get(679966), ArcInspectorFactory.getAllFilters().get(2));
		PathTimeCars_3 = new ShortestPathData(g3,g3.getNodes().get(38048),g3.getNodes().get(679966), ArcInspectorFactory.getAllFilters().get(3));
		PathTimePedestrian_3 = new ShortestPathData(g3,g3.getNodes().get(38048),g3.getNodes().get(679966), ArcInspectorFactory.getAllFilters().get(4));

		
		
		//CONSTRUCTION DIJKSTRA
		System.out.println("Execution Dijkstra");
		DijkstraAlgorithm D1 = new DijkstraAlgorithm(emptyPath);
		DijkstraAlgorithm D2 = new DijkstraAlgorithm(singleNodePath);
		DijkstraAlgorithm D3 = new DijkstraAlgorithm(infeasiblePath);
		
		DijkstraAlgorithm D4 = new DijkstraAlgorithm(PathLength_1);
		DijkstraAlgorithm D5 = new DijkstraAlgorithm(PathLengthCars_1);
		DijkstraAlgorithm D6 = new DijkstraAlgorithm(PathTime_1);
		DijkstraAlgorithm D7 = new DijkstraAlgorithm(PathTimeCars_1);
		DijkstraAlgorithm D8 = new DijkstraAlgorithm(PathTimePedestrian_1);
		
		DijkstraAlgorithm D9 = new DijkstraAlgorithm(PathLength_2);
		DijkstraAlgorithm D10 = new DijkstraAlgorithm(PathLengthCars_2);
		DijkstraAlgorithm D11 = new DijkstraAlgorithm(PathTime_2);
		DijkstraAlgorithm D12 = new DijkstraAlgorithm(PathTimeCars_2);
		DijkstraAlgorithm D13 = new DijkstraAlgorithm(PathTimePedestrian_2);
		
		DijkstraAlgorithm D14 = new DijkstraAlgorithm(PathLength_3);
		DijkstraAlgorithm D15 = new DijkstraAlgorithm(PathLengthCars_3);
		DijkstraAlgorithm D16 = new DijkstraAlgorithm(PathTime_3);
		DijkstraAlgorithm D17 = new DijkstraAlgorithm(PathTimeCars_3);
		DijkstraAlgorithm D18 = new DijkstraAlgorithm(PathTimePedestrian_3);


		RESemptyPath = D1.doRun();
		RESsingleNodePath = D2.doRun();
		RESinfeasiblePath = D3.doRun();

		RESPathLength_1 = D4.doRun();
		RESPathLengthCars_1 = D5.doRun();
		RESPathTime_1 = D6.doRun();
		RESPathTimeCars_1 = D7.doRun();
		RESPathTimePedestrian_1 = D8.doRun();

		RESPathLength_2 = D9.doRun();
		RESPathLengthCars_2 = D10.doRun();
		RESPathTime_2 = D11.doRun();
		RESPathTimeCars_2 = D12.doRun();
		RESPathTimePedestrian_2 = D13.doRun();
		
		RESPathLength_3 = D14.doRun();
		RESPathLengthCars_3 = D15.doRun();
		RESPathTime_3 = D16.doRun();
		RESPathTimeCars_3 = D17.doRun();
		RESPathTimePedestrian_3 = D18.doRun();

		
		
		//CONSTRUCTION BELLMAN
		System.out.println("Execution Bellman");
		BellmanFordAlgorithm B4 = new BellmanFordAlgorithm(PathLength_1);
		BellmanFordAlgorithm B5 = new BellmanFordAlgorithm(PathLengthCars_1);
		BellmanFordAlgorithm B6 = new BellmanFordAlgorithm(PathTime_1);
		BellmanFordAlgorithm B7 = new BellmanFordAlgorithm(PathTimeCars_1);
		BellmanFordAlgorithm B8 = new BellmanFordAlgorithm(PathTimePedestrian_1);
		
		BellmanFordAlgorithm B9 = new BellmanFordAlgorithm(PathLength_2);
		BellmanFordAlgorithm B10 = new BellmanFordAlgorithm(PathLengthCars_2);
		BellmanFordAlgorithm B11 = new BellmanFordAlgorithm(PathTime_2);
		BellmanFordAlgorithm B12 = new BellmanFordAlgorithm(PathTimeCars_2);
		BellmanFordAlgorithm B13 = new BellmanFordAlgorithm(PathTimePedestrian_2);
		
		BellmanFordAlgorithm B14 = new BellmanFordAlgorithm(PathLength_3);
		BellmanFordAlgorithm B15 = new BellmanFordAlgorithm(PathLengthCars_3);
		BellmanFordAlgorithm B16 = new BellmanFordAlgorithm(PathTime_3);
		BellmanFordAlgorithm B17 = new BellmanFordAlgorithm(PathTimeCars_3);
		BellmanFordAlgorithm B18 = new BellmanFordAlgorithm(PathTimePedestrian_3);
		
		

		BelRESPathLength_1 = B4.doRun();
		BelRESPathLengthCars_1 = B5.doRun();
		BelRESPathTime_1 = B6.doRun();
		BelRESPathTimeCars_1 = B7.doRun();
		BelRESPathTimePedestrian_1 = B8.doRun();

		BelRESPathLength_2 = B9.doRun();
		BelRESPathLengthCars_2 = B10.doRun();
		BelRESPathTime_2 = B11.doRun();
		BelRESPathTimeCars_2 = B12.doRun();
		BelRESPathTimePedestrian_2 = B13.doRun();
		
		BelRESPathLength_3 = B14.doRun();
		BelRESPathLengthCars_3 = B15.doRun();
		BelRESPathTime_3 = B16.doRun();
		BelRESPathTimeCars_3 = B17.doRun();
		BelRESPathTimePedestrian_3 = B18.doRun();
		
		

	}

	//____________METHODES________________
	@Test //VALIDE
	public void testPathIsValid() {
		assertTrue(RESemptyPath.getPath().isValid());
		assertTrue(RESsingleNodePath.getPath().isValid());
		assertTrue(RESinfeasiblePath.getPath().isValid());
		
		assertTrue(RESPathLength_1.getPath().isValid());
		assertTrue(RESPathLengthCars_1.getPath().isValid());
		assertTrue(RESPathTime_1.getPath().isValid());
		assertTrue(RESPathTimeCars_1.getPath().isValid());
		assertTrue(RESPathTimePedestrian_1.getPath().isValid());
		
		assertTrue(RESPathLength_2.getPath().isValid());
		assertTrue(RESPathLengthCars_2.getPath().isValid());
		assertTrue(RESPathTime_2.getPath().isValid());
		assertTrue(RESPathTimeCars_2.getPath().isValid());
		assertTrue(RESPathTimePedestrian_2.getPath().isValid());
		
		assertTrue(RESPathLength_3.getPath().isValid());
		assertTrue(RESPathLengthCars_3.getPath().isValid());
		assertTrue(RESPathTime_3.getPath().isValid());
		assertTrue(RESPathTimeCars_3.getPath().isValid());
		assertTrue(RESPathTimePedestrian_3.getPath().isValid());
		
	}

	@Test //FEASIBLE
	public void testPathStatus() {
		assertFalse(RESemptyPath.isFeasible());
		assertTrue(RESsingleNodePath.isFeasible());
		assertFalse(RESinfeasiblePath.isFeasible());

		assertTrue(RESPathLength_1.isFeasible());
		assertTrue(RESPathLengthCars_1.isFeasible());
		assertTrue(RESPathTime_1.isFeasible());
		assertTrue(RESPathTimeCars_1.isFeasible());
		assertTrue(RESPathTimePedestrian_1.isFeasible());
		
		assertTrue(RESPathLength_2.isFeasible());
		assertTrue(RESPathLengthCars_2.isFeasible());
		assertTrue(RESPathTime_2.).isFeasible());
		assertTrue(RESPathTimeCars_2.isFeasible());
		assertTrue(RESPathTimePedestrian_2.isFeasible());
		
		assertTrue(RESPathLength_3.isFeasible());
		assertTrue(RESPathLengthCars_3.isFeasible());
		assertTrue(RESPathTime_3.isFeasible());
		assertTrue(RESPathTimeCars_3.isFeasible());
		assertTrue(RESPathTimePedestrian_3.isFeasible());
	}
	
	@Test
		
		
	@Test //CHEMIN EGAL
	public void testEqualPath() {
		int i;
		assertEquals(RESPathLength_1.getPath().getArcs().size(),BelRESPathLength3.getPath().getArcs().size());
		for(i=0;i<BelRESPathLength3.getPath().getArcs().size();i++) {
			assertEquals(RESPathLength3.getPath().getArcs().get(i),BelRESPathLength3.getPath().getArcs().get(i));
		}
		
		assertEquals(RESPathLength3.getPath().getArcs().size(),BelRESPathLength3.getPath().getArcs().size());
		for(i=0;i<BelRESPathLength3.getPath().getArcs().size();i++) {
			assertEquals(RESPathLength3.getPath().getArcs().get(i),BelRESPathLength3.getPath().getArcs().get(i));
		}
		
		assertEquals(RESPathTime3.getPath().getArcs().size(),BelRESPathTime3.getPath().getArcs().size());
		for(i=0;i<BelRESPathTime3.getPath().getArcs().size();i++) {
			assertEquals(RESPathTime3.getPath().getArcs().get(i),BelRESPathTime3.getPath().getArcs().get(i));
		}
		
		
		
	}
	@Test //OPTIMAL
	public void testPathOptimal() {
		/*
		assertEquals(RESPathLength1.getPath().getLength(),BelRESPathLength1.getPath().getLength(),1e-6);
		assertEquals(RESPathTime1.getPath().getMinimumTravelTime(),BelRESPathTime1.getPath().getMinimumTravelTime(),1e-6);
		assertEquals(RESPathLength2.getPath().getLength(),BelRESPathLength2.getPath().getLength(),1e-6);
		assertEquals(RESPathTime2.getPath().getMinimumTravelTime(),BelRESPathTime2.getPath().getMinimumTravelTime(),1e-6);
		*/
		assertEquals(RESPathLength3.getPath().getLength(),BelRESPathLength3.getPath().getLength(),1e-6);		
		assertEquals(RESPathTime3.getPath().getMinimumTravelTime(),BelRESPathTime3.getPath().getMinimumTravelTime(),1e-6);
		
	}
}


