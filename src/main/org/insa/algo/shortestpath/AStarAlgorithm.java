package org.insa.algo.shortestpath;

import java.util.ArrayList;
import java.util.Collections;

import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.utils.BinaryHeap;
import org.insa.algo.utils.ElementNotFoundException;
import org.insa.graph.Arc;
import org.insa.graph.Label;
import org.insa.graph.LabelStar;
import org.insa.graph.Node;
import org.insa.graph.Path;

public class AStarAlgorithm extends DijkstraAlgorithm {

	
	
    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    void InitRun (ShortestPathData data) {
    	this.tas = new BinaryHeap<Label>();

        this.labels=new LabelStar[data.getGraph().size()];
        for( Node n : this.data.getGraph().getNodes()) {
        	labels[n.getId()]=new LabelStar(n,data.getDestination(),data.getMode(),data.getMaximumSpeed());
        	if(n.equals(data.getOrigin())) {
        		labels[n.getId()].setCost(0);
                this.tas.insert(labels[n.getId()]);
                
        	}
        }
    }

}
