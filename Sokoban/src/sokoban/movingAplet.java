package sokoban;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Timer;

public class movingAplet  extends Applet{
	static 	Applet  applet;
	TaskMov task = new TaskMov();
	Timer timer = new Timer();
	public void init()
	{
		applet = this;
	applet.setSize(600,400);	
	applet.setBackground((Color.LIGHT_GRAY));
	timer.scheduleAtFixedRate(task, 10, 10);
	}
	public void paint(Graphics g)
	{
		g.setColor((Color.RED));
		g.fillRect(task.poz1, task.poz2, 100, 100);
//		Font font = new Font("New Times Roman",Font.BOLD, 24);
//		g.setColor((Color.BLUE));
//		g.drawString("hello", 250, 100);
	}
}








