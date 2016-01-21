package sokoban;

public class Board {

	int[][] board;
	int x;
	int y;
        int HeroX;
        int HeroY;
	
	public Board(int[][] board, int x, int y)
	{
		this.board = board;
		this.x = x;
		this.y = y;
                
                for (int j = 0; j < board[1].length; j++)
                {
                    
                for (int i = 0; i < board.length; i++)
                {
                    Integer check = board[i][j];
                    
                   if(  check.equals(4))
                   {
                       HeroX = j;
                       HeroY = i;
                   }   }
	}
                
        }
	
	
}
