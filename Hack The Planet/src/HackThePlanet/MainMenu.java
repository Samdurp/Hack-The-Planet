package HackThePlanet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MainMenu {
	
	public static Hitbox playButton = new Hitbox(255, 125, 80, 379, true, Color.GREEN, null, false);
	public static Hitbox tutorialButton = new Hitbox(467, 125, 80, 551, true, Color.red, null, false);
	public static Hitbox settingsButton = new Hitbox(448, 125, 80, 723, true, Color.white, null, false);
	public static Hitbox exitButton = new Hitbox(220, 125, 80, 895, true, Color.red, null, false);
	
	public MainMenu() {
	
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("PLAYBUTTON clicked");
				Main.changeWindow("levelselect", 0);
				Main.frame.setVisible(false);
				Main.frame.setVisible(true);
				LevelSelect.doLocks();
				removeButtons();
			}
		});
		settingsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Settings.getSettingsFromFile();
				//System.out.println("SETTINGSBUTTON clicked");
				Main.changeWindow("settings", 0);
				Settings.addButtons();
				//Settings.tickTimer.start();
				Settings.setupLabels();
				Main.frame.setVisible(false);
				Main.frame.setVisible(true);
				removeButtons();
			}
		});
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.frame.setVisible(false);
				//System.out.println("EXITBUTTON clicked");
				System.exit(0);
				removeButtons();
			}
		});
		tutorialButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.changeWindow("tutorial", 0);
				Main.frame.setVisible(false);
				Main.frame.setVisible(true);
				removeButtons();
			}
		});
		
		Main.frame.repaint();
	}
	
	private static void removeButtons() {
		Main.frame.remove(playButton);
		Main.frame.remove(settingsButton);
		Main.frame.remove(exitButton);
		Main.frame.remove(tutorialButton);
	}
	
	public static void addButtons() {
		Main.frame.add(playButton);
		Main.frame.add(settingsButton);
		Main.frame.add(exitButton);
		Main.frame.add(tutorialButton);
	}
}
