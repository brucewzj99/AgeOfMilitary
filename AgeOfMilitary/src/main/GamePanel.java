package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import entity.Player;
import object.Controller;
import object.CountDownTimer;
import object.SuperObject;
import screens.leader_board.LeaderBoardScreen;
import screens.winning.WinningScreen;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	// region Game settings
	public final int ORIGINAL_TILE_SIZE = 32; // wall and crate destroyed should use original size
	public final int CHARACTER_SIZE = ORIGINAL_TILE_SIZE * 2; // bullet and cannon should use char size

	// Screen
	public final int MAX_SCREEN_COL = 44;
	public final int MAX_SCREEN_ROW = 26;
	public final int SCREEN_WIDTH = ORIGINAL_TILE_SIZE * MAX_SCREEN_COL;
	public final int SCREEN_HEIGHT = ORIGINAL_TILE_SIZE * MAX_SCREEN_ROW;

	// Frames per second
	public final int FPS = 60;

	// Game state enum
	public static enum STATE {
		MENU, GAME, WIN1, WIN2, NO_WIN, HELP, LEADER_BOARD;
	};

	// Current game state
	public static STATE state = STATE.MENU;
	// endregion

	// create the maps
	public TileManager tileM = new TileManager(this);
	// UI
	public UI ui = new UI(this);
	// creates the menu
	private menu menu = new menu();
	private Help help = new Help();
	// create sound obj
	static Sound sound = new Sound();
	static Sound bgm = new Sound();
	private boolean musicplaying, playedVictory = false;

	// intialise P1 and P2
	KeyHandler p1KeyH = new KeyHandler(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_F);
	// KeyHandler p2KeyH = new KeyHandler(KeyEvent.VK_UP, KeyEvent.VK_DOWN,
	// KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_NUMPAD0);
	KeyHandler p2KeyH = new KeyHandler(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
			KeyEvent.VK_CONTROL);
	

	private ArrayList<String> player1Config;
	private ArrayList<String> player2Config;
	
	Thread gameThread;
	// x:50 , 1200 y: 650,50
	public Player player1;
	public Player player2;
	

	// initialise collision checker
	public CollisionChecker cChecker = new CollisionChecker(this);
	
	private ConfigHandler configHandler = new ConfigHandler();
	
	// CountDownTimer
	public CountDownTimer cdt = new CountDownTimer(this);

	// AssetSetter
	public AssetSetter aSetter = new AssetSetter(this);

	// prepare 10 slots for objects [0][1]....
	// means display 5 object at same time, dosent mean only can display 5 objects
	public SuperObject obj[] = new SuperObject[4];

	// bullet controller object
	public Controller c = new Controller();

	// Screens
	public WinningScreen winningScreen = new WinningScreen(this);
	public LeaderBoardScreen leaderBoardScreen = new LeaderBoardScreen(this);


	public GamePanel() {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.WHITE);
		this.setDoubleBuffered(true);
		this.addKeyListener(p1KeyH);
		this.addKeyListener(p2KeyH);
		this.setFocusable(true);
		this.addMouseListener(new MouseHandler(this));
	}

	public void setupGame() {
		// call before game start
		configHandler.loadConfig();
		setUpConfig();
		// play menu music
		playSE(1);

	}
	public void setUpConfig() {
		player1Config =configHandler.getPlayerConfig(1);
		player2Config =configHandler.getPlayerConfig(2);
		
		player1 = new Player(this, p1KeyH,player1Config,configHandler);
		player2 = new Player(this, p2KeyH,player2Config,configHandler);
	}
	/**
	 * Setting all state of the game "DON'T CHANGE IF YOU DON'T KNOW WHAT IT WILL
	 * AFFECT!"
	 */
	public void refreshCurrentState() {
		if (state == STATE.MENU) {
			leaderBoardScreen.showAllLabels(false);
		} else if (state == STATE.GAME) {
			if (musicplaying == false) {
				playMusic(0);
				aSetter.setObject();// set object coors in first run
				musicplaying = true;
			}

			if (cdt.getTime() == 45 || cdt.getTime() == 30 || cdt.getTime() == 15) {
				aSetter.setObject();// updates object coors according to time( timing not fixed )
			}

			if (playedVictory = true) {
				playedVictory = false;
			}
			if (player1.getPlayerLife() <= 0 || player2.getPlayerLife() <= 0 || cdt.getTime() <= 0) {
				if (player1.getPlayerLife() > player2.getPlayerLife()) {
					state = STATE.WIN1;
				} else if (player1.getPlayerLife() < player2.getPlayerLife()) {
					state = STATE.WIN2;
				} else {
					state = STATE.NO_WIN;
				}
			}
		} else if (state == STATE.WIN1 || state == STATE.WIN2 || state == STATE.NO_WIN) {
			if (musicplaying == true) {
				stopMusic();
				musicplaying = false;
			}
			// Reset values for new game later
			player1 = new Player(this, p1KeyH,player1Config,configHandler);
			player2 = new Player(this, p2KeyH,player2Config,configHandler);
			
			cdt.setWinningTime(cdt.getTime());
			cdt.resetTimer();
			if (playedVictory == false) {
				playSE(11);
				playedVictory = true;
			}

		} else if (state == STATE.LEADER_BOARD) {
			// System.out.println("LEADEROARD STATE");

		}
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	// Game loop, update location of char and update stats
	@Override
	public void run() {

		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (gameThread != null) {
			refreshCurrentState();
			// basically update position of character
			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;

			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}

	// function to check for key input, and appropriately executing movement
	public void update() {
		player1.update();
		player2.update();
		c.update();
	}

	// draw object
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		/// MENU SCREEN
		if (state == STATE.MENU) {
			// call class menu and method draw
			menu.draw(g2, SCREEN_WIDTH, SCREEN_HEIGHT);
			winningScreen.enableTextFieldName();
		} else if (state == STATE.HELP) {
			help.draw(g2, SCREEN_WIDTH, SCREEN_HEIGHT);
		} else if (state == STATE.WIN1 || state == STATE.WIN2 || state == STATE.NO_WIN) {
			winningScreen.draw(g2, SCREEN_WIDTH, SCREEN_HEIGHT);
			// Reset values for new game later
			tileM.MapLoad();
			player1.setDefaultValues();
			player2.setDefaultValues();
			cdt.resetTimer();

			if (musicplaying == true) {
				bgm.stop();
				musicplaying = false;
			}
			if (playedVictory == false) {
				playSE(11);
				playedVictory = true;
			}

		} else if (state == STATE.LEADER_BOARD) {
			winningScreen.enableTextFieldName();
			leaderBoardScreen.draw(g2, SCREEN_WIDTH, SCREEN_HEIGHT);
		} else if (state == STATE.GAME) {
			// draw tiles on Map
			tileM.draw(g2);

			// object
			for (int i = 0; i < obj.length; i++) {
				if (obj[i] != null && cdt.getTime() != 45 && cdt.getTime() != 30 && cdt.getTime() != 15) {
					obj[i].draw(g2, this);// dont draw when updating coors
				}
			}
			player1.draw(g2);
			player2.draw(g2);
			c.draw(g2);
			cdt.draw(g2);

			// UI
			ui.draw(g2);
			g2.dispose();
		}
	}

	// Playmusic
	public static void playMusic(int i) {
		bgm.setFile(i);
		bgm.play();
		bgm.loop();
	}

	// Remove music
	public static void stopMusic() {
		bgm.stop();
	}

	// Sound effects
	public void playSE(int i) {
		sound.setFile(i);
		sound.play();
	}
}
