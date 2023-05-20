package maze.controller.maze;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import maze.model.Data;
import maze.view.Maze;

public class SliderButtonCountVertically implements ChangeListener {
	private final Maze maze;
	private final Data data;

	public SliderButtonCountVertically(Maze maze) {
		this.maze = maze;
		this.data = maze.getData();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		int value = maze.getSliderButtonCountVertically().getValue();
		maze.getLblButtonCountVertically().setText(Integer.toString(value));
		data.setVertical(value);
		
	}

}
