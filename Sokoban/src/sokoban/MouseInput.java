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
            *     public Rectangle playButton = new Rectangle(350, 200, 240, 50);
    public Rectangle creatorButton = new Rectangle(350, 300, 240, 50);
    public Rectangle quitButton = new Rectangle(350, 400, 240, 50);
            */
            if(mouseX >= 350 && mouseX <= 590)
            {
                if (mouseY >= 200 && mouseY <= 250) {
                    //Pressed play
                    Sokoban.State = Sokoban.STATE.GAME;
                }
            }
            
            if(mouseX >= 350 && mouseX <= 590)
            {
                if (mouseY >= 300 && mouseY <= 350) {
                    //Pressed create levels
                    Sokoban.Instance().creator.ShowCreator();
                }
            }
            
            if(mouseX >= 350 && mouseX <= 590)
            {
                if (mouseY >= 400 && mouseY <= 450) {
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
