import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class GamePanel extends JPanel implements Runnable{
	
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH*(0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMATER = 20;
	static final int PADDLE_WIDTH = 10;
	static final int PADDLE_HEIGHT = 120;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	Color color = new Color(255, 0, 0);
	int r,g,b;
	boolean bool = false;
	
	GamePanel(){
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		//zal de keys lezen van je toetsenbord
		this.setFocusable(true);
		// al = actionlistener van de actionlisttener class beneneden
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
		
		random = new Random();
	}
	
	public void newBall() {
		// random = new Random();
		ball = new Ball((GAME_WIDTH/2) - (BALL_DIAMATER/2), (GAME_HEIGHT/2)-(BALL_DIAMATER/2), BALL_DIAMATER, BALL_DIAMATER);
	}
	public void newPaddles() {
		//de 1 en 2 zijn de id's
		paddle1 = new Paddle(10, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1, Color.blue);
		paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH, 0, PADDLE_WIDTH, GAME_HEIGHT, 2, color);
	}
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,  0 ,  0,  this);
	}
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	public void move() {
		paddle1.move();
		ball.move();
	}
	public void checkCollision() {
		//zal paddles stoppen als ze de rand rkaen
		if (paddle1.y<=0) paddle1.y=0;
		else if(paddle1.y>= (GAME_HEIGHT-PADDLE_HEIGHT)) paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;

		
		//zal ball bounce van de paddle
		if (ball.intersects(paddle1)) {
			ball.xVelocity = -ball.xVelocity;
			bool = true;
			//zal ball velocity vegroten bij elke bots	
			//ball.yVelocity++;
		}
		//zal player2 of player 1een punt geven en creert een nieuwe ball en paddles als de ball uit de linkse zone gaat
		else if(ball.x <= 0) {
			if (score.highscore<score.player1)
				score.highscore=score.player1;
			score.player1 = 0;
			score.deaths++;
			newPaddles();
			newBall();
		}
		
		//ALS RECHTS RAAKT
		if (ball.x >= GAME_WIDTH-PADDLE_WIDTH-BALL_DIAMATER) {
			ball.xVelocity = -ball.xVelocity;
			if (ball.xVelocity>-25)
				ball.xVelocity-=1;
			
			r = random.nextInt(155)+100;
			g = random.nextInt(155)+100;
			b = random.nextInt(155)+100;

			paddle2.color = new Color(r, g, b);
		}	
		//zal bal bij randen terugkaatsen
		if(ball.y <= 0 || ball.y >= GAME_HEIGHT-BALL_DIAMATER) {
			ball.setYDirection(-ball.yVelocity);
		}
		//zal score +1 geven als terugkaats(om glitch van score te vermijden)
		if (ball.x>30 && ball.xVelocity>0 && bool) { 
			score.player1++;
			bool = false;
		}

	}
	public void run() {
		//game loop van 60fps
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		//ns = nanoseconds
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			if (delta >= 1) {
				move();
				checkCollision();
				//repaint zal de draw() weer uitvoeren
				repaint();
				delta--;

				

			}
		}
	}
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
	
}
