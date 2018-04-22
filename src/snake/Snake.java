package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener 
{
	public JFrame frame;
	
	public PanelRender panel;
	
	public Timer time = new Timer(20, this);
	
	public static Snake snake;
	
	public Dimension dimension;
	
	public ArrayList<Point> parts = new ArrayList<Point>();
	
	public static final int up = 1, right = 2, down = 3, left = 4;
	public static final int extreme = 1, hard = 2, normal = 3, easy = 4, easiest = 5;
	
	public int tick = 0, score = 0;
	
	public int direction = right;
	public int difficulty = easiest;
	
	public int snakeLength = 0;
	
	public boolean outOfBounds = false;
	
	public Point snakeHead, food;
	
	public Random randomNo;
	
	public Snake()
	{
		dimension = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame("Snake");
		frame.setVisible(true);
		frame.setSize(800, 700);
		frame.add(panel = new PanelRender());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.addKeyListener(this);
		
		startSnake();		
	}
	
	public void startSnake()
	{
		score = 0;		
		snakeLength = 0;
		tick = 0;
		outOfBounds = false;
		direction = right;
		parts.clear();
		snakeHead = new Point(0,0);
		randomNo = new Random();
		food = new Point(randomNo.nextInt(frame.getWidth() / 10),randomNo.nextInt(frame.getHeight() / 10));
		if(food.x == frame.getWidth() || food.x == 0)
		{
			food = new Point(randomNo.nextInt(frame.getWidth() / 10),randomNo.nextInt(frame.getHeight() / 10));
		}
		if(food.y == frame.getHeight() || food.y == 0)
		{
			food = new Point(randomNo.nextInt(frame.getWidth() / 10),randomNo.nextInt(frame.getHeight() / 10));
		}
		
		System.out.println(frame.getWidth());
		System.out.println(frame.getHeight());
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
		
		if(tick % difficulty == 0 && snakeHead != null && outOfBounds != true) // every half second do the following
		{
			parts.add((Point)snakeHead.clone());
			if (direction == up)
			{
				if(snakeHead.y - 1 >= 0 && noBody(snakeHead.x, snakeHead.y - 1))
				{
					snakeHead = new Point(snakeHead.x, snakeHead.y - 1);
				}
				else
				{
					outOfBounds = true;
				}
			}
			
			if (direction == right)
			{
				if(snakeHead.x + 1 < (frame.getWidth() / 10) - 1 && noBody(snakeHead.x + 1, snakeHead.y))
				{
					snakeHead = new Point(snakeHead.x + 1, snakeHead.y);
				}
				else
				{
					outOfBounds = true;
				}
			}
			
			if (direction == down)
			{
				if(snakeHead.y + 1 < (frame.getHeight() / 10) - 3 && noBody(snakeHead.x, snakeHead.y + 1))
				{
					snakeHead = new Point(snakeHead.x, snakeHead.y + 1);
				}
				else
				{
					outOfBounds = true;
				}
			}
			
			if (direction == left)
			{
				if(snakeHead.x - 1 >= 0 && noBody(snakeHead.x - 1, snakeHead.y))
				{
					snakeHead = new Point(snakeHead.x - 1, snakeHead.y);
				}
				else
				{
					outOfBounds = true;
				}
			}
			if(parts.size() > snakeLength)
			{
				parts.remove(0);
			}
			
			if(food != null)
			{
				if(snakeHead.x == food.x && snakeHead.y == food.y)
				{
					score++;
					snakeLength++;
					food = new Point(randomNo.nextInt(frame.getWidth() / 10),randomNo.nextInt(frame.getHeight() / 10));
					while(food.x == frame.getWidth() || food.x == 0)
					{
						food = new Point(randomNo.nextInt(frame.getWidth() / 10),randomNo.nextInt(frame.getHeight() / 10));
					}
					while(food.y == frame.getHeight() || food.y == 0)
					{
						food = new Point(randomNo.nextInt(frame.getWidth() / 10),randomNo.nextInt(frame.getHeight() / 10));
					}
					
					if(snakeLength <= 8)
					{
						difficulty = easiest;
					}
					if(snakeLength >= 9 && snakeLength <= 16)
					{
						difficulty = easy;
					}
					if(snakeLength >= 17 && snakeLength <= 24)
					{
						difficulty = normal;
					}
					if(snakeLength >= 25 && snakeLength <= 30)
					{
						difficulty = hard;
					}
					if(snakeLength >= 31)
					{
						difficulty = extreme;
					}
				}
			}
		}		
	}

	private boolean noBody(int x, int y) 
	{
		for(Point partPoint : parts)
		{
			if(partPoint.equals(new Point(x, y)))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public void keyPressed(KeyEvent key) 
	{
		int keyPress = key.getKeyCode();
		
		if(keyPress == KeyEvent.VK_W && direction != down)
		{
			direction = up;
		}
		
		if(keyPress == KeyEvent.VK_D && direction != left)
		{
			direction = right;
		}
		
		if(keyPress == KeyEvent.VK_S && direction != up)
		{
			direction = down;
		}
		
		if(keyPress == KeyEvent.VK_A && direction != right)
		{
			direction = left;
		}
		if(keyPress == KeyEvent.VK_SPACE && outOfBounds == true)
		{
			startSnake();
		}
	}

	@Override
	public void keyReleased(KeyEvent key) 
	{		
	}

	@Override
	public void keyTyped(KeyEvent key) 
	{		
	}
}
