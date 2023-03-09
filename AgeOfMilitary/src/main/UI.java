package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_Heart;
import object.OBJ_shield;
import object.SuperObject;

public class UI {
    private GamePanel gp;
    private Font arial_30;
    private BufferedImage shieldImage;
    private BufferedImage heart_full, heart_half, heart_blank;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_30 = new Font("Arial", Font.PLAIN, 30);
        
        OBJ_shield shield = new OBJ_shield();
        shieldImage = shield.getImage();

        // create heart
        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.getImage();
        heart_half = heart.getImage2();
        heart_blank = heart.getImage3();

    }

    public void draw(Graphics2D g2) {
        g2.setFont(arial_30);
        g2.setColor(Color.black);

        // old concept to display shield boolean as string on ui
        // g2.drawString("Shield = " + gp.player1.shield, 170, 30);
        // g2.drawString("Shield = " + gp.player2.shield, (gp.orignialTileSize *
        // gp.maxScreenCol) - 160, 30);

        g2.drawString("Player 1", 50, 30);
        g2.drawString("Player 2", (gp.ORIGINAL_TILE_SIZE * gp.MAX_SCREEN_COL) - 160, 30);
        // g2.drawString("Player 2", (gp.orignialTileSize * gp.maxScreenCol) - 300, 30);

        drawPlayerLife(g2, 60, 40, gp.player1.getPlayerLife(), gp.player1.getMaxLife());
        drawPlayerLife(g2, (gp.ORIGINAL_TILE_SIZE * gp.MAX_SCREEN_COL) - 150, 40, gp.player2.getPlayerLife(),
                gp.player2.getMaxLife());

        int player1shield = 0, player2shield = 0;
        if (gp.player1.getShield()){
            player1shield = 1;
        }
        if(gp.player2.getShield()){
            player2shield = 1;
        }
        drawPlayerShield(g2, 60, 60, player1shield);
        drawPlayerShield(g2, (gp.ORIGINAL_TILE_SIZE * gp.MAX_SCREEN_COL) - 85, 60, player2shield);       

    }

    private void drawPlayerLife(Graphics2D g2, int x, int y, int life, int maxLife) {
        while (maxLife > 0) {
            if (life / 2 > 0) {
                g2.drawImage(heart_full, x, y, null);
                life -= 2;
            } else if (life % 2 > 0) {
                g2.drawImage(heart_half, x, y, null);
                life -= 1;
            } else if (life == 0) {
                g2.drawImage(heart_blank, x, y, null);
            }
            x += gp.ORIGINAL_TILE_SIZE;
            maxLife -= 2;
        }
    }

    private void drawPlayerShield(Graphics2D g2, int x, int y, int shield) {
        while (shield > 0) {
            g2.drawImage(shieldImage, x, y, null);
            x += gp.ORIGINAL_TILE_SIZE;
            shield -= 1;    
        }
        
    }

}
