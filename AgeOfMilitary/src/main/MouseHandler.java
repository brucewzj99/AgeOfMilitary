package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.GamePanel.STATE;

public class MouseHandler implements MouseListener {
	GamePanel gp;
	
	public MouseHandler(GamePanel gp){
		this.gp = gp;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//used for debugging by getting x y coordinates of mouseclick
		// int x = e.getX();
		// int y = e.getY();
		// System.out.println(x + "," + y);// these co-ords are relative to the component
	}

	@Override
	public void mousePressed(MouseEvent e) {

		// used to determine the xy location on mouseclick, used to set button
		// clickzones
		int mx = e.getX();
		int my = e.getY();

		// Menu Screen
		if (GamePanel.state == STATE.MENU) {

			if (mx >= (GamePanel.WIDTH / 2) + 520 && mx <= (GamePanel.WIDTH / 2) + 890) {
				// Play Button
				if (my >= 265 && my <= 340) {
					GamePanel.state = GamePanel.STATE.GAME;
					gp.tileM.MapLoad();
				}

				// Help Button
				else if (my >= 375 && my <= 450) {
					GamePanel.state = GamePanel.STATE.HELP;
				}
				// Leader Board Button
				else if (my >= 485 && my <= 560) {
					GamePanel.state = GamePanel.STATE.LEADER_BOARD;
				}

				// Quit Button
				else if (my >= 595 && my <= 670) {
					System.exit(1);
				}
			}
		}
		// Winning Screen
		else if (GamePanel.state == STATE.WIN1 || GamePanel.state == STATE.WIN2 || GamePanel.state == STATE.NO_WIN) {

			// Play Again Button
			if (mx >= (GamePanel.WIDTH / 2) + 520 && mx <= (GamePanel.WIDTH / 2) + 890) {
				if (my >= 375 && my <= 450) {
					GamePanel.state = GamePanel.STATE.GAME;
				}
			}

			// Leader Board Button
			if (mx >= (GamePanel.WIDTH / 2) + 520 && mx <= (GamePanel.WIDTH / 2) + 890) {
				if (my >= 485 && my <= 560) {
					GamePanel.state = GamePanel.STATE.LEADER_BOARD;
				}
			}

			// Quit Button
			if (mx >= (GamePanel.WIDTH / 2) + 520 && mx <= (GamePanel.WIDTH / 2) + 890) {
				if (my >= 595 && my <= 1000) {
					System.exit(1);
				}
			}
		}
		// Leader Board Screen
		else if (GamePanel.state == STATE.LEADER_BOARD || GamePanel.state == STATE.HELP) {

			// Menu Button
			if (mx >= (GamePanel.WIDTH / 2) + 520 && mx <= (GamePanel.WIDTH / 2) + 890) {
				if (my >= 595 && my <= 670) {
					GamePanel.state = STATE.MENU;
					gp.leaderBoardScreen.setFetchBrd(false);
				}
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// not used

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// not used
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// not used
	}

}
