import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Layout {

	public static int rows;
	public static int cols;
    public static ArrayList<String> list = new ArrayList<String>();
    
    /* "Getters" and "Setters"
     * This allows the secure transfer of info from the read-in file between FileIO and Layout classes */
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
	
	public static void setBody( ArrayList<String> data){
		list = data;
	}
	public static ArrayList<String> getBody(){
		return list;
	}

	public Layout()
	{
		FileIO io = new FileIO();
		System.out.println("# of Rows: " + getRows() );
		System.out.println("# of Cols: " + getCols() );
		System.out.println("Body is: \n" + getBody() );
		
		
		JFrame frame = new JFrame("Slide Puzzle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel tile1 = new JLabel();
		tile1.setOpaque(true);
		tile1.setBackground(new Color(50, 200, 30));
		tile1.setPreferredSize(new Dimension(100,50));


		

		JButton btn_solve = new JButton("Solve");
		btn_solve.setSize(70,30);
		frame.add(btn_solve, BorderLayout.CENTER);
		btn_solve.setVisible(true);
		
		frame.setResizable(false);
		frame.getContentPane().add(tile1, BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
	}
}
