package HackThePlanet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class HackScreen {
	
	public static Hitbox minimize = new Hitbox(19, 15, 845, 298, false, Color.blue, null, false);
	
	public HackScreen(int levelNo) {
		
		minimize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("MINIMIZE clicked");
				
				Main.frame.remove(minimize);
				
				Main.frame.add(Desktop.hackScreenDesktopIcon);
				
				Main.changeWindow("desktop", 1);
				Main.changeScreenImage(new JLabel(new ImageIcon(Main.class.getResource("/levelone/desktop.png"))));
				if (levelNo == 1) {
					GamePlay.hackScreen = false;
				}
			}
		});
		Main.frame.repaint();
	}
}
