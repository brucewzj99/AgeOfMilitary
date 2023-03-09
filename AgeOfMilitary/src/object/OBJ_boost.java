package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_boost extends SuperObject{
	public OBJ_boost(){
		setName("boost");
		try {
			setImage2(ImageIO.read(getClass().getResourceAsStream("/objects/boost.png")));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
