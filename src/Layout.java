import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class Layout {

	//public static final int WIDTH = 300;
	//public static final int HEIGHT = 300;

	public static int rows;
	public static int cols;

	public static void setRows(int data){
		rows = data;
	}
	public static void setCols(int data){
		cols = data;
	}

	public static int getRows(){
		return rows;
	}
	public static int getCols(){
		return cols;
	}

	public Layout()
	{
		JFrame frame = new JFrame("Slide Puzzle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel tile1 = new JLabel();
		tile1.setOpaque(true);
		tile1.setBackground(new Color(50, 200, 30));
		tile1.setPreferredSize(new Dimension(100,50));


		System.out.println("# of Rows: " + getRows() );
		System.out.println("# of Cols: " + getCols() );

		JButton btn_solve = new JButton("Solve");
		btn_solve.setSize(70,30);
		tile1.add(btn_solve, BorderLayout.CENTER);
		btn_solve.setVisible(true);
		
		frame.getContentPane().add(tile1, BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
	}
}
