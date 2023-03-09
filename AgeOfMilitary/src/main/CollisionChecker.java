package main;

import java.awt.Rectangle;

import entity.Bullet;
import entity.DestroyedCrate;
import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
    	Rectangle solidArea = entity.getSolidAreaRec();
    	int xPosition = entity.getXPosition();
    	int yPosition = entity.getYPosition();
    	int pSpeed = entity.getpSpeed();
        // Player collision box directions
        int LeftWorldX = xPosition + solidArea.x;
        int RightWorldX = xPosition  + solidArea.x + solidArea.width;
        int CenterWorldX = xPosition  + solidArea.x + solidArea.width / 2;
        int TopWorldY = yPosition + solidArea.y;
        int BottomWorldY = yPosition + solidArea.y + solidArea.height;
        int CenterWorldY = yPosition + solidArea.y + solidArea.height / 2;

        int LeftCol = LeftWorldX / gp.ORIGINAL_TILE_SIZE;
        int RightCol = RightWorldX / gp.ORIGINAL_TILE_SIZE;
        int CenterCol = CenterWorldX / gp.ORIGINAL_TILE_SIZE;
        int TopRow = TopWorldY / gp.ORIGINAL_TILE_SIZE;
        int BottomRow = BottomWorldY / gp.ORIGINAL_TILE_SIZE;
        int CenterRow = CenterWorldY / gp.ORIGINAL_TILE_SIZE;

        int tileNum1 = 0;
        int tileNum2 = 0;
        int tileNum3 = 0;

        switch (entity.getpDirection()) {
            case "up":

                // predicting where the entity will go
                TopRow = (TopWorldY - pSpeed) / gp.ORIGINAL_TILE_SIZE;

                // checks which tile its colliding with on map
                tileNum1 = gp.tileM.maptileNum[LeftCol][TopRow];
                tileNum2 = gp.tileM.maptileNum[RightCol][TopRow];
                tileNum3 = gp.tileM.maptileNum[CenterCol][TopRow];

                break;
            case "down":
                // predicting where the entity will go
                BottomRow = (BottomWorldY + pSpeed) / gp.ORIGINAL_TILE_SIZE;

                // checks which tile its colliding with on map
                tileNum1 = gp.tileM.maptileNum[LeftCol][BottomRow];
                tileNum2 = gp.tileM.maptileNum[RightCol][BottomRow];
                tileNum3 = gp.tileM.maptileNum[CenterCol][BottomRow];

                break;
            case "left":
                // predicting where the entity will go
                LeftCol = (LeftWorldX - pSpeed) / gp.ORIGINAL_TILE_SIZE;

                // checks which tile its colliding with on map
                tileNum1 = gp.tileM.maptileNum[LeftCol][TopRow];
                tileNum2 = gp.tileM.maptileNum[LeftCol][BottomRow];
                tileNum3 = gp.tileM.maptileNum[LeftCol][CenterRow];

                break;
            case "right":
                // predicting where the entity will go
                RightCol = (RightWorldX + pSpeed) / gp.ORIGINAL_TILE_SIZE;

                // checks which tile its colliding with on map
                tileNum1 = gp.tileM.maptileNum[RightCol][TopRow];
                tileNum2 = gp.tileM.maptileNum[RightCol][BottomRow];
                tileNum3 = gp.tileM.maptileNum[RightCol][CenterRow];

                break;
        }
        // if entity hits solid tile
        if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true
                || gp.tileM.tile[tileNum3].collision == true) {
            entity.collisionOn = true;
            if(entity.getClass()== Bullet.class) {
                if (tileNum3 == 2) {
                    // remove bullet, show destroyed png and play crate destroy wav, replace with
                    // grass tile
                    gp.c.removeBullet(entity);
                    switch (entity.getpDirection()) {

                        case "up":
                            gp.tileM.maptileNum[CenterCol][TopRow] = 0;
                            gp.c.addDestroyedCrate(new DestroyedCrate(CenterCol * gp.ORIGINAL_TILE_SIZE - 16,
                                    TopRow * gp.ORIGINAL_TILE_SIZE - 16, gp));
                            break;
                        case "down":
                            gp.tileM.maptileNum[CenterCol][BottomRow] = 0;
                            gp.c.addDestroyedCrate(new DestroyedCrate(CenterCol * gp.ORIGINAL_TILE_SIZE - 16,
                                    BottomRow * gp.ORIGINAL_TILE_SIZE - 16, gp));
                            break;
                        case "left":
                            gp.tileM.maptileNum[LeftCol][CenterRow] = 0;
                            gp.c.addDestroyedCrate(new DestroyedCrate(LeftCol * gp.ORIGINAL_TILE_SIZE - 16,
                                    CenterRow * gp.ORIGINAL_TILE_SIZE - 16, gp));
                            break;
                        case "right":
                            gp.tileM.maptileNum[RightCol][CenterRow] = 0;
                            gp.c.addDestroyedCrate(new DestroyedCrate(RightCol * gp.ORIGINAL_TILE_SIZE - 16,
                                    CenterRow * gp.ORIGINAL_TILE_SIZE - 16, gp));
                            break;
                    }
                    // System.out.println("crate replaced with grass tile");
                    gp.playSE(5); //play crate_destroy sound effect

                } else if (tileNum3 == 1) {
                    gp.c.removeBullet(entity);
                    // System.out.println("hit wall");
                } else {
                    entity.collisionOn = false;
                }
            }
        }
        //if(entity.getClass() !=  Player.class) {System.out.println(entity.getClass());}
    }

    // bullet player collision check
    public boolean checkBullet(Rectangle bullet, Rectangle player) {
        if (bullet.intersects(player)) {
            return true;
        }
        return false;
    }

    // check if player touch any objects AKA powerups
    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        Rectangle solidArea = entity.getSolidAreaRec();
    	int xPosition = entity.getXPosition();
    	int yPosition = entity.getYPosition();
    	int pSpeed = entity.getpSpeed();
    	String pDirection = entity.getpDirection();
        
        // scan the length of obj which is 5
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null && gp.cdt.getTime()!=45 && gp.cdt.getTime()!=30 && gp.cdt.getTime()!=15) {
                // get entity's solid area position
                solidArea.x = xPosition + solidArea.x;
                solidArea.y = yPosition + solidArea.y;
                // get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].getWorldX() + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].getWorldY() + gp.obj[i].solidArea.y;

                switch (pDirection) {
                    case "up":
                        solidArea.y -= pSpeed;
                        if (solidArea.intersects(gp.obj[i].solidArea)) {
                            // if object is solid
                            if (gp.obj[i].getCollision() == true) {
                                entity.collisionOn = true;
                            }
                            // if this is player.
                            // this is for other npc or monster that walks ard and unable to pick the
                            // powerups
                            // might be better to merge into the above if statement
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        solidArea.y += pSpeed;
                        if (solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].getCollision() == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        solidArea.x -= pSpeed;
                        if (solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].getCollision() == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        solidArea.x += pSpeed;
                        if (solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].getCollision() == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                            break;
                        }
                }
                solidArea.x = entity.getSolidAreaDefaultX();
                solidArea.y = entity.getSolidAreaDefaultY();
                gp.obj[i].solidArea.x = gp.obj[i].getSolidAreaDefaultX();
                gp.obj[i].solidArea.y = gp.obj[i].getSolidAreaDefaultY();
            }
        }

        return index;
    }

}
