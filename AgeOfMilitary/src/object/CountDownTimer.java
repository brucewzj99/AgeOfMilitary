package object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.GamePanel;
import main.GamePanel.STATE;
import utility.Utility;

public class CountDownTimer {
	private static int GAME_TIME = 60; // Game time in seconds
	private GamePanel gp;
	private int FPS;
	private int time;
	private int winningTime;
	private int repaintCount = 0;
	private Utility utility;

	/**
	 * CountDownTimer non-default constructor with JPanel.
	 * 
	 * @param gp GamePanel object.
	 */
	public CountDownTimer(GamePanel gp) {
		this.utility = new Utility();
		this.gp = gp;
		this.FPS = gp.FPS;
		this.time = GAME_TIME;
	}

	public void draw(Graphics g) {
		// region Draw CountDownTimer
		g.setColor(Color.BLACK);
		g.setFont(utility.getFontDigit7(Font.BOLD, 40));
		g.drawString("TIME", (gp.SCREEN_WIDTH / 2) - 20, 40);
		g.drawString("" + time, (gp.SCREEN_WIDTH / 2) - 5, 80);
		// endregion

		// One repaint count = 1 frame
		repaintCount++;

		// When repaint count == Frames per second time decreases by 1.
		if (repaintCount == FPS) {
			repaintCount = 0;
			// Stop decreasing time out of game state.
			if (GamePanel.state == STATE.GAME) {
				time--;
			}
		}
	}

	/**
	 * Reset game time.
	 */
	public void resetTimer() {
		time = GAME_TIME;
	}

	/**
	 * Set winning time.
	 * 
	 * @param winningTime Integer winning time.
	 */
	public void setWinningTime(int winningTime) {
		this.winningTime = winningTime;
	}

	/**
	 * Get winning time.
	 * 
	 * @return Integer winning time.
	 */
	public int getWinningTime() {
		return this.winningTime;
	}

	/**
	 * Get CountDownTimer current time.
	 * 
	 * @return Integer time.
	 */
	public int getTime() {
		return this.time;
	}
}
