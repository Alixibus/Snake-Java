package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelRender extends JPanel 
{
	
	public static Color chosenColour = new Color(8388608);
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(chosenColour);
		g.fillRect(0, 0, 800,  700);
		Snake snake = Snake.snake;
		g.setColor(Color.BLUE);
		for(Point point : snake.parts)
		{			
			g.fillRect(point.x * 10,  point.y * 10, 10, 10);
		}
		
		g.fillRect(snake.snakeHead.x * 10,  snake.snakeHead.y * 10, 10, 10);
		g.setColor(Color.GREEN);
		g.fillRect(snake.food.x * 10, snake.food.y * 10, 10, 10);
		
		String scoreString = "Score: " + snake.score +" Current Speed: " + snake.difficulty + " Time: " + (snake.tick /25);
		g.drawString(scoreString, (snake.frame.getWidth() / 2) - 80, 10);
	}
}
