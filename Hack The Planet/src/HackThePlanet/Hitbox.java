package HackThePlanet;

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.KeyStroke;

public class Hitbox extends JButton {
	
	private static int sizeX, sizeY, coordX, coordY;
	
	public Hitbox(int sizeXz, int sizeYz, int coordXz, int coordYz, boolean visible, Color color, String text, boolean contentAreaFilled) {
		this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		sizeX = sizeXz;
		sizeY = sizeYz;
		
		coordX = coordXz;
		coordY = coordYz;
		
		this.setText(text);
		this.setBackground(color);
		
		this.setSize(sizeX, sizeY);
		this.setLocation(coordX, coordY);
		
		//Formats buttons to not look garbage
		this.setOpaque(true);
		this.setBorderPainted(false);
		this.setContentAreaFilled(contentAreaFilled);
		this.setFocusPainted(false);
		
		Main.frame.add(this);
		
		this.setEnabled(false);
		Main.frame.repaint();
		
		this.setEnabled(true);
		Main.frame.repaint();
		
		this.setFont(new Font("Arial", Font.PLAIN, 14));
	}
	
	public static int getSizeX() {
		return sizeX;
	}
	public static void setSizeX(int sizeX) {
		Hitbox.sizeX = sizeX;
	}
	public static int getSizeY() {
		return sizeY;
	}
	public static void setSizeY(int sizeY) {
		Hitbox.sizeY = sizeY;
	}
	public static int getCoordX() {
		return coordX;
	}
	public static void setCoordX(int coordX) {
		Hitbox.coordX = coordX;
	}
	public static int getCoordY() {
		return coordY;
	}
	public static void setCoordY(int coordY) {
		Hitbox.coordY = coordY;
	}
}
