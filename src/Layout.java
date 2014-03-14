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
	public static int num;
	
	public static int[][] board;
	
	/* the "body" of the file is stored line by line in a java ArrayList */
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
	
	public static int getNum(){
		return num;
	}
	public static void setNum(int data){
		num = data;
	}

	public Layout()
	{
		FileIO io = new FileIO();
		int nrows = getRows();
		int ncols = getCols();
		int numTiles = getNum();
		
		System.out.println("# of Rows: " + getRows() );
		System.out.println("# of Cols: " + getCols() );
		System.out.println("Body is: " + getBody() );
		System.out.println("# of tiles: " + numTiles);
		
		/* at most n-1 tiles because there needs to be at least 1 empty space */
		//Tile[] tile = new Tile[(Layout.getRows()*Layout.getCols())-1];
		
		JFrame frame = new JFrame("Slide Puzzle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setSize(new Dimension(375, 500));
		frame.setBounds(0, 0, 256, 312);
		frame.setLocationRelativeTo(null);
		
		
		
		JPanel field = new JPanel();

		Tile[][] tiles = new Tile[nrows][ncols];
		
		field.setSize(192, 192);
		
		int currID = 0;
		for(int i=0; i<nrows; i++){
			for(int j=0; j<ncols; j++){
				
				tiles[i][j] = new Tile(currID);
				currID++;
				tiles[i][j].setOpaque(true);
				
				tiles[i][j].setBounds(i*32, j*32, tiles[i][j].width*32, tiles[i][j].height*32);
				tiles[i][j].setBackground(new Color(50, 200, 30));
				//tiles[i][j].setPreferredSize(new Dimension(32,32));
				//tiles[i][j].setLocation(i*100, j*50);
				
				field.add(tiles[i][j]);
				if(currID >numTiles-1) break;
			}
		}
			

		JPanel button_panel = new JPanel();
		frame.getContentPane().add(button_panel, BorderLayout.NORTH);
		
		JButton btn_solve = new JButton("Solve");
		btn_solve.setSize(70,30);
		//frame.add(btn_solve, BorderLayout.CENTER);
		button_panel.add(btn_solve, BorderLayout.NORTH);
		btn_solve.setVisible(true);
		
		
		JButton btn_hint = new JButton("Hint");
		btn_hint.setSize(70,30);
		button_panel.add(btn_hint, BorderLayout.NORTH);
		btn_hint.setVisible(true);
		
		frame.setResizable(false);
		frame.getContentPane().add(field, BorderLayout.CENTER);
		//frame.getContentPane().add(tile1);
		//frame.pack();
		frame.setVisible(true);
	}
}
