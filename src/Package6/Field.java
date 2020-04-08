package Package6;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

	
	private static  int arrayH=450;
	private static  int arrayW=450;
	
	
	//FOR BOSS
	private boolean paused1;
	private boolean resumeLol;
	private boolean bossFlag = false;
	private BigBoss[][] ArrayOfBoss= new BigBoss[arrayH][arrayW];
	
	

    public static int getArrayH() {
	        return arrayH;
	}

	public static int getArrayW() {
	    return arrayW;
	}
	public BigBoss[][] getArrayOfBoss() {
	    return ArrayOfBoss;
	}
	
	public void deleteBoss(int x,int y)
	{
		ArrayOfBoss[x][y]=null;
	}

	// method which add boss
	public void addBoss() {
		while(true) {
			BigBoss boss = new BigBoss(this);
	    	if (ArrayOfBoss[boss.getX()][boss.getY()] == null) {
	    		ArrayOfBoss[boss.getX()][boss.getY()] = boss;
	    		break;
	    	}
	    }
	}	
	// method which delete bosses
	public void deleteBosses()
	{
    	for(int i=0;i<arrayH;i++) {
    		for(int j=0;j<arrayW;j++) {
    			if(ArrayOfBoss[i][j]!=null)
    				ArrayOfBoss[i][j]=null;
    		}
    	}		
	}	
	
	

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
	    for(int i=0;i<arrayH;i++) {
	        for(int j=0;j<arrayW;j++) {
	        	 if (ArrayOfBoss[i][j] != null)
	        		 ArrayOfBoss[i][j].paint(canvas);
	        }     
	    }
	}
	
	//(...........................SYNCHRONIZATION..................................)
	
	
	// only one stream can be inside
	public synchronized void pause() {
		paused1 = true;
		resumeLol = false;
	    // start pause mode
	    paused = true;
	}

	//check ability of ball to move( pause mode or not )
	public synchronized void canMove(BouncingBall ball)
	    throws InterruptedException {
	        if (paused) {
	           ball.getColor();
	            //if pause mode then stream which come inside this method sleep
	           wait();
	        }
	}
	//check ability of ball to move( pause mode or not )
	public synchronized void canMove(BigBoss ball)
		 throws InterruptedException {
		       if (paused) {
		            //if pause mode then stream which come inside this method sleep
		           wait();
		        }
	}
	
	//close pause mode
	public synchronized void resume() {
	    paused1 = false;
	    paused = false;
	    // wake up all the stream that are waiting to continue
	    notifyAll();
	}
}
