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
	private static ShortestPathSolution ResD[];
	private static ShortestPathSolution ResA[];
	private static ShortestPathSolution ResB[];

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
		DijkstraAlgorithm D[] = new DijkstraAlgorithm[18];
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
			System.out.println("Execution Dijstra "+i);
			ResD[i] = D[i].doRun();
		}


		
		//CONSTRUCTION ASTAR
				System.out.println("Execution A Star");
				AStarAlgorithm A[] = new AStarAlgorithm[18];
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
				BellmanFordAlgorithm B[] = new BellmanFordAlgorithm[18];
				
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
					System.out.println("Execution Dijstra "+i);
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
			
			assertEquals(ResD[i].getPath().getArcs().size(),ResB[i].getPath().getArcs().size());
			
			for(i=0;i<ResB[i].getPath().getArcs().size();i++) {
				assertTrue(ResD[i].getPath().getArcs().get(i) == ResB[i].getPath().getArcs().get(i)
						|| ResD[i].getPath().getLength() == ResB[i].getPath().getLength()
						|| ResD[i].getPath().getMinimumTravelTime() == ResB[i].getPath().getMinimumTravelTime());
			}
		}
	}
	
	@Test //CHEMIN EGAL
	public void testEqualPathA() {
		System.out.println("Test equalPath A Star");
		
		for(int i = 3; i<18; i++) {
			
			assertEquals(ResA[i].getPath().getArcs().size(),ResB[i].getPath().getArcs().size());
			
			for(i=0;i<ResA[i].getPath().getArcs().size();i++) {
				assertTrue((ResA[i].getPath().getArcs().get(i) == ResB[i].getPath().getArcs().get(i))
						|| ResA[i].getPath().getLength() == ResB[i].getPath().getLength()
						|| ResA[i].getPath().getMinimumTravelTime() == ResB[i].getPath().getMinimumTravelTime());
			}
		}
	}
	

	 
}


