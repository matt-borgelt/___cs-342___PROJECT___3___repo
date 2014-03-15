import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Layout implements MouseInputListener, MouseMotionListener, ActionListener{

	public static int rows;
	public static int cols;
	public static int num;

	private int clicX = 0;
	private int clicY = 0;
	private int y1, x1; //original x and y coordinates of a button being moved

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
		frame.setBounds(0, 0, 512, 568);
		frame.setLocationRelativeTo(null);



		JPanel field = new JPanel();
		field.setLayout(null);
		field.setBackground(Color.WHITE);

		Tile[][] tiles = new Tile[nrows][ncols];

		field.setBounds(0, 0, 192*2, 192*2);

		int currID = 0;
		for(int i=0; i<nrows; i++){
			for(int j=0; j<ncols; j++){

				tiles[i][j] = new Tile(currID);
				currID++;
				tiles[i][j].setOpaque(true);

				tiles[i][j].setBounds(tiles[i][j].x*64, tiles[i][j].y*64, tiles[i][j].width*64, tiles[i][j].height*64);
				tiles[i][j].setBackground(new Color(50, 200, 30));
				//tiles[i][j].setPreferredSize(new Dimension(32,32));
				//tiles[i][j].setLocation(i*100, j*50);
				tiles[i][j].addMouseMotionListener(this);
				tiles[i][j].addMouseListener(this);

				field.add(tiles[i][j]);
				// event
				if(currID >numTiles-1) break;
				//tiles[i][j].repaint();
			}
			if(currID >numTiles-1) break;
		}

		//field.repaint();


		JPanel button_panel = new JPanel();
		frame.getContentPane().add(button_panel, BorderLayout.NORTH);

		/* solve button */
		JButton btn_solve = new JButton("Solve");
		btn_solve.setSize(70,30);
		button_panel.add(btn_solve, BorderLayout.NORTH);
		btn_solve.setVisible(true);

		/* hint button */
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

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(MouseEvent e) {
		JComponent jc = (JComponent)e.getSource();
		Tile tile = (Tile) e.getSource();
		int id = tile.id;
		if (id == 0){
			System.out.println("You pressed Z");
		} else System.out.println("You pressed " + id);

		clicX = e.getX();
		clicY = e.getY();
		x1 = jc.getX();
		y1 = jc.getY();


	}
	@Override
	public void mouseReleased(MouseEvent e) {

		JComponent jc = (JComponent)e.getSource();
		int modX = jc.getX() % 64;
		int modY = jc.getY() % 64;
		if (modX < 32){
			jc.setLocation(jc.getX()-modX, jc.getY());
		}
		else{
			jc.setLocation(jc.getX()+(64-modX), jc.getY());
		}
		//System.out.println(""+jc.getX());
		if (modY < 32){
			jc.setLocation(jc.getX(), jc.getY()-modY);
		}
		else{
			jc.setLocation(jc.getX(), jc.getY()+(64-modY));
		}
		System.out.println(""+jc.getY());
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseDragged(MouseEvent e) {

		Tile jc = (Tile)e.getSource();
		//jc.setLocation(jc.getX()+e.getX()-clicX, jc.getY()+e.getY()-clicY);

		if(jc.horizontal == true){
			jc.setLocation(jc.getX()+e.getX()-clicX, jc.getY());
			if(Math.abs(jc.getX() - x1) > Math.abs(jc.getY() - y1)){
				jc.setLocation(jc.getX(), y1);
			}
			if(jc.getX() < 0) jc.setLocation(0, jc.getY());
			else if(jc.getX() > 512-(64*jc.width)) jc.setLocation(512-(64*jc.width), jc.getY());
		}

		if(jc.vertical==true){
			jc.setLocation(jc.getX(), jc.getY()+e.getY()-clicY);
			if(Math.abs(jc.getX() - x1) < Math.abs(jc.getY() - y1)){
				jc.setLocation(x1, jc.getY());
			}
			if(jc.getY() < 0) jc.setLocation(jc.getX(), 0);
			else if(jc.getY() > 512-(64*jc.height)) jc.setLocation(jc.getX(), 512-(64*jc.height));
		}
		//System.out.println(""+(jc.getX() % 64));

	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}


