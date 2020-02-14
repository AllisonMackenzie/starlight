package game;

import javax.swing.*;
import java.awt.*;

/**
 * Window is a class which does what it sounds like. It is responsible for creating a window on the computer screen to house the game so that the user can 
 * actually play the game. It accomplishes this task by first setting the dimensions and constraints of these dimensions on the game object and then the game, 
 * acting as a Canvas, is placed inside of a JFrame. Once everything is set up properly the window is made visible and the game is called to start.
 * 
 * @author Allison Johnson
 * @version March 18th, 2019
 *
 */
public class Window	
{	
	/**
	 * Constructor for class Window. It is how the Window object is created using the following parameters to accomplish the task described in detail 
	 * above in the description for the class itself.
	 * 
	 * @param width The width of the game
	 * @param height The height of the game
	 * @param title The title of the game
	 * @param game A game object used to place inside of a JFrame
	 */
	public Window(int width, int height, String title, Game game)
	{
		game.setPreferredSize(new Dimension(width, height));
		game.setMaximumSize(new Dimension(width, height));
		game.setMinimumSize(new Dimension(width, height));
		
		JFrame frame = new JFrame(title);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
}
