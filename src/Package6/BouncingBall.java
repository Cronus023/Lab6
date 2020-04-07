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
		//current field
		this.field = field;
		
		// radius of a ball random size
		radius = new Double(Math.random()*(MAX_RADIUS - MIN_RADIUS)).intValue()+ MIN_RADIUS;

		// dependencies between speed and size of ball
		speed = new Double(Math.round(5*MAX_SPEED / radius)).intValue();
		if (speed>MAX_SPEED) {
			speed = MAX_SPEED;
		}
		
		//start direction, corner-(0,2pi)
		double angle = Math.random()*2*Math.PI;
		
		//colculate vertical and horizantal components of speed
		speedX = 3*Math.cos(angle);
		speedY = 3*Math.sin(angle);
		
		//ball have random color
		color = new Color((float)Math.random(), (float)Math.random(),(float)Math.random());
		
		//random start coordinates of a ball
		x = Math.random()*(field.getSize().getWidth() - 2*radius) + radius;
		y = Math.random()*(field.getSize().getHeight() - 2*radius) + radius;
		
		// create new stream, this - link to yourself
		Thread thisThread = new Thread(this);
		// start stream
		thisThread.start();
	}
	
	
	public void run() {

	}

}
