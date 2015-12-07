package sokoban;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;

import javax.imageio.ImageIO;

import com.sun.jmx.snmp.tasks.Task;

public class Sokoban extends Applet implements KeyListener {
	int rozmiar1 = 14*40;
	int rozmiar2 = 10*40;
	static 	Applet  applet;
	TaskLvl task = new TaskLvl();
	Timer timer = new Timer();
	Image bufor;
	Graphics bg; //rysuje obrazek
	static int stan = 1; //czy zakonczona gra-> 1 siê toczy i rysuj plansze.
	
	Image box;
	Image floor;
	Image hero;
	Image wall;
	Image exit;
	Image placedBox;
	
	
	public void init()
	{
		 Class<Sokoban> klasa = Sokoban.class; 
		 File sourceimage = new File("C:/Users/Ja/Documents/Sokoban/Wall.jpg");
		 try {
			Image image = ImageIO.read(sourceimage);
			wall = image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 
		 File   sourceimage2 = new File("C:/Users/Ja/Documents/Sokoban/floor.jpg");
		 try {
			Image image = ImageIO.read(sourceimage2);
			floor = image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		 
		 File   sourceimage3 = new File("C:/Users/Ja/Documents/Sokoban/point.jpg");
			 try {
				Image image = ImageIO.read(sourceimage3);
				exit = image;
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
			 File   sourceimage4 = new File("C:/Users/Ja/Documents/Sokoban/Chest.jpg");
			 try {
				Image image = ImageIO.read(sourceimage4);
				box = image;
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
			 File   sourceimage5 = new File("C:/Users/Ja/Documents/Sokoban/player.jpg");
			 try {
				Image image = ImageIO.read(sourceimage5);
				   hero = image;
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
		 
		applet = this;
		applet.addKeyListener(this);
	applet.setSize(rozmiar1, rozmiar2);	
	applet.setBackground((Color.LIGHT_GRAY));
	bufor = createImage(rozmiar1, rozmiar2);
	bg = bufor.getGraphics();
	timer.scheduleAtFixedRate(task, 5, 5);
	

//	placedBox = getImage(getDocumentBase(),"Images/chestPlaced.jpg");
	
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
		switch(stan)
		{
			case 1:
				drawLevel(g);
				break;
			case 2:
				endScreen(g);
				break;
		}
     
	}
	public void endScreen(Graphics g) //wyœwietla komunikat/grafike jak przeœlismy grê
	{
		
	}
	public void drawLevel(Graphics g)
	{
		for (int i=0; i<task.board.length;i++)
		{
			for (int j=0; j<task.board[0].length;j++)
			{
				
				switch(task.board[i][j])
				{
					case 0: // pod³oga z obrazka -> floor
						g.drawImage(floor, 40*j, 40*i, applet); //w tym aplecie this
						break;
					case 1: // sciana z obrazka -> wall
						g.drawImage(wall, 40*j, 40*i, applet);
						break;

					
					
				}
				if(task.exits[i][j]==2)
				{
					g.drawImage(exit, 40*j, 40*i, this); // rysuje wyjscia
				}
				
				switch(task.board[i][j]) // inaczej po najechaniu skrzynka na X
				// znika³a skyrznka
				{
				case 3: // skrzynka z obrazka -> box
					g.drawImage(box, 40*j, 40*i, applet);
					break;
					// zeby postac byla z przodu
				case 4: // rysuje postaæ
					g.drawImage(floor, 40*j, 40*i, applet);
					g.drawImage(hero, 40*j, 40*i, applet);
					break;
				}
			}
			
			
		}
	}

	public void keyPressed(KeyEvent arg0) 
	{

		switch(arg0.getKeyCode())
		{
		//arg0.getKeyCode()  37 <-       38 góra  -> 39  dó³ 40 
			case 37: // w lewo strzalka
				task.move('l');
				break;
			case 38: // do gory strzalka
				task.move('g');
				break;
			case 39: // w prawo strzalka
				task.move('p');
				break;
			case 40: // do dolu strzalka
				task.move('d');
				break;
//			case default :
//				break;
		
		}
	}

	public void keyReleased(KeyEvent arg0)
	{

		
	}

	public void keyTyped(KeyEvent arg0) 
	{
	
		
	}
		
	}



