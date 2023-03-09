package main;


import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class menu {

	private Rectangle playbutton = new Rectangle (524,264,363,75);
	private Rectangle helpbutton = new Rectangle (524,374,363,75);
	private Rectangle leaderboardbutton = new Rectangle (524,484,363,75);
	private Rectangle quitbutton = new Rectangle (524,594,363,75);
	public void draw(Graphics2D g2,int screenWidth, int screenHeight) {
		try {
			BufferedImage menuBG = ImageIO.read(getClass().getResourceAsStream("/menu/menu.png"));
			g2.drawImage(menuBG,0 , 0, screenWidth,screenHeight , null);
			g2.draw(playbutton);
			g2.draw(helpbutton);    
			g2.draw(leaderboardbutton);     
			g2.draw(quitbutton);
		} catch (IOException e) {
			e.printStackTrace();
		}
		       
	}

}
