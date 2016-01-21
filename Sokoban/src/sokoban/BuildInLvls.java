package sokoban;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

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
				
				
	int lvl3[][]=				{{1,1,1,1,1,1,1,1,1,1,1,1,0,0},
				{1,0,0,0,0,1,0,0,0,0,0,1,1,1},
				{1,0,0,0,0,1,0,3,0,0,3,0,0,1},
				{1,0,0,0,0,1,3,1,1,1,1,0,0,1},
				{1,0,0,0,0,3,0,4,0,1,1,0,0,1},
				{1,0,2,0,0,1,0,1,0,0,3,0,1,1},
				{1,1,1,1,1,1,0,1,1,3,0,3,0,1},
				{0,0,1,0,3,0,0,3,0,3,0,3,0,1},
				{0,0,1,0,0,0,0,1,0,0,0,0,0,1},
				{0,0,1,1,1,1,1,1,1,1,1,1,1,1}	};
		
	 ArrayList<int[][]> lista = new ArrayList<int[][]>();
	
	 BuildInLvls() {
		 lista.add(lvl2);
		 lista.add(lvl3);
                 
                  ArrayList<int[][]> lista = loadLvls();
	 }
	 
             private ArrayList<int[][]> loadLvls()  {
        ArrayList<int[][]> lista = new ArrayList<int[][]>();
        String path = "";
             File directory = new File (".");
		 try {
                path = directory.getCanonicalPath();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
                 
                 // (path + "/graphics/gameElements/" +  name + ".png");
        
        File folder = new File(path + "/lvls");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            
      
        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith(".sokobanlevel")) {
                try {
                    String content = FileUtils.readFileToString(file);
                    /* do somthing with content */
                } catch (IOException ex) {
                    Logger.getLogger(BuildInLvls.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
  }
        return lista;
    }

         
	 }

