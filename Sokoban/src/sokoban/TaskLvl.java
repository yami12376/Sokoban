package sokoban;

import java.util.TimerTask;

public class TaskLvl extends TimerTask 
{
	int board[][] = new int[10][14];
	public void run() 
	{
		Sokoban.applet.repaint();	
	}
	public void makeBoard()
	{ 
		// 0 - puste pola
		// 1- bloki przez ktore nie mozemy przejsc, ani ich przesuwac
		// 2 - pola na ktore kamienie maja trafic
		// 3 - skrzynki, te ktore poruszamy
		// 4 - to my
		
		int lvl2[][] = {
				{1,1,1,1,1,1,1,1,1,1,1,1,0,0},
				{1,2,2,0,0,1,0,0,0,0,0,1,1,1},
				{1,2,2,0,0,1,0,3,0,0,3,0,0,1},
				{1,2,2,0,0,1,3,1,1,1,1,0,0,1},
				{1,2,2,0,0,0,0,4,0,1,1,0,0,1},
				{1,2,2,0,0,1,0,1,0,0,3,0,1,1},
				{1,1,1,1,1,1,0,1,1,3,0,3,0,1},
				{0,0,1,0,3,0,0,3,0,3,0,3,0,1},
				{0,0,1,0,0,0,0,1,0,0,0,0,0,1},
				{0,0,1,1,1,1,1,1,1,1,1,1,1,1}
		};
		board = lvl2;  //board.clone(lvl2); albo forem
		
	}

}
