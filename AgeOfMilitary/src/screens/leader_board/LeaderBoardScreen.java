package screens.leader_board;


import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.LeaderBoardInfo;
import main.GamePanel;
import utility.Utility;

public class LeaderBoardScreen {

    private final int NAME_WIDTH = 250;
    private final int NAME_HEIGHT = 40;
    private final int NAME_POSITION_X = 390;
    private final int NAME_POSITION_Y = 315;

    private final int SCORE_WIDTH = 80;
    private final int SCORE_HEIGHT = NAME_HEIGHT;
    private final int SCORE_POSITION_X = NAME_POSITION_X + 270;
    private final int SCORE_POSITION_Y = NAME_POSITION_Y;

    private final int DATE_WIDTH = 230;
    private final int DATE_HEIGHT = NAME_HEIGHT;
    private final int DATE_POSITION_X = SCORE_POSITION_X + 140;
    private final int DATE_POSITION_Y = NAME_POSITION_Y;

    private BufferedImage screen;
    private JLabel labelName, labelName2, labelName3, leaderScore, leaderScore2, leaderScore3, labelDate, labelDate2,
            labelDate3;
    private Utility utility;
    private JPanel gamePanel;
    private SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/YY hh:mm a");
    private boolean fetchBrd;
    ArrayList<LeaderBoardInfo> list;

    /**
     * LeaderBoardScreen non-default constructor.
     * 
     * @param gamePanel
     */
    public LeaderBoardScreen(JPanel gamePanel) {

        this.utility = new Utility();
        initLeaderScore();
        initLeaderScore2();
        initLeaderScore3();
        initLabelName();
        initLabelName2();
        initLabelName3();
        initLabelDate();
        initLabelDate2();
        initLabelDate3();
        this.gamePanel = gamePanel;
        this.fetchBrd = false;
        list = null;
    }

