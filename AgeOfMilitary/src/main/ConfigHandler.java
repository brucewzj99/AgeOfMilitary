package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.net.URL;
import java.util.ArrayList;

public class ConfigHandler {
	private ArrayList<ArrayList<String>> configList;
	private ArrayList<ArrayList<String>> playerConfig;
	private ArrayList<String> configHeader;
	private ArrayList<String> bulletConfig;
	private ArrayList<String> colorConfig;
	private ArrayList<String> spawnConfig;

	public ArrayList<ArrayList<String>> loadConfig() {
		configList = new ArrayList<ArrayList<String>>();
		configHeader = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(getClass().getResourceAsStream("/config/config.ini")));
			String strCurrentLine;
			int title = -1;
			while ((strCurrentLine = reader.readLine()) != null) {
				// Header string
				if (strCurrentLine.contains("[") && strCurrentLine.contains("]")) {
					title++;
					configList.add(new ArrayList<String>());
					configHeader.add(strCurrentLine.substring(1, strCurrentLine.length() - 1));
					configList.get(title).add(strCurrentLine.substring(1, strCurrentLine.length() - 1));
				} else {
					if (!strCurrentLine.isEmpty()) {
						configList.get(title).add(strCurrentLine);
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Cant find config file");
		} finally {
		}
		loadToIndividualConfig();
		return configList;
	}

	public ArrayList<ArrayList<String>> loadConfig(String filename) {
		configList = new ArrayList<ArrayList<String>>();
		configHeader = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(getClass().getResourceAsStream("/config/" + filename)));
			String strCurrentLine;
			int title = -1;
			while ((strCurrentLine = reader.readLine()) != null) {
				// Header string
				if (strCurrentLine.contains("[") && strCurrentLine.contains("]")) {
					title++;
					configList.add(new ArrayList<String>());
					configHeader.add(strCurrentLine.substring(1, strCurrentLine.length() - 1));
					configList.get(title).add(strCurrentLine.substring(1, strCurrentLine.length() - 1));
				} else {
					if (!strCurrentLine.isEmpty()) {
						configList.get(title).add(strCurrentLine);
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Cant find config file");
		}
		loadToIndividualConfig();
		return configList;
	}

	public void saveConfig(ArrayList<ArrayList<String>> configList, String filename) {
		try {
			//URL resourceUrl = getClass().getResource("/config/");
			// File file = new File(resourceUrl.toURI()+filename);
			File file = new File("res/config/" + filename);
			file.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			String writeString;
			for (int i = 0; i < configList.size(); i++) {
				for (int j = 0; j < configList.get(i).size(); j++) {
					writeString = configList.get(i).get(j);
					// Header string
					if (j == 0) {
						if (i != 0) {
							writer.newLine();
						}
						writer.write("[" + writeString + "]\n");
					} else {
						writer.write(writeString + "\n");
					}
				}
			}
			writer.close();
		} catch (Exception e) {
			System.out.println("Cant write config file " + e.toString());
		}
	}

	private void loadToIndividualConfig() {
		String titleString;
		playerConfig = new ArrayList<ArrayList<String>>();
		bulletConfig  = new ArrayList<String>();
		colorConfig  = new ArrayList<String>();
		spawnConfig  = new ArrayList<String>();
		for (int i = 0; i < configList.size(); i++) {
			titleString = configList.get(i).get(0);
			if (titleString.contains("player")) {
				playerConfig.add(configList.get(i));
			} else if (titleString.contains("bullet")) {
				bulletConfig = configList.get(i);

			} else if (titleString.contains("color")) {
				colorConfig = configList.get(i);

			} else if (titleString.contains("spawn")) {
				spawnConfig = configList.get(i);
			}
		}
	}

	public void printConfigList() {
		System.out.println("configHeader");
		System.out.println(configHeader);
		
		System.out.println("playerConfig");
		System.out.println(playerConfig);
		
		System.out.println("bulletConfig");
		System.out.println(bulletConfig);
		
		System.out.println("colorConfig");
		System.out.println(colorConfig);
		
		System.out.println("spawnConfig");
		System.out.println(spawnConfig);
	}
	
	public ArrayList<ArrayList<String>> getConfigList() {
		return configList;
	}

	public ArrayList<String> getPlayerConfig() {
		return playerConfig.get(0);
	}

	public ArrayList<String> getPlayerConfig(int playerNo) {
		return playerConfig.get(playerNo);
	}

	public String getPlayerConfig(String n, int playerNo){
		for (int i = 0; i < playerConfig.get(playerNo).size(); i++) {
			if(playerConfig.get(playerNo).get(i).contains(n)) {
				return clearConfig(playerConfig.get(playerNo).get(i));
			}
		}
		return "";
	}

	public ArrayList<String> getBulletConfig() {
		return bulletConfig;

	}

	public String getBulletConfig(String n){
		for (int i = 0; i < bulletConfig.size(); i++) {
			if(bulletConfig.get(i).contains(n)) {
				return clearConfig(bulletConfig.get(i));
			}
		}
		return "";
	}
	public ArrayList<String> getColorConfig() {
		return colorConfig;

	}

	public ArrayList<String> getSpawnConfig() {
		return spawnConfig;
	}

	public int[] getSpawnConfig(int i) {
		int[] pPosition;
		try {
			String[] temp = spawnConfig.get(i).split(",",2);
			pPosition = new int[temp.length];
			for(int k=0;k<temp.length;k++) {
				pPosition[k]=Integer.parseInt(temp[k]);
			}
		}catch(Exception e) {
			System.out.println("Error convert spawn config to int, "+e.toString());
			pPosition = new int[] {50,50};
		}
		return pPosition;
	}
	
	public String clearConfig(String n) {
		int index = n.indexOf('=');
		return n.substring(index+1, n.length());
	}

	public boolean checkColor(String n) {
		return colorConfig.contains(n);
	}
}