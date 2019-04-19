package org.insa.algo.shortestpath;

import java.util.ArrayList;
import java.util.Collections;

import org.insa.graph.Arc;
import org.insa.graph.Node;
import org.insa.graph.Path;
import org.insa.graph.Label;
import org.insa.algo.utils.BinaryHeap;
import org.insa.algo.utils.ElementNotFoundException;
import org.insa.algo.AbstractSolution.Status;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        
        //INITIALISATION
        BinaryHeap<Label> tas = new BinaryHeap<Label>();

        ArrayList<Label> labels=new ArrayList<Label>();
        for( Node n : this.data.getGraph().getNodes()) {
        	labels.add(new Label(n));
        	if(n.equals(data.getOrigin())) {
        		labels.get(labels.size()-1).setCost(0);
                tas.insert(labels.get(labels.size()-1));
                
        	}
        }
     // Notify observers about the first event (origin processed).
        notifyOriginProcessed(data.getOrigin());
        
        //PROCEDURE
        Label x, laby=null;
        while(!tas.isEmpty()) {
        	x=tas.findMin();
        	tas.remove(x);
        	x.setMark(true);
        	notifyNodeReached(x.getCurrentNode());
        	for(Arc y : x.getCurrentNode().getSuccessors() ) {
        		
        		if (!data.isAllowed(y)) {
                    continue;
                }
        		
        		
        		for(Label l : labels) { // on recupère le label associé a l'origine de l'arc y
        			if(l.getCurrentNode().equals(y.getDestination())) {
        				laby=l;
        				break;
        			}
        		}
        		
        		if(laby.getMark() == false && laby.getCost() >(x.getCost() + y.getLength())) {
        			laby.setCost(x.getCost() + y.getLength());
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
        //on reconstitue la solution 
        ArrayList<Node> nodesSol = new ArrayList<Node>();
        Label labdest=null, labori=null;
        for(Label l : labels) { // on recupère le label associé a la destination et l'origine du chemin
			if(l.getCurrentNode().equals(data.getOrigin())) {
				labori=l;
			}
			if(l.getCurrentNode().equals(data.getDestination())) {
				labdest=l;
			}
		}
        
        Label l = labdest;
        
        while(!l.equals(labori)) {
        	if ((l.getPred())==null) {
        		solution = new ShortestPathSolution(data,Status.INFEASIBLE);
        		return solution;
        	}
        	nodesSol.add(l.getCurrentNode());
        	
        	for(Label lab : labels) { //cherche le label associé au noeud prédecesseur 
        		if(lab.getCurrentNode().equals(l.getPred().getOrigin())) {
        			l=lab;
        			break;
        			
        		}
        	}
        }
        
        // The destination has been found, notify the observers.
        notifyDestinationReached(data.getDestination());
        
        nodesSol.add(labori.getCurrentNode());
        Collections.reverse(nodesSol);
        
        Path p = Path.createShortestPathFromNodes(this.data.getGraph(), nodesSol);
        solution = new ShortestPathSolution(data,Status.OPTIMAL,p);
        return solution;
    }

}
