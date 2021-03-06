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
    
	
    public double getSpeedX() {
	    return speedX;
	}
    public double getSpeedY() {
	    return speedY;
	}

    public void getColor() {
    	//if(color == Color.RED)
    		//System.out.println("lol");
	}
    
    public double getRadius() {
	    return radius;
	}
    public void setRadius(int r) {
    	radius = r;
	}
    
    
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
	// method run() hold true inside stream; when it stop then stream also stop
	public void run() {
       try{
    	   while(true) { 
    		   // synchronization of streams in field object
        	   // if ball can move  - control will return to method
        	   // else  - active stream will sleep
        	   field.canMove(this);
        	   
        	   for(int i=0;i<field.getArrayH();i++)
                   for(int j=0;j<field.getArrayW();j++)
                   {
                       if(field.getArrayOfBoss()[i][j]!=null) {
                           if ((y + speedY >= field.getArrayOfBoss()[i][j].getY()) && (y + speedY <= field.getArrayOfBoss()[i][j].getWidth() + field.getArrayOfBoss()[i][j].getY()) && x + speedX >= field.getArrayOfBoss()[i][j].getX() + field.getArrayOfBoss()[i][j].getLength()) {
                               if ((x + speedX <= field.getArrayOfBoss()[i][j].getX() + field.getArrayOfBoss()[i][j].getLength() + radius)) {
                                                                   // �������� ����� ��������� ��������������, ������������� ������
                                   speedX = -speedX;
                                   x = field.getArrayOfBoss()[i][j].getX() + field.getArrayOfBoss()[i][j].getLength() + radius;
                                   
                                       field.getArrayOfBoss()[i][j].flagNow--;
                                  
                                   if(field.getArrayOfBoss()[i][j].flagNow<=0) {
                                       field.deleteBoss(i, j);
                                   }
                               }
                           }
                           else if((y + speedY >= field.getArrayOfBoss()[i][j].getY()) && (y+ speedY <= field.getArrayOfBoss()[i][j].getWidth()+field.getArrayOfBoss()[i][j].getY()) && (x+speedX <= field.getArrayOfBoss()[i][j].getX())) {
                               if ((x + speedX >= field.getArrayOfBoss()[i][j].getX() - radius)) {
                                                                   // �������� ������ ��������� ��������������, ������������� �����
                                   speedX = -speedX;
                                   x = field.getArrayOfBoss()[i][j].getX() - radius;
                                   
                                       field.getArrayOfBoss()[i][j].flagNow--;
                                  
                                   if(field.getArrayOfBoss()[i][j].flagNow<=0) {
                                       field.deleteBoss(i, j);
                                   }
                               }
                           }
                           else if((x+speedX >= field.getArrayOfBoss()[i][j].getX()) && (x+speedX <= field.getArrayOfBoss()[i][j].getLength()+field.getArrayOfBoss()[i][j].getX()) && (y+speedY <= field.getArrayOfBoss()[i][j].getY())) {
                               if (y + speedY >= field.getArrayOfBoss()[i][j].getY() - radius) {
                                                                   // �������� ������� ��������� ��������������, ������������� �����
                                   speedY = -speedY;
                                   y = field.getArrayOfBoss()[i][j].getY() - radius;
                                   
                                       field.getArrayOfBoss()[i][j].flagNow--;
                                  
                                   if(field.getArrayOfBoss()[i][j].flagNow<=0) {
                                       field.deleteBoss(i, j);
                                   }
                               }
                           }
                           else if((x+speedX >= field.getArrayOfBoss()[i][j].getX()) && (x+speedX <= field.getArrayOfBoss()[i][j].getLength()+field.getArrayOfBoss()[i][j].getX()) && (y+speedY >= field.getArrayOfBoss()[i][j].getY()+field.getArrayOfBoss()[i][j].getWidth())) {
                               if ((y + speedY <= field.getArrayOfBoss()[i][j].getY() + field.getArrayOfBoss()[i][j].getWidth() + radius)) {
                                                                   // �������� ������ ��������� ��������������, ������������� ����
                                   speedY = -speedY;
                                   y = field.getArrayOfBoss()[i][j].getY() + field.getArrayOfBoss()[i][j].getWidth() + radius;
                                   
                                       field.getArrayOfBoss()[i][j].flagNow--;
                                   
                                   if(field.getArrayOfBoss()[i][j].flagNow<=0) {
                                       field.deleteBoss(i, j);
                                   }
                               }
                           }

                       }
                   }
              
    		 if (x + speedX <= radius) {
    			// left wall? jump to right wall
    			speedX = -speedX;
    			x = radius;
    		}
    		else if (x + speedX >= field.getWidth() - radius) {
    			 /// right wall? jump to left wall
    			  speedX = -speedX;
    			  x=new Double(field.getWidth()-radius).intValue();
    	    } else if (y + speedY <= radius) {
    			 // top wall? then:
    			 speedY = -speedY;
    			 y = radius;
    		} 
    	    else if (y + speedY >= field.getHeight() - radius) {
    	    	  //lower wall? then:
    			  speedY = -speedY;
    			  y=new Double(field.getHeight()-radius).intValue();
    		} 
    	    else {
    	    	// simple move in work field
    			x+=speedX;
    			y+=speedY;
    		}
    		//stream sleep for a 1mc when it(SPEED) fast and for 15 when it slow
    		Thread.sleep(16-speed);
    	   }
       } catch(InterruptedException ex) {
    	
       }
	}
	
	// draw ball
	public void paint(Graphics2D canvas) {
	    canvas.setColor(color);
	    canvas.setPaint(color);
	    Ellipse2D.Double ball = new Ellipse2D.Double(x-radius, y-radius,2*radius, 2*radius);
	    canvas.draw(ball);
	    canvas.fill(ball);
	}
}
