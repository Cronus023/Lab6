package Package6;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import java.awt.geom.GeneralPath;

public class BigBoss{
	
	private static final int minLength = 50;
    private static final int maxLength = 200;
    private static final int minWidth = 50;
    private static final int maxWidth = 200;

    //work field
    private Field field;
    
    //size of Boss
    private  int length;
    private  int width;
    private Color color;

    
    private int prochnost = 10;
    public  int flagNow = prochnost;
    public  int flagNext = 9;


    private int x;
    private int y;
	    
    Random r=new Random();
    public int getLength() {
        return length;
    }

    public  int getWidth() {
        return width;
    }

    public int getProchnost() {
    	return prochnost; 
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
	public BigBoss(Field field) {
		 this.field = field;
       
         length= r.nextInt(maxLength-minLength)+minLength;
         width= r.nextInt(maxWidth-minWidth)+minWidth;
         
        color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
        x =r.nextInt(field.getArrayH());
        y = r.nextInt(field.getArrayW());
	}

	
	// draw boss
	public void paint(Graphics2D canvas) {
		canvas.setColor(color);
        canvas.setPaint(color);
        GeneralPath kirp = new GeneralPath();
        kirp.moveTo(x, y);
        kirp.lineTo(x, y + width);
        kirp.lineTo(x + length, y + width);
        kirp.lineTo(x + length, y);
        kirp.closePath();
        canvas.draw(kirp);
        canvas.fill(kirp);
        canvas.setColor(Color.BLACK);
        Font myFont = new Font("Broadway", Font.BOLD, 24);
        canvas.setFont(myFont);
        if (flagNow == flagNext) {
            flagNext--;
            prochnost--;
        }
        String s = String.valueOf(prochnost);
        canvas.drawString(s, (int) x + 10, (int) y + 20);
	}

}
