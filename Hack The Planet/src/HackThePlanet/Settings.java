package HackThePlanet;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.Reader;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Settings implements ActionListener, KeyListener {
	
	public static double peopleVolume;
	public static double musicVolume;
	public static char keyBind;
	
	private static Hitbox backButton = new Hitbox(300, 141, 0, 0, false, Color.GREEN, "", true);
	
	private static Hitbox musicVolumeUp = new Hitbox(75, 75, 1068, 365, false, Color.WHITE, "", true);
	private static Hitbox musicVolumeDown = new Hitbox(75, 75, 868, 365, false, Color.WHITE, "", true);
	
	private static Hitbox soundVolumeUp = new Hitbox(75, 75, 1068, 540, false, Color.WHITE, "", true);
	private static Hitbox soundVolumeDown = new Hitbox(75, 75, 868, 540, false, Color.WHITE, "", true);
	
	private static Hitbox soundVolumeLabel = new Hitbox(75, 75, 968, 540, true, Color.WHITE, "", false);
	private static Hitbox musicVolumeLabel = new Hitbox(75, 75, 968, 365, true, Color.WHITE, "", false);
	
	private static Hitbox saveSettings = new Hitbox(262, 90, 85, 877, false, Color.yellow, "", false);
	
	private static JTextField keyBindField = new JTextField();
	
	public static File file = new File("src/settings.txt");
	
	public static Font basic;
	
	public Settings(String yes) {
		try {
			basic = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResource("/basic.ttf").openStream());
			GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			genv.registerFont(basic);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		musicVolumeLabel.setFont(basic.deriveFont(Font.PLAIN, 25f));
		musicVolumeLabel.setForeground(Color.BLACK);
		soundVolumeLabel.setFont(basic.deriveFont(Font.PLAIN, 25f));
		soundVolumeLabel.setForeground(Color.BLACK);
		
		backButton.setFont(basic.deriveFont(Font.PLAIN, 20f));
		backButton.setSize(100, 50);
		backButton.setLocation(1810,1020);
		backButton.setBackground(Color.BLACK);
		backButton.setForeground(Color.white);
		
		setupThings();
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("BACK TO MENU clicked");
				
				removeButtons();
				
				Main.frame.add(MainMenu.playButton);
				Main.frame.add(MainMenu.tutorialButton);
				Main.frame.add(MainMenu.settingsButton);
				Main.frame.add(MainMenu.exitButton);
				
				Main.changeWindow("mainmenu", 0);
				Main.frame.setVisible(false);
				Main.frame.setVisible(true);
			}
		});
		musicVolumeUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMusicVolume(musicVolume + 0.1);
				if (musicVolume >= 1.0) {
					musicVolume = 1.0;
				}
				int volume = ((int) (musicVolume * 10));
				//GamePlayOne.playMusic("/audio/ding.wav", false);
				musicVolumeLabel.setText("" + volume);
			}
		});
		musicVolumeDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMusicVolume(musicVolume - 0.1);
				if (musicVolume <= 0.0) {
					musicVolume = 0.0;
				}
				int volume = ((int) (Math.round(musicVolume * 10)));
				//GamePlayOne.playMusic("/audio/ding.wav", false);
				musicVolumeLabel.setText("" + volume);
			}
		});
		soundVolumeUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setPeopleVolume(peopleVolume + 0.1);
				if (peopleVolume >= 1.0) {
					peopleVolume = 1.0;
				}
				int volume = ((int) (peopleVolume * 10));
				//GamePlayOne.playSound("/audio/ding.wav", false);
				soundVolumeLabel.setText("" + volume);
			}
		});
		soundVolumeDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setPeopleVolume(peopleVolume - 0.1);
				if (peopleVolume <= 0.0) {
					peopleVolume = 0.0;
				}
				int volume = ((int) (Math.round(peopleVolume * 10)));
				//GamePlayOne.playSound("/audio/ding.wav", false);
				soundVolumeLabel.setText("" + volume);
			}
		});
		saveSettings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("save clicked");
				//System.out.println("");
				writeSettingsToFile();
				Sound.playSound("/audio/ding.wav", false);
			}
		});
		Main.frame.repaint();
	}
	
	public static void getSettingsFromFile() {
		try {
			FileReader reader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(reader);
			LineNumberReader lnr = new LineNumberReader(new FileReader(file));
			bufferedReader.readLine(); //This skips the first line
			setMusicVolume(Double.parseDouble(bufferedReader.readLine()));
			setPeopleVolume(Double.parseDouble(bufferedReader.readLine()));
			keyBind = bufferedReader.readLine().charAt(0);
			
            reader.close();
            bufferedReader.close();
            lnr.close();
		} 
		catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	public static char getKeybindFromFile() {
		char output = 0;
		try {
			FileReader reader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(reader);
			LineNumberReader lnr = new LineNumberReader(new FileReader(file));
			bufferedReader.readLine(); //This skips the first line
			bufferedReader.readLine(); //This skips the music volume
			bufferedReader.readLine(); //This skips the sound volume
			output = bufferedReader.readLine().charAt(0);
			
            reader.close();
            bufferedReader.close();
            lnr.close();
		} 
		catch (Exception e) {
			System.out.println("I don't know why but this throws an error, its fine tho");
		}
		return output;
	}
	
	public static void writeSettingsToFile() {
		try {
			PrintWriter pw = new PrintWriter(file);
			pw.close();
		} catch (Exception e) {
		}
		try {
			if (keyBindField.getText().isEmpty()) {
				keyBind = ' ';
			}
			else {
				keyBind = keyBindField.getText().charAt(0);
			}
			FileWriter writer = new FileWriter(file, true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			LineNumberReader lnr = new LineNumberReader(new FileReader(file));
			bufferedWriter.write("Settings - Music, Sound, Keybind. Respectively.");
			for (int i = 0; i <= 4; i += 2) {
				lnr.setLineNumber(i);
	            	bufferedWriter.newLine();
	            if (i == 0) {
	            		bufferedWriter.write("" + getMusicVolume());
	            }
	            if (i == 2) {
	            		bufferedWriter.write("" + getPeopleVolume());
	            }
	            if (i == 4) {
	            		bufferedWriter.write("" + keyBind);
	            }
			}
			bufferedWriter.close();
            lnr.close();
			writer.close();
		} catch (Exception e) {
			System.out.println("I don't know why but this throws an error, its fine tho");
		}
	}
	
	public void yeet() {
		file = new File(getClass().getResource("settings.txt").getFile());
	}
	
	public static void setupLabels() {
		keyBindField.setLocation(586, 714);
		
		musicVolumeLabel.setOpaque(true);
		soundVolumeLabel.setOpaque(true);
		keyBindField.setOpaque(true);
		
		keyBindField.setSize(75, 75);
		
		keyBindField.setBackground(Color.WHITE);
		
		keyBindField.setFont(basic.deriveFont(Font.PLAIN, 25f));
		keyBindField.setForeground(Color.BLACK);
		
		musicVolumeLabel.setText("" + (int) Math.round(musicVolume * 10));
		soundVolumeLabel.setText("" + (int) Math.round(peopleVolume * 10));
		keyBindField.setText(getKeybindFromFile() + "");
	}
	
	public static void addButtons() {
		Main.frame.add(backButton);
		Main.frame.add(musicVolumeUp);
		Main.frame.add(musicVolumeDown);
		Main.frame.add(soundVolumeUp);
		Main.frame.add(soundVolumeDown);
		Main.frame.add(musicVolumeLabel);
		Main.frame.add(soundVolumeLabel);
		Main.frame.add(saveSettings);
		Main.frame.add(keyBindField);
	}
	
	public static void removeButtons() {
		Main.frame.remove(backButton);
		Main.frame.remove(musicVolumeUp);
		Main.frame.remove(musicVolumeDown);
		Main.frame.remove(soundVolumeUp);
		Main.frame.remove(soundVolumeDown);
		Main.frame.remove(musicVolumeLabel);
		Main.frame.remove(soundVolumeLabel);
		Main.frame.remove(saveSettings);
		Main.frame.remove(keyBindField);
	}
	
	public static void setupThings() {
		musicVolumeUp.setIcon(new ImageIcon(Main.class.getResource("/upArrow.png")));
		musicVolumeDown.setIcon(new ImageIcon(Main.class.getResource("/downArrow.png")));
		soundVolumeUp.setIcon(new ImageIcon(Main.class.getResource("/upArrow.png")));
		soundVolumeDown.setIcon(new ImageIcon(Main.class.getResource("/downArrow.png")));
		Main.frame.add(soundVolumeLabel);
		Main.frame.add(musicVolumeLabel);
		Main.frame.add(keyBindField);
		backButton.setText("Back");
		soundVolumeLabel.setText("uh");
		musicVolumeLabel.setText("mem");
		saveSettings.setText("Save");
		//soundVolumeLabel.setEnabled(false);
		//musicVolumeLabel.setEnabled(false);
		GamePlay.lossScreen.setEnabled(false);
		GamePlay.lossScreen.setVisible(false);
		GamePlay.victoryScreen.setEnabled(false);
		GamePlay.victoryScreen.setVisible(false);
		GamePlay.gainedPoints.setEnabled(false);
		GamePlay.gainedPoints.setVisible(false);
		GamePlay.lostPoints.setEnabled(false);
		GamePlay.lostPoints.setVisible(false);
		GamePlay.playButton.setVisible(false);
		GamePlay.playButton.setEnabled(false);
		GamePlay.pauseButton.setVisible(false);
		GamePlay.pauseButton.setEnabled(false);
		GamePlay.pausedScreen.setVisible(false);
		GamePlay.pausedScreen.setEnabled(false);
	}
	
	public static void removeThings() {
		GamePlay.lossScreen.setEnabled(false);
		GamePlay.lossScreen.setVisible(false);
		GamePlay.victoryScreen.setEnabled(false);
		GamePlay.victoryScreen.setVisible(false);
		GamePlay.gainedPoints.setEnabled(false);
		GamePlay.gainedPoints.setVisible(false);
		GamePlay.lostPoints.setEnabled(false);
		GamePlay.lostPoints.setVisible(false);
		GamePlay.playButton.setVisible(false);
		GamePlay.playButton.setEnabled(false);
		GamePlay.pauseButton.setVisible(false);
		GamePlay.pauseButton.setEnabled(false);
		GamePlay.pausedScreen.setVisible(false);
		GamePlay.pausedScreen.setEnabled(false);
	}
	
	public static void setPeopleVolume(double peopleVolume2) {
		peopleVolume = (float) peopleVolume2;
	}
	public static double getPeopleVolume() {
		return peopleVolume;
	}
	public static void setMusicVolume(double musicVolume2) {
		musicVolume = (float) musicVolume2;
	}
	public static double getMusicVolume() {
		return musicVolume;
	}
	public static char getKeyBind() {
		return keyBind;
	}
	public static void setKeyBind(char a) {
		keyBind = a;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
