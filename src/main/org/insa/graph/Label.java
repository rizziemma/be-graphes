package org.insa.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.insa.algo.AbstractSolution.Status;
import org.insa.graph.Arc;
import org.insa.graph.Graph;
import org.insa.graph.Node;
import org.insa.graph.Path;

public class Label{
	private Node currentNode;
	private Boolean mark;
	private double cost ;
	private Arc pred;
	
	public Label(Node n) {
		this.currentNode = n;
		this.mark = false; 
		this.cost = Double.POSITIVE_INFINITY;
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
		return this;cost;
	}
	
	public Arc getPred() {
		return this.pred;
	}
	
}