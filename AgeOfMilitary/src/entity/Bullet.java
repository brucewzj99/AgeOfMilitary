package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Bullet extends Entity implements EntityDrawer {

	private GamePanel gp;
	private BufferedImage bimage1;
	private int bulletLevel;
	private Entity player;
	
	public Bullet(int x, int y, GamePanel gp, Entity player) {
		this.player = player;
		this.gp = gp;
		this.setpPosition(new int[] {x,y});
		setpDirection(player.getpDirection());
		this.bulletLevel = player.getpLevel();
		setBulletStat(player.getBulletSpeed(), player.getBulletDamage());
		setArea(player.getpDirection());
		getImages();
	}

	public void update() {
		// bullet speed and collision
		collisionOn = false;
		// bullet wall collision check
		gp.cChecker.checkTile(this);
		if (collisionOn == false) {
			switch (getpDirection()) {
				case "up":
					updateNewYPosition(-getBulletSpeed());
					break;
				case "down":
					updateNewYPosition(+getBulletSpeed());
					break;
				case "left":
					updateNewXPosition(-getBulletSpeed());
					break;
				case "right":
					updateNewXPosition(+getBulletSpeed());
					break;
			}
		}

		// bullet player collision check
		if (player == gp.player1) {
			if (gp.cChecker.checkBullet(this.getBounds(), gp.player2.getBounds()) == true) {
				// System.out.println("Player 1 shoot player 2: Checking Shield: "+gp.player2.getShield());
				if (gp.player2.getShield()) {
					gp.player2.deactivateShield();
				} else {
					gp.player2.minusLife();
					gp.c.addExplosion(new Explosion(getXPosition(), getYPosition(), getpDirection(), gp));
					gp.playSE(6); // hit player sound effect
				}
				gp.c.removeBullet(this);
			}
		} else if (player == gp.player2) {
			// System.out.println("Player 2 shoot player 1: Checking Shield: "+gp.player1.getShield());
			if (gp.cChecker.checkBullet(this.getBounds(), gp.player1.getBounds()) == true) {
				if (gp.player1.getShield()) {
					gp.player1.deactivateShield();
				} else {
					gp.player1.minusLife();
					gp.c.addExplosion(new Explosion(getXPosition(), getYPosition(), getpDirection(), gp));
					gp.playSE(6); // hit player sound effect
				}
				gp.c.removeBullet(this);

			}
		}
	}

	public void draw(Graphics2D g2) {

		// get bullet images based on direction and level
		bimage1 = getCurrentImage(getpDirection(), bulletLevel);
		
		// draw bullet image on map
		g2.drawImage(bimage1, getXPosition(),getYPosition(), gp.CHARACTER_SIZE, gp.CHARACTER_SIZE, null);
		
		// debugging purpose
		//g2.fillRect(getXPosition()+getSolidAreaRecX(), getYPosition()+getSolidAreaRecY(), getSolidAreaRec().width,  getSolidAreaRec().height);


	}

	public BufferedImage getCurrentImage(String direction, int level) {
		BufferedImage[][] bullets = { { b_up, b_down, b_left, b_right },
				{ c_up_1, c_down_1, c_left_1, c_right_1 }, { c_up_2, c_down_2, c_left_2, c_right_2 } };
		switch (direction) {
			case "up":
				return bullets[level - 1][0];
			case "down":

				return bullets[level - 1][1];
			case "left":

				return bullets[level - 1][2];
			case "right":
				return bullets[level - 1][3];
		}
		return bullets[level - 1][0];

	}

	public void getImages() {
		try {
			// loading projectile images
			c_up_1 = ImageIO.read(getClass().getResourceAsStream("/bullets/cannon_up.png"));
			c_up_2 = ImageIO.read(getClass().getResourceAsStream("/bullets/laser_up.png"));
			b_up = ImageIO.read(getClass().getResourceAsStream("/bullets/bullet_up.png"));
			c_down_1 = ImageIO.read(getClass().getResourceAsStream("/bullets/cannon_down.png"));
			c_down_2 = ImageIO.read(getClass().getResourceAsStream("/bullets/laser_down.png"));
			b_down = ImageIO.read(getClass().getResourceAsStream("/bullets/bullet_down.png"));
			c_left_1 = ImageIO.read(getClass().getResourceAsStream("/bullets/cannon_left.png"));
			c_left_2 = ImageIO.read(getClass().getResourceAsStream("/bullets/laser_left.png"));
			b_left = ImageIO.read(getClass().getResourceAsStream("/bullets/bullet_left.png"));
			c_right_1 = ImageIO.read(getClass().getResourceAsStream("/bullets/cannon_right.png"));
			c_right_2 = ImageIO.read(getClass().getResourceAsStream("/bullets/laser_right.png"));
			b_right = ImageIO.read(getClass().getResourceAsStream("/bullets/bullet_right.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setArea(String direction) {
		if (direction == "up") {
			setSolidAreaRec(23, 35, 18, 25);
		} else if (direction == "down") {
			setSolidAreaRec(23, 5, 18, 25);
		} else if (direction == "left") {
			setSolidAreaRec(36, 23, 25, 18);
		} else {// if non of above directions, it is definitely right facing
			setSolidAreaRec(4, 23, 25, 18);
		}
	}

	// get current bullet x coordinates
	public int getCurrBulletX() {
		int bX = getXPosition()+ getSolidAreaRecX();
		return bX;
	}

	// get current bullet Y coordinates
	public int getCurrBulletY() {
		int bY = getXPosition()+ getSolidAreaRecY();
		return bY;
	}

}