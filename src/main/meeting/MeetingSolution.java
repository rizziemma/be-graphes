package meeting;


import java.util.ArrayList;

import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.algo.shortestpath.ShortestPathSolution;
import org.insa.graph.Label;


public class MeetingSolution extends ShortestPathSolution {
	private ArrayList<Label> sol;
	public MeetingSolution(ShortestPathData data, Status status, ArrayList<Label> sol) {
		super(data, status);
		this.setSol(sol);
	}
	
	public ArrayList<Label> getSol() {
		return sol;
	}
	public void setSol(ArrayList<Label> sol) {
		this.sol = sol;
	}
	

}
