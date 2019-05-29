package meeting;

import java.util.ArrayList;
import org.insa.graph.Arc;
import org.insa.graph.Node;
import org.insa.graph.Label;
import org.insa.algo.utils.BinaryHeap;
import org.insa.algo.utils.ElementNotFoundException;
import org.insa.algo.AbstractInputData.Mode;
import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.shortestpath.AStarAlgorithm;
import org.insa.algo.shortestpath.ShortestPathAlgorithm;
import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.algo.shortestpath.ShortestPathSolution;

public class DijkstraAlgoModified extends ShortestPathAlgorithm{


	protected BinaryHeap<Label> tas;
	protected Label labels[];
	protected double C;
	protected ArrayList<Label> Sol;

	public DijkstraAlgoModified(ShortestPathData data) {
		super(data);
		this.Sol = new ArrayList<Label>();
	}

	void InitRun (ShortestPathData data) {
		ShortestPathAlgorithm A = new AStarAlgorithm(data);
		ShortestPathSolution S = A.run();
		if(S.isFeasible()) {
			if(data.getMode()==Mode.TIME) {
				this.C = S.getPath().getMinimumTravelTime();
			}else{
				this.C = S.getPath().getLength();
			}
		}else {
			
		}
		
		
		
		this.tas = new BinaryHeap<Label>();

		this.labels=new Label[data.getGraph().size()];
		for( Node n : this.data.getGraph().getNodes()) {
			labels[n.getId()]=new Label(n);
			if(n.equals(data.getOrigin())) {
				labels[n.getId()].setCost(0);
				this.tas.insert(labels[n.getId()]);

			}
		}
	}



	@Override
	protected MeetingSolution doRun() {
		ShortestPathData data = getInputData();
		MeetingSolution solution = null;

		//TEST DATA
		if(data.getDestination()==null || data.getOrigin()==null || data.getGraph() == null) {
			solution = new MeetingSolution(data,Status.INFEASIBLE,null);
			return solution;
		}

		InitRun(data);

		// Notify observers about the first event (origin processed).
		notifyOriginProcessed(data.getOrigin());

		//PROCEDURE
		Label x = null, laby=null;
		while(!this.tas.isEmpty()) {
			
			x=this.tas.findMin();
			this.tas.remove(x);
			this.Sol.add(x);
			x.setMark(true);


			if(x.getCost()> 1.3*C) { //dépasse le cout max autorisé
				break;
			}
			
			
			for(Arc y : x.getCurrentNode().getSuccessors() ) {        		
				if (!data.isAllowed(y)) {
					continue;
				} 
				laby = labels[y.getDestination().getId()];// on recupère le label associé a l'origine de l'arc y
				if(laby.getMark() == false && laby.getCost() > (x.getCost() + data.getCost(y))) {
					laby.setCost(x.getCost() + data.getCost(y));
					laby.setPred(y);

					try { //on insere le label sil n'etait pas dans la liste, on a pas d'iterator pour parcourir un tas donc on l'enleve puis le reinsere
						tas.remove(laby);
					}
					catch (ElementNotFoundException e) {
						//nothing
					}
					tas.insert(laby);
				}
			}
		}
		

		solution = new MeetingSolution(data,Status.FEASIBLE,Sol);
		return solution;
	}

}
