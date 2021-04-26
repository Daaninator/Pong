import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//de frame waar die x en minimize op staat
public class GameFrame extends JFrame{

	GamePanel panel;
	
	GameFrame(){
		panel = new GamePanel();
		this.add(panel);
		this.setTitle("pog game");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// zal correct rond de gamepanel vestigen door this.pack
		this.pack();
		this.setVisible(true);
		//zal de gameframe en gamepanel in het midden van de computer scherm zetten
		this.setLocationRelativeTo(null);
	} 
}
