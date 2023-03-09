package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.ConfigHandler;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity implements EntityDrawer {
	private GamePanel gp;
	private KeyHandler keyH;
	private boolean shield;
	private ConfigHandler configHandler;
	private String color;
	
	public Player(GamePanel gp, KeyHandler keyH, ArrayList<String> playerConfig,ConfigHandler configHandler) {
		this.configHandler = configHandler;
		this.gp = gp;
		this.keyH = keyH;
		int spawn = 1;
		
		for(int i=0;i<playerConfig.size();i++) {
			if(playerConfig.get(i).contains("color")) {
				this.color = configHandler.clearConfig(playerConfig.get(i));
				
				//default color
				if(!configHandler.checkColor(color)) {
					this.color = "red";
				}
			}else if(playerConfig.get(i).contains("spawn")) {
				try {
					spawn = Integer.parseInt(configHandler.clearConfig(playerConfig.get(i)));
				}catch(Exception e){
					//System.out.println("Spawn config is not an int, "+e.toString());
					spawn = 1;
				}finally {
					
					setpPosition(configHandler.getSpawnConfig(spawn));
				}
			}
		}
		setDefaultValues();
		getImages();
		getShieldImage();
	}

	public void setDefaultValues() {
		try {
			// Set player defaults
			int level = Integer.parseInt(configHandler.getPlayerConfig("level", 0));
			int speed = Integer.parseInt(configHandler.getPlayerConfig("speed", 0));
			int life = Integer.parseInt(configHandler.getPlayerConfig("life", 0));
			String direction = configHandler.getPlayerConfig("direction", 0);
			boolean shield = Boolean.parseBoolean(configHandler.getPlayerConfig("shield", 0));
			int bulletDT =  Integer.parseInt(configHandler.getBulletConfig("downtime"));
			int bulletSpeed =  Integer.parseInt(configHandler.getBulletConfig("speed"));
			int bulletDamage =  Integer.parseInt(configHandler.getBulletConfig("damage"));
			
			setPlayerStat(level, speed, life, direction);
			if(shield) {
				activateShield();
			}else {
				deactivateShield();
			}

			// Set bullet defaults
			setBulletDownTime(bulletDT);
			setBulletStat(bulletSpeed, bulletDamage);

		}catch (Exception e) {
			System.out.println("Something wrong w setting from config, set to default, "+e.toString());
			setPlayerStat(1, 4, 6, "down");
			deactivateShield();		

			// Set bullet defaults
			setBulletDownTime(60);
			setBulletStat(6, 1);
			
		}
		
		// Based on gp.charSize 64, set hit box
		setSolidAreaRec(10, 10, 44, 44);
		setDefaultSolidArea(10, 10);

	}
	public void activateShield() {
		shield = true;
	}

	public void deactivateShield() {
		shield = false;
	}

	public boolean getShield() {
		return shield;
	}

	// Get player keyboard handler
	public void update() {
		bulletPenalty();
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
				|| keyH.rightPressed == true) {
			if (keyH.upPressed == true) {
				setpDirection("up");

			} else if (keyH.downPressed == true) {
				setpDirection("down");

			} else if (keyH.leftPressed == true) {
				setpDirection("left");

			} else if (keyH.rightPressed == true) {
				setpDirection("right");

			}

			// Checks tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);

			// check object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);

			// if collision false, player able to move
			if (collisionOn == false) {
				switch (getpDirection()) {
				case "up":
					updateNewYPosition(-getpSpeed());
					break;
				case "down":
					updateNewYPosition(+getpSpeed());
					break;
				case "left":
					updateNewXPosition(-getpSpeed());
					break;
				case "right":
					updateNewXPosition(+getpSpeed());
					break;
				}
			}
		}

		// Check if player is shooting
		if (keyH.shootPressed == true && getBulletDownTime() < 0) {
			int tempX = getXPosition();
			int tempY = getYPosition();
			int shootBuffer = gp.CHARACTER_SIZE / 2;
			String currentDirection = getpDirection();
			if (currentDirection == "down") {
				tempY += shootBuffer;
			} else if (currentDirection == "up") {
				tempY -= shootBuffer;
			} else if (currentDirection == "right") {
				tempX += shootBuffer;
			} else if (currentDirection == "left") {
				tempX -= shootBuffer;
			}
			gp.c.addBullet(new Bullet(tempX, tempY, gp, this));
			resetBulletDownTime();

			// play sound effect based on level
			gp.playSE(getpLevel() + 1);
		}

	}

	public void pickUpObject(int i) {
		if (i != 999) // if i is 999, means player did not touch any powerup
		{
			String objectName = gp.obj[i].getName();
			switch (objectName) {
			case "powerup": // level up
				if (getpLevel() < 3) {
					powerUp();
					gp.playSE(8); // play powerup sound effect
					break;
				}
			case "boost": // increase movement speed
				increasepSpeed();
				gp.playSE(9); // play boost sound effect
				break;
			case "shield": // block one attack
				activateShield();
				gp.playSE(10); // play shield sound effect
				break;
			case "heartpower":
				increaseLife();
				gp.playSE(7);
			}
			gp.obj[i] = null; // deletes powerup that was touched by entity
		}
	}

	// to draw the stuff into the screen
	public void draw(Graphics2D g2) {
		//
		// get player images based on direction and level
		BufferedImage pImage = null;
		pImage = getCurrentImage(getpDirection(), getpLevel());

		// draw player image on the map
		g2.drawImage(pImage, getXPosition(), getYPosition(), gp.CHARACTER_SIZE, gp.CHARACTER_SIZE, null);
		if (getShield()) {
			g2.drawImage(shield_img, getXPosition(), getYPosition(), gp.CHARACTER_SIZE, gp.CHARACTER_SIZE, null);
		}

		// debugging purpose
		// g2.fillRect(pPosition[0]+solidArea.x,
		// pPosition[1]+solidArea.y,solidArea.width,solidArea.height);

	}

	public void minusLife() {
		if (this == gp.player1) {
			gp.player1.decreaseLife(gp.player2.getBulletDamage());
		} else if (this == gp.player2) {
			gp.player2.decreaseLife(gp.player1.getBulletDamage());
		}
		// int bulletDamage = getBulletDamage();
		// this.life = life - bulletDamage;
	}

	public void getShieldImage() {
		try {
			shield_img = ImageIO.read(getClass().getResourceAsStream("/player/shield.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// use to get the correct image for the player
	public BufferedImage getCurrentImage(String direction, int level) {
		BufferedImage[][] play = { { p_up_1, p_down_1, p_left_1, p_right_1 }, { p_up_2, p_down_2, p_left_2, p_right_2 },
				{ p_up_3, p_down_3, p_left_3, p_right_3 } };
		switch (direction) {
		case "up":
			return play[level - 1][0];
		case "down":
			return play[level - 1][1];
		case "left":
			return play[level - 1][2];
		case "right":
			return play[level - 1][3];
		}
		return play[level - 1][0];

	}

	public void getImages() {
		try {
			// loading player images
			p_up_1 = ImageIO.read(getClass().getResourceAsStream("/player/"+color+"_up_1.png"));
			p_up_2 = ImageIO.read(getClass().getResourceAsStream("/player/"+color+"_up_2.png"));
			p_up_3 = ImageIO.read(getClass().getResourceAsStream("/player/"+color+"_up_3.png"));
			p_down_1 = ImageIO.read(getClass().getResourceAsStream("/player/"+color+"_down_1.png"));
			p_down_2 = ImageIO.read(getClass().getResourceAsStream("/player/"+color+"_down_2.png"));
			p_down_3 = ImageIO.read(getClass().getResourceAsStream("/player/"+color+"_down_3.png"));
			p_left_1 = ImageIO.read(getClass().getResourceAsStream("/player/"+color+"_left_1.png"));
			p_left_2 = ImageIO.read(getClass().getResourceAsStream("/player/"+color+"_left_2.png"));
			p_left_3 = ImageIO.read(getClass().getResourceAsStream("/player/"+color+"_left_3.png"));
			p_right_1 = ImageIO.read(getClass().getResourceAsStream("/player/"+color+"_right_1.png"));
			p_right_2 = ImageIO.read(getClass().getResourceAsStream("/player/"+color+"_right_2.png"));
			p_right_3 = ImageIO.read(getClass().getResourceAsStream("/player/"+color+"_right_3.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
