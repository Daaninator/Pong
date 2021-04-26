import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score extends Rectangle{
	
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int player1=0;
	int player2;
	int highscore=0;
	int deaths=0;
	
	Score(int GAME_WIDTH, int GAME_HEIGHT){
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
	}
	public void draw(Graphics g){
		g.setColor(Color.white);
		g.setFont(new Font("Consolas", Font.PLAIN, 40));
		
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		g.drawString("score: " + String.valueOf(player1/10) + String.valueOf(player1%10), GAME_WIDTH/2, 30);
		
		g.setFont(new Font("Consolas", Font.PLAIN, 15));
		g.drawString("highscore: " + String.valueOf(highscore/10) + String.valueOf(highscore%10), GAME_WIDTH/2 +5, 45);
		g.drawString("deaths: " + String.valueOf(deaths/10) + String.valueOf(deaths%10), GAME_WIDTH/2 +5, 60);
	}
}
