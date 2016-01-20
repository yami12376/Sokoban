/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

/**
 *
 * @author Ja
 */

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;






public class applet  extends Applet{
	Applet applet = this;
	public void init()
	{
	applet.setSize(600,400);	
	applet.setBackground((Color.LIGHT_GRAY));
	}
	
	
	public void paint(Graphics g)
	{
		g.setColor((Color.RED));
		g.fillRect(25, 25, 100, 100);
		Font font = new Font("New Times Roman",Font.BOLD, 24);
		g.setColor((Color.BLUE));
		g.drawString("hello", 250, 100);
	}
    
}
