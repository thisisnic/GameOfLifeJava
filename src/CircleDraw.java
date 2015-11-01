import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.JFrame;

public class CircleDraw extends JFrame {
  Shape circle = new Ellipse2D.Float(100.0f, 100.0f, 100.0f, 100.0f);
  Shape square = new Rectangle2D.Double(100, 100,100, 100);
  
  public void paint(Graphics g) {
	  Graphics2D ga = (Graphics2D)g;
	  ga.draw(circle);
	  ga.setPaint(Color.green);
	  ga.fill(circle);
	  ga.setPaint(Color.red);
	  ga.draw(square);
  }
  
  public static void main(String args[]) {
	  JFrame frame = new CircleDraw();
	  frame.setSize(300, 250);
	  frame.setVisible(true);
  	}
}