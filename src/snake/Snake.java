package snake;

import java.awt.Toolkit;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Snake 
{
	public JFrame frame;
	
	public static Snake snake;
	
	public Snake()
	{
		frame = new JFrame("Snake");
		frame.setVisible(true);
		frame.setSize(600, 500);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args)
	{
		snake = new Snake();
	}

}
