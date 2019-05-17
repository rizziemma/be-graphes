package org.insa.graph;

import org.insa.graph.Arc;
import org.insa.graph.Node;


public class Label implements Comparable<Label> {
	private Node currentNode;
	private Boolean mark;
	protected double cost ;
	private Arc pred;
	
	public Label(Node n) {
		this.currentNode = n;
		this.mark = false; 
		this.cost = Double.POSITIVE_INFINITY;
		this.pred=null;
	}
	
	public void setMark(Boolean b) {
		this.mark=b;
	}
	
	public void setCost(double c) {
		this.cost=c;
	}
	
	public void setPred(Arc a) {
		this.pred=a;
	}
	
	public Node getCurrentNode () {
		return this.currentNode;
	}
	
	public Boolean getMark() {
		return this.mark;
	}
	
	public double getCost() {
		return this.cost;
	}
	
	public Arc getPred() {
		return this.pred;
	}
	
	public boolean equals(Label other) {
	    return this.currentNode.equals(other.getCurrentNode());
	}

	   
	public int compareTo(Label other) {
	    return Double.compare(this.getCost(), other.getCost());
	}

}
