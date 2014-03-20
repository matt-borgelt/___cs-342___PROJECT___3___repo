import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
//import java.io.FileReader;
import java.util.ArrayList;
//import java.util.Random;
//import java.util.Scanner;

public class Layout implements MouseInputListener, MouseMotionListener, ActionListener{

	public static Rectangle[] r;

	public static int rows;
	public static int cols;
	public static int num;
	private int width;
	private int height;

	private int clicX = 0;
	private int clicY = 0;
	private int y1, x1; //original x and y coordinates of a button being moved
	private int rightBound, leftBound, upperBound, lowerBound; //Bounds on current tile movement

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

	public static Rectangle[] getRect(){
		return r;
	}

	public static void setRect(Rectangle[] data){
		r = data;
	}

	public Layout(int level)
	{
		FileIO io = new FileIO(level);
		int nrows = getRows();
		this.width = nrows * 64;
		int ncols = getCols();
		this.height = ncols * 64;
		int numTiles = getNum();
		
		//Initialize bounds
		this.rightBound = this.width;
		this.leftBound = 0;
		this.upperBound = 0;
		this.lowerBound = this.height;

		System.out.println("# of Rows: " + getRows() );
		System.out.println("# of Cols: " + getCols() );
		System.out.println("Body is: " + getBody() );
		System.out.println("# of tiles: " + numTiles);

		/* at most n-1 tiles because there needs to be at least 1 empty space */
		//Tile[] tile = new Tile[(Layout.getRows()*Layout.getCols())-1];

		JFrame frame = new JFrame("Slide Puzzle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setSize(new Dimension(375, 500));
		frame.getContentPane().setPreferredSize(new Dimension(width, height));
		//frame.setBounds(0, 0, width, height+64);
		frame.setLocationRelativeTo(null);
		
		//Add buttons
		JPanel button_panel = new JPanel();
		button_panel.setBounds(0,0,frame.getWidth(),64);
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



		JPanel field = new JPanel();
		field.setLayout(null);
		field.setBackground(Color.WHITE);

		Tile[][] tiles = new Tile[nrows][ncols];
		r = new Rectangle[numTiles];




		field.setBounds(0, 0, 192*2, 192*2);

		int currID = 0;
		for(int i=0; i<nrows; i++){
			for(int j=0; j<ncols; j++){

				tiles[i][j] = new Tile(currID);
				//currID++;
				tiles[i][j].setOpaque(true);

				tiles[i][j].setBounds(tiles[i][j].x*64, tiles[i][j].y*64, tiles[i][j].width*64, tiles[i][j].height*64);
				tiles[i][j].addMouseMotionListener(this);
				tiles[i][j].addMouseListener(this);

				field.add(tiles[i][j]);
				r[currID] = tiles[i][j].getVisibleRect();
				currID++;

				// event
				if(currID >numTiles-1) break;
				//tiles[i][j].repaint();
			}
			if(currID >numTiles-1) break;
		}
		//setRect(r);
		//field.repaint();


		

		frame.setResizable(false);
		frame.getContentPane().add(field, BorderLayout.CENTER);
		//frame.getContentPane().add(tile1);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		

	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		

	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		

	}
	@Override
	public void mousePressed(MouseEvent e) {
		Tile tile = (Tile)e.getSource();

		Rectangle rtemp = new Rectangle(tile.getBounds());
		Rectangle[] r = getRect();

		for(int i=0; i< getNum(); i++){
			if (i != tile.id && rtemp.intersects(r[i])){
				System.out.println("INTERSECTION BETWEEN "+tile.id+" AND "+i);
			}
		}
		
		clicX = e.getX();
		clicY = e.getY();
		x1 = tile.getX(); //starting x position of tile
		y1 = tile.getY(); //starting y position of tile

		// TODO: loop left, right, up, and down to find bounds for legal moves

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
		//System.out.println(""+jc.getY());
		
		this.rightBound = this.width;
		this.leftBound = 0;
		this.upperBound = 0;
		this.lowerBound = this.height;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		

	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Give credit to source for jc.getX()+e.getX()-clicX, jc.getY()+e.getY()-clicY
		Tile tile = (Tile)e.getSource();

		Rectangle rtemp = tile.getBounds();
		Rectangle[] r = getRect();

		for(int i=0; i< getNum(); i++){
			if (i != tile.id && rtemp.intersects(r[i])){
				System.out.println("INTERSECTION BETWEEN "+tile.id+" AND "+i+"\n");
			}
		}


		if(tile.horizontal == true){
			tile.setLocation(tile.getX()+e.getX()-clicX, tile.getY());
			if(Math.abs(tile.getX() - x1) > Math.abs(tile.getY() - y1)){
				tile.setLocation(tile.getX(), y1);
			}
			if(tile.getX() < leftBound) tile.setLocation(leftBound, tile.getY());
			else if(tile.getX() > rightBound-(64*tile.width)) tile.setLocation(rightBound-(64*tile.width), tile.getY());
		}

		if(tile.vertical==true){
			tile.setLocation(tile.getX(), tile.getY()+e.getY()-clicY);
			if(Math.abs(tile.getX() - x1) < Math.abs(tile.getY() - y1)){
				tile.setLocation(x1, tile.getY());
			}
			if(tile.getY() < upperBound) tile.setLocation(tile.getX(), upperBound);
			else if(tile.getY() > lowerBound-(64*tile.height)) tile.setLocation(tile.getX(), lowerBound-(64*tile.height));
		}
		//System.out.println(""+(tile.getX() % 64));

	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		

	}

}
