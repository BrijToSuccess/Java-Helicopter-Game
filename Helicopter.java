//the class with the actual game

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

public class Helicopter extends JPanel implements ActionListener {
//required variables
	JLabel label;
	JFrame goframe = new JFrame("Game Over!");
    int heliY = 100;
    int heliX = 100;
    int height=50;
    int vertInc = 2;
    int verticalrate=1;
    int x1;
    int difficulty;
    int y1;
    int x2;
    int y2;
    int x3;
    int x4;
    boolean flag=false;
    String a;
    boolean found=true;
    boolean obstacle1=true;
    boolean obstacle2=false;
    int highscore=500;
    boolean gameover = false;
    int y3;
    Timer t1 = new Timer (5,this);
    int score=0;
    JPanel panel = new JPanel(new BorderLayout());
	Scanner fileScan = null;
    //constructor recieves difficulty level (1 for easy, 2 for hard)
    public Helicopter(int b) {
    	//reads the current high score from file
    	try{
    		fileScan = new Scanner(new File("Highscore.txt"));  //websites.inp is just a text file
        	a=fileScan.next();
        	highscore=Integer.parseInt(a);
        	System.out.println(highscore);
        	fileScan.close();
    	}
    	catch (FileNotFoundException exception) {	}
    	difficulty=b;
    	//sets random values for these coordinates
    	Random number = new Random();
    	x1 = number.nextInt (20)+800;
		y1 = number.nextInt (200)+50;
		x2 = number.nextInt (20)+1200;
		y2 = number.nextInt (200)+50;
		x3 = number.nextInt (20)+1332;
		y3 = number.nextInt (200)+50;
		x4=1000;
    	setFocusable( true );
    }


//repaint method
    public void paintComponent(Graphics gc)   {

    	super.paintComponent(gc);
    	//adds mouse listener
    	MouseListener listener = new MyMouseListener();
		addMouseListener(listener);
		//creates and displays cloud image background
		Image image4 = (new ImageIcon("clouds.jpg")).getImage();
		gc.drawImage(image4, 0,50,800,300,null);
		//creates flag for highscore
		Image image3 = (new ImageIcon("flag.png")).getImage();
		//dispalys flag when score gets close to highscore
		if (score>=(highscore-x4)){
 			gc.drawImage(image3, heliX+(highscore-score)+25,230,50,150,null);
 		}
	//displays heli and boom pics at the correct times
		Rectangle heli = new Rectangle (heliX,heliY,50,50);

		Image image1 = (new ImageIcon("heli2.png")).getImage();
		Image image2 = (new ImageIcon("boom.png")).getImage();
		if (gameover==false)
		gc.drawImage(image1, heliX,heliY,50,50,null);
		if (gameover==true){
			gc.drawImage(image2, heliX,heliY,50,50,null);
		}
		gc.setColor(Color.black);
		//roof and floor
		Rectangle roof = new Rectangle (0,0,800,height);
		Rectangle floor = new Rectangle (0,400-height,800,height);

    	gc.fillRect(roof.x,roof.y,roof.width,roof.height);
    	gc.fillRect(floor.x,floor.y,floor.width,floor.height);
//obstacles
    	Rectangle r1 = new Rectangle (x1,y1,20,100);
    	gc.fillRect (r1.x,r1.y,r1.width,r1.height);
    	Rectangle r2 = new Rectangle (x2,y2,20,100);
    	gc.fillRect (r2.x,r2.y,r2.width,r2.height);
    		gc.setColor(Color.white);
		//displays a label that shows score
		gc.drawString("Score: "+score,  20,  20);

//starts timer
 		t1.start();
//collision detections
 		if (heli.intersects(r1)){

 			t1.stop();
 			gameover=true;
 			repaint();
 		}
 		if (heli.intersects(r2)){

 			t1.stop();
 			gameover=true;
 			repaint();
 		}
 		if (heli.intersects(roof)){

 			t1.stop();
 			gameover=true;
 			repaint();
 		}
 		if (heli.intersects(floor)){

 			t1.stop();
 			gameover=true;
 			repaint();
 		}

 	//when game is over, opens the game over page
 		if (gameover==true&&goframe.isShowing()==false){
 			GameOver gameoverr = new GameOver(score,highscore);
     	   	goframe.setSize(400, 400);
     	   	goframe.setLocationRelativeTo(null);
     	   	goframe.setVisible(true);
     	   	goframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     	   	goframe.setResizable(false);
     	   	goframe.add(gameoverr);
     	   	inputoutput();

 		}
    }
//writes new highscore to file when required
    public void inputoutput() {
    	String file = "Highscore.txt";
      	PrintWriter outFile;
      	try {
        	if (score>highscore){
        		FileWriter fw = new FileWriter(file);	//represents a text output file, but has minimal method support for manipulating data
      			BufferedWriter bw = new BufferedWriter(fw);  // not required, gives output stream buffering capabilities, makes more efficient (good practice)
       			outFile = new PrintWriter(bw);
        		outFile.println(score);
        		outFile.close();
        	}
      	}
      	catch (FileNotFoundException exception) {	}
      	catch (IOException exception) {	}
   }

    private class MyMouseListener implements MouseMotionListener, MouseListener
   {

      public void mouseMoved(MouseEvent event) {

      }


      public void mouseDragged(MouseEvent event) {

      }

      public void mouseClicked(MouseEvent event) {

      }

      //------------------------------------------------------------------------
      //  Empty definitions for unused events. Must Include
      //------------------------------------------------------------------------
      public void mouseExited(MouseEvent event) {}
      public void mouseEntered(MouseEvent event) {

      	} //Invoked when the mouse enters a component.
      public void mouseReleased(MouseEvent event) {
      	//causes heli's vertical direction to be down when mouse is released
      	vertInc=1*verticalrate;
      } //Invoked when the mouse is released.
      public void mousePressed(MouseEvent event) {
      	     	//causes heli's vertical direction to be up when mouse is pressed
    	vertInc=-1*verticalrate;
      } //Invoked when the mouse is pressed.
   }

   	public void actionPerformed(ActionEvent e){
   		//creates moving obstacles when difficulty mode is hard
		if (difficulty==2 && obstacle1==true && y1<250){
			y1++;
		}
		if (difficulty==2 && obstacle1==true && y1==250){
			obstacle1=false;
		}
		if (difficulty==2 && obstacle1==false && y1>50){
			y1--;
		}
		if (difficulty==2 && obstacle1==false && y1==50){
		obstacle1=true;
		}
		if (difficulty==2 && obstacle2==false && y2<250){
		y2++;
		}
		if (difficulty==2 && obstacle2==false && y2==250){
			obstacle2=true;
		}
		if (difficulty==2 && obstacle2==true && y2>50){
			y2--;
		}
		if (difficulty==2 && obstacle2==true && y2==50){
			obstacle2=false;
		}



//moves and creates obstacles
		if (score>(highscore-1000)){
		x4=x4-2;}


 		if (x1<-20){
 			Random number = new Random();
    		x1 = number.nextInt (20)+800;
			y1= number.nextInt (400)-50;
 		}
 		else{
 			x1=x1-2;//*difficulty;
 		}
 		if (x2<-20){
 			Random number = new Random();
    		x2 = number.nextInt (20)+800;
			y2 = number.nextInt (400)-50;
 		}
 		else{
 			x2=x2-2;//*difficulty;
 		}
 	//keeps track of score
 		score=score+2;
	//moves helicopter
 		heliY = heliY + vertInc;

 		if (gameover==false)
 			repaint();
 	}
}
