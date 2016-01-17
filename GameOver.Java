//game over page
import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class GameOver extends JPanel  implements ActionListener  {
//variables
	int score;
	int highscore;
	int x=0;
	boolean right=true;
	Timer t1=new Timer (5,this);
//constructor which recieves score and highscore
    public GameOver(int s,int hs) {
    	setFocusable( true );
    	score=s;
    	highscore=hs;
    	repaint();
    }

//repaint method
    public void paintComponent(Graphics gc)   {
    	super.paintComponent(gc);
    	//sets background to black
    	gc.setColor(Color.black);
		gc.fillRect (0,0,400,400);
		//displays game over image
    	Image image1 = (new ImageIcon("gameoverimage2.jpg")).getImage();
		gc.drawImage(image1, x,50,300,200,null);
		//displays score/new highscore when the score is higher than old highscore
		if (score>highscore){
			gc.setColor(Color.white);

			gc.drawString("New Highscore!",  140,  270);

			gc.drawString("Score: "+score,  150,  250);
		}
		else{
			//displays current score and current highscore when score is not higher than old highscore
			gc.setColor(Color.white);
			gc.drawString("Current Highscore: " + highscore,  140,  270);
			gc.drawString("Your Score: "+score,  150,  250);
		}
		//starts timer
		t1.start();
    }
//moves game over picture left and right
    public void actionPerformed(ActionEvent e){
    		if (right==true&&x<100)
    			x++;
    		if (right==true&&x==100)
    			right=false;
    		if (right==false&&x>0)
    			x--;
    		if (right==false&&x==0)
    			right=true;
    		repaint();
    }
}
