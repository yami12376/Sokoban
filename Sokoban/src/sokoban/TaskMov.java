package sokoban;

import java.util.TimerTask;

public class TaskMov extends TimerTask
{
	int poz1 = 0;
	int poz2 = 0;
	int kierunek = 1;
	int licznik = 0;
	public void run()
	{
		switch(kierunek)
		{
		case 1:
			licznik++;
			poz1++;
			if(licznik==200)
			{
				licznik = 0;
				kierunek = 2;
			}
			break;
		case 2:
			licznik++;
			poz2++;
			if(licznik==200)
			{
				licznik = 0;
				kierunek = 3;
			}
			break;
		case 3:
			licznik++;
			poz1--;
			if(licznik==200)
			{
				licznik = 0;
				kierunek = 4;
			}
			break;
		case 4:
			licznik++;
			poz2--;
			if(licznik==200)
			{
				licznik = 0;
				kierunek = 1;
			}
			break;
		}
//		poz1++;
//		poz2++;
		
		movingAplet.applet.repaint();
	}

}
