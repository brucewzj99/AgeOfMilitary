package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_Heart extends SuperObject {

    public OBJ_Heart(GamePanel gp) {
        setName("heart");
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png")));
            setImage2(ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png")));
            setImage3(ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png")));
            // uTool.scaleImage(image, gp.orignialTileSize, gp.orignialTileSize)

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
