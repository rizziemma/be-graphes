package org.insa.graph;


public class LabelStar extends Label implements Comparable<Label>{
	private double estimatedCost;

	public LabelStar(Node n, Node Dest) {
		super(n);
		this.estimatedCost=Point.distance(n.getPoint(), Dest.getPoint());

	}
	
	public double getTotalCost() {
		return(this.cost+this.estimatedCost);
	}
	
	public double getEstimatedCost() {
		return this.estimatedCost;
	}

	public int compareTo(Label other) {
		int ret = Double.compare(this.getTotalCost(), ((LabelStar)other).getTotalCost());
		if(ret ==0) {
			ret = Double.compare(this.estimatedCost,((LabelStar)other).getEstimatedCost());
		}
		return ret;
	}
	

}
