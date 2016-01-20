/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 *
 * @author Jaworski
 */
public class MouseInput implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
            /**
            public Rectangle playButton = new Rectangle(235, 150, 100, 50);
            public Rectangle quitButton = new Rectangle(235, 250, 100, 50);
            */
            if(mouseX >= 235 && mouseX <= 335) // nie tylko w menu dzia³a !
            {
                if (mouseY >= 150 && mouseY <= 200) {
                    //Pressed play
                    Sokoban.State = Sokoban.STATE.GAME;
                }
            }
             if(mouseX >= 235 && mouseX <= 335)
            {
                if (mouseY >= 250 && mouseY <= 300) {
                    //Pressed quit
                    System.exit(1);
                }
            }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
