package Package6;
import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class BouncingBall implements Runnable {
	// max radius for a ball
	private static final int MAX_RADIUS = 40;
	// min radius for a ball
	private static final int MIN_RADIUS = 3;
	// max speed for a ball
	private static final int MAX_SPEED = 15;
	
	//current work field
	private Field field;
	
	private int radius;
	private Color color;
	
	//current coordinates of a ball
	private double x;
	private double y;
	// vertical and horizantal components of speed
	private int speed;
	private double speedX;
	private double speedY;

	
	public BouncingBall(Field field) {
		
	}
	
	
	public void run() {

	}

}
