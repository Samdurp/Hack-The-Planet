package HackThePlanet;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	
	public static Clip clip;
	public static Clip musicClip;
	
	public static void playSound(String filename, boolean loop) {
		try (InputStream in = Main.class.getResourceAsStream(filename)) {
            InputStream bufferedIn = new BufferedInputStream(in);
            try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
                clip = AudioSystem.getClip();
                clip.open(audioIn);
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float range = volumeControl.getMaximum() - volumeControl.getMinimum();
                double volume = (range * Settings.getPeopleVolume()) + volumeControl.getMinimum();
                float dB = (float) volume;
                volumeControl.setValue(dB);
                clip.start();
                if(loop) {
                	clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
            }
        } catch (Exception e) {
           System.out.println("Error with sound");
       }
	}
	//Stop current sound
	public static void stopSound() {
		clip.stop();
	}
	
	public static void playMusic(String filename, boolean loop) {
		try (InputStream in = Main.class.getResourceAsStream(filename)) {
            InputStream bufferedIn = new BufferedInputStream(in);
            try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
                musicClip = AudioSystem.getClip();
                musicClip.open(audioIn);
                FloatControl volumeControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
                float range = volumeControl.getMaximum() - volumeControl.getMinimum();
                double volume = (range * Settings.getMusicVolume()) + volumeControl.getMinimum();
                float dB = (float) volume;
                volumeControl.setValue(dB);
                musicClip.start();
                if(loop) {
                	musicClip.loop(Clip.LOOP_CONTINUOUSLY);
                }
            }
        } catch (Exception e) {
           System.out.println("Error with sound");
       }
	}
	//Stop current music
	public static void stopMusic() {
		musicClip.stop();
	}
}
