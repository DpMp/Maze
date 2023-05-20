package maze.controller.solver.aStar;

import maze.controller.maze.Node;
import maze.controller.maze.NodeType;
import maze.model.Data;

public class Field extends Node {
	private boolean isValid = true;
	private double cost, heuristic, function;
	private Field parent;

	public Field(int x, int y, boolean visited, NodeType maze, Data data) {
		super(x, y, visited, maze, data);
	}

	/**
	 * @return the isValid
	 */
	public boolean isValid() {
		return isValid;
	}

	/**
	 * @param isValid the isValid to set
	 */
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * @return the heuristic
	 */
	public double getHeuristic() {
		return heuristic;
	}

	/**
	 * @param heuristic the heuristic to set
	 */
	public void setHeuristic(double heuristic) {
		this.heuristic = heuristic;
	}

	/**
	 * @return the function
	 */
	public double getFunction() {
		return function;
	}

	/**
	 * @param function the function to set
	 */
	public void setFunction(double function) {
		this.function = function;
	}

	/**
	 * @return the parent
	 */
	public Field getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Field parent) {
		this.parent = parent;
	}

}
