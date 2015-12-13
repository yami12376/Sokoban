/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;

/**
 *
 * @author Jaworski
 */
public class Menu {
    public Rectangle playButton = new Rectangle(235, 150, 100, 50);
    public Rectangle quitButton = new Rectangle(235, 250, 100, 50);
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        Font titleFont = new Font("Arial", Font.BOLD, 50);
        g.setFont(titleFont);
        g.setColor(Color.white);
        g.drawString("Sokoban", 180, 100);
        
        Font buttonsFont = new Font("Arial", Font.BOLD, 30);
        g.setFont(buttonsFont);
        g.drawString("Play", playButton.x+19, playButton.y+35);
        g.drawString("Quit", quitButton.x+19, quitButton.y+35);
        
        g2d.draw(playButton);
        g2d.draw(quitButton);
    }
}
