package maze.controller.maze;

import java.awt.Color;

public enum NodeType {
	START(Color.GREEN),
	END(Color.RED),
	PATH(Color.WHITE),
	WALL(Color.BLACK),
	NONE(Color.GRAY),
	;
	
	private final Color color;

	private NodeType(Color color) {
		this.color = color;
	}

	/**
	 * @return the color of the field
	 */
	public Color getColor() {
		return color;
	}

}
