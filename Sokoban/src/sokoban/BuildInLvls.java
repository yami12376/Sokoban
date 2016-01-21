package sokoban;

import java.util.ArrayList;

public class BuildInLvls {		
	// 0 - puste pola
        // 1- bloki przez ktore nie mozemy przejsc, ani ich przesuwac
        // 2 - pola na ktore kamienie maja trafic
        // 3 - skrzynki, te ktore poruszamy
        // 4 - to my
		
        int lvl2[][] = {
            {1,1,1,1,1,1,1,1,1,1,1,1,0,0}, //0
            {1,0,0,0,0,1,0,0,0,0,0,1,1,1}, //1
            {1,0,0,0,0,1,0,3,0,0,3,0,0,1}, //2
            {1,3,0,0,0,1,3,1,1,1,1,0,0,1}, //3
            {1,2,0,0,0,3,0,4,0,1,1,0,0,1}, //4
            {1,0,2,0,0,1,0,1,0,0,3,0,1,1}, //5
            {1,1,1,1,1,1,0,1,1,3,0,3,0,1}, //6
            {0,0,1,0,3,0,0,3,0,3,0,3,0,1}, //7
            {0,0,1,0,0,0,0,1,0,0,0,0,0,1}, //8
            {0,0,1,1,1,1,1,1,1,1,1,1,1,1}}; //9
				
				
	int lvl3[][] = {
            {1,1,1,1,1,1,1,1,1,1,1,1,0,0},
            {1,0,0,0,0,1,0,0,0,0,0,1,1,1},
            {1,0,0,0,0,1,0,3,0,0,3,0,0,1},
            {1,0,0,0,0,1,3,1,1,1,1,0,0,1},
            {1,0,0,0,0,3,0,4,0,1,1,0,0,1},
            {1,0,2,0,0,1,0,1,0,0,3,0,1,1},
            {1,1,1,1,1,1,0,1,1,3,0,3,0,1},
            {0,0,1,0,3,0,0,3,0,3,0,3,0,1},
            {0,0,1,0,0,0,0,1,0,0,0,0,0,1},
            {0,0,1,1,1,1,1,1,1,1,1,1,1,1}};
		
	 ArrayList<int[][]> lista = new ArrayList<int[][]>();
	
	 BuildInLvls() {
            lista.add(lvl2);
            lista.add(lvl3);
	 }
	 
}

