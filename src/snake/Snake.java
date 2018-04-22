package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener 
{
	public JFrame frame;
	
	public PanelRender panel;
	
	public Timer time = new Timer(20, this);
	
	public static Snake snake;
	
	public ArrayList<Point> parts = new ArrayList<Point>();
	
	public static final int up = 1, right = 2, down = 3, left = 4;
	
	//this will be used to count through snake parts addition
	public int tick = 0, score = 0;
	
	public int direction = down;
	
	public Point snakeHead, food;
	
	public Random randomNo;
	
	public Snake()
	{
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame("Snake");
		frame.setVisible(true);
		frame.setSize(600, 500);
		frame.add(panel = new PanelRender());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		snakeHead = new Point(0,0);
		randomNo = new Random();
		food = new Point(dimension.width / 10, dimension.height / 10);
		time.start();
	}
	
	public static void main(String[] args)
	{
		snake = new Snake();	
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		panel.repaint();
		tick++;
		
		
		if(tick % 10 == 0 && snakeHead != null) // every half second do the following
		{
			if (direction == up)
			{
				parts.add(new Point(snakeHead.x, snakeHead.y - 1));
			}
			if (direction == right)
			{
				parts.add(new Point(snakeHead.x + 1, snakeHead.y));
			}
			if (direction == down)
			{
				parts.add(new Point(snakeHead.x, snakeHead.y + 1));
			}
			if (direction == left)
			{
				parts.add(new Point(snakeHead.x - 1, snakeHead.y));
			}
			if(food != null)
			{
				
			}
		}		
	}
}
