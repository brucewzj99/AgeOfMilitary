package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int maptileNum[][];
    public String a, b;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        maptileNum = new int[gp.MAX_SCREEN_COL][gp.MAX_SCREEN_ROW];
        getTitleImage();
        MapLoad();
    }

    // method to get images
    public void getTitleImage() {
        try {
            // create new tile
            tile[0] = new Tile();
            tile[1] = new Tile();
            tile[2] = new Tile();
            // gets image file
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/crate.png"));
            // sets collision
            tile[1].collision = true;
            tile[2].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // method to load map

    public void MapLoad() {
        try {
        	int map_no = 1 + (int) (Math.random() * 4);
        	InputStream is = getClass().getResourceAsStream("/maps/map"+map_no+".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW) {
                String line = br.readLine();
                while (col < gp.MAX_SCREEN_COL) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    maptileNum[col][row] = num;
                    col++;
                }
                if (col == gp.MAX_SCREEN_COL) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {

        }

    }
    public void draw(Graphics2D g2) {
        // sets tiles to fill the screen/map
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW) {

            int tileNum = maptileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.ORIGINAL_TILE_SIZE, gp.ORIGINAL_TILE_SIZE, null);
            col++;
            x += gp.ORIGINAL_TILE_SIZE;

            if (col == gp.MAX_SCREEN_COL) {
                col = 0;
                x = 0;
                row++;
                y += gp.ORIGINAL_TILE_SIZE;
            }

        }

    }

}