package utility;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import entity.Player;
import main.GamePanel;
import main.GamePanel.STATE;

public class Utility {

    // region Font paths
    private final URL DIGIT_7_PATH = getClass().getResource("/font/digital-7.ttf");
    // endregion

    /**
     * Default Constructor
     */
    public Utility() {
    }

    /**
     * Get Digit 7 font.
     * 
     * @param style Font style eg. Font.PLAIN, Font.BOLD, Font.ITALIC.
     * @param size  Font size.
     * @return Font object.
     */
    public Font getFontDigit7(int style, int size) {
        Font font = null;
        try {
        	InputStream is = this.getClass().getResourceAsStream("/font/digital-7.ttf");
            //font = Font.createFont(Font.TRUETYPE_FONT, new File(DIGIT_7_PATH.toURI())).deriveFont(style, size);
            font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(style, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return font;
    }

    /**
     * Calculate game score.
     * 
     * @param gp GamePanel object from main class.
     * @return Game score.
     */
    public int calculateScore(GamePanel gp) {

        int currentTime = gp.cdt.getTime();
        int multiplier = 54;
        
        // Calculate player 1 score.
        if (GamePanel.state == STATE.WIN1) {
            Player player1 = gp.player1;
			// System.out.println("p1 life: "+player1.getLife()+" score: "+player1.getLife()*multiplier);
			// System.out.println("currentTime: "+currentTime+" score: "+currentTime*multiplier);
            return player1.getLife() * multiplier + currentTime * multiplier;
        }

        // Calculate player 2 score.
        if (GamePanel.state == STATE.WIN2) {
            Player player2 = gp.player2;
			// System.out.println("p2 life: "+player2.getLife()+" score: "+player2.getLife()*multiplier);
			// System.out.println("currentTime: "+currentTime+" score: "+currentTime*multiplier);
            return player2.getLife() * multiplier + currentTime * multiplier;
        }

        // Negative is equivalent to no score.
        return -1;
    }
}
