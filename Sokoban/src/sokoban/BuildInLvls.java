package sokoban;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.apache.commons.io.FileUtils;

public class BuildInLvls {

	// 0 - puste pola
    // 1- bloki przez ktore nie mozemy przejsc, ani ich przesuwac
    // 2 - pola na ktore kamienie maja trafic
    // 3 - skrzynki, te ktore poruszamy
    // 4 - to my
    int lvl2[][] = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}, //0
        {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1}, //1
        {1, 0, 0, 0, 0, 1, 0, 3, 0, 0, 3, 0, 0, 1}, //2
        {1, 3, 0, 0, 0, 1, 3, 1, 1, 1, 1, 0, 0, 1}, //3
        {1, 2, 0, 0, 0, 3, 0, 4, 0, 1, 1, 0, 0, 1}, //4
        {1, 0, 2, 0, 0, 1, 0, 1, 0, 0, 3, 0, 1, 1}, //5
        {1, 1, 1, 1, 1, 1, 0, 1, 1, 3, 0, 3, 0, 1}, //6
        {0, 0, 1, 0, 3, 0, 0, 3, 0, 3, 0, 3, 0, 1}, //7
        {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1}, //8
        {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}}; //9
    
  
    
    int lvl3[][] = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
    {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1},
    {1, 0, 0, 0, 0, 1, 0, 3, 0, 0, 3, 0, 0, 1},
    {1, 0, 0, 0, 0, 1, 3, 1, 1, 1, 1, 0, 0, 1},
    {1, 0, 0, 0, 0, 3, 0, 4, 0, 1, 1, 0, 0, 1},
    {1, 0, 2, 0, 0, 1, 0, 1, 0, 0, 3, 0, 1, 1},
    {1, 1, 1, 1, 1, 1, 0, 1, 1, 3, 0, 3, 0, 1},
    {0, 0, 1, 0, 3, 0, 0, 3, 0, 3, 0, 3, 0, 1},
    {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
    {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
    
    Board first = new Board(lvl2, lvl2.length, lvl2[1].length); 
    Board second = new Board(lvl3, lvl3.length, lvl3[1].length); 

    ArrayList<Board> lista = new ArrayList<Board>();

    BuildInLvls() {
        lista.add(first);
        lista.add(second);

        ArrayList<Board> lista2 = loadLvls();
        
        for (Board item : lista2) {
             lista.add(item);
        }
    }

    private ArrayList<Board> loadLvls() {
        ArrayList<Board> listaTemp = new ArrayList<Board>();
        String path = "";
        File directory = new File(".");
        try {
            path = directory.getCanonicalPath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

                 // (path + "/graphics/gameElements/" +  name + ".png");
   //     File folder = new File(path + "/lvls");
        File folder = new File("C:/Users/Ja/Documents/Sokoban/Sokoban/lvls");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {

            for (int i = 0; i < listOfFiles.length; i++) {
                File file = listOfFiles[i];
                if (file.isFile() && file.getName().endsWith(".sokobanlevel")) {
                    

                    	Scanner skaner;
                        try {
                            skaner = new Scanner(file);
                            List<Integer> temp = new ArrayList<>();
                            int x = 0;
                            int y = 0;
                            while(skaner.hasNextInt())
                            {
                                temp.add(skaner.nextInt());
                            }
                            x = temp.get(0);
                            y = temp.get(1);
                           int[][] loadElements = new int[x][y];
                            for (int t = 0; t < x; t++) {
                                for (int j = 0; j < y; j++) {
                                    loadElements[t][j] = temp.get(2+t*x+j);
                                } // end 2nd for
                            } // end 1st for
                            
                            //createNewBoard(x, y, loadElements);
                        
                            Board brd = new Board(loadElements,x,y);
                            listaTemp.add(brd);
                    
                    } // end try
                        catch (IOException ex) {
                        Logger.getLogger(BuildInLvls.class.getName()).log(Level.SEVERE, null, ex);
                    } // end catch
                    

                
       
    } // end if
            } // end for

} return listaTemp;
        } // end loadLvls()



} // end class