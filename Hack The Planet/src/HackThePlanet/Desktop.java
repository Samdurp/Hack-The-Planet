package HackThePlanet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Desktop {
	
	public static Hitbox hackScreenDesktopIcon = new Hitbox(37, 33, GamePlay.terminalLocationX, GamePlay.terminalLocationY, false, Color.black, null, false);
	
	public Desktop(int levelNo) {
		
		hackScreenDesktopIcon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("DESKTOP ICON clicked");
				
				Main.frame.remove(hackScreenDesktopIcon);
				
				Main.frame.add(HackScreen.minimize);
				
				Main.changeWindow("hackscreen", 1);
				Main.changeScreenImage(new JLabel(new ImageIcon(Main.class.getResource("/levelone/screenImage.png"))));
				if (levelNo == 1) {
					GamePlay.hackScreen = true;
				}
			}
		});
		Main.frame.repaint();
	}
}
