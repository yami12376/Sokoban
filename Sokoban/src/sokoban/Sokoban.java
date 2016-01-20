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
    	   public static void main(String[] args) {
        // TODO code application logic here
    }
	int rozmiar1 = 14*40; // Here size fix !
	int rozmiar2 = 10*40; // Here size fix !
	static 	Applet  applet;
	TaskLvl task = new TaskLvl();
	Timer timer = new Timer();
	Image bufor;
	Graphics bg; //rysuje obrazek
	static int stan = 1; //czy zakonczona gra-> stan =  1, gra sie toczy i rysuj plansze.
        
        Menu menu;
	
	Image box;
	Image floor;
	Image hero;
	Image wall;
	Image exit;
	Image placedBox;
	
	int whichLevel  = 0;
        
        public static enum STATE{
            MENU,
            GAME,
            NEXT,
        }
        
        public static STATE State = STATE.MENU;
	

    public Image loadFile(String path, String name)
    {
    	Image image = null;
    	
    	 File   imageSource = new File(path + "/" +  name + ".jpg");
		 try {
			   image = ImageIO.read(imageSource);			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
		 return image;
    }
        
	public void init()
	{
		 File directory = new File (".");
		 String path = "";
		 try {
			 	path =     directory.getCanonicalPath();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		 Class<Sokoban> klasa = Sokoban.class; 
                 menu = new Menu();
                 
		wall = loadFile(path, "Wall");
		floor = loadFile(path, "floor");
		exit = loadFile(path, "point");
		box = loadFile(path, "Chest"); 
		hero = loadFile(path, "player");
		
		applet = this;
		applet.addKeyListener(this);
                applet.addMouseListener(new MouseInput());
	applet.setSize(rozmiar1, rozmiar2);	
	applet.setBackground((Color.BLUE));
	bufor = createImage(rozmiar1, rozmiar2);
	bg = bufor.getGraphics();
	timer.scheduleAtFixedRate(task, 5, 5);

      task.makeBoard(0); // Here X, Y
	}
	public void update(Graphics g) //update-repaint trafia tu
	{
                bg.clearRect(0, 0, rozmiar1, rozmiar2); //czyscimy bufor
		paint(bg); //do obrazka w pamieci->bufora rysujemy
		g.drawImage(bufor, 0,0, applet);
		 //likwidujemy efekt migotania, rysujemy bufor z pami�ci
	}
	
	public void paint(Graphics g)//jak nie bylo update-repaint trafiamy tu.
	{
            if (State == STATE.GAME) {
              		switch(stan)
		{
			case 1:
				drawLevel(g);
				break;			
		}
            }
            if(State == State.NEXT) { // odpala X razy metode nizej.
            	NextLvl(g);
            }
            if (State == State.MENU) {
                menu.render(g);
            }
	}
	public void endScreen(Graphics g) //wyswietla komunikat/grafike jak przeszlismy gre
	{
		 // task.makeBoard(true);
		//  drawLevel(g);
		
	}
	public void NextLvl(Graphics g) 
	{
		whichLevel++;
		//applet.repaint();

		 task.makeBoard(whichLevel);
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
					case 0: // pod�oga z obrazka -> floor
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
				// znika�a skyrznka
				{
				case 3: // skrzynka z obrazka -> box
					g.drawImage(box, 40*j, 40*i, applet);
					break;
					// zeby postac byla z przodu
				case 4: // rysuje posta�
					g.drawImage(floor, 40*j, 40*i, applet);
					g.drawImage(hero, 40*j, 40*i, applet);
					break;
				}
			}
			
			
		}
	}

	public void keyPressed(KeyEvent arg0) 
	{
            if (State == State.GAME || State == State.NEXT) {
                            switch(arg0.getKeyCode())
		{
		//arg0.getKeyCode()  37 <-       38 gora  -> 39  , dol 40 
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
		}
            }
	}

	public void keyReleased(KeyEvent arg0)
	{	
	}
	public void keyTyped(KeyEvent arg0) 
	{
	}
	
	}