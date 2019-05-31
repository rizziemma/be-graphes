package org.insa.algo.shortestpath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;


import org.junit.BeforeClass;
import org.junit.Test;

import org.insa.graph.*;

import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;


import org.insa.algo.ArcInspectorFactory;


public class ShortestPathAlgorithmTest {

	//___________ ATTRIBUTS_______________ 

	private static ShortestPathSolution RESemptyPathD, RESsingleNodePathD, RESinfeasiblePathD, RESemptyPathA, RESsingleNodePathA, RESinfeasiblePathA;
	private static ShortestPathSolution ResD[] = new ShortestPathSolution[18];
	private static ShortestPathSolution ResA[]= new ShortestPathSolution[18];
	private static ShortestPathSolution ResB[]= new ShortestPathSolution[18];
	private static DijkstraAlgorithm D[] = new DijkstraAlgorithm[18];
	private static AStarAlgorithm A[] = new AStarAlgorithm[18];
	private static BellmanFordAlgorithm B[] = new BellmanFordAlgorithm[18];
	
	//____________CONSTRUCTOR______________
	@BeforeClass
	public static void Init() throws IOException{
		//CONSTRUCTION GRAPHES
		//String mapDirectory = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/";
		String mapDirectory = "D:/Projets/Maps/";
		String mapCarreDense = mapDirectory + "carre-dense.mapgr";
		String mapChile =  mapDirectory + "chile.mapgr";
		String mapBelgium =  mapDirectory + "belgium.mapgr";
		String mapHG =  mapDirectory + "haute-garonne.mapgr";
		
		ShortestPathData emptyPath, singleNodePath, infeasiblePath, PathLength_1, PathLengthCars_1, PathTime_1, PathTimeCars_1, PathTimePedestrian_1, PathLength_2, PathLengthCars_2, PathTime_2, PathTimeCars_2, PathTimePedestrian_2, PathLength_3, PathLengthCars_3, PathTime_3, PathTimeCars_3, PathTimePedestrian_3;

		GraphReader readerCarreDense = new BinaryGraphReader(
				new DataInputStream(new BufferedInputStream(new FileInputStream(mapCarreDense))));
		GraphReader readerChile = new BinaryGraphReader(
				new DataInputStream(new BufferedInputStream(new FileInputStream(mapChile))));
		GraphReader readerBelgium = new BinaryGraphReader(
				new DataInputStream(new BufferedInputStream(new FileInputStream(mapBelgium))));
		GraphReader readerHG = new BinaryGraphReader(
				new DataInputStream(new BufferedInputStream(new FileInputStream(mapHG))));
		Graph g1 = readerCarreDense.read();
		Graph g2 = readerHG.read();
		Graph g3 = readerBelgium.read();
		Graph g4 = readerChile.read();
		//CONSTRUCTION DATA

		emptyPath = new ShortestPathData(g1,null,null,ArcInspectorFactory.getAllFilters().get(0));
		singleNodePath = new ShortestPathData(g1,g1.getNodes().get(531),g1.getNodes().get(531), ArcInspectorFactory.getAllFilters().get(0));
		infeasiblePath = new ShortestPathData(g4,g4.getNodes().get(642747),g4.getNodes().get(25215), ArcInspectorFactory.getAllFilters().get(0));

		PathLength_1 = new ShortestPathData(g1,g1.getNodes().get(12000),g1.getNodes().get(120000), ArcInspectorFactory.getAllFilters().get(0));
		PathLengthCars_1 = new ShortestPathData(g1,g1.getNodes().get(12000),g1.getNodes().get(120000), ArcInspectorFactory.getAllFilters().get(1));
		PathTime_1 = new ShortestPathData(g1,g1.getNodes().get(12000),g1.getNodes().get(120000), ArcInspectorFactory.getAllFilters().get(2));
		PathTimeCars_1 = new ShortestPathData(g1,g1.getNodes().get(12000),g1.getNodes().get(120000), ArcInspectorFactory.getAllFilters().get(3));
		PathTimePedestrian_1 = new ShortestPathData(g1,g1.getNodes().get(12000),g1.getNodes().get(120000), ArcInspectorFactory.getAllFilters().get(4));

		PathLength_2 = new ShortestPathData(g2,g2.getNodes().get(58507),g2.getNodes().get(97598), ArcInspectorFactory.getAllFilters().get(0));
		PathLengthCars_2 = new ShortestPathData(g2,g2.getNodes().get(58507),g2.getNodes().get(97598), ArcInspectorFactory.getAllFilters().get(1));
		PathTime_2 = new ShortestPathData(g2,g2.getNodes().get(58507),g2.getNodes().get(97598), ArcInspectorFactory.getAllFilters().get(2));
		PathTimeCars_2 = new ShortestPathData(g2,g2.getNodes().get(58507),g2.getNodes().get(97598), ArcInspectorFactory.getAllFilters().get(3));
		PathTimePedestrian_2 = new ShortestPathData(g2,g2.getNodes().get(58507),g2.getNodes().get(97598), ArcInspectorFactory.getAllFilters().get(4));

		PathLength_3 = new ShortestPathData(g3,g3.getNodes().get(57024),g3.getNodes().get(549966), ArcInspectorFactory.getAllFilters().get(0));
		PathLengthCars_3 = new ShortestPathData(g3,g3.getNodes().get(57024),g3.getNodes().get(549966), ArcInspectorFactory.getAllFilters().get(1));
		PathTime_3 = new ShortestPathData(g3,g3.getNodes().get(57024),g3.getNodes().get(549966), ArcInspectorFactory.getAllFilters().get(2));
		PathTimeCars_3 = new ShortestPathData(g3,g3.getNodes().get(57024),g3.getNodes().get(549966), ArcInspectorFactory.getAllFilters().get(3));
		PathTimePedestrian_3 = new ShortestPathData(g3,g3.getNodes().get(57024),g3.getNodes().get(549966), ArcInspectorFactory.getAllFilters().get(4));



		//CONSTRUCTION DIJKSTRA
		System.out.println("Execution Djikstra");

		D[0] = new DijkstraAlgorithm(emptyPath);
		D[1] = new DijkstraAlgorithm(singleNodePath);
		D[2] = new DijkstraAlgorithm(infeasiblePath);

		D[3] = new DijkstraAlgorithm(PathLength_1);
		D[4] = new DijkstraAlgorithm(PathLengthCars_1);
		D[5] = new DijkstraAlgorithm(PathTime_1);
		D[6] = new DijkstraAlgorithm(PathTimeCars_1);
		D[7] = new DijkstraAlgorithm(PathTimePedestrian_1);

		D[8] = new DijkstraAlgorithm(PathLength_2);
		D[9] = new DijkstraAlgorithm(PathLengthCars_2);
		D[10] = new DijkstraAlgorithm(PathTime_2);
		D[11]= new DijkstraAlgorithm(PathTimeCars_2);
		D[12] = new DijkstraAlgorithm(PathTimePedestrian_2);

		D[13] = new DijkstraAlgorithm(PathLength_3);
		D[14] = new DijkstraAlgorithm(PathLengthCars_3);
		D[15] = new DijkstraAlgorithm(PathTime_3);
		D[16] = new DijkstraAlgorithm(PathTimeCars_3);
		D[17] = new DijkstraAlgorithm(PathTimePedestrian_3);


		RESemptyPathD = D[0].doRun();
		RESsingleNodePathD = D[1].doRun();
		RESinfeasiblePathD = D[2].doRun();


		for(int i = 3; i<18; i++) {
			System.out.println("Execution Djikstra "+i);
			ResD[i] = D[i].doRun();
		}



		//CONSTRUCTION ASTAR
		System.out.println("Execution A Star");

		A[0] = new AStarAlgorithm(emptyPath);
		A[1] = new AStarAlgorithm(singleNodePath);
		A[2] = new AStarAlgorithm(infeasiblePath);

		A[3] = new AStarAlgorithm(PathLength_1);
		A[4] = new AStarAlgorithm(PathLengthCars_1);
		A[5] = new AStarAlgorithm(PathTime_1);
		A[6] = new AStarAlgorithm(PathTimeCars_1);
		A[7] = new AStarAlgorithm(PathTimePedestrian_1);

		A[8] = new AStarAlgorithm(PathLength_2);
		A[9] = new AStarAlgorithm(PathLengthCars_2);
		A[10] = new AStarAlgorithm(PathTime_2);
		A[11]= new AStarAlgorithm(PathTimeCars_2);
		A[12] = new AStarAlgorithm(PathTimePedestrian_2);

		A[13] = new AStarAlgorithm(PathLength_3);
		A[14] = new AStarAlgorithm(PathLengthCars_3);
		A[15] = new AStarAlgorithm(PathTime_3);
		A[16] = new AStarAlgorithm(PathTimeCars_3);
		A[17] = new AStarAlgorithm(PathTimePedestrian_3);


		RESemptyPathA = A[0].doRun();
		RESsingleNodePathA = A[1].doRun();
		RESinfeasiblePathA = A[2].doRun();


		for(int i = 3; i<18; i++) {
			System.out.println("Execution A Star "+i);
			ResA[i] = A[i].doRun();
		}

		//CONSTRUCTION BELLMAN
		System.out.println("Execution Bellman");


		B[3] = new BellmanFordAlgorithm(PathLength_1);
		B[4] = new BellmanFordAlgorithm(PathLengthCars_1);
		B[5] = new BellmanFordAlgorithm(PathTime_1);
		B[6] = new BellmanFordAlgorithm(PathTimeCars_1);
		B[7] = new BellmanFordAlgorithm(PathTimePedestrian_1);

		B[8] = new BellmanFordAlgorithm(PathLength_2);
		B[9] = new BellmanFordAlgorithm(PathLengthCars_2);
		B[10] = new BellmanFordAlgorithm(PathTime_2);
		B[11]= new BellmanFordAlgorithm(PathTimeCars_2);
		B[12] = new BellmanFordAlgorithm(PathTimePedestrian_2);

		B[13] = new BellmanFordAlgorithm(PathLength_3);
		B[14] = new BellmanFordAlgorithm(PathLengthCars_3);
		B[15] = new BellmanFordAlgorithm(PathTime_3);
		B[16] = new BellmanFordAlgorithm(PathTimeCars_3);
		B[17] = new BellmanFordAlgorithm(PathTimePedestrian_3);



		for(int i = 3; i<18; i++) {
			System.out.println("Execution Bellman "+i);
			ResB[i] = B[i].doRun();
		}



	}

