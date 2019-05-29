package meeting;

import java.util.ArrayList;

import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.graph.Label;
import org.insa.graph.Node;

public class MeetingZone<Observer> {
	private ArrayList<Node> zone;
	private ShortestPathData data1;
	private ShortestPathData data2;
	protected final ArrayList<Observer> observers;

	public MeetingZone(ShortestPathData data1, ShortestPathData data2) {
		this.zone = new ArrayList<Node>();
		this.data1 = data1;
		this.data2 = data2;
		this.observers = new ArrayList<Observer>();
	}
	
	
	public void run() {
		MeetingSolution N1 = new DijkstraAlgoModified(this.data1).doRun();
		MeetingSolution N2 = new DijkstraAlgoModified(this.data2).doRun();
		
		for(Label L1 : N1.getSol()) {
			for(Label L2 : N2.getSol()) {
				
				if(L1.getCurrentNode().equals(L2.getCurrentNode())
						&& Math.abs(L1.getCost() - L2.getCost())/L1.getCost() <= 0.15
						&& Math.abs(L1.getCost() - L2.getCost())/L2.getCost() <= 0.15) {
					this.zone.add(L1.getCurrentNode());
					//notifyNodeReached(L1.getCurrentNode();
				}
				
			}
		}
		
		
	}
}
