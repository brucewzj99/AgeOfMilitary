package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_heartpower extends SuperObject{
	public OBJ_heartpower(){
		setName("heartpower");
		try {
			setImage4(ImageIO.read(getClass().getResourceAsStream("/objects/heart.png")));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