	//____________METHODES________________
	@Test //VALIDE
	public void testPathIsValidD() {
		System.out.println("Test isValid Dijstra");
		assertTrue(RESemptyPathD.getPath().isValid());
		assertTrue(RESsingleNodePathD.getPath().isValid());
		assertTrue(RESinfeasiblePathD.getPath().isValid());

		for(int i = 3; i<18; i++) {
			assertTrue(ResD[i].getPath().isValid());
		}
	}

	@Test //VALIDE
	public void testPathIsValidA() {
		System.out.println("Test isValid A Star");
		assertTrue(RESemptyPathA.getPath().isValid());
		assertTrue(RESsingleNodePathA.getPath().isValid());
		assertTrue(RESinfeasiblePathA.getPath().isValid());

		for(int i = 3; i<18; i++) {
			assertTrue(ResA[i].getPath().isValid());
		}
	}




	@Test //FEASIBLE
	public void testPathStatusD() {
		System.out.println("Test isFeasible Dijstra");
		assertFalse(RESemptyPathD.isFeasible());
		assertTrue(RESsingleNodePathD.isFeasible());
		assertFalse(RESinfeasiblePathD.isFeasible());

		for(int i = 3; i<18; i++) {
			assertTrue(ResD[i].isFeasible());
		}
	}

	@Test //FEASIBLE
	public void testPathStatusA() {
		System.out.println("Test isFeasible A Star");
		assertFalse(RESemptyPathA.isFeasible());
		assertTrue(RESsingleNodePathA.isFeasible());
		assertFalse(RESinfeasiblePathA.isFeasible());

		for(int i = 3; i<18; i++) {
			assertTrue(ResA[i].isFeasible());
		}
	}





	@Test //CHEMIN EGAL
	public void testEqualPathD() {
		System.out.println("Test equalPath Dijstra");

		for(int i = 3; i<18; i++) {
			assertTrue(ResD[i].getPath().getLength() - ResB[i].getPath().getLength() <1e-6
					|| ResD[i].getPath().getMinimumTravelTime() - ResB[i].getPath().getMinimumTravelTime() < 1e-6);
		}
	}

	@Test //CHEMIN EGAL
	public void testEqualPathA() {
		System.out.println("Test equalPath A Star");

		for(int i = 3; i<18; i++) {
			assertTrue((ResA[i].getPath().getLength() - ResB[i].getPath().getLength() < 1e-6)
					||( ResA[i].getPath().getMinimumTravelTime() - ResB[i].getPath().getMinimumTravelTime()) < 1e-6);
		}
	}



}


