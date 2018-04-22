package snake;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelRender extends JPanel 
{
	
	public static Color chosenColour = new Color(8388608);
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(chosenColour);
		g.fillRect(0, 0, 600,  500);
	}
}
