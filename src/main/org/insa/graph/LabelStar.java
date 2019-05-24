package org.insa.graph;

import org.insa.algo.AbstractInputData;
import org.insa.algo.AbstractInputData.Mode;

public class LabelStar extends Label implements Comparable<Label>{
	private double estimatedCost;

	public LabelStar(Node n, Node Dest,AbstractInputData.Mode m,int maxSpeed) {
		super(n);
		if (m == Mode.LENGTH) {
			this.estimatedCost=Point.distance(n.getPoint(), Dest.getPoint());
		}
		else {
			if(maxSpeed == GraphStatistics.NO_MAXIMUM_SPEED)
			{
				this.estimatedCost=Point.distance(n.getPoint(), Dest.getPoint())/27;
			}
			else {
			this.estimatedCost=Point.distance(n.getPoint(), Dest.getPoint())/(maxSpeed/3.6);
			}
		}


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
