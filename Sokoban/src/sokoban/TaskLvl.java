package sokoban;

import java.util.TimerTask;

public class TaskLvl extends TimerTask 
{
	int board[][] = new int[10][14];
	int exits[][] = new int[10][14]; //tak zeby luzno po wyjsciach chodzic
	int poz_HeroY = 4;
	int poz_HeroX = 7;
	//boolean endGame = false; //czy potrzebne?
	public void run() 
	{
		if(endGame()==true) //sprawdza czy konie gry nast¹pi³
		{
			
			Sokoban.stan =  2;
		}
		Sokoban.applet.repaint();	
	}
	public boolean endGame()
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
	public void makeBoard()
	{ 
		// 0 - puste pola
		// 1- bloki przez ktore nie mozemy przejsc, ani ich przesuwac
		// 2 - pola na ktore kamienie maja trafic
		// 3 - skrzynki, te ktore poruszamy
		// 4 - to my
		
		int lvl2[][] = {
				{1,1,1,1,1,1,1,1,1,1,1,1,0,0},
				{1,0,0,0,0,1,0,0,0,0,0,1,1,1},
				{1,0,0,0,0,1,0,3,0,0,3,0,0,1},
				{1,0,0,0,0,1,3,1,1,1,1,0,0,1},
				{1,0,0,0,0,3,0,4,0,1,1,0,0,1},
				{1,0,2,0,0,1,0,1,0,0,3,0,1,1},
				{1,1,1,1,1,1,0,1,1,3,0,3,0,1},
				{0,0,1,0,3,0,0,3,0,3,0,3,0,1},
				{0,0,1,0,0,0,0,1,0,0,0,0,0,1},
				{0,0,1,1,1,1,1,1,1,1,1,1,1,1}
				
				
//				{1,1,1,1,1,1,1,1,1,1,1,1,0,0},
//				{1,2,2,0,0,1,0,0,0,0,0,1,1,1},
//				{1,2,2,0,0,1,0,3,0,0,3,0,0,1},
//				{1,2,2,0,0,1,3,1,1,1,1,0,0,1},
//				{1,2,2,0,0,3,0,4,0,1,1,0,0,1},
//				{1,2,2,0,0,1,0,1,0,0,3,0,1,1},
//				{1,1,1,1,1,1,0,1,1,3,0,3,0,1},
//				{0,0,1,0,3,0,0,3,0,3,0,3,0,1},
//				{0,0,1,0,0,0,0,1,0,0,0,0,0,1},
//				{0,0,1,1,1,1,1,1,1,1,1,1,1,1}
		};
		board = lvl2;  //board.clone(lvl2); albo forem
		
//		for (int i=0; i<board.length; i++)
//		{
//			for (int j=0; j<board[0].length; j++)
//			{
//				board[i][j]= lvl2[i][j];
//			}
//		}
		
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
