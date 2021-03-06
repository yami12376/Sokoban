package sokoban;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import javax.imageio.ImageIO;
import com.sun.jmx.snmp.tasks.Task;

public class Sokoban extends Applet implements KeyListener {
        private static Sokoban instance = null;

        public static Sokoban Instance() {
           if(instance == null) {
              instance = new Sokoban();
           }
           return instance;
        }
	int rozmiar1 = 14*64;
	int rozmiar2 = 10*64;
	static 	Applet  applet;
	TaskLvl task = new TaskLvl();
	Timer timer = new Timer();
	Image bufor;
	Graphics bg; //rysuje obrazek
	static int stan = 1; //czy zakonczona gra-> stan =  1, gra sie toczy i rysuj plansze.

        
        EndScreen endScreen;
        Menu menu;
	Creator creator;
	Image box;
	Image floor;
	Image hero;
	Image wall;
	Image exit;
	Image placedBox;
        
	int whichLevel  = 0;
        
        public static enum STATE{
            MENU,
            CREATOR,
            GAME,
            NEXT,
            End,
        }
        
        public static STATE State = STATE.MENU;
	
	
    public Image loadFile(String path, String name)
    {
    	Image image = null;
    	
            //     File   imageSource = new File(path + "/graphics/gameElements/" +  name + ".png"); // NetBeans

            	// File   imageSource = new File(path +  name + ".png"); 
    	 File   imageSource = new File(path +  name + ".png");  // Eclipse
		 try {
			   image = ImageIO.read(imageSource);			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
		 return image;
    }
        
	public void init()
	{
            instance = this;
		 File directory = new File (".");
		 String path = "";
            
		 try {
                path = directory.getCanonicalPath();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
            
		 Class<Sokoban> klasa = Sokoban.class; 
                 menu = new Menu();
                 endScreen = new EndScreen();
			 
		wall = loadFile(path, "wall");
		floor = loadFile(path, "floor");
		exit = loadFile(path, "targetSpot");
		box = loadFile(path, "box"); 
		hero = loadFile(path, "player");
			 
		applet = this;
		applet.addKeyListener(this);
                applet.addMouseListener(new MouseInput());
	applet.setSize(rozmiar1, rozmiar2);	
	applet.setBackground((Color.BLUE));
	bufor = createImage(rozmiar1, rozmiar2);
	bg = bufor.getGraphics();
	timer.scheduleAtFixedRate(task, 5, 5);
            task.makeBoard(0);
            creator = new Creator();
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
                switch(stan) {
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
             if (State == State.End) {
                endScreen.render(g);
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
		for (int i=0; i<task.brd.board.length;i++)
		{
			for (int j=0; j<task.brd.board[0].length;j++)
			{
				switch(task.brd.board[i][j])
				{
					case 0: // pod�oga z obrazka -> floor
						g.drawImage(floor, 64*j, 64*i, applet); //w tym aplecie this
						break;
					case 1: // sciana z obrazka -> wall
						g.drawImage(wall, 64*j, 64*i, applet);
						break;								
				}
				if(task.exits[i][j]==2)
				{
					g.drawImage(exit, 64*j, 64*i, this); // rysuje wyjscia
				}
				
				switch(task.brd.board[i][j]) // inaczej po najechaniu skrzynka na X
				// znika�a skyrznka
				{
				case 3: // skrzynka z obrazka -> box
					g.drawImage(box, 64*j, 64*i, applet);
					break;
					// zeby postac byla z przodu
				case 4: // rysuje posta�
					g.drawImage(floor, 64*j, 64*i, applet);
					g.drawImage(hero, 64*j, 64*i, applet);
					break;
				}
			}			
		}
                g.drawString("Level" + (whichLevel+1), 10, 30);
	}


/**
 * W zależności od tego, który klawisz został naciśnięty na klawiaturze(która strzałka)
 * wywoła się task.move('x');   gdzie x -  to analogia do strzałek na klawiszach "wsad"
 */     
	public void keyPressed(KeyEvent e) 
	{
		int keyCode = e.getKeyCode();
            if (State == State.GAME || State == State.NEXT) {
            	 switch( keyCode ) { 
                 case KeyEvent.VK_UP:
                     // handle up 
                	 task.move('g');
                     break;
                 case KeyEvent.VK_DOWN:
                     // handle down 
                	 task.move('d');
                     break;
                 case KeyEvent.VK_LEFT:
                     // handle left
                		task.move('l');
                     break;
                 case KeyEvent.VK_RIGHT :
                	 task.move('p');
                     // handle right
                     break;
              }
            }
	}
            	
                          
		
	
            
	

	public void keyReleased(KeyEvent arg0) {}

	public void keyTyped(KeyEvent arg0) {}
	
	}
