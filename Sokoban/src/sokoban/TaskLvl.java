package sokoban;

import java.util.ArrayList;
import java.util.TimerTask;

import javafx.scene.input.TouchPoint.State;

public class TaskLvl extends TimerTask 
{
	BuildInLvls lvls = new BuildInLvls();
	
	
	
	int board[][] = new int[10][14];
	int exits[][] = new int[10][14]; //tak zeby luzno po wyjsciach chodzic
	int poz_HeroY = 4;
	int poz_HeroX = 7;
	
	Board brd = new Board(board,poz_HeroX,poz_HeroY);
	//boolean endGame = false; //czy potrzebne?
	public void run() 
	{
	// sprawdza czy konie danego lvlu nastapil, jesli tak to idz do nastepnego.
		if(endGame()==true && Sokoban.State != Sokoban.State.MENU && Sokoban.State != Sokoban.State.End) 
		{
			Sokoban.State = Sokoban.STATE.NEXT;
		}
		Sokoban.applet.repaint();	
	}
	public boolean endGame() // sprawdza przy kazdym kroku czy zakonczyl gre, czy klocki leza gdzie powinny
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
		if(thisLvl > lvls.lista.size())
		{
			Sokoban.State = Sokoban.State.End;
			return; // wychodzi z metody.
		}
		// Sokoban.State = Sokoban.State.GAME;
		if(brd != lvls.lista.get(thisLvl))
		{
			brd = lvls.lista.get(thisLvl);  //board.clone(lvl2); albo forem
		     
			 poz_HeroY = brd.x;
			 poz_HeroX = brd.y;
                         
			 if(Sokoban.State != Sokoban.State.MENU && Sokoban.State != Sokoban.State.End)
			Sokoban.State = Sokoban.State.GAME;
				
			 //restart exists - tablicy wyjsc.
			 exits = new int[10][14];
		}
		
		
		for (int i=0; i<brd.board.length; i++)
		{
			for (int j=0; j<brd.board[0].length; j++)
			{
				if (brd.board[i][j]==2)
				{
						brd.board[i][j] = 0;
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
			if (brd.board[brd.y][brd.x-1]==0)
			{ 
				brd.board[brd.y][brd.x-1]=4;
				brd.board[brd.y][brd.x]=0;
				brd.x--;
			}
			else if ((brd.board[brd.y][brd.x-1]==3)&&(brd.board[brd.y][brd.x-2]==0))
			{
				brd.board[brd.y][brd.x-2]=3;
				brd.board[brd.y][brd.x-1]=4;
				brd.board[brd.y][brd.x]=0;
				brd.x--;
			}
			break;
		case 'p':
			if (brd.board[brd.y][brd.x+1]==0)
			{ 
				brd.board[brd.y][brd.x+1]=4;
				brd.board[brd.y][brd.x]=0;
				brd.x++;
			}
			else if ((brd.board[brd.y][brd.x+1]==3)&&(brd.board[brd.y][brd.x+2]==0))
			{
				brd.board[brd.y][brd.x+2]=3;
				brd.board[brd.y][brd.x+1]=4;
				brd.board[brd.y][brd.x]=0;
				brd.x++;
			}
			break;
		case 'g':
			if (brd.board[brd.y-1][brd.x]==0)
			{ 
				brd.board[brd.y-1][brd.x]=4;
				brd.board[brd.y][brd.x]=0;
				brd.y--;
			}
			else if ((brd.board[brd.y-1][brd.x]==3)&&(brd.board[brd.y-2][brd.x]==0))
			{
				brd.board[brd.y-2][brd.x]=3;
				brd.board[brd.y-1][brd.x]=4;
				brd.board[brd.y][brd.x]=0;
				brd.y--;
			}
			break;
		case 'd':
			if (brd.board[brd.y+1][brd.x]==0)
			{ 
				brd.board[brd.y+1][brd.x]=4;
				brd.board[brd.y][brd.x]=0;
				brd.y++;
			}
			else if ((brd.board[brd.y+1][brd.x]==3)&&(brd.board[brd.y+2][brd.x]==0))
			{
				brd.board[brd.y+2][brd.x]=3;
				brd.board[brd.y+1][brd.x]=4;
				brd.board[brd.y][brd.x]=0;
				brd.y++;
			}
			break;
	
		}
}
}
