package maze.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import maze.controller.maze.BtnGenerateMaze;
import maze.controller.maze.SliderButtonCountHorizontally;
import maze.controller.maze.SliderButtonCountVertically;
import maze.controller.solver.aStar.AStar;
import maze.model.Data;
import java.awt.Color;

public class Maze extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private final JSlider sliderButtonCountVertically;
	private final JSlider sliderButtonCountHorizontally;
	private final JButton btnGenerateMaze;
	private final JLabel lblButtonCountVertically;
	private final JLabel lblButtonCountHorizontally;
	private final JPanel panelMaze;
	
	private final Data data;
	private JLabel lblText;
	private JButton btnAStar;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Maze frame = new Maze();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Maze() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSolver = new JPanel();
		contentPane.add(panelSolver, BorderLayout.WEST);
		panelSolver.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblText = new JLabel("Algorithms");
		lblText.setHorizontalAlignment(SwingConstants.CENTER);
		lblText.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelSolver.add(lblText);
		
		btnAStar = new JButton("AStar");
		btnAStar.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelSolver.add(btnAStar);
		
		btnNewButton_1 = new JButton("New button");
		panelSolver.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("New button");
		panelSolver.add(btnNewButton_2);
		
		JPanel panelSettings = new JPanel();
		contentPane.add(panelSettings, BorderLayout.EAST);
		panelSettings.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		panelSettings.add(lblSettings);
		
		JLabel lblNone1 = new JLabel("");
		panelSettings.add(lblNone1);
		
		JLabel lblButtonCount = new JLabel("Buttoncount");
		lblButtonCount.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblButtonCount.setHorizontalAlignment(SwingConstants.CENTER);
		panelSettings.add(lblButtonCount);
		
		JLabel lblButtonHorizontally = new JLabel("Horizontally:");
		lblButtonHorizontally.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelSettings.add(lblButtonHorizontally);
		
		lblButtonCountHorizontally = new JLabel("81");
		lblButtonCountHorizontally.setHorizontalAlignment(SwingConstants.CENTER);
		panelSettings.add(lblButtonCountHorizontally);
		
		sliderButtonCountHorizontally = new JSlider();
		sliderButtonCountHorizontally.setSnapToTicks(true);
		sliderButtonCountHorizontally.setMajorTickSpacing(2);
		sliderButtonCountHorizontally.setMinorTickSpacing(2);
		sliderButtonCountHorizontally.setValue(81);
		sliderButtonCountHorizontally.setMaximum(151);
		sliderButtonCountHorizontally.setMinimum(11);
		panelSettings.add(sliderButtonCountHorizontally);
		
		JLabel lblButtonVertically = new JLabel("Vertically:");
		lblButtonVertically.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelSettings.add(lblButtonVertically);
		
		lblButtonCountVertically = new JLabel("81");
		lblButtonCountVertically.setHorizontalAlignment(SwingConstants.CENTER);
		panelSettings.add(lblButtonCountVertically);
		
		sliderButtonCountVertically = new JSlider();
		sliderButtonCountVertically.setSnapToTicks(true);
		sliderButtonCountVertically.setValue(81);
		sliderButtonCountVertically.setMaximum(151);
		sliderButtonCountVertically.setMinimum(11);
		sliderButtonCountVertically.setMinorTickSpacing(2);
		sliderButtonCountVertically.setMajorTickSpacing(2);
		panelSettings.add(sliderButtonCountVertically);
		
		btnGenerateMaze = new JButton("Generate Maze");
		btnGenerateMaze.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelSettings.add(btnGenerateMaze);
		
		panelMaze = new JPanel();
		contentPane.add(panelMaze, BorderLayout.CENTER);
		panelMaze.setLayout(null);
		
		this.data = new Data(this);
		listeners();
	}
	
	private void listeners() {
		sliderButtonCountHorizontally.addChangeListener(new SliderButtonCountHorizontally(this));
		sliderButtonCountVertically.addChangeListener(new SliderButtonCountVertically(this));
		btnGenerateMaze.addActionListener(new BtnGenerateMaze(this));
		
		btnAStar.addActionListener(new AStar(this));
	}

	/**
	 * @return the data
	 */
	public Data getData() {
		return data;
	}

	/**
	 * @return the sliderButtonCountHorizontally
	 */
	public JSlider getSliderButtonCountHorizontally() {
		return sliderButtonCountHorizontally;
	}

	/**
	 * @return the sliderButtonCountVertically
	 */
	public JSlider getSliderButtonCountVertically() {
		return sliderButtonCountVertically;
	}

	/**
	 * @return the btnGenerateMaze
	 */
	public JButton getBtnGenerateMaze() {
		return btnGenerateMaze;
	}

	/**
	 * @return the lblButtonCountVertically
	 */
	public JLabel getLblButtonCountVertically() {
		return lblButtonCountVertically;
	}

	/**
	 * @return the lblButtonCountHorizontally
	 */
	public JLabel getLblButtonCountHorizontally() {
		return lblButtonCountHorizontally;
	}

	/**
	 * @return the panelMaze
	 */
	public JPanel getPanelMaze() {
		return panelMaze;
	}
}
