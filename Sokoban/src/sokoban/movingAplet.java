package sokoban;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Timer;

public class movingAplet  extends Applet{
	int rozmiar1 = 800;
	int rozmiar2 = 600;
	static 	Applet  applet;
	TaskMov task = new TaskMov();
	Timer timer = new Timer();
	Image bufor;
	Graphics bg; //rysuje obrazek
	public void init()
	{
		applet = this;
	applet.setSize(rozmiar1, rozmiar2);	
	applet.setBackground((Color.LIGHT_GRAY));
	bufor = createImage(rozmiar1, rozmiar2);
	bg = bufor.getGraphics();
	timer.scheduleAtFixedRate(task, 5, 5);
	}
	public void update(Graphics g) //update-repaint trafia tu
	{
		bg.clearRect(0, 0, rozmiar1, rozmiar2); //czyœcimy bufor
		paint(bg); //do obrazka w pamiêci->bufora rysujemy
		g.drawImage(bufor, 0,0, applet);
		 //likwidujemy efekt migotania, rysujemy bufor z pamiêci
	}
	
	public void paint(Graphics g)//jak nie by³o update-repaint trafiamy tu.
	{
		g.setColor((Color.RED));
		g.fillRect(task.poz1, task.poz2, 100, 100);
//		Font font = new Font("New Times Roman",Font.BOLD, 24);
//		g.setColor((Color.BLUE));
//		g.drawString("hello", 250, 100);
	}
}








