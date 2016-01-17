//Name: Brij Bhavsar
//Date: June 11
//Description: A helicopter game - stay as long as you can.


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

//heliMenu class
public class HeliMenu extends JFrame implements ActionListener{
//required variables
    JButton play, play2,instruction, quit;
    JFrame frame = new JFrame("Helicopter");
    JFrame iframe = new JFrame("Instructions");
	ImageIcon easymode = new ImageIcon("easymode.jpg");
	ImageIcon hardmode = new ImageIcon("hardmode.jpg");
	ImageIcon instructions = new ImageIcon("instructionsbutton.jpg");
	ImageIcon quits = new ImageIcon("quitbutton.jpg");
	JLabel background,title;
    //constructor
    public HeliMenu() {

		//constructs the different components such as buttons and images
		background=new JLabel(new ImageIcon("black.jpg"));
		title=new JLabel(new ImageIcon("copter.jpg"));
        play = new JButton ();
        play.setIcon(easymode);
        play2 = new JButton ();
        play2.setIcon(hardmode);
        instruction = new JButton ();
        instruction.setIcon(instructions);
        quit = new JButton ();
        quit.setIcon(quits);

//creates panel
        JPanel panel = new JPanel(new FlowLayout());
//adds action listener to buttons
        play.addActionListener (this);
        play2.addActionListener (this);
        instruction.addActionListener (this);
        quit.addActionListener (this);

//adds everything to panel
       panel.add(background);
     //  background.setLayout(null);
       background.add(title);
       background.add(play);
       background.add(play2);
       background.add(instruction);
       background.add(quit);
//sets coordinates and size of the components
 background.setBounds(0,-20,383,500);
       title.setBounds(81,30,220,80);
       play.setBounds(50,150,128,96);
       play2.setBounds(200,150,128,96);
       instruction.setBounds(112,300,159,38);
       quit.setBounds(112,360,159,43);

//displays frame and sets properties

       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // frame.setLocationRelativeTo(null);
       frame.setSize(383,500);
       frame.getContentPane().add(panel);
       frame.setVisible(true);
       frame.setResizable(false);

    }


//action performed event
     public void actionPerformed (ActionEvent e)
    {
    	//clicking on easy mode button opens easy made game
        if (e.getSource () == play)
        {
           frame.setVisible(false);
     	   Helicopter helicopter = new Helicopter(1);
     	   JFrame gframe = new JFrame("Game");
     	   gframe.setSize(800, 400);
     	   gframe.setLocationRelativeTo(null);
     	   gframe.setVisible(true);
     	   gframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     	   gframe.setResizable(false);
     	   gframe.add(helicopter);
        }
        //clicking on hard mode button hard easy made game
        if (e.getSource () == play2)
        {
           frame.setVisible(false);
     	   Helicopter helicopter = new Helicopter(2);
     	   JFrame gframe = new JFrame("Game");
     	   gframe.setSize(800, 400);
     	   gframe.setLocationRelativeTo(null);
     	   gframe.setVisible(true);
     	   gframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     	   gframe.setResizable(false);
     	   gframe.add(helicopter);


        }
        //clicking on instruction button opens instructions
        else if (e.getSource () == instruction&&iframe.isShowing()==false)
        {
     	   Instructions instructions = new Instructions();
     	   iframe.setSize(250, 330);
     	   iframe.setLocationRelativeTo(null);
     	   iframe.setVisible(true);
     	   iframe.setResizable(false);
     	   iframe.add(instructions);
        }
        else if (e.getSource () == quit)
        {
           System.exit(1);
        }
    }
    //main method calls constructor for HeliMenu
    public static void main(String[] args) {
     	new HeliMenu();
    }
}
