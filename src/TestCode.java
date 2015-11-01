import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

class NewPanel extends JPanel{
	
	static Color thisColor = Color.BLACK;
	
	public void paint(Graphics g) {
		g.setColor(thisColor);			 			
		g.fillRect(10, 10, 50, 50);
	}
}

public class TestCode {
	
	static NewPanel pan = new NewPanel();
	static JFrame myFrame = new JFrame();
	
	
	
	
	
	public static void showGUI(JFrame thisFrame){
		thisFrame.add(pan);
		thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		thisFrame.setBounds(30, 30, 400, 400);
		thisFrame.setLocationRelativeTo(null);
		thisFrame.setVisible(true);
	}
	
	public static void updateGUI(JFrame thisFrame, Color color){
		NewPanel.thisColor = color;
		showGUI(myFrame);
	}
	
	public static void main(String[]args){
		
			
			
			
			SwingUtilities.invokeLater(new Runnable() {
			    public void run() {
			        try {
			        	updateGUI(myFrame,Color.RED);
						Thread.sleep(1000);
						updateGUI(myFrame,Color.BLUE);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			    }
			});
			
			
		
		
	}
}
