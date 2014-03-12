import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class Layout {
	
	public static final int WIDTH = 300;
	public static final int HEIGHT = 300;
	
	public static int rows;
	public static int cols;
	
	public static void setRows(int data){
		rows = data;
	}
	
	public static int getRows(){
		return rows;
	}
	
	public Layout()
	{
		JFrame frame = new JFrame("Slide Puzzle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLayout( new GridLayout(20, 20));
		frame.setSize(375, 500);

		//frame.setMinimumSize(new Dimension(500, 500));
		frame.setResizable(false);
		
		/* get rows and columns */
		FileIO readfile = new FileIO();
		
		
		
		/* red panel */
		JPanel red = new JPanel();
		red.setSize(5, 10);
		red.setBackground(Color.RED);
		red.setVisible(true);
		
		/* blue panel */
		JPanel blue = new JPanel();
		blue.setSize(5, 5);
		blue.setBackground(Color.BLUE);
		blue.setVisible(true);
		
		/* field containing panels */
		JPanel field = new JPanel();
		field.setBackground(Color.YELLOW);
		//field.setLayout(new BoxLayout(field, BoxLayout.X_AXIS));
		//field.setLayout( new GridLayout(1, 1));
		//field.setSize(10, 10);
		//field.setMinimumSize(new Dimension(10, 10));
		field.setLocation(10, 10);
		field.add(red);
		field.add(blue);
		
		field.setVisible(true);
		
		int rows = getRows();
		System.out.println(rows);
		
		
		
		
		//Scanner in = new Scanner(new FileReader("level1.txt"));
		//button.setPreferredSize(new Dimension(10, 10));
		//frame.add(button);
		
		//button.setVisible(true);
		frame.add(field);
		//frame.add(red);
		//frame.add(blue);
		//frame.pack();
		frame.setVisible(true);
	}
}
