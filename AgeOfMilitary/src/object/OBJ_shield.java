package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_shield extends SuperObject{
	public OBJ_shield(){
		setName("shield");
		try {
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/shield_icon.png")));
			setImage3(ImageIO.read(getClass().getResourceAsStream("/objects/shield.png")));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}