    public void draw(Graphics2D g2, int screenWidth, int screenHeight) {
        try {
            GamePanel gp = (GamePanel) gamePanel;
            // gp.add(labelScore);

            gp.winningScreen.showLabelScore(false);
            gp.winningScreen.showTextFieldName(false);

            // Read the leaderboard image
            screen = ImageIO.read(getClass().getResourceAsStream("/menu/leaderboard.png"));

            if (fetchBrd == false) {
                // Read the leaderboard.txt file
                list = this.retrieveLeaderboard();
                fetchBrd = true;
            }

            // Display Label Score After retrieving the leadboard information
            if (list.size() >= 1) {
                leaderScore.setText("" + list.get(0).getScore());
                gp.add(leaderScore);
                labelName.setText(list.get(0).getName());
                gp.add(labelName);
                labelDate.setText(dateForm.format(list.get(0).getDate()).toString());
                gp.add(labelDate);
                showOne(true);
            }

            if (list.size() >= 2) {
                leaderScore2.setText("" + list.get(1).getScore());
                gp.add(leaderScore2);
                labelName2.setText(list.get(1).getName());
                gp.add(labelName2);
                labelDate2.setText(dateForm.format(list.get(1).getDate()).toString());
                gp.add(labelDate2);
                showTwo(true);
            }

            if (list.size() >= 3) {
                leaderScore3.setText("" + list.get(2).getScore());
                gp.add(leaderScore3);
                labelName3.setText(list.get(2).getName());
                gp.add(labelName3);
                labelDate3.setText(dateForm.format(list.get(2).getDate()).toString());
                gp.add(labelDate3);
                showAllLabels(true);
            }

            g2.drawImage(screen, 0, 0, screenWidth, screenHeight, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<LeaderBoardInfo> retrieveLeaderboard() {
        ArrayList<LeaderBoardInfo> leaderBoardList = new ArrayList<>();
        //URL leader_board = getClass().getResource("/leaderboardinformation/leaderboard.txt");
		InputStream is = this.getClass().getResourceAsStream("/leaderboardinformation/leaderboard.txt");
        
        try {

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is));
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                leaderBoardList.add(new LeaderBoardInfo(values[0], Integer.parseInt(values[1]), new Date(values[2])));
            }
            Collections.sort(leaderBoardList);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return leaderBoardList;

    }

    public void showOne(boolean show) {
        showLabelName(show);
        showLeaderScore(show);
        showLabelDate(show);
    }

    public void showTwo(boolean show) {
        showLabelName(show);
        showLabelName2(show);
        showLeaderScore(show);
        showLeaderScore2(show);
        showLabelDate(show);
        showLabelDate2(show);
    }

    public void showAllLabels(boolean show) {
        showLabelName(show);
        showLabelName2(show);
        showLabelName3(show);
        showLeaderScore(show);
        showLeaderScore2(show);
        showLeaderScore3(show);
        showLabelDate(show);
        showLabelDate2(show);
        showLabelDate3(show);
    }

    public void showLabelName(boolean show) {
        labelName.setVisible(show);
    }

    public void showLabelName2(boolean show) {
        labelName2.setVisible(show);
    }

    public void showLabelName3(boolean show) {
        labelName3.setVisible(show);
    }

    public void showLeaderScore(boolean show) {
        leaderScore.setVisible(show);
    }

    public void showLeaderScore2(boolean show) {
        leaderScore2.setVisible(show);
    }

    public void showLeaderScore3(boolean show) {
        leaderScore3.setVisible(show);
    }

    public void showLabelDate(boolean show) {
        labelDate.setVisible(show);
    }

    public void showLabelDate2(boolean show) {
        labelDate2.setVisible(show);
    }

    public void showLabelDate3(boolean show) {
        labelDate3.setVisible(show);
    }

    private void initLabelName() {
        labelName = new JLabel("Test Subject");
        labelName.setHorizontalAlignment(JLabel.CENTER);
        labelName.setBounds(NAME_POSITION_X, NAME_POSITION_Y, NAME_WIDTH, NAME_HEIGHT);
        labelName.setFont(utility.getFontDigit7(Font.BOLD, 30));
        labelName.setOpaque(false);
        showLabelName(false);
    }

    private void initLabelName2() {
        labelName2 = new JLabel("Test Subject");
        labelName2.setHorizontalAlignment(JLabel.CENTER);
        labelName2.setBounds(NAME_POSITION_X, NAME_POSITION_Y + 110, NAME_WIDTH, NAME_HEIGHT);
        labelName2.setFont(utility.getFontDigit7(Font.BOLD, 30));
        labelName2.setOpaque(false);
        showLabelName(false);
    }

    private void initLabelName3() {
        labelName3 = new JLabel("Test Subject");
        labelName3.setHorizontalAlignment(JLabel.CENTER);
        labelName3.setBounds(NAME_POSITION_X, NAME_POSITION_Y + 210, NAME_WIDTH, NAME_HEIGHT);
        labelName3.setFont(utility.getFontDigit7(Font.BOLD, 30));
        labelName3.setOpaque(false);
        showLabelName(false);
    }

    private void initLeaderScore() {
        leaderScore = new JLabel();
        leaderScore.setHorizontalAlignment(JLabel.CENTER);
        leaderScore.setBounds(SCORE_POSITION_X, SCORE_POSITION_Y, SCORE_WIDTH, SCORE_HEIGHT);
        leaderScore.setFont(utility.getFontDigit7(Font.BOLD, 30));
        leaderScore.setOpaque(false);
        showLeaderScore(false);
    }

    private void initLeaderScore2() {
        leaderScore2 = new JLabel();
        leaderScore2.setHorizontalAlignment(JLabel.CENTER);
        leaderScore2.setBounds(SCORE_POSITION_X, SCORE_POSITION_Y + 110, SCORE_WIDTH, SCORE_HEIGHT);
        leaderScore2.setFont(utility.getFontDigit7(Font.BOLD, 30));
        leaderScore2.setOpaque(false);
        showLeaderScore(false);
    }

    private void initLeaderScore3() {
        leaderScore3 = new JLabel();
        leaderScore3.setHorizontalAlignment(JLabel.CENTER);
        leaderScore3.setBounds(SCORE_POSITION_X, SCORE_POSITION_Y + 210, SCORE_WIDTH, SCORE_HEIGHT);
        leaderScore3.setFont(utility.getFontDigit7(Font.BOLD, 30));
        leaderScore3.setOpaque(false);
        showLeaderScore(false);
    }

    private void initLabelDate() {
        labelDate = new JLabel();
        labelDate.setHorizontalAlignment(JLabel.LEFT);
        labelDate.setBounds(DATE_POSITION_X, DATE_POSITION_Y, DATE_WIDTH, DATE_HEIGHT);
        labelDate.setFont(utility.getFontDigit7(Font.BOLD, 30));
        labelDate.setOpaque(false);
        showLabelDate(false);
    }

    private void initLabelDate2() {
        labelDate2 = new JLabel();
        labelDate2.setHorizontalAlignment(JLabel.LEFT);
        labelDate2.setBounds(DATE_POSITION_X, DATE_POSITION_Y + 110, DATE_WIDTH, DATE_HEIGHT);
        labelDate2.setFont(utility.getFontDigit7(Font.BOLD, 30));
        labelDate2.setOpaque(false);
        showLabelDate(false);

    }

    private void initLabelDate3() {
        labelDate3 = new JLabel();
        labelDate3.setHorizontalAlignment(JLabel.LEFT);
        labelDate3.setBounds(DATE_POSITION_X, DATE_POSITION_Y + 210, DATE_WIDTH, DATE_HEIGHT);
        labelDate3.setFont(utility.getFontDigit7(Font.BOLD, 30));
        labelDate3.setOpaque(false);
        showLabelDate(false);
    }

    public boolean getFetchBrd() {
        return this.fetchBrd;
    }

    public void setFetchBrd(boolean fetchBrd) {
        this.fetchBrd = fetchBrd;
    }

}
