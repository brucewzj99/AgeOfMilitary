package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity {
	// character info
	private int[] pPosition = new int[2];
	private int pSpeed;
	private String pDirection;
	private int pLevel;
	private int bulletDownTime;
	private int bulletDownTimeCounter;
	private int bulletSpeed;
	private int bulletDamage;
	private int maxLife;
	private int life;

	// character hitbox for collision
	private Rectangle solidArea;
	public boolean collisionOn = false;
	private int solidAreaDefaultX, solidAreaDefaultY;

	// images for entity
	public BufferedImage p_up_1, p_up_2, p_up_3, p_down_1, p_down_2, p_down_3, p_left_1, p_left_2, p_left_3, p_right_1,
			p_right_2, p_right_3, c_up_1, c_up_2, b_up, c_down_1, c_down_2, b_down, c_left_1, c_left_2, b_left,
			c_right_1,
			c_right_2, b_right, d_up, d_down, d_left, d_right, shield_img;

	public abstract void getImages();

	public Rectangle getBounds() {
		Rectangle solidArea = getSolidAreaRec();
		return (new Rectangle(getXPosition() + solidArea.x, getYPosition() + solidArea.y, solidArea.width,
				solidArea.height));

	}

	// test method for debugging players
	// public void setTest() {
	// setpDirection("left");
	// setBulletStat(12, 3);
	// setpPosition(new int[] {120,100});
	// }

	public void setDefaultSolidArea(int x, int y) {
		this.solidAreaDefaultX = x;
		this.solidAreaDefaultY = y;
	}

	public void setSolidAreaRec(int x, int y, int width, int height) {
		this.solidArea = new Rectangle(x, y, width, height);
	}

	public Rectangle getSolidAreaRec() {
		return solidArea;
	}

	public int getSolidAreaRecX() {
		return solidArea.x;
	}

	public int getSolidAreaRecY() {
		return solidArea.y;
	}

	public int getPlayerLife() {
		return life;
	}

	public void setpPosition(int[] pPosition) {
		this.pPosition = pPosition;
	}

	public void updateNewXPosition(int distance) {
		pPosition[0] += distance;
	}

	public void updateNewYPosition(int distance) {
		pPosition[1] += distance;
	}

	public int getXPosition() {
		return pPosition[0];
	}

	public int getYPosition() {
		return pPosition[1];
	}

	public int getpSpeed() {
		return pSpeed;
	}

	public void increasepSpeed() {
		this.pSpeed++;
	}

	public String getpDirection() {
		return pDirection;
	}

	public void setpDirection(String pDirection) {
		this.pDirection = pDirection;
	}

	public int getpLevel() {
		return pLevel;
	}

	public int getBulletDownTime() {
		return bulletDownTimeCounter;
	}

	public int getBulletSpeed() {
		return bulletSpeed;
	}

	public void increaseLife() {
		// If life is full or close to full
		if (maxLife - life < 2) {
			life = maxLife;
		} else {
			life += 2;
		}
	}

	public void decreaseLife(int damage) {
		life -= damage;
	}

	public void setBulletStat(int bulletSpeed, int bulletDamage) {
		this.bulletSpeed = bulletSpeed;
		this.bulletDamage = bulletDamage;
	}

	public void bulletPenalty() {
		bulletDownTimeCounter--;
	}

	public void setBulletDownTime(int bulletDownTime) {
		this.bulletDownTime = bulletDownTime;
		this.bulletDownTimeCounter = bulletDownTime;
	}

	public void resetBulletDownTime() {
		bulletDownTimeCounter = bulletDownTime;
	}

	public int getBulletDamage() {
		return bulletDamage;
	}

	public void powerUp() {
		pLevel++;
		pSpeed++;
		bulletSpeed++;
		bulletDamage++;
	}

	public int getMaxLife() {
		return maxLife;
	}

	public void setPlayerStat(int pLevel, int pSpeed, int life, String direction) {
		this.pLevel = pLevel;
		this.pSpeed = pSpeed;
		this.life = life;
		this.maxLife = life;
		this.pDirection = direction;
	}

	public int getLife() {
		return life;
	}

	public int getSolidAreaDefaultX() {
		return solidAreaDefaultX;
	}

	public void setSolidAreaDefaultX(int solidAreaDefaultX) {
		this.solidAreaDefaultX = solidAreaDefaultX;
	}

	public int getSolidAreaDefaultY() {
		return solidAreaDefaultY;
	}

	public void setSolidAreaDefaultY(int solidAreaDefaultY) {
		this.solidAreaDefaultY = solidAreaDefaultY;
	}

}
