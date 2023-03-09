package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class DestroyedCrate extends Entity implements EntityDrawer {

	private GamePanel gp;
	private BufferedImage dimage1;
	private int x, y;
	private int explodeUptime = 0;

	public DestroyedCrate(int x, int y, GamePanel gp) {
		this.x = x;
		this.y = y;
		this.gp = gp;
		getImages();
	}

	public void draw(Graphics2D g2) {

		// get crate Destroyed image
		ExplodeDelay();
		// draw Destroyed image on map
		g2.drawImage(dimage1, x, y, gp.CHARACTER_SIZE, gp.CHARACTER_SIZE, null);
		

	}

	public void getImages() {
		try {
			// loading projectile images
			dimage1 = ImageIO.read(getClass().getResourceAsStream("/bullets/crate_destroyed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ExplodeDelay() {
		explodeUptime++;
		if (explodeUptime >= 10) {
			gp.c.removeDestroyedCrate(this);
		}

	}
}