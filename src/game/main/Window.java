package game.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{
	private static final long serialVersionUID = 492636734070584756L;
	public JFrame frame;
	public Window(int width, int height, String title, Game game) {
		frame = new JFrame(title);
	    frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.getContentPane().setPreferredSize(new Dimension(width, height));
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);

		game.start();
		
	}
}
