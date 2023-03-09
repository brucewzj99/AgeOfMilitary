package main;


import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Help {

	private Rectangle backhome = new Rectangle (524,594,363,75);
	public void draw(Graphics2D g2,int screenWidth, int screenHeight) {
		try {
			BufferedImage menuBG = ImageIO.read(getClass().getResourceAsStream("/menu/help.png"));
			g2.drawImage(menuBG,0 , 0, screenWidth,screenHeight , null);
			g2.draw(backhome);
		} catch (IOException e) {
			e.printStackTrace();
		}
		       
	}

}
