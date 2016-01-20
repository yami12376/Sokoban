/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;

/**
 *
 * @author Jaworski
 */
public class Creator {
    
    JFrame menuFrame, previewFrame, helpFrame;    
    JPanel mainMenuPanel, subNorthPanel, subNorthNorthPanel, subNorthSouthPanel, subMiddlePanel, subSouthPanel, previewPanel, helpPanel; 
    JButton loadBoardButton, makeNewBoardButton, previewButton, saveBoardButton, helpButton;  
    JLabel xLabel, yLabel, floorLabel, wallLabel, targetSpotLabel, boxLabel, playerLabel, helpLabel;
    JTextField textFieldXSize, textFieldYSize;  
    JTextField[][] mapElements;
    JLabel[][] previewElements;
    ImageIcon wallImageIcon, floorImageIcon, targetSpotImageIcon, playerImageIcon, boxImageIcon, wallImage, floorImage, targetSpotImage, boxImage, playerImage;
    AbstractDocument xSizeDocument, ySizeDocument, boardDocument;
    DocumentFilter sizeInputFilter, boardInputFilter;
    Applet applet;
    boolean isBoardCreated;
    final int maxSizeInputLength = 2;
    final int maxBoardInputLength = 1;

    
    public Creator()
    {
        getGraphics();
        createGUI();
        addListeners();
    }
    
