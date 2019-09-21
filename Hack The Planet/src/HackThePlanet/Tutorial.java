package HackThePlanet;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Tutorial {
	
	public static Hitbox nextButton = new Hitbox(330, 61, 795, 998, true, Color.green, "Next", true);
	public static int slideNumber = 1;
	
	public static Font basic;
	
	public Tutorial() {
		
		try {
			basic = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResource("/basic.ttf").openStream());
			GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			genv.registerFont(basic);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		nextButton.setFont(basic.deriveFont(Font.PLAIN, 25f));
		
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.frame.remove(Main.bg);
				if (slideNumber == 1) {
					//System.out.println("1 firing");
					Main.changeBackground(new JLabel(new ImageIcon(Main.class.getResource("/tutorial/tutorial2.png"))));
					slideNumber++;
				}
				else if(slideNumber == 2) {
					//System.out.println("2 firing");
					Main.changeBackground(new JLabel(new ImageIcon(Main.class.getResource("/tutorial/tutorial3.png"))));
					slideNumber++;
				}
				else if (slideNumber == 3) {
					//System.out.println("3 firing");
					Main.changeBackground(new JLabel(new ImageIcon(Main.class.getResource("/tutorial/tutorial4.png"))));
					slideNumber++;
				}
				else if (slideNumber == 4) {
					//System.out.println("4 firing");
					Main.changeBackground(new JLabel(new ImageIcon(Main.class.getResource("/tutorial/tutorial5.png"))));
					slideNumber++;
				}
				else if (slideNumber == 5) {
					//System.out.println("4 firing");
					Main.changeBackground(new JLabel(new ImageIcon(Main.class.getResource("/tutorial/tutorial6.png"))));
					slideNumber++;
				}
				else if (slideNumber == 6) {
					//System.out.println("4 firing");
					Main.changeBackground(new JLabel(new ImageIcon(Main.class.getResource("/tutorial/tutorial7.png"))));
					slideNumber++;
					nextButton.setText("Menu");
				}
				else if (slideNumber >= 7) {
					nextButton.setText("Next");
					//System.out.println("5 firing");
					Main.frame.setVisible(false);
					Main.frame.setVisible(true);
					Main.changeWindow("mainmenu", 0);
					Main.frame.remove(nextButton);
					slideNumber = 1;
				}
				//System.out.println(slideNumber);
				Main.frame.repaint();
			}
		});
	}
}