package maze.controller.maze;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import maze.model.Data;
import maze.model.Pair;
import maze.view.Maze;

public class BtnGenerateMaze implements ActionListener {
	private int delay = 100;
	private final Maze maze;
	private final Data data;
	private int horizontally;
	private int vertically;
	private Node[][] grid;
	private List<Pair<Integer, Integer>> nodes;
	private Timer timer;
	private TimerTask task;

	public BtnGenerateMaze(Maze maze) {
		this.maze = maze;
		data = maze.getData();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (timer != null) {
			timer.cancel();
		}

		horizontally = data.getHorizontal();
		vertically = data.getVertical();

		int start;

		if (vertically % 4 == 1) {
			start = (vertically - 1) / 2 + 1;
		} else {
			start = (vertically - 1) / 2;
		}

		maze.getPanelMaze().removeAll();
		JLabel[][] visualGrid = new JLabel[horizontally][vertically];
		int width = (int) (maze.getPanelMaze().getWidth() / horizontally);
		int height = (int) (maze.getPanelMaze().getHeight() / vertically);
		for (int i = 0; i < horizontally; i++) {
			for (int j = 0; j < vertically; j++) {
				visualGrid[i][j] = new JLabel();
				visualGrid[i][j].setBounds(i * width, j * height, width, height);
				visualGrid[i][j].setOpaque(true);
				visualGrid[i][j].setBackground(Color.BLACK);
				maze.getPanelMaze().add(visualGrid[i][j]);
			}
		}
		data.setVisualGrid(visualGrid);
		maze.repaint();

		grid = new Node[horizontally][vertically];
		for (int i = 0; i < horizontally; i++) {
			for (int j = 0; j < vertically; j++) {
				grid[i][j] = new Node(i, j, false, NodeType.WALL, data);
			}
		}

		nodes = new ArrayList<Pair<Integer, Integer>>();
		nodes.add(new Pair<Integer, Integer>(1, start));

		task = new TimerTask() {

			@Override
			public void run() {
				if (nodes.isEmpty()) {
					this.cancel();

					Pair<Integer, Integer> maxValue = new Pair<Integer, Integer>(0, start);
					for (int i = 0; i < horizontally; i++) {
						for (int j = 0; j < vertically; j++) {
							if (grid[maxValue.getKey()][maxValue.getValue()].getValue() < grid[i][j].getValue()) {
								maxValue.setKey(i);
								maxValue.setValue(j);
							}
						}
					}
					grid[maxValue.getKey()][maxValue.getValue()].setType(NodeType.END);

					return;
				}
				Pair<Integer, Integer> node = nodes.get(nodes.size() - 1);
				nodes.remove(node);
				generateMaze(node.getKey(), node.getValue());

			}

		};
		grid[0][start].setType(NodeType.START);

		timer = new Timer("Timer");
		delay = (int) Math.sqrt(Math.sqrt(10 * (vertically + horizontally)));
		timer.schedule(task, 1000, 1);

		data.setGrid(grid);

	}

	private void generateMaze(int x, int y) {
		grid[x][y].setVisited(true);
		grid[x][y].setType(NodeType.PATH);

		List<Pair<Integer, Integer>> directions = new ArrayList<>();

		directions.add(new Pair<Integer, Integer>(2, 0));
		directions.add(new Pair<Integer, Integer>(-2, 0));
		directions.add(new Pair<Integer, Integer>(0, 2));
		directions.add(new Pair<Integer, Integer>(0, -2));

		List<Pair<Integer, Integer>> dir = new ArrayList<>();

		for (Pair<Integer, Integer> pair : directions) {
			int dx = pair.getKey();
			int dy = pair.getValue();
			if (x + dx > 0 && y + dy > 0 && x + dx < horizontally && y + dy < vertically
					&& !grid[x + dx][y + dy].isVisited()) {
				dir.add(pair);
			}
		}

		if (!dir.isEmpty()) {
			if (dir.size() > 1) {
				nodes.add(new Pair<Integer, Integer>(x, y));
			}

			Collections.shuffle(dir);
			Pair<Integer, Integer> direction = dir.get(0);
			int dx = direction.getKey();
			int dy = direction.getValue();
			nodes.add(new Pair<Integer, Integer>(x + dx, y + dy));

			sleep(delay);
			grid[x + dx / 2][y + dy / 2].setType(NodeType.PATH);
			sleep(delay);
			grid[x + dx][y + dy].setVisited(true);
			grid[x + dx][y + dy].setValue(grid[x][y].getValue() + 1);
			grid[x + dx][y + dy].setType(NodeType.PATH);
		}

	}

	private void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		horizontally = data.getHorizontal();
//		vertically = data.getVertical();
//
//		maze.getPanelMaze().removeAll();
//		JLabel[][] visualGrid = new JLabel[horizontally][vertically];
//		int width = (int) (maze.getPanelMaze().getWidth() / horizontally);
//		int height = (int) (maze.getPanelMaze().getHeight() / vertically);
//		for (int i = 0; i < horizontally; i++) {
//			for (int j = 0; j < vertically; j++) {
//				visualGrid[i][j] = new JLabel();
//				visualGrid[i][j].setBounds(i * width, j * height, width, height);
//				visualGrid[i][j].setOpaque(true);
//				visualGrid[i][j].setBackground(Color.BLACK);
//				maze.getPanelMaze().add(visualGrid[i][j]);
//			}
//		}
//		data.setVisualGrid(visualGrid);
//		maze.repaint();
//
//		grid = new Node[horizontally][vertically];
//		for (int i = 0; i < horizontally; i++) {
//			for (int j = 0; j < vertically; j++) {
//				if (i % 2 == 1 && j % 2 == 1) {
//					grid[i][j] = new Node(i, j, false, NodeType.PATH, data);
//				} else {
//					grid[i][j] = new Node(i, j, false, NodeType.WALL, data);
//				}
//			}
//		}
//		generateMaze(grid, 1, vertically / 2 + 1);
//		grid[0][vertically/2+1].setType(NodeType.START);
//		grid[1][vertically/2+1].setType(NodeType.PATH);
//		grid[horizontally-1][vertically/2+1].setType(NodeType.END);
//
//		data.setGrid(grid);
//	}
//
//	private Node[][] generateMaze(int x, int y) {
//		grid[x][y].setVisited(true);
//
//		List<Pair<Integer, Integer>> directions = new ArrayList<>();
//		directions.add(new Pair<Integer, Integer>(2, 0));
//		directions.add(new Pair<Integer, Integer>(-2, 0));
//		directions.add(new Pair<Integer, Integer>(0, 2));
//		directions.add(new Pair<Integer, Integer>(0, -2));
//
//		Collections.shuffle(directions);
//		for (Pair<Integer, Integer> direction : directions) {
//			int dx = direction.getKey();
//			int dy = direction.getValue();
//			if (x + dx > 0 && y + dy > 0 && x + dx < horizontally && y + dy < vertically
//					&& !grid[x + dx][y + dy].isVisited()) {
//				grid[x + dx / 2][y + dy / 2].setType(NodeType.PATH);
//				grid[x + dx][y + dy].setType(NodeType.PATH);
//				generateMaze(grid, x + dx, y + dy);
//				
//			}
//		}
//		return grid;
//	}
}
