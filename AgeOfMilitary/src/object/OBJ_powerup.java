package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_powerup extends SuperObject{
	public OBJ_powerup(){
		setName("powerup");
		try {
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/powerup.png")));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
