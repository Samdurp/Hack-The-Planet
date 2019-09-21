package HackThePlanet;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JComponent implements ActionListener, KeyListener {
	
	public static int highestLevel;
	
	//True = game is running, false = game is stopped/paused
	public static boolean play = false;
	
	//Timer that causes an event every second
	public static Timer secondTimer = null;
	//Makes timer fire about 1 time per second
	public static int secondDelay = 1000;
	//Counts every second
	public static int secondCounter = 0;
	//Stores the previous second, basically secondCounter--
	public static int lastSecond = 0;
	
	//Timer that causes an event every tick
	public static Timer tickTimer = null;
	//Makes timer fire about 60 times per second
	public static int tickDelay = 15;
	//Counts every tick
	public static int tickCounter = 1;
	
	//Says if a good or bad guy is at the door
	public static boolean goodAtDoor;
	public static boolean badAtDoor;
	
	//Int that holds the value of the person coming to the door
	public static int person;
	//String that says who is coming to the door
	public static String personString;
	
	//Max and min seconds for people to visit
	public static int maxSeconds;
	public static int minSeconds;
	//The time until next visitor, randomly generated (between max and min seconds)
	public static int randNum;
	
	//Width of progress bar (in pixels)
	public static int pbWidth = 0;
	public static double pbWidthDouble = (double) pbWidth;
	   
	//Progress bar (green part)
	public static JLabel progressBar = new JLabel();
	//Percentage of progress bar that is completed, in a double for calculation purposes
	public static JLabel progressPercentage = new JLabel();
	
	//Containers for images of the people who come to visit you
	public static JLabel good1;
	public static JLabel good2;
	public static JLabel bad1;
	public static JLabel bad2;
	
	//Strings that hold directory for sounds of people coming to door
	public static String good1Sound;
	public static String good2Sound;
	public static String bad1Sound;
	public static String bad2Sound;
	
	//Strings that hold directory for images of people in door
	public static String good1Picture;
	public static String good2Picture;
	public static String bad1Picture;
	public static String bad2Picture;
	
	//Some stuff to make sure stuff only happens once 
	public static boolean done = false;
	public static boolean didIt = false;
	
	//True = hackscreen is open, false = desktop is open
	public static boolean hackScreen = true;	
	
	//Time until loss (in seconds)
	public static int timeLeft;
	//Container for showing time left before loss
	public static JLabel timeLabel = new JLabel();
	
	//What comes up when you lose
	public static Hitbox lossScreen = new Hitbox(1920, 1080, 0, 0, false, Color.WHITE, "Is this loss", false);
	//What comes up when you win
	public static Hitbox victoryScreen = new Hitbox(1920, 1080, 0, 0, false, Color.WHITE, "Bictory", false);
	
	//Comes up when you gain progress from a person
	public static Hitbox gainedPoints = new Hitbox(300, 30, 561, 586, false, null, "GAINED POINTS!", false);
	//Comes up when you lose progress from a person
	public static Hitbox lostPoints = new Hitbox(300, 30, 238, 580, false, null, "LOST POINTS!", false);
	
	//Pauses game when clicked
	public static Hitbox pauseButton = new Hitbox(50, 50, 1865, 5, false, new Color(0,0,0,0), null, false);
	//Resumes game when clicked (only shows when game paused)
	public static Hitbox playButton = new Hitbox(50, 50, 1865, 5, false, new Color(0,0,0,0), null, false);
	//Shows on screen when game paused
	public static Hitbox pausedScreen = new Hitbox(1920, 1080, 0, 0, false, new Color(64,64,64, 127), null, true);
	
	//Audio controller thing
	public static Clip clip;
	
	//Audio controller thing for music
	public static Clip musicClip;
	
	//Listener for pressing space
	public static KeyEventDispatcher keyEventDispatcher = null;
	
	public static boolean devMode = false;
	
	//Position and size of the visitor images
	public static int peopleLocationX;
	public static int peopleLocationY;
	public static int peopleSizeX;
	public static int peopleSizeY;
	
	//Strings that contain the directory for the images used for the level
	public static String backgroundString;
	public static String desktopString;
	public static String hackScreenString;
	
	//String that contains the directory for the song of the level
	public static String levelMusic;
	
	public static int timeLabelPosX;
	public static int timeLabelPosY;
	
	public static Font digital;
	public static Font basic;
	
	public static JLabel loseCauseOfDeath = new JLabel();
	public static JLabel loseTimeLeft = new JLabel();
	public static JLabel losePercentHacked = new JLabel();
	
	public static JLabel winCurrentLevel = new JLabel();
	public static JLabel winNextLevel = new JLabel();
	public static JLabel winTimeLeft = new JLabel();
	
	public static int terminalLocationX;
	public static int terminalLocationY;
	
	public static double percentHacked;
	
	public static void setupLevelOne() {
		//Places the second counter at the specified coords
		timeLabelPosX = 964;
		timeLabelPosY = 737;
		//Sets directories for images
		backgroundString = "/levelone/Background.png";
		desktopString = "/levelone/desktop.png";
		hackScreenString = "/levelone/screenImage.png";
		//Sets size and location of visitor pictures
		peopleLocationX = 1232;
		peopleLocationY = 0;
		peopleSizeX = 688;
		peopleSizeY = 1080;
		//Sound setup
		good1Sound = "/audio/russian.wav";
		good2Sound = "/audio/bad2.wav";
		bad1Sound = "/audio/cop.wav";
		bad2Sound = "/audio/swat.wav";
		//Picture setup
		good1Picture = "/levelone/good1.png";
		good2Picture = "/levelone/good2.png";
		bad1Picture = "/levelone/bad1.png";
		bad2Picture = "/levelone/bad2.png";
		
		terminalLocationX = 821;
		terminalLocationY = 578;
		
		levelMusic = "/audio/valley.wav";
		
		System.out.println("Setup done, level 1 loaded");
	}
	public static void setupLevelTwo() {
		//Places the second counter at the specified coords
		timeLabelPosX = 1028;
		timeLabelPosY = 363;
		//Sets directories for images
		backgroundString = "/leveltwo/Background.png";
		desktopString = "/leveltwo/desktop.png";
		hackScreenString = "/leveltwo/hackscreen.png";
		//Sets size and location of visitor pictures
		peopleLocationX = 1198;
		peopleLocationY = 292;
		peopleSizeX = 329;
		peopleSizeY = 517;
		//Sound setup
		good1Sound = "/audio/russian.wav";
		good2Sound = "/audio/bad2.wav";
		bad1Sound = "/audio/cop.wav";
		bad2Sound = "/audio/swat.wav";
		//Picture setup
		good1Picture = "/leveltwo/good1.png";
		good2Picture = "/leveltwo/good2.png";
		bad1Picture = "/leveltwo/bad1.png";
		bad2Picture = "/leveltwo/bad2.png";
		
		terminalLocationX = 269;
		terminalLocationY = 316;
		Desktop.hackScreenDesktopIcon.setLocation(terminalLocationX, terminalLocationY);
		
		levelMusic = "/audio/bassdrop.wav";
		
		System.out.println("Setup done, level 2 loaded");
	}
	public static void setupLevelThree() {
		//Places the second counter at the specified coords
		timeLabelPosX = 1093;
		timeLabelPosY = 304;
		//Sets directories for images
		backgroundString = "/levelthree/Background.png";
		desktopString = "/levelthree/desktop.png";
		hackScreenString = "/levelthree/hackScreen.png";
		//Sets size and location of visitor pictures
		peopleLocationX = 1432;
		peopleLocationY = 61;
		peopleSizeX = 434;
		peopleSizeY = 702;
		//Sound setup
		good1Sound = "/audio/russian.wav";
		good2Sound = "/audio/bad2.wav";
		bad1Sound = "/audio/cop.wav";
		bad2Sound = "/audio/swat.wav";
		//Picture setup
		good1Picture = "/levelthree/good1.png";
		good2Picture = "/levelthree/good2.png";
		bad1Picture = "/levelthree/bad1.png";
		bad2Picture = "/levelthree/bad2.png";
		
		terminalLocationX = 648;
		terminalLocationY = 498;
		Desktop.hackScreenDesktopIcon.setLocation(terminalLocationX, terminalLocationY);
		
		levelMusic = "/audio/hammer.wav";
		
		System.out.println("Setup done, level 3 loaded");
		//269 316 L2
		//648 498 L3
	}
	
	public GamePlay(Level level) {
		try {
			digital = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResource("/digital.ttf").openStream());
			GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			genv.registerFont(digital);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			basic = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResource("/basic.ttf").openStream());
			GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			genv.registerFont(basic);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		setupThings();
		
		if (level.getLevelName().equalsIgnoreCase("googel")) {
			setupLevelOne();
		}
		else if (level.getLevelName().equalsIgnoreCase("appel")) {
			setupLevelTwo();
		}
		else if (level.getLevelName().equalsIgnoreCase("wall streat")) {
			setupLevelThree();
		}
		else if (level.getLevelName().equalsIgnoreCase("hexagon")) {
			setupLevelOne();
		}
		else if (level.getLevelName().equalsIgnoreCase("purgatory")) {
			setupLevelOne();
		}
		else if (level.getLevelName().equalsIgnoreCase("god")) {
			setupLevelOne();
		}
		startTimers();
		Settings.removeButtons();
		//Gets the time allotted from the level object in method arguments ^
		timeLeft = level.getTime();
		
		//Sets up the label that tells the player how much time they have left
		timeLabel.setLocation(timeLabelPosX, timeLabelPosY);
		timeLabel.setSize(150, 30);
		if (level.getLevelName().equalsIgnoreCase("wall streat")) {
			timeLabel.setFont(digital.deriveFont(Font.BOLD, 50f));
		}
		else if (level.getLevelName().equalsIgnoreCase("Googel")) {
			timeLabel.setFont(digital.deriveFont(Font.BOLD, 40f));
		}
		else { 
			timeLabel.setFont(digital.deriveFont(Font.BOLD, 25f));
		}
		timeLabel.setForeground(Color.black);
		
		//Adds it to frame, but keeps it invisible and unclickable until loss occurs
		Main.frame.add(lossScreen);
		lossScreen.setEnabled(false);
		lossScreen.setVisible(false);
		//Changes the label's background to loss.jpg
		lossScreen.setIcon(new ImageIcon(Main.class.getResource("/loss7.png")));
		
		//Adds it to frame, but keeps it invisible and unclickable until loss occurs
		Main.frame.add(victoryScreen);
		victoryScreen.setEnabled(false);
		victoryScreen.setVisible(false);
		//Changes the label's background to win.jpg
		victoryScreen.setIcon(new ImageIcon(Main.class.getResource("/win.png")));
		
		//Sets up the progress bar 
		progressBar.setLocation(87, 224);
		progressBar.setSize(pbWidth, 35);
		//Makes the progress bar visible and green
		progressBar.setOpaque(true);
		progressBar.setBackground(Color.GREEN);
		
		//Sets up the label that tells the player what percentage they have completed
		progressPercentage.setLocation(78, 256);
		progressPercentage.setSize(400, 100);
		//Arial with no bold, underline, etc. Size 14 font.
		progressPercentage.setFont(basic.deriveFont(Font.PLAIN, 20f));
		
		gainedPoints.setFont(basic.deriveFont(Font.PLAIN, 20f));
		lostPoints.setFont(basic.deriveFont(Font.PLAIN, 20f));
		gainedPoints.setForeground(Color.green);
		lostPoints.setForeground(Color.red);
		
		playButton.setIcon(new ImageIcon(Main.class.getResource("/play.png")));
		pauseButton.setIcon(new ImageIcon(Main.class.getResource("/pause.png")));

		//Sets max and min seconds to what was specified in the level object in method arguments
		maxSeconds = level.maxSeconds;
		minSeconds = level.minSeconds;
		
		Main.frame.add(gainedPoints);
		gainedPoints.setVisible(false);
		Main.frame.add(lostPoints);
		lostPoints.setVisible(false);

		loseCauseOfDeath.setLocation(483, 427);
		loseCauseOfDeath.setSize(500, 42);
		loseCauseOfDeath.setFont(basic.deriveFont(Font.PLAIN, 48f));
		loseCauseOfDeath.setForeground(Color.red);
		loseTimeLeft.setLocation(375, 569);
		loseTimeLeft.setSize(500, 40);
		loseTimeLeft.setFont(basic.deriveFont(Font.PLAIN, 48f));
		loseTimeLeft.setForeground(Color.red);
		losePercentHacked.setLocation(510, 715);
		losePercentHacked.setSize(500, 40);
		losePercentHacked.setFont(basic.deriveFont(Font.PLAIN, 48f));
		losePercentHacked.setForeground(Color.red);
		
		winCurrentLevel.setLocation(228, 427);
		winCurrentLevel.setSize(500, 42);
		winCurrentLevel.setFont(basic.deriveFont(Font.PLAIN, 48f));
		winCurrentLevel.setForeground(Color.green);
		winNextLevel.setLocation(228, 569);
		winNextLevel.setSize(500, 40);
		winNextLevel.setFont(basic.deriveFont(Font.PLAIN, 48f));
		winNextLevel.setForeground(Color.green);
		winTimeLeft.setLocation(363, 715);
		winTimeLeft.setSize(500, 40);
		winTimeLeft.setFont(basic.deriveFont(Font.PLAIN, 48f));
		winTimeLeft.setForeground(Color.green);
		
		//Rolls the time for the first visitor
		randomNumber();
		
		//Plays background music
		Sound.playMusic(levelMusic, false);
		
		Main.frame.repaint();
		
		//Basially a key listener, listens for keybind being pressed
		while (hackScreen = true) {
			keyEventDispatcher = new KeyEventDispatcher() {
				@Override
				public boolean dispatchKeyEvent(KeyEvent e) {
					//Devmode on allows keybind to be held down, off only counts individual presses
					if (devMode) {
						if (e.getID() == KeyEvent.KEY_PRESSED) {
							char what = Settings.getKeybindFromFile();
							if (e.getKeyChar() == what && play && hackScreen) { //If the key pressed is the keybind, do the thing below
								pbWidth += 3; //This is normally set to 3
							}
						}
					}
					else {
						if (e.getID() == KeyEvent.KEY_RELEASED) {
							char what = Settings.getKeybindFromFile();
							if (e.getKeyChar() == what && play && hackScreen) { //If the key pressed is the keybind, do the thing below
								pbWidth += 3; //This is normally set to 3
							}
						}
					}
					return false;
				}
			};
			//Honestly dunno what this does but the program needs it
			KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
			break;
		}
		
		//Event for when the button that comes up when you lose a round is clicked
		lossScreen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loseCauseOfDeath.setVisible(false);
				loseTimeLeft.setVisible(false);
				losePercentHacked.setVisible(false);
				Sound.stopSound();
				Main.changeWindow("mainmenu", 0);
				Main.frame.remove(lossScreen);
				Main.frame.remove(gainedPoints);
				Main.frame.remove(lostPoints);
				Main.frame.remove(HackScreen.minimize);
				Main.frame.remove(Desktop.hackScreenDesktopIcon);
				pauseButton.setVisible(false);
				playButton.setVisible(false);
				reset();
			}
		});
		
		//Stuff happens when you click on the victory screen
		victoryScreen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				winCurrentLevel.setVisible(false);
				winNextLevel.setVisible(false);
				winTimeLeft.setVisible(false);
				//Sound.stopSound();
				Main.changeWindow("mainmenu", 0);
				Main.frame.remove(victoryScreen);
				Main.frame.remove(gainedPoints);
				Main.frame.remove(lostPoints);
				Main.frame.remove(HackScreen.minimize);
				Main.frame.remove(Desktop.hackScreenDesktopIcon);
				pauseButton.setVisible(false);
				playButton.setVisible(false);
				reset();
			}
		});
		
		//Game is paused, play button and paused screen come up
		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				play = false;
				pausedScreen.setVisible(true);
				pauseButton.setVisible(false);
				pauseButton.setEnabled(false);
				playButton.setVisible(true);
				playButton.setEnabled(true);
				freezeButtons();
			}
		});
		
		//Game is resumed, paused screen and play button disappear
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("play clicked");
				play = true;
				playButton.setVisible(false);
				playButton.setEnabled(false);
				pauseButton.setVisible(true);
				pauseButton.setEnabled(true);
				pausedScreen.setVisible(false);
				unfreezeButtons();
			}
		});

		//Event that fires ~60 times per second
		tickTimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (play) {
					//Settings.removeButtons();
					//Refreshes the label that shows time left
					Main.bg.remove(timeLabel);
					Main.bg.add(timeLabel);
					//If you fill the bar to capacity, you win and the game stops
					if(pbWidth >= 442) {
						if (level.getLevelName().equalsIgnoreCase("googel")) {
							winCurrentLevel.setText("1");
							winNextLevel.setText("2");
						}
						else if (level.getLevelName().equalsIgnoreCase("appel")) {
							winCurrentLevel.setText("2");
							winNextLevel.setText("3");
						}
						else if (level.getLevelName().equalsIgnoreCase("wall streat")) {
							winCurrentLevel.setText("3");
							winNextLevel.setText("4");
						}
						else if (level.getLevelName().equalsIgnoreCase("hexagon")) {
							winCurrentLevel.setText("4");
							winNextLevel.setText("5");
						}
						else if (level.getLevelName().equalsIgnoreCase("purgatory")) {
							winCurrentLevel.setText("5");
							winNextLevel.setText("6");
						}
						else if (level.getLevelName().equalsIgnoreCase("god")) {
							winCurrentLevel.setText("6");
							winNextLevel.setText("7");
						}
						winTimeLeft.setText(timeLeft + " seconds");
						
						winCurrentLevel.setVisible(true);
						winNextLevel.setVisible(true);
						winTimeLeft.setVisible(true);
						
						pauseButton.setVisible(false);
						Sound.stopMusic();
						pbWidth = 442;
						//Sound.playSound("/audio/victory.wav", false);
						stopTimers();
						play = false;
						victoryScreen.setEnabled(true);
						victoryScreen.setVisible(true);
						freezeButtons();
						//Allows you to move to level 2
						if (level.getLevelName().equalsIgnoreCase("googel") && LevelSelect.highestLevel < 2){
							LevelSelect.highestLevel = 2;
							highestLevel = 2;
							System.out.println("UPDATE: " + LevelSelect.highestLevel);
						}
						else if (level.getLevelName().equalsIgnoreCase("appel") && LevelSelect.highestLevel < 3){
							LevelSelect.highestLevel = 3;
							highestLevel = 3;
							System.out.println("UPDATE: " + LevelSelect.highestLevel);
						}
						else if (level.getLevelName().equalsIgnoreCase("wall streat") && LevelSelect.highestLevel < 4){
							LevelSelect.highestLevel = 4;
							highestLevel = 4;
							System.out.println("UPDATE: " + LevelSelect.highestLevel);
						}
						else if (level.getLevelName().equalsIgnoreCase("hexagon") && LevelSelect.highestLevel < 5){
							LevelSelect.highestLevel = 5;
							highestLevel = 5;
							System.out.println("UPDATE: " + LevelSelect.highestLevel);
						}
						else if (level.getLevelName().equalsIgnoreCase("purgatory") && LevelSelect.highestLevel < 6){
							LevelSelect.highestLevel = 6;
							highestLevel = 6;
							System.out.println("UPDATE: " + LevelSelect.highestLevel);
						}
						else if (level.getLevelName().equalsIgnoreCase("god") && LevelSelect.highestLevel < 7){
							LevelSelect.highestLevel = 7;
							highestLevel = 7;
							System.out.println("UPDATE: " + LevelSelect.highestLevel);
						}
					}
					//Makes sure the progress bar is always at or above 0 pixels wide
					if(pbWidth <= 0) {
						pbWidth = 0;
					}
					//Calculates what % of bar is completed & sets jlabel to answer
					pbWidthDouble = (double) pbWidth;
					progressBar.setSize(pbWidth, 35);
					percentHacked = (double) ((pbWidthDouble/442) * 100);
					progressPercentage.setText(level.getLevelName() + " " + (int) percentHacked + "% hacked");
					
					tickCounter++;
				}
				
				//If you have the hackscreen OPEN and a bad1 or bad2 comes to the door, you lose
				if (hackScreen && badAtDoor) {
					loseCauseOfDeath.setText("YOU WERE CAUGHT");
					loseTimeLeft.setText(timeLeft + " seconds");
					losePercentHacked.setText((int) percentHacked + "%");
					
					loseCauseOfDeath.setVisible(true);
					loseTimeLeft.setVisible(true);
					losePercentHacked.setVisible(true);
					pauseButton.setVisible(false);
					Sound.stopMusic();
					play = false;
					stopTimers();
					Sound.playSound("/audio/gunshot.wav", false);
					//whatSound();
					lossScreen.setEnabled(true);
					lossScreen.setVisible(true);
					freezeButtons();
				}
				//If you have the hackscreen OPEN and a good1 or good2 comes to the door, you gain progress
				else if (hackScreen && goodAtDoor) {
					//Makes sure it only adds once per person
					if (!done) {
						pbWidth += 30;
						done = true;
					}
				}
				//If you have the hackscreen CLOSED and a good1 or good2 comes to the door, you lose progress
				else if (!hackScreen && goodAtDoor) {
					//Makes sure it only subtracts once per person
					if (!done) {
						pbWidth -= 50;
						done = true;
					}
				}
				Main.frame.repaint();
				Main.bg.repaint();
			}
		});
		
		//Event that fires once every second
		secondTimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (play) {
					//Remove 1 second from allotted time & update time label
					timeLeft -= 1;
					if (timeLeft < 10) {
						timeLabel.setText("00:0" + timeLeft);
					}
					else {
						timeLabel.setText("00:" + timeLeft);
					}
					//If you run out of time, you lose
					if (timeLeft <= 0) {
						loseCauseOfDeath.setText("OUT OF TIME");
						loseTimeLeft.setText("0 SECONDS");
						losePercentHacked.setText((int) percentHacked + "%");
						
						loseCauseOfDeath.setVisible(true);
						loseTimeLeft.setVisible(true);
						losePercentHacked.setVisible(true);
						
						pauseButton.setVisible(false);
						Sound.stopMusic();
						play = false;
						stopTimers();
						//whatSound();
						lossScreen.setEnabled(true);
						lossScreen.setVisible(true);
						freezeButtons();
					}
					//Determines who is coming to the door and when, and prints to console (DEV MODE ONLY FOR CONSOLE PRINT)
					if (!didIt) {
						rollGoodBad();
						whoIsIt();
						System.out.println("");
						String goodorbad = null;
						if (person == 1 || person == 2) {
							goodorbad = "bad";
						}
						if (person == 3 || person == 4) {
							goodorbad = "good";
						}
						if (devMode) {
							System.out.println("At second " + randNum + ", " + personString.toUpperCase() + " is heard, " + goodorbad.toUpperCase());
						}
						didIt = true;
					}
					//Prints to console what second between visitor intervals you are on (DEV MODE ONLY)
					if (devMode) {
						System.out.println("Second " + (secondCounter + 1));
					}
					secondCounter++;
					//Decreases progress by amount specified by level object in method arguments
					pbWidth -= level.decayAmount;
					
					//When it's time for someone to come to the door
					if (randNum <= secondCounter) {
						secondCounter = 0; //Resets second counter
						pauseButton.setVisible(false);
						//bad1
						if (person == 1) {
							Sound.playSound(bad1Sound, false);
							//After 1 second, show bad1 at door, and set everything in backend to mirror that
							secondTimer.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent arg0) {
									badAtDoor = true;
									//lostPoints.setVisible(true);
									freezeButtons();
									//System.out.println("bad1 IS HERE");
									bad1 = new JLabel(new ImageIcon(getClass().getResource(bad1Picture)));
									bad1.setLocation(peopleLocationX, peopleLocationY);
									bad1.setSize(peopleSizeX, peopleSizeY);
									Main.bg.add(bad1);
									//Remove current second listener
									secondTimer.removeActionListener(this);
									
									//After 2 seconds, remove the bad1 from the screen and set everything in backend to mirror that
									secondTimer.addActionListener(new ActionListener() { //Second 1 
										@Override
										public void actionPerformed(ActionEvent arg0) {
											secondTimer.addActionListener(new ActionListener() { //Second 2
												@Override
												public void actionPerformed(ActionEvent arg0) {
													Main.bg.remove(bad1);
													//lostPoints.setVisible(false);
													unfreezeButtons();
													badAtDoor = false;
													//Remove current second listener
													secondTimer.removeActionListener(this);
													//Fix all this crap
													done = false;
													didIt = false;
													pauseButton.setVisible(true);
												}
											});
											//Remove current second listener
											secondTimer.removeActionListener(this);
										}
									});
									//Reset determined person
									person = 0;
								}
							});
						}
						
						//bad2
						else if (person == 2) {
							Sound.playSound(bad2Sound, false);
							//After 1 second, show bad2 at door, and set everything in backend to mirror that
							secondTimer.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent arg0) {
									freezeButtons();
									badAtDoor = true;
									//lostPoints.setVisible(true);
									//System.out.println("bad2 IS HERE");
									bad2 = new JLabel(new ImageIcon(getClass().getResource(bad2Picture)));
									bad2.setLocation(peopleLocationX, peopleLocationY);
									bad2.setSize(peopleSizeX, peopleSizeY);
									Main.bg.add(bad2);
									//Remove current second listener
									secondTimer.removeActionListener(this);
									
									//After 2 seconds, remove the bad2 from the screen and set everything in backend to mirror that
									secondTimer.addActionListener(new ActionListener() { //Second 1
										@Override
										public void actionPerformed(ActionEvent arg0) {
											secondTimer.addActionListener(new ActionListener() { //Second 2
												@Override
												public void actionPerformed(ActionEvent arg0) {
													Main.bg.remove(bad2);
													//lostPoints.setVisible(false);
													unfreezeButtons();
													badAtDoor = false;
													//Remove current second listener
													secondTimer.removeActionListener(this);
													//Fix all this crap
													done = false;
													didIt = false;
													pauseButton.setVisible(true);
												}
											});
											//Remove current second listener
											secondTimer.removeActionListener(this);
										}
									});
									//Reset determined person
									person = 0;
								}
							});
						}
						
						//RUSSIAN HACKER
						else if (person == 3) {
							Sound.playSound(good1Sound, false);
							//After 1 second, show russian hacker at door, and set everything in backend to mirror that
							secondTimer.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent arg0) {
									freezeButtons();
									goodAtDoor = true;
									if (hackScreen) {
										gainedPoints.setVisible(true);
									}
									else if (!hackScreen) {
										lostPoints.setVisible(true);
									}
									//System.out.println("RUSSIAN IS HERE");
									good1 = new JLabel(new ImageIcon(Main.class.getResource(good1Picture)));
									good1.setLocation(peopleLocationX, peopleLocationY);
									good1.setSize(peopleSizeX, peopleSizeY);
									Main.bg.add(good1);
									//Remove current second listener
									secondTimer.removeActionListener(this);
									
									//After 2 seconds, remove the russian hacker from the screen and set everything in backend to mirror that
									secondTimer.addActionListener(new ActionListener() { //Second 1
										@Override
										public void actionPerformed(ActionEvent arg0) {
											secondTimer.addActionListener(new ActionListener() { //Second 2
												@Override
												public void actionPerformed(ActionEvent arg0) {
													Main.bg.remove(good1);
													gainedPoints.setVisible(false);
													lostPoints.setVisible(false);
													unfreezeButtons();
													goodAtDoor = false;
													//Remove current second listener
													secondTimer.removeActionListener(this);
													//Fix all this crap
													done = false;
													didIt = false;
													pauseButton.setVisible(true);
												}
											});
											//Remove current second listener
											secondTimer.removeActionListener(this);
										}
									});
									//Reset determined person
									person = 0;
								}
							});
						}
						
						//ANONYMOUS HACKER
						else if (person == 4) {
							Sound.playSound(good2Sound, false);
							//After 1 second, show anonymous hacker at door, and set everything in backend to mirror that
							secondTimer.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent arg0) {
									freezeButtons();
									goodAtDoor = true;
									if (hackScreen) {
										gainedPoints.setVisible(true);
									}
									else if (!hackScreen) {
										lostPoints.setVisible(true);
									}
									//System.out.println("ANONYMOUS IS HERE");
									good2 = new JLabel(new ImageIcon(Main.class.getResource(good2Picture)));
									good2.setLocation(peopleLocationX, peopleLocationY);
									good2.setSize(peopleSizeX, peopleSizeY);
									Main.bg.add(good2);
									//Remove current second listener
									secondTimer.removeActionListener(this);
									
									//After 2 seconds, remove the anonymous hacker from the screen and set everything in backend to mirror that
									secondTimer.addActionListener(new ActionListener() { //Second 1
										@Override
										public void actionPerformed(ActionEvent arg0) {
											secondTimer.addActionListener(new ActionListener() { //Second 2
												@Override
												public void actionPerformed(ActionEvent arg0) {
													Main.bg.remove(good2);
													gainedPoints.setVisible(false);
													lostPoints.setVisible(false);
													unfreezeButtons();
													goodAtDoor = false;
													//Remove current second listener
													secondTimer.removeActionListener(this);
													//Fix all this crap
													done = false;
													didIt = false;
													pauseButton.setVisible(true);
												}
											});
											//Remove current second listener
											secondTimer.removeActionListener(this);
										}
									});
									//Reset determined person
									person = 0;
								}
							});
						}
						//Resets second counter
						secondCounter = 0;
						//Roll new person
						randomNumber();
					}
				}
			}
		});
	}
	
	
	
	public static void setupThings() {
		Main.frame.add(loseCauseOfDeath);
		Main.frame.add(losePercentHacked);
		Main.frame.add(loseTimeLeft);
		loseCauseOfDeath.setVisible(false);
		loseTimeLeft.setVisible(false);
		losePercentHacked.setVisible(false);
		
		Main.frame.add(winCurrentLevel);
		Main.frame.add(winNextLevel);
		Main.frame.add(winTimeLeft);
		winCurrentLevel.setVisible(false);
		winNextLevel.setVisible(false);
		winTimeLeft.setVisible(false);
		
		gainedPoints.setEnabled(true);
		lostPoints.setEnabled(true);
		gainedPoints.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lostPoints.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		pauseButton.setVisible(true);
		pauseButton.setEnabled(true);
		playButton.setEnabled(false);
		playButton.setVisible(false);
		pausedScreen.setVisible(false);
		pausedScreen.setEnabled(false);
		HackScreen.minimize.setText("");
	}
	
	public static void whoIsIt() {
		if (person == 1) {
			personString = "bad1";
		}
		else if (person == 2) {
			personString = "bad2";
		}
		else if (person == 3) {
			personString = "good1";
		}
		else if (person == 4) {
			personString = "good2";
		}
	}
	
	public static void whatSound() {
		int roll = ((int) (Math.random() * ((1000 + 1) - 1))) + 1;
		System.out.println("Number is " + roll);
		if (roll == 420) {
			Sound.playSound("/audio/lose.wav", false);
		}
		else {
			Sound.playSound("/audio/lose.wav", false);
		}
	}
	
	//Disallow buttons from being clicked
	public static void freezeButtons() {
		if (hackScreen) {
			HackScreen.minimize.setEnabled(false);
		}
		else if (!hackScreen) {
			Desktop.hackScreenDesktopIcon.setEnabled(false);
		}
		Main.frame.repaint();
	}
	//Allow buttons to be clicked
	public static void unfreezeButtons() {
		if (hackScreen) {
			HackScreen.minimize.setEnabled(true);
		}
		else if (!hackScreen) {
			Desktop.hackScreenDesktopIcon.setEnabled(true);
		} 
		Main.frame.repaint();
	}
	
	//Start second and tick timers
	public void startTimers() {
		secondTimer = new Timer(secondDelay, this);
		tickTimer = new Timer(tickDelay, this);
		secondTimer.start();
		tickTimer.start();
	}
	
	//Stop second and tick timers
	public void stopTimers() {
		secondTimer.stop();
		tickTimer.stop();
	}
	
	//Rolls time between visitors
	public static void randomNumber() {
		randNum = ((int) (Math.random() * ((maxSeconds + 1) - minSeconds))) + minSeconds;
	}
	
	//Rolls who is coming
	public static void rollGoodBad() {
		person = ((int) (Math.random() * (5 - 1))) + 1;
	}
	
	//Resets all values in the game
	public static void reset() {
		//play = false;
		personString = null;
		hackScreen = false;
		secondTimer = null;
		secondDelay = 1000;
		secondCounter = 0;
		lastSecond = 0;
		tickTimer = null;
		tickDelay = 15;
		tickCounter = 1;
		randNum = 0;
		goodAtDoor = false;
		badAtDoor = false;
		person = 0;
		maxSeconds = 0;
		minSeconds = 0;
		pbWidth = 0;
		pbWidthDouble = (double) pbWidth;
		progressBar = new JLabel();
		progressPercentage = new JLabel();
		good1 = null;
		good2 = null;
		bad1 = null;
		bad2 = null;
		timeLeft = 0;
		done = false;
		hackScreen = true;	
		timeLabel = new JLabel();
		lossScreen = new Hitbox(1920, 1080, 0, 0, true, Color.WHITE, "Oops", false);
		lossScreen.setVisible(false);
		lossScreen.setEnabled(false);
		victoryScreen = new Hitbox(1920, 1080, 0, 0, true, Color.white, "Oops", false);
		victoryScreen.setVisible(false);
		victoryScreen.setEnabled(false);
		good1Sound = null;
		good2Sound = null;
		bad1Sound = null;
		bad2Sound = null;
		good1Picture = null;
		good2Picture = null;
		bad1Picture = null;
		bad2Picture = null;
		didIt = false;
		KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(keyEventDispatcher);
		unfreezeButtons();
	}
	
	public static boolean getPlay() {
		return play;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	//Bass Drop Zero Hero
	//2nd Drop Bossfight Work
	//1st Drop Atmozfears Hate
	//Fuck me im famous dougal gammer
	//Valley of Stevie
	//1st drop Dirtyphonics Hammer
	//Stay Dirty audio second part second drop
	
	//1232, 1080 <-- location for door people
}
