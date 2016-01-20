/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;
import java.awt.*;
/**
 *
 * @author Jaworski
 */
public class Menu {
    public Rectangle playButton = new Rectangle(350, 200, 240, 50);
    public Rectangle creatorButton = new Rectangle(350, 300, 240, 50);
    public Rectangle quitButton = new Rectangle(350, 400, 240, 50);
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        Font titleFont = new Font("Arial", Font.BOLD, 50);
        g.setFont(titleFont);
        g.setColor(Color.white);
        
        Font buttonsFont = new Font("Arial", Font.BOLD, 30);
        g.setFont(buttonsFont);
        g.drawString("Play", playButton.x+90, playButton.y+35);
        g.drawString("Level Creator", creatorButton.x+29, creatorButton.y+35);
        g.drawString("Quit", quitButton.x+90, quitButton.y+35);
        
        g2d.draw(playButton);
        g2d.draw(creatorButton);
        g2d.draw(quitButton);
    }
            /**
         * @param args the command line argumentsz
         */
        public static void main(String[] args) {

        }
}
