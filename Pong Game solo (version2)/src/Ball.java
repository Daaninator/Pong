import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Ball extends Rectangle{
	
	Random random;
	int xVelocity;
	int yVelocity;
	int initialSpeed = 5;
	Color color;
	
	
	Ball(int x, int y, int width, int height){
		//zal naar rectangle class gaan
		super(x, y, width, height);
		random = new Random();
		//random x en y richting van ball
		
		setXDirection(-1*initialSpeed);
		
		int randomYDirection = random.nextInt(2);
		if (randomYDirection == 0) 
			randomYDirection--;
		setYDirection(randomYDirection*initialSpeed);
	}
	
	public void setXDirection(int randomXDirection) {
		xVelocity = randomXDirection;
	}
	public void setYDirection(int randomYDirection) {
		yVelocity = randomYDirection;

	}
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}
	
}
