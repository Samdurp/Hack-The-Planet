package HackThePlanet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	
	public static int windowWidth = 1920;
	public static int windowHeight = 1080;
	
	public static Scanner sc = new Scanner(System.in);
	
	public static JFrame frame = new JFrame("Hack the Planet Delta 1.0");
	
	public static JLabel bg = new JLabel(new ImageIcon(Main.class.getResource("/Menu.png")));
	public static JLabel screenImage;
	
	public static String OS = System.getProperty("os.name").toLowerCase();
	
	public static MainMenu menu;
	public static HackScreen hackScreen;
	public static Desktop desktop;
	public static Settings settings;
	public static Tutorial tut;
	public static LevelSelect ls;
	
	public static int countHackScreen = 0, countDesktop = 0;
	
	public static boolean desktopAlreadyExists = false;
	public static boolean hackScreenAlreadyExists = false;
	public static boolean settingsScreenAlreadyExists = false;
	public static boolean tutorialAlreadyExists = false;
	public static boolean mainMenuAlreadyExists = false;
	public static boolean levelSelectAlreadyExists = false;
	
	public static boolean firstScreenImageChange = true;
	
	public static void main(String[] args) {
		
		System.out.println("Operating System: " + OS.toUpperCase());
		System.out.println("");
		drawWindow();
		frame.setVisible(false);
		frame.setVisible(true);
	}
	
	public static void drawWindow() {
		Settings.getSettingsFromFile();
		Settings.removeButtons();
		
		frame.setLayout(null);
		
		frame.setBackground(Color.black);
		
		frame.setSize(windowWidth, windowHeight);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		
		frame.add(bg);
		changeWindow("mainmenu", 0);
		
		Hitbox killSwitch = new Hitbox(45, 40, 0, 0, false, Color.red, "", false);
		killSwitch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				print("Kill button clicked");
				System.exit(0);
			}
		});
		
		frame.setVisible(true);
		frame.repaint();
	}
	
	public static void changeWindow(String newWindow, int levelNo) {
		frame.remove(bg);
		if (newWindow.equalsIgnoreCase("hackscreen")) {
			changeBackground(new JLabel(new ImageIcon(Main.class.getResource(GamePlay.backgroundString))));
			changeScreenImage(new JLabel(new ImageIcon(Main.class.getResource(GamePlay.hackScreenString))));
			if (hackScreenAlreadyExists == false) {
				hackScreen = new HackScreen(levelNo);
				hackScreenAlreadyExists = true;
			}
		}
		else if (newWindow.equalsIgnoreCase("desktop")) {
			changeBackground(new JLabel(new ImageIcon(Main.class.getResource(GamePlay.backgroundString))));
			changeScreenImage(new JLabel(new ImageIcon(Main.class.getResource(GamePlay.desktopString))));
			if (desktopAlreadyExists == false) {
				desktop = new Desktop(levelNo);
				desktopAlreadyExists = true;
			}
		}
		else if (newWindow.equalsIgnoreCase("settings")) {
			changeBackground(new JLabel(new ImageIcon(Main.class.getResource("/Settings.png"))));
			if (settingsScreenAlreadyExists == false) {
				settings = new Settings("yes");
				settingsScreenAlreadyExists= true;
			}
			else {
				settings.addButtons();
			}
		}
		else if (newWindow.equalsIgnoreCase("mainmenu")) {
			changeBackground(new JLabel(new ImageIcon(Main.class.getResource("/Menu.png"))));
			if (mainMenuAlreadyExists == false) {
				menu = new MainMenu();
				mainMenuAlreadyExists = true;
			}
			else {
				menu.addButtons();
			}
		}
		else if (newWindow.equalsIgnoreCase("tutorial")) {
			changeBackground(new JLabel(new ImageIcon(Main.class.getResource("/tutorial/tutorial1.png"))));
			Main.frame.add(Tutorial.nextButton);
			if (tutorialAlreadyExists == false) {
				tut = new Tutorial();
				tutorialAlreadyExists = true;
			}
		}
		else if (newWindow.equalsIgnoreCase("levelSelect")) {
			changeBackground(new JLabel(new ImageIcon(Main.class.getResource("/levelSelect2.png"))));
			if (levelSelectAlreadyExists == false) {
				ls = new LevelSelect();
				levelSelectAlreadyExists = true;
			}
			else {
				ls.highestLevel = GamePlay.highestLevel;
				LevelSelect.addButtons();
			}
		}
		frame.repaint();
	}
	
	public static void changeBackground(JLabel path) {
		bg = path;
		bg.setSize(1920, 1080);
		bg.setLocation(0, 0);
		frame.add(bg);
		frame.repaint();
	}
	
	public static void changeScreenImage(JLabel p) {
		if (GamePlay.play) {
			if (GamePlay.hackScreen) {
				screenImage.remove(GamePlay.progressBar);
				screenImage.remove(GamePlay.progressPercentage);
			}
			else if (!GamePlay.hackScreen) {
				screenImage.add(GamePlay.progressBar);
				screenImage.add(GamePlay.progressPercentage);
			}
		}
		screenImage = p;
		screenImage.setSize(619, 348);
		screenImage.setLocation(252, 294);
		if (firstScreenImageChange) {
			bg.add(screenImage);
			bg.repaint();
			firstScreenImageChange = false;
		}
		else {
			bg.remove(screenImage);
			bg.add(screenImage);
		}
		frame.repaint();
	}
	
	public static void print(String s) {
		System.out.println(s);
	}
}
