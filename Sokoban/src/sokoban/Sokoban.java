package sokoban;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Timer;

public class Sokoban extends Applet {
	int rozmiar1 = 14*40;
	int rozmiar2 = 10*40;
	static 	Applet  applet;
	TaskLvl task = new TaskLvl();
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
	
      task.makeBoard();
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
       drawLevel(g);
	}
	public void drawLevel(Graphics g)
	{
		for (int i=0; i<task.board.length;i++)
		{
			for (int j=0; j<task.board[0].length;j++)
			{
				switch(task.board[i][j])
				{
					case 1:
						g.setColor(Color.RED);
						g.fillRect(40*j, 40*i, 40, 40);
						break;
					case 2:
						g.setColor(Color.BLUE);
						g.fillRect(40*j, 40*i, 40, 40);
						break;
					case 3:
						g.setColor(Color.GREEN);
						g.fillOval(40*j, 40*i, 40, 40);
						break;
					case 4:
						g.setColor(Color.YELLOW);
						g.fillOval(40*j, 40*i, 40, 40);
						break;
				}
			}
		}
	}
		
	}



