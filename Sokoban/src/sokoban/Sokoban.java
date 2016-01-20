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
import java.awt.Label;
import javax.swing.JPanel;

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
	static int stan = 1; //czy zakonczona gra-> 1 sie toczy i rysuj plansze.

        Menu menu;
	Creator creator;
	Image box;
	Image floor;
	Image hero;
	Image wall;
	Image exit;
	Image placedBox;
        
        public static enum STATE{
            MENU,
            CREATOR,
            GAME,
        }
        
        public static STATE State = STATE.MENU;
	
	
	public void init()
	{
            instance = this;
            File directory = new File (".");
            String path = "";
            
            try {
                path = directory.getCanonicalPath();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
            Class<Sokoban> klasa = Sokoban.class; 
            menu = new Menu();
            
            File sourceimage = new File(path+"/graphics/wall.png");
            try {
                Image image = ImageIO.read(sourceimage);
                wall = image;
            } catch (IOException e) {
                e.printStackTrace();
            }

            File sourceimage2 = new File(path + "/graphics/floor.png");
            try {
                Image image = ImageIO.read(sourceimage2);
                floor = image;
            } catch (IOException e) {
                e.printStackTrace();
            }


            File sourceimage3 = new File(path + "/graphics/targetSpot.png");
            try {
                Image image = ImageIO.read(sourceimage3);
                exit = image;
            } catch (IOException e) {
                e.printStackTrace();
            }

            File sourceimage4 = new File(path + "/graphics/box.png");
            try {
                Image image = ImageIO.read(sourceimage4);
                box = image;
            } catch (IOException e) {
                e.printStackTrace();
            }

            File   sourceimage5 = new File(path +"/graphics/player.png");
            try {
                Image image = ImageIO.read(sourceimage5);
                hero = image;
            } catch (IOException e) {
                e.printStackTrace();
            }


            applet = this;
            applet.addKeyListener(this);
            applet.addMouseListener(new MouseInput());
            applet.setSize(rozmiar1, rozmiar2);	
            applet.setBackground((Color.BLUE));
            bufor = createImage(rozmiar1, rozmiar2);
            bg = bufor.getGraphics();
            timer.scheduleAtFixedRate(task, 5, 5);
            task.makeBoard();
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
                    case 3:
                        endScreen(g);
                        break;                          
		}
            } else if (State == State.MENU) {
                menu.render(g);
            }           
	}
	public void endScreen(Graphics g) //wyswietla komunikat/grafike jak przeszlismy gre
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

                    switch(task.board[i][j]) // inaczej po najechaniu skrzynka na X
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
            g.drawString("Level 1", 10, 30);
	}

	public void keyPressed(KeyEvent arg0) 
	{
            if (State == State.GAME) {
                switch(arg0.getKeyCode())
		{
                    //arg0.getKeyCode()  37 <-       38 gora  -> 39  d� 40 
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
                    //case default :
                    //break;		
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



