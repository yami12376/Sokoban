package sokoban;

import java.util.ArrayList;
import java.util.TimerTask;

public class TaskLvl extends TimerTask 
{
	BuildInLvls lvls = new BuildInLvls();
	
	
	int board[][] = new int[10][14];
	int exits[][] = new int[10][14]; //tak zeby luzno po wyjsciach chodzic
	int poz_HeroY = 4;
	int poz_HeroX = 7;
	//boolean endGame = false; //czy potrzebne?
	public void run() 
	{
		
		if(endGame()==true && Sokoban.State != Sokoban.State.MENU) //sprawdza czy konie gry nastapil
		{
			Sokoban.State = Sokoban.STATE.NEXT;
			//Sokoban.re
			//Sokoban.stan =  2; // here next LVL todo
		}
		Sokoban.applet.repaint();	
	}
	public boolean endGame() // sprawdza przy ka¿dym kroku czy zakoñczyæ grê, czy klocki le¿¹ gdzie powinny
	{
		for (int i=0; i<board.length; i++)
		{
			for (int j=0; j<board[0].length; j++)
			{
				if ((exits[i][j]==2) &&(board[i][j]!=3))//znajduje sie wyjscie i skrzynka
				{
						return false;
				}
			}
		}
		return true;
	}
	public void makeBoard(int thisLvl)
	{ 
		
		// Sokoban.State = Sokoban.State.GAME;
		if(board != lvls.lista.get(thisLvl))
		{
			board = lvls.lista.get(thisLvl);  //board.clone(lvl2); albo forem
			
			 poz_HeroY = 4;
			 poz_HeroX = 7;	
			 if(Sokoban.State != Sokoban.State.MENU)
			Sokoban.State = Sokoban.State.GAME;
			 
			 //restart exists - tablicy wyjsc.
			 exits = new int[10][14];
		}
		

		for (int i=0; i<board.length; i++)
		{
			for (int j=0; j<board[0].length; j++)
			{
				if (board[i][j]==2)
				{
						board[i][j] = 0;
						exits[i][j] = 2;
				}
			}
		}
	}
	public void move(char where)
	{
		switch(where)  // 3 ,1
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
