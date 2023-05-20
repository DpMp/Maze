package maze.model;

import javax.swing.JLabel;

import maze.controller.maze.Node;
import maze.view.Maze;

public class Data {
	private final Maze maze;
	private int horizontal;
	private int vertical;
	private Node[][] grid;
	private JLabel[][] visualGrid;
	
	public Data(final Maze maze) {
		this.maze = maze;
		horizontal = maze.getSliderButtonCountHorizontally().getValue();
		vertical = maze.getSliderButtonCountVertically().getValue();
	}

	/**
	 * @return the maze
	 */
	public Maze getMaze() {
		return maze;
	}

	/**
	 * @return the grid
	 */
	public Node[][] getGrid() {
		return grid;
	}

	/**
	 * @param grid the grid to set
	 */
	public void setGrid(Node[][] grid) {
		this.grid = grid;
	}

	/**
	 * @return the horizontal
	 */
	public int getHorizontal() {
		return horizontal;
	}

	/**
	 * @param horizontal the horizontal to set
	 */
	public void setHorizontal(int horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * @return the vertical
	 */
	public int getVertical() {
		return vertical;
	}

	/**
	 * @param vertical the vertical to set
	 */
	public void setVertical(int vertical) {
		this.vertical = vertical;
	}

	/**
	 * @return the buttonGrid
	 */
	public JLabel[][] getVisualGrid() {
		return visualGrid;
	}

	/**
	 * @param buttonGrid the buttonGrid to set
	 */
	public void setVisualGrid(JLabel[][] visualGrid) {
		this.visualGrid = visualGrid;
	}

}
