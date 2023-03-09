package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {
    private BufferedImage image, image2, image3,image4;
    private String name;
    private boolean collision = false;
    private int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 44, 44);
    private int solidAreaDefaultX = 0;
    private int solidAreaDefaultY = 0;

    // create draw

    public void draw(Graphics2D g2, GamePanel gp) {

        g2.drawImage(image, worldX, worldY, gp.ORIGINAL_TILE_SIZE, gp.ORIGINAL_TILE_SIZE, null);
        // debugging purposes
        //g2.fillRect(worldX, worldY, solidArea.width,solidArea.height);
        g2.drawImage(image2, worldX, worldY, gp.ORIGINAL_TILE_SIZE, gp.ORIGINAL_TILE_SIZE, null);
        g2.drawImage(image3, worldX, worldY, gp.ORIGINAL_TILE_SIZE, gp.ORIGINAL_TILE_SIZE, null);
        g2.drawImage(image4, worldX, worldY, gp.ORIGINAL_TILE_SIZE, gp.ORIGINAL_TILE_SIZE, null);
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage2() {
        return this.image2;
    }

    public void setImage2(BufferedImage image2) {
        this.image2 = image2;
    }

    public BufferedImage getImage3() {
        return this.image3;
    }

    public void setImage3(BufferedImage image3) {
        this.image3 = image3;
    }

    public BufferedImage getImage4() {
        return this.image4;
    }

    public void setImage4(BufferedImage image4) {
        this.image4 = image4;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCollision() {
        return this.collision;
    }

    public boolean getCollision() {
        return this.collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getWorldX() {
        return this.worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return this.worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public int getSolidAreaDefaultX() {
        return this.solidAreaDefaultX;
    }

    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return this.solidAreaDefaultY;
    }

    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }

}
