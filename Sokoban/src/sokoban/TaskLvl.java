package sokoban;

import java.util.TimerTask;

public class TaskLvl extends TimerTask 
{
	int board[][] = new int[10][14];
	int poz_HeroY = 4;
	int poz_HeroX = 7;
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
				{1,2,2,0,0,3,0,4,0,1,1,0,0,1},
				{1,2,2,0,0,1,0,1,0,0,3,0,1,1},
				{1,1,1,1,1,1,0,1,1,3,0,3,0,1},
				{0,0,1,0,3,0,0,3,0,3,0,3,0,1},
				{0,0,1,0,0,0,0,1,0,0,0,0,0,1},
				{0,0,1,1,1,1,1,1,1,1,1,1,1,1}
		};
		board = lvl2;  //board.clone(lvl2); albo forem
	}
	public void move(char where)
	{
		switch(where)
		{
			case 'l':
				if (board[poz_HeroY][poz_HeroX-1]==0)
				{ 
					board[poz_HeroY][poz_HeroX-1]=4;
					board[poz_HeroY][poz_HeroX]=0;
					poz_HeroX--;
				}
				else if ((board[poz_HeroY][poz_HeroX-1]==3)&&(board[poz_HeroY][poz_HeroX-2]==0))
				{
					board[poz_HeroY][poz_HeroX-2]=3;
					board[poz_HeroY][poz_HeroX-1]=4;
					board[poz_HeroY][poz_HeroX]=0;
					poz_HeroX--;
				}
				break;
			case 'p':
				if (board[poz_HeroY][poz_HeroX+1]==0)
				{ 
					board[poz_HeroY][poz_HeroX+1]=4;
					board[poz_HeroY][poz_HeroX]=0;
					poz_HeroX++;
				}
				else if ((board[poz_HeroY][poz_HeroX+1]==3)&&(board[poz_HeroY][poz_HeroX+2]==0))
				{
					board[poz_HeroY][poz_HeroX+2]=3;
					board[poz_HeroY][poz_HeroX+1]=4;
					board[poz_HeroY][poz_HeroX]=0;
					poz_HeroX++;
				}
				break;
			case 'g':
				if (board[poz_HeroY-1][poz_HeroX]==0)
				{ 
					board[poz_HeroY-1][poz_HeroX]=4;
					board[poz_HeroY][poz_HeroX]=0;
					poz_HeroY--;
				}
				else if ((board[poz_HeroY-1][poz_HeroX]==3)&&(board[poz_HeroY-2][poz_HeroX]==0))
				{
					board[poz_HeroY-2][poz_HeroX]=3;
					board[poz_HeroY-1][poz_HeroX]=4;
					board[poz_HeroY][poz_HeroX]=0;
					poz_HeroY--;
				}
				break;
			case 'd':
				if (board[poz_HeroY+1][poz_HeroX]==0)
				{ 
					board[poz_HeroY+1][poz_HeroX]=4;
					board[poz_HeroY][poz_HeroX]=0;
					poz_HeroY++;
				}
				else if ((board[poz_HeroY+1][poz_HeroX]==3)&&(board[poz_HeroY+2][poz_HeroX]==0))
				{
					board[poz_HeroY+2][poz_HeroX]=3;
					board[poz_HeroY+1][poz_HeroX]=4;
					board[poz_HeroY][poz_HeroX]=0;
					poz_HeroY++;
				}
				break;
		
  		}
	}
	
	
}
