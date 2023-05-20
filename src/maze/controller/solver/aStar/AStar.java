package maze.controller.solver.aStar;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import maze.controller.maze.Node;
import maze.controller.maze.NodeType;
import maze.model.Data;
import maze.view.Maze;

public class AStar implements ActionListener {
	private final Data data;
	private Field[][] grid;

	private ArrayList<Field> path;
	private ArrayList<Field> openList;
	private ArrayList<Field> closedList;
	private JLabel[][] visualGrid;

	public AStar(Maze maze) {
		this.data = maze.getData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Node[][] temp = new Node[data.getHorizontal()][data.getVertical()];
		temp = data.getGrid();
		grid = new Field[data.getHorizontal()][data.getVertical()];

		for (int i = 0; i < data.getHorizontal(); i++) {
			for (int j = 0; j < data.getVertical(); j++) {
				grid[i][j] = new Field(i, j, true, temp[i][j].getType(), data);
			}
		}

		visualGrid = data.getVisualGrid();

		solveBasic();
		
		closedList.remove(0);

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				if (path.isEmpty() && closedList.isEmpty()) {
					reset();
					this.cancel();
					return;
				} else if (!closedList.isEmpty()) {
					visualGrid[closedList.get(0).getX()][closedList.get(0).getY()].setBackground(Color.CYAN);
					closedList.remove(0);
				} else {
					visualGrid[path.get(0).getX()][path.get(0).getY()].setBackground(Color.BLUE);
					path.remove(0);
				}

			}

		};

		path.remove(path.size() - 1);
		Timer timer = new Timer("Timer");
		int delay = (int) Math.sqrt(Math.sqrt(50 * (data.getVertical() + data.getHorizontal())));
		timer.schedule(task, 1000, delay);

	}

	private ArrayList<Field> solveBasic() {
		Field start = new Field(0, 0, true, NodeType.WALL, data);
		Field end = new Field(0, 0, true, NodeType.WALL, data);

		for (int i = 0; i < data.getHorizontal(); i++) {
			for (int j = 0; j < data.getVertical(); j++) {
				if (grid[i][j].getType() == NodeType.WALL) {
					grid[i][j].setValid(false);
				} else if (grid[i][j].getType() == NodeType.START) {
					start = grid[i][j];
				} else if (grid[i][j].getType() == NodeType.END) {
					end = grid[i][j];
				}
			}
		}

		if (start == null && end == null) {
			return null;
		}

		if (start.equals(end)) {
			this.path = new ArrayList<>();
			return path;
		}

		this.path = new ArrayList<>();

		this.openList = new ArrayList<>();
		this.closedList = new ArrayList<>();

		this.openList.add(start);

		while (!openList.isEmpty()) {
			Field current;
			current = getLowestF();

			if (end.getX() == current.getX() && end.getY() == current.getY()) {
				retracePath(current, start, end);
				break;
			}

			openList.remove(current);
			closedList.add(current);

			ArrayList<Field> temp = calculateNeighbours(current);

			for (Field n : temp) {

				if (closedList.contains(n) || !n.isValid()) {
					continue;
				}

				double tempScore = current.getCost() + distanceTo(current, n);

				if (openList.contains(n)) {
					if (tempScore < n.getCost()) {
						n.setCost(tempScore);
						n.setParent(current);
					}
				} else {
					n.setCost(tempScore);
					openList.add(n);
					n.setParent(current);
				}

				n.setHeuristic(distanceTo(n, end));
				n.setFunction(n.getCost() + n.getHeuristic());

			}

		}
		return path;
	}

	private void retracePath(Field current, Field start, Field end) {
		Field temp = current;
		this.path.add(current);

		while (temp.getParent() != null) {
			this.path.add(temp.getParent());
			temp = temp.getParent();
		}
		if (path.contains(start)) {
			path.remove(start);
		}

		if (!path.contains(end)) {
			path.add(0, end);
		}

		Collections.reverse(path);
	}

	private Field getLowestF() {
		Field lowest = openList.get(0);
		for (Field n : openList) {
			if (n.getFunction() < lowest.getFunction()) {
				lowest = n;
			}
		}
		return lowest;
	}

	private ArrayList<Field> calculateNeighbours(Field node) {

		ArrayList<Field> nodes = new ArrayList<>();

		int minX = 0;
		int minY = 0;
		int maxX = data.getHorizontal();
		int maxY = data.getVertical();

		if (node.getX() > minX) {
			nodes.add(grid[node.getX() - 1][node.getY()]); // west
		}

		if (node.getX() < maxX - 1) {
			nodes.add(grid[node.getX() + 1][node.getY()]); // east
		}

		if (node.getY() < maxY - 1) {
			nodes.add(grid[node.getX()][node.getY() + 1]); // north
		}

		if (node.getX() > minY) {
			nodes.add(grid[node.getX()][node.getY() - 1]); // south
		}

		return nodes;

	}

	private double distanceTo(Field from, Field to) {
		return new Point(from.getX(), from.getX()).distance(new Point(to.getX(), to.getX()));
	}

	public void reset() {
		this.path = null;
		this.openList = null;
		this.closedList = null;
		this.path = null;
		for (int i = 0; i < data.getHorizontal(); i++) {
			for (int j = 0; j < data.getVertical(); j++) {
				grid[i][j].setValid(true);
				grid[i][j].setCost(0);
				grid[i][j].setFunction(0);
				grid[i][j].setHeuristic(0);
				grid[i][j].setParent(null);
			}
		}
	}
}