    private void createGUI()
    {
        menuFrame = new JFrame("Sokoban Creator");
        menuFrame.setSize(800,600);
        menuFrame.setLocationRelativeTo(null);
        
        previewFrame = new JFrame("Sokoban Creator");  
                
        previewPanel = new JPanel();
        previewFrame.add(previewPanel);
                      
        helpFrame = new JFrame("Creator Help Information");
        helpFrame.setSize(400,200);
        helpFrame.setLocationRelativeTo(null);
        
        helpPanel = new JPanel();
        helpFrame.add(helpPanel);
        helpPanel.setLayout(new BorderLayout());
        helpLabel = new JLabel();
        helpLabel.setHorizontalAlignment(JTextField.CENTER);
        setHelpInformation(helpLabel);
        helpPanel.add(helpLabel, BorderLayout.CENTER);
        
        mainMenuPanel = new JPanel();
        subNorthPanel = new JPanel();
        subNorthNorthPanel = new JPanel();
        subNorthSouthPanel = new JPanel();
        subMiddlePanel = new JPanel();
        subSouthPanel = new JPanel();
        
        loadBoardButton = new JButton("Load board");
        makeNewBoardButton = new JButton("Make new board");
        previewButton = new JButton("Preview your board");
        saveBoardButton = new JButton("Save board");
        helpButton = new JButton("Help");
        
        menuFrame.add(mainMenuPanel);
        mainMenuPanel.setLayout(new BorderLayout());
        mainMenuPanel.add(subNorthPanel, BorderLayout.NORTH);
        mainMenuPanel.add(subMiddlePanel);
        mainMenuPanel.add(subSouthPanel, BorderLayout.SOUTH);           
        
        subNorthPanel.setLayout(new BorderLayout());
        subNorthPanel.add(subNorthNorthPanel, BorderLayout.NORTH);
        subNorthPanel.add(subNorthSouthPanel, BorderLayout.SOUTH);

        subNorthNorthPanel.setLayout(new GridLayout(1,4));
        subNorthNorthPanel.add(loadBoardButton);
        subNorthNorthPanel.add(makeNewBoardButton);
        subNorthNorthPanel.add(previewButton);
        subNorthNorthPanel.add(saveBoardButton);
        subNorthNorthPanel.add(helpButton);
        
        xLabel = new JLabel("Put X size here.");
        yLabel = new JLabel("Put Y size here.");       
        
        sizeInputFilter = new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offs, int length,
                String str, AttributeSet a) throws BadLocationException {

                String text = fb.getDocument().getText(0,
                        fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length() - length) <= maxSizeInputLength
                        && text.matches("^(1[0-5]?|[2-9])$")) {
                    super.replace(fb, offs, length, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
            @Override
            public void insertString(FilterBypass fb, int offs, String str,
                AttributeSet a) throws BadLocationException {

                String text = fb.getDocument().getText(0,
                        fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length()) <= maxSizeInputLength
                        && text.matches("^(1[0-5]?|[2-9])$")) {
                    super.insertString(fb, offs, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        };      

        textFieldXSize = new JTextField(2);       
        xSizeDocument = (AbstractDocument) textFieldXSize.getDocument();
        xSizeDocument.setDocumentFilter(sizeInputFilter);
        textFieldXSize.setHorizontalAlignment(JTextField.CENTER);
        textFieldYSize = new JTextField(2);
        ySizeDocument = (AbstractDocument) textFieldYSize.getDocument();
        ySizeDocument.setDocumentFilter(sizeInputFilter);
        textFieldYSize.setHorizontalAlignment(JTextField.CENTER);
        
        boardInputFilter = new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offs, int length,
                String str, AttributeSet a) throws BadLocationException {

                String text = fb.getDocument().getText(0,
                        fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length() - length) <= maxBoardInputLength
                        && text.matches("^[1-4]$")) {
                    super.replace(fb, offs, length, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
            @Override
            public void insertString(FilterBypass fb, int offs, String str,
                AttributeSet a) throws BadLocationException {

                String text = fb.getDocument().getText(0,
                        fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length()) <= maxBoardInputLength
                        && text.matches("^[0-4]$")) {
                    super.insertString(fb, offs, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        }; 
        
        subNorthSouthPanel.add(xLabel);
        subNorthSouthPanel.add(textFieldXSize);
        subNorthSouthPanel.add(yLabel);
        subNorthSouthPanel.add(textFieldYSize); 
        
        floorLabel = new JLabel("Empty spot - 0", floorImageIcon,  JLabel.CENTER);
        wallLabel = new JLabel("Wall - 1", wallImageIcon, JLabel.CENTER);
        targetSpotLabel = new JLabel("Target spot - 2", targetSpotImageIcon, JLabel.CENTER);
        boxLabel = new JLabel("Box - 3", boxImageIcon, JLabel.CENTER);
        playerLabel = new JLabel("Hero - 4", playerImageIcon, JLabel.CENTER);
        
        subSouthPanel.setLayout(new GridLayout(1, 5));
        subSouthPanel.add(floorLabel);
        subSouthPanel.add(wallLabel);
        subSouthPanel.add(targetSpotLabel);
        subSouthPanel.add(boxLabel);
        subSouthPanel.add(playerLabel);
        
        menuFrame.validate();
    }
    
    private void addListeners()
    {
        makeNewBoardButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                createNewBoard();
            }           
        });
        
        saveBoardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    saveNewBoard();
                } catch (IOException ex) {
                    Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        loadBoardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
                loadBoard();
            }
            
        });
        
        previewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
                previewCurrentBoard();
            }
            
        });
        
        helpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
                showHelpInformation();
            }
            
        });
    }
    
    
    private ImageIcon loadFile(String path, String name)
    {
    	Image image = null;
    	
    	 File   imageSource = new File(path + "/" +  name + ".png");
		 try {
			   image = ImageIO.read(imageSource);			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 ImageIcon imageIcon = new ImageIcon(image);
		 
		 return imageIcon;
    }
    
    private void getGraphics()
    {
   
        File directory = new File (".");
        String path = "";
        
        try {
            path = directory.getCanonicalPath();
        } catch (IOException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*File sourceimage = new File(path + "/graphics/gameElements/wall.png");
        try {
            Image image = ImageIO.read(sourceimage);
            wallImage = image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        File sourceimage2 = new File(path + "/graphics/gameElements/floor.png");
        try {
            Image image = ImageIO.read(sourceimage2);
            floorImage = image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        File sourceimage3 = new File(path + "/graphics/gameElements/targetSpot.png");
        try {
            Image image = ImageIO.read(sourceimage3);
            targetSpotImage = image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        File sourceimage4 = new File(path + "/graphics/gameElements/box.png");
        try {
            Image image = ImageIO.read(sourceimage4);
            boxImage = image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        File sourceimage5 = new File(path + "/graphics/gameElements/player.png");
        try {
            Image image = ImageIO.read(sourceimage5);
            playerImage = image;
        } catch (IOException e) {
            e.printStackTrace();
        }*/
		
        wallImage = loadFile(path, "wall");
		floorImage = loadFile(path, "floor");
		targetSpotImage = loadFile(path, "targetSpot");
		boxImage = loadFile(path, "box"); 
		playerImage = loadFile(path, "player");
        
		wallImageIcon = loadFile(path, "wall");
		floorImageIcon = loadFile(path, "floor");
		targetSpotImageIcon = loadFile(path, "targetSpot");
		boxImageIcon = loadFile(path, "box"); 
		playerImageIcon = loadFile(path, "player");
	        
        
      
//		
//		wallImage = new ImageIcon(path + "\\graphics\\gameElements\\wall.png");
//        floorImage = new ImageIcon(path + "\\graphics\\gameElements\\floor.png");
//        targetSpotImage = new ImageIcon(path + "\\graphics\\gameElements\\targetSpot.png");
//        boxImage = new ImageIcon(path + "\\graphics\\gameElements\\box.png");       
//        playerImage = new ImageIcon(path + "\\graphics\\gameElements\\player.png");
//        
//        wallImageIcon = new ImageIcon(path + "\\graphics\\icons\\wall.png");
//        floorImageIcon = new ImageIcon(path + "\\graphics\\icons\\floor.png");
//        targetSpotImageIcon = new ImageIcon(path + "\\graphics\\icons\\targetSpot.png");
//        boxImageIcon = new ImageIcon(path + "\\graphics\\icons\\box.png");       
//        playerImageIcon = new ImageIcon(path + "\\graphics\\icons\\player.png");		
    }
    
    private void createNewBoard()
    {
        if (textFieldXSize.getText().isEmpty() || textFieldYSize.getText().isEmpty()) {
            JOptionPane.showMessageDialog(menuFrame,
            "Put board size first!");
            return;
        }
        
        isBoardCreated = true;
        
        subMiddlePanel.removeAll();
        subMiddlePanel.repaint();
        
        int xElements = Integer.parseInt(textFieldXSize.getText());
        int yElements = Integer.parseInt(textFieldYSize.getText());
        
        subMiddlePanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        mapElements = new JTextField[xElements][yElements];
        for (int i = 0; i < xElements; i++) {
            for (int j = 0; j < yElements; j++) {
                mapElements[i][j] = new JTextField(2); 
                boardDocument = (AbstractDocument) mapElements[i][j].getDocument();
                boardDocument.setDocumentFilter(boardInputFilter);               
                mapElements[i][j].setHorizontalAlignment(JTextField.CENTER);
                gridBagConstraints.gridx = i;
                gridBagConstraints.gridy = j;
                subMiddlePanel.add(mapElements[i][j], gridBagConstraints);
            }
        }
        menuFrame.validate();
    }
    
    private void createNewBoard(int x, int y, int[][] elements)
    {
        isBoardCreated = true;
                
        subMiddlePanel.removeAll();
        subMiddlePanel.repaint();
        
        textFieldXSize.setText(Integer.toString(x));
        textFieldYSize.setText(Integer.toString(y));       
        
        subMiddlePanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        mapElements = new JTextField[x][y];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                mapElements[j][i] = new JTextField(2);
                mapElements[j][i].setHorizontalAlignment(JTextField.CENTER);
                gridBagConstraints.gridx = i;
                gridBagConstraints.gridy = j;
                mapElements[j][i].setText(Integer.toString(elements[j][i]));
                subMiddlePanel.add(mapElements[j][i], gridBagConstraints);
            }
        }
        menuFrame.validate();
    }
    
    private void saveNewBoard() throws FileNotFoundException, IOException
    {
        if (!isBoardCreated) {
            JOptionPane.showMessageDialog(menuFrame,
            "Create new board first!");
            return;
        }
        
        if (!validateBoard(Integer.parseInt(textFieldXSize.getText()), Integer.parseInt(textFieldYSize.getText())) )
            return;
           
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home") + "\\Desktop");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("SokobanLevel", "sokobanlevel");
        fileChooser.setFileFilter(filter);
        if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            OutputStream outputStream = new FileOutputStream(file.getAbsolutePath() + ".sokobanlevel");
            try (Writer outputStreamWriter = new OutputStreamWriter(outputStream)) {                   
                    outputStreamWriter.write(mapElements.length + " " + mapElements[0].length + System.lineSeparator());

                    for (int i = 0; i < mapElements[0].length; i++) {
                        for (int j = 0; j < mapElements.length; j++) {
                            outputStreamWriter.write(mapElements[j][i].getText() + " ");
                        }
                        
                    outputStreamWriter.write(System.lineSeparator());
                }
            }
        }
        isBoardCreated = false;
    }
    
    private void loadBoard()
    {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home") + "\\Desktop");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("SokobanLevel", "sokobanlevel");
        fileChooser.setFileFilter(filter);
        if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            Scanner skaner;
            try {
                skaner = new Scanner(file);
                int[][] elements;
                List<Integer> temp = new ArrayList<>();
                int x = 0;
                int y = 0;
                while(skaner.hasNextInt())
                {
                    temp.add(skaner.nextInt());
                }
                x = temp.get(0);
                y = temp.get(1);
                elements = new int[x][y];
                for (int i = 0; i < x; i++) {
                    for (int j = 0; j < y; j++) {
                        elements[i][j] = temp.get(2+i*x+j);
                    }
                }
                createNewBoard(x, y, elements);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
    }
    
    private boolean validateBoard(int xElements, int yElements)
    {
        int heroesCounter = 0;
        for (int i = 0; i < yElements; i++) {
            for (int j = 0; j < xElements; j++) {
                if(mapElements[j][i].getText().isEmpty()) {
                    JOptionPane.showMessageDialog(menuFrame,
                    "Fill all fields before previewing!");
                    return false;
                }
                if(Integer.parseInt(mapElements[j][i].getText()) == 4) {
                    heroesCounter++;
                    if (heroesCounter > 1) {
                        JOptionPane.showMessageDialog(menuFrame,
                        "You must have 1 and only 1 hero in your level!");
                        return false;
                    }
                }
            }
        }
        if (heroesCounter == 0) {
            JOptionPane.showMessageDialog(menuFrame,
            "You must have hero in your level!");
            return false;
        }
        return true;
    }
    private void previewCurrentBoard()
    {          
        int xElements = Integer.parseInt(textFieldXSize.getText());
        int yElements = Integer.parseInt(textFieldYSize.getText());
        
        if (!validateBoard(xElements, yElements)) 
            return;
        
        //max wymiary plansz
        previewElements = new JLabel[15][15];
        previewFrame.setSize(64*15, 64*15);
        previewPanel.setLayout(new GridLayout(15, 15));
        
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                previewElements[i][j] = new JLabel("", wallImage, JLabel.CENTER);
                previewPanel.add(previewElements[i][j]);
            }
        }
        
        for (int i = 0; i < yElements; i++) {
            for (int j = 0; j < xElements; j++) {
                switch(Integer.parseInt(mapElements[j][i].getText()))
                    {
                    case 0:
                        previewElements[j][i].setIcon(floorImage);
                        break;
                    case 1:
                        previewElements[j][i].setIcon(wallImage);
                        break;
                    case 2:
                        previewElements[j][i].setIcon(targetSpotImage);
                        break;
                    case 3:
                        previewElements[j][i].setIcon(boxImage);
                        break;
                    case 4:
                        previewElements[j][i].setIcon(playerImage);
                    }
            }
        }           
        previewFrame.setLocationRelativeTo(null);  
        previewFrame.setVisible(true);
    }
    
    private void setHelpInformation(JLabel label)
    {
        label.setText("<html>Basic rules of using creator:<br/><br/>"
                + "- You can't create bigger board than 15x15<br/>"
                + "- In board input fields use only digits from legend (from 0 to 4)<br/>"
                + "- You must have 1 and only 1 hero in your level</html>");
        
    }
    private void showHelpInformation()
    {
        helpFrame.setVisible(true);
        helpFrame.validate();
    }
    
    public void ShowCreator()
    {
        
        menuFrame.setVisible(true);
    }
}
