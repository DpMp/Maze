package maze.controller.maze;

import maze.model.Data;

public class Node {
	private final int x;
	private final int y;
	private boolean visited;
	private NodeType type;
	private final Data data;
	private int value = 0;
	
	public Node(int x, int y, boolean visited, NodeType maze, Data data) {
		this.x = x;
		this.y = y;
		this.visited = visited;
		this.type = maze;
		this.data = data;
	}

	/**
	 * @return the visited
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * @param visited the visited to set
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the maze
	 */
	public NodeType getType() {
		return type;
	}

	/**
	 * @param type the maze to set
	 */
	public void setType(NodeType type) {
		this.type = type;
		data.getVisualGrid()[x][y].setBackground(type.getColor());
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "x = " + x + " ; y = " + y;
	}

}
