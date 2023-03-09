package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Explosion extends Entity implements EntityDrawer{

	private GamePanel gp;
	private BufferedImage dimage1;
	private int x, y;
	private int explodeUptime = 0;
	private String direction;

	public Explosion(int x, int y, String direction, GamePanel gp) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.gp = gp;
		getImages();
	}

	public void draw(Graphics2D g2) {

		// get explosion images based on direction
		dimage1 = getCurrentImage(direction);
		ExplodeDelay(); // remove object upon certain time past
		// draw explosion image on map
		g2.drawImage(dimage1, x, y, gp.CHARACTER_SIZE, gp.CHARACTER_SIZE, null);
		// debugging purpose
		// g2.fillRect(this.x+solidArea.x, this.y+solidArea.y, this.getWidth(),
		// this.getHeight());

		// debugging position 
		// System.out.println((this.x + solidArea.x)/gp.orignialTileSize +","+ (this.y +
		// solidArea.y)/gp.orignialTileSize+","+ solidArea.width+","+ solidArea.height);

	}

	public BufferedImage getCurrentImage(String direction) {
		BufferedImage[] explosion = { d_up, d_down, d_left, d_right };

		switch (direction) {
			case "up":
				return explosion[1];
			case "down":

				return explosion[0];
			case "left":

				return explosion[3];
			case "right":
				return explosion[2];
		}
		return explosion[0];
	}

	public void getImages() {
		try {
			// loading projectile images
			d_up = ImageIO.read(getClass().getResourceAsStream("/bullets/damaged_up.png"));
			d_down = ImageIO.read(getClass().getResourceAsStream("/bullets/damaged_down.png"));
			d_left = ImageIO.read(getClass().getResourceAsStream("/bullets/damaged_left.png"));
			d_right = ImageIO.read(getClass().getResourceAsStream("/bullets/damaged_right.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ExplodeDelay() {
		explodeUptime++;
		if (explodeUptime >= 20) {
			gp.c.removeExplosion(this);
		}

	}
}