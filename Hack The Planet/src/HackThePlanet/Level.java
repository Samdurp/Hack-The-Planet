package HackThePlanet;

public class Level {
	
	public static String levelName;
	public static double decayRate;
	public static int decayAmount;
	public static int minSeconds;
	public static int maxSeconds;
	public static int time;
	
	public Level(String name, double rate, int amount, int minseconds, int maxseconds, int timed) {
		levelName = name;
		decayRate = rate;
		decayAmount = amount;
		minSeconds = minseconds;
		maxSeconds = maxseconds;
		time = timed;
	}
	public static String getLevelName() {
		return levelName;
	}
	public static void setLevelName(String levelName) {
		Level.levelName = levelName;
	}
	public static double getDecayRate() {
		return decayRate;
	}
	public static void setDecayRate(int decayRate) {
		Level.decayRate = decayRate;
	}
	public static int getDecayAmount() {
		return decayAmount;
	}
	public static void setDecayAmount(int decayAmount) {
		Level.decayAmount = decayAmount;
	}	
	public static int getTime() {
		return time;
	}
	public static void setTime(int timed) {
		Level.time = timed;
	}
}