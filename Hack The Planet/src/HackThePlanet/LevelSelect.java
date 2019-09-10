package HackThePlanet;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public class LevelSelect {
	
	public static boolean level1Exists = false;
	
	public static int highestLevel = 1;
	
	public static GamePlay gp;
	
	public static Hitbox level1 = new Hitbox(400, 125, 80, 276, false, Color.red, "Google", false);
	public static Hitbox level2 = new Hitbox(312, 125, 80, 401, false, Color.orange, "Appel", false);
	public static Hitbox level3 = new Hitbox(612, 125, 80, 526, false, Color.yellow, "Wall Streat", false);
	public static Hitbox level4 = new Hitbox(648, 125, 80, 651, false, Color.green, "Pentagon", false);
	public static Hitbox level5 = new Hitbox(564, 125, 80, 776, false, Color.blue, "Devil", false);
	public static Hitbox level6 = new Hitbox(230, 125, 80, 901, false, Color.MAGENTA, "God", false);
	public static Hitbox backToMM = new Hitbox(100, 100, 1500, 900, true, Color.red, "Back", true);
	
	public static Hitbox lock2 = new Hitbox(64, 64, 15, 435, true, Color.black, null, true);
	public static Hitbox lock3 = new Hitbox(64, 64, 15, 558, true, Color.black, null, true);
	
	public static boolean level2enabled = true;
	public static boolean level3enabled = true;
	public static boolean level4enabled = true;
	public static boolean level5enabled = true;
	public static boolean level6enabled = true;
	
	public static Font basic;
			
	public LevelSelect() {
		try {
			basic = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResource("/basic.ttf").openStream());
			GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			genv.registerFont(basic);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	
		backToMM.setFont(basic.deriveFont(Font.PLAIN, 20f));
		backToMM.setSize(100, 50);
		backToMM.setLocation(1810,1020);
		backToMM.setBackground(Color.BLACK);
		backToMM.setForeground(Color.white);
		
		lock2.setIcon(new ImageIcon(Main.class.getResource("/lock.png")));
		lock3.setIcon(new ImageIcon(Main.class.getResource("/lock.png")));
		
		doLocks();
		
		level1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (level1Exists) {
					GamePlay.reset();
					Main.frame.add(HackScreen.minimize);
				}
				gp = new GamePlay(new Level("Googel", 0, 7, 5, 15, 60));
				removeButtons();
				Main.changeWindow("hackscreen", 1);
				GamePlay.play = true;
				Main.screenImage.add(GamePlay.progressBar);
				Main.screenImage.add(GamePlay.progressPercentage);
				Main.bg.add(GamePlay.timeLabel);
				GamePlay.timeLabel.setText("00:" + GamePlay.timeLeft);
				Main.frame.repaint();
				level1Exists = true;
			}
		});
		level2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (highestLevel >= 2 && level2enabled) {
					if (level1Exists) {
						GamePlay.reset();
						Main.frame.add(HackScreen.minimize);
					}
					gp = new GamePlay(new Level("Appel", 0, 10, 5, 12, 55));
					removeButtons();
					Main.changeWindow("hackscreen", 1);
					GamePlay.play = true;
					Main.screenImage.add(GamePlay.progressBar);
					Main.screenImage.add(GamePlay.progressPercentage);
					Main.bg.add(GamePlay.timeLabel);
					GamePlay.timeLabel.setText("00:" + GamePlay.timeLeft);
					Main.frame.repaint();
					level1Exists = true;
					}
				}
			});
		level3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (highestLevel >= 3 && level3enabled) {
					if (level1Exists) {
						GamePlay.reset();
						Main.frame.add(HackScreen.minimize);
					}
					gp = new GamePlay(new Level("Wall Streat", 0, 13, 4, 10, 45));
					removeButtons();
					Main.changeWindow("hackscreen", 1);
					GamePlay.play = true;
					Main.screenImage.add(GamePlay.progressBar);
					Main.screenImage.add(GamePlay.progressPercentage);
					Main.bg.add(GamePlay.timeLabel);
					GamePlay.timeLabel.setText("00:" + GamePlay.timeLeft);
					Main.frame.repaint();
					level1Exists = true;
				}
			}
		});
		backToMM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.changeWindow("mainmenu", 0);
				Main.frame.add(MainMenu.playButton);
				Main.frame.add(MainMenu.settingsButton);
				Main.frame.add(MainMenu.exitButton);
				Main.frame.setVisible(false);
				Main.frame.setVisible(true);
				Settings.removeThings();
				removeButtons();
			}
		});
	}
	
	public static void doLocks() {
		if(highestLevel > 1) {
			lock2.setVisible(false);
		}
		if(highestLevel > 2) {
			lock3.setVisible(false);
		}
	}
	
	public static void removeButtons() {
		Main.frame.remove(level1);
		Main.frame.remove(level2);
		Main.frame.remove(level3);
		Main.frame.remove(level4);
		Main.frame.remove(level5);
		Main.frame.remove(level6);
		Main.frame.remove(backToMM);
		Main.frame.remove(lock2);
		Main.frame.remove(lock3);
	}
	
	public static void addButtons() {
		Main.frame.add(lock2);
		Main.frame.add(lock3);
		Main.frame.add(level1);
		Main.frame.add(level2);
		Main.frame.add(level3);
		Main.frame.add(level4);
		Main.frame.add(level5);
		Main.frame.add(level6);
		Main.frame.add(backToMM);
	}
}
