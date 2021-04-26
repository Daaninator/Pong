import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Paddle extends Rectangle{
	
	int id;
	int yVelocity;
	int speed = 15;
	Color color;
	
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id, Color color){
		//zal naar rectangle class gaan
		super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		this.id = id;
		this.color = color;
	}
	//als de key x ingedrukt wordt dan
	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(-speed);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(speed);
				move();
			}
			break;
		}
	}
	//als de key x gereleased wordt dan
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(0);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(0);
				move();
				
			}
			break;

		}
	}
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}
	public void move() {
		y = y + yVelocity;
	}
	public void draw(Graphics g) {
		if (id == 1)
			g.setColor(Color.blue);

		else 
			g.setColor(color);
		g.fillRect(x,y, width, height);
	}
}
