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

	protected BinaryHeap<Label> tas;
	protected Label labels[];
	
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }
    
    void InitRun (ShortestPathData data) {
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
    protected ShortestPathSolution doRun() {
        ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        
        //TEST DATA
        if(data.getDestination()==null || data.getOrigin()==null || data.getGraph() == null) {
        	solution = new ShortestPathSolution(data,Status.INFEASIBLE,new Path(data.getGraph()));
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
        	x.setMark(true);
        	notifyNodeReached(x.getCurrentNode());
        	
        	if(data.getDestination().equals(x.getCurrentNode())) { //destination atteinte et marquée, peut arreter le parcours
        		break;
        	}
        	
        	for(Arc y : x.getCurrentNode().getSuccessors() ) {
        		
        		if (!data.isAllowed(y)) {
                    continue;
                }
        		
        		
        		laby = labels[y.getDestination().getId()];// on recupère le label associé a l'origine de l'arc y
        	
        		
        		if(laby.getMark() == false && laby.getCost() >(x.getCost() + data.getCost(y))) {
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
        //on reconstitue la solution 
        ArrayList<Node> nodesSol = new ArrayList<Node>();
        Label labdest=null, labori=null;
        labori = labels[data.getOrigin().getId()];// on recupère le label associé a la destination et l'origine du chemin
        labdest = labels[data.getDestination().getId()];
		
        
        Label l = labdest;
        
        while(!l.equals(labori)) {
        	if ((l.getPred())==null) {
        		solution = new ShortestPathSolution(data,Status.INFEASIBLE);
        		return solution;
        	}
        	nodesSol.add(l.getCurrentNode());
        	
        	l = labels[l.getPred().getOrigin().getId()];//cherche le label associé au noeud prédecesseur 
        		
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
