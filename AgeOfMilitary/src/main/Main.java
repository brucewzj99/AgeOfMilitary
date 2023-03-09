package main;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Age of Military");
		// window.setLocationRelativeTo(null);
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();

		window.setVisible(true);
		
		// call object before game start
		gamePanel.setupGame();
		gamePanel.startGameThread();
	}
}
