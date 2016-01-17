//instructions page
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

public class Instructions extends JPanel {
//constructor

    public Instructions() {
    	setFocusable( true );
    	repaint();
    }
//repaint method displays instructions
    public void paintComponent(Graphics gc)   {
    	super.paintComponent(gc);
		gc.setColor(Color.black);
		gc.fillRect (0,0,250,330);
    	Image image1 = (new ImageIcon("instructionspic.jpg")).getImage();
		gc.drawImage(image1, 0,0,250,300,null);

    }
}
