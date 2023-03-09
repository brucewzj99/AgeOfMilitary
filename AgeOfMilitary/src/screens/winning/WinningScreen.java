package screens.winning;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.GamePanel;
import main.GamePanel.STATE;
import utility.Utility;

public class WinningScreen {
	private BufferedImage screen;
	private Utility utility;

	private GamePanel gamePanel;
	private JTextField textFieldName;
	private JLabel labelScore;

	/**
	 * Winning Screen non-default constructor with JPanel.
	 * 
	 * @param gamePanel JPanel object.
	 */
	public WinningScreen(GamePanel gamePanel) {
		this.utility = new Utility();
		this.gamePanel = gamePanel;

		// Initialize JTextField and JksLabel.
		initTextFieldName();
		initLabelScore();
	}

	public void draw(Graphics2D g2, int screenWidth, int screenHeight) {

		try {
			// Player 1 wins
			if (GamePanel.state == STATE.WIN1) {
				screen = ImageIO.read(getClass().getResourceAsStream("/winscreens/p1win.png"));
				if (!textFieldName.isVisible()) {
					textFieldName.setEnabled(true);
					showTextFieldName(true);
					showLabelScore(true);
					setScore(utility.calculateScore(gamePanel));
				}
			}
			// Player 2 wins
			else if (GamePanel.state == STATE.WIN2) {
				screen = ImageIO.read(getClass().getResourceAsStream("/winscreens/p2win.png"));
				if (!textFieldName.isVisible()) {
					textFieldName.setEnabled(true);
					showTextFieldName(true);
					showLabelScore(true);
					setScore(utility.calculateScore(gamePanel));
				}
			}
			// Game draw
			else if (GamePanel.state == STATE.NO_WIN) {
				screen = ImageIO.read(getClass().getResourceAsStream("/winscreens/tie.png"));

				// Hide when state == NO WIN
				if (textFieldName.isVisible()) {
					showTextFieldName(false);
					showLabelScore(false);
				}
			} else {
				// Hide when playing
				if (textFieldName.isVisible()) {
					showTextFieldName(false);
					showLabelScore(false);
				}
			}

			g2.drawImage(screen, 0, 0, screenWidth, screenHeight, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * To show or hide Name text field.
	 * 
	 * @param show Boolean True to show and False to hide.
	 */
	public void showTextFieldName(boolean show) {
		textFieldName.setVisible(show);
	}

	/**
	 * To show or hide Score labe.
	 * 
	 * @param show Boolean True to show and False to hide.
	 */
	public void showLabelScore(boolean show) {
		labelScore.setVisible(show);
	}

	public void enableTextFieldName() {
		textFieldName.enable();
		textFieldName.setText("Enter Your Name");
	}

	private void setScore(int score) {
		labelScore.setText("" + score);
	}

	/**
	 * Initialize Name text field and set it to hidden
	 */
	private void initTextFieldName() {
		textFieldName = new JTextField();
		textFieldName.setHorizontalAlignment(JTextField.CENTER);
		textFieldName.setBounds(524, 263, 365, 78);
		textFieldName.setFont(utility.getFontDigit7(Font.BOLD, 40));
		textFieldName.replaceSelection("Enter Your Name");
		showTextFieldName(false);
		textFieldName.setEnabled(true);
		gamePanel.add(textFieldName);
		textFieldName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveScore();
				textFieldName.setEnabled(false);

			};
		});
	}

	/**
	 * Initialize Score label and set it to hidden
	 */
	private void initLabelScore() {
		labelScore = new JLabel();
		labelScore.setHorizontalAlignment(JLabel.CENTER);
		labelScore.setBounds(105, 350, 300, 200);
		labelScore.setForeground(Color.WHITE);
		showLabelScore(true);
		labelScore.setFont(utility.getFontDigit7(Font.BOLD, 80));
		gamePanel.add(labelScore);

	}

	private void saveScore() {

		Date thisDate = new Date();
		SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/YY hh:mm a");
		String latestDate = dateForm.format(thisDate).toString();
		URL leaderBoardTxt = getClass().getResource("/leaderboardinformation/leaderboard.txt");
	  
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(leaderBoardTxt.toURI()), true));
			writer.append(textFieldName.getText());
			writer.append(",");
			writer.append(labelScore.getText());
			writer.append(",");
			writer.append(latestDate);
			writer.newLine();
			writer.close();
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
