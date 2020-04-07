package Package6;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Field extends JPanel {
	// flag for stop balls
	private boolean paused;
	// list of balls
	private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);
	
	//anonim class timer which constantly generate event ActionEvent
	private Timer repaintTimer = new Timer(10, new ActionListener() {
	public void actionPerformed(ActionEvent ev) {
      	// the one task of event ActionEvent - redraw window
	    repaint();
	}
	});
	
	// call constructor of BouncingBall
	public Field() {
	    // set background color
	    setBackground(Color.WHITE);
	    // start timer
	    repaintTimer.start();
	}
	
	// method which add ball to list
	public void addBall() {
		//initialisation of new ball in constructor BouncingBall 
		balls.add(new BouncingBall(this));
	}
	
	// method in Jpanel which redraw component
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D canvas = (Graphics2D) g;
	    //draw all ball in list
	    for (BouncingBall ball: balls) {
	        ball.paint(canvas);
	    }
	}

}
