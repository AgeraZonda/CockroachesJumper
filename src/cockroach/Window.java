package cockroach;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
		// class tạo các thông số của game
public class Window extends Canvas {
	JFrame frame = new JFrame("MyGame");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Window(int width, int height, String title, Game game) {

		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();

	}

}
