import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.util.ArrayList;

public class Layout implements MouseInputListener, MouseMotionListener, ActionListener{
	public static boolean fileFound;
	private static Rectangle[] r;

	public static int rows;
	public static int cols;
	public static int num;
	private int width;
	private int height;
	private int currMoves = 0;
	public static int lowMoves = 999;
	
	private static Tile[] tiles;
	private JFrame frame;
	private JLabel moves_counter;

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
	
	public static void setRect(){
		for(int i = 0; i < num; i++){
			r[i] = new Rectangle(tiles[i].getVisibleRect());
			r[i].setLocation(tiles[i].getX(),tiles[i].getY());
			System.out.println(r[i]);
		}
	}

	public Layout(int level)
	{
		FileIO io = new FileIO(level);
		if(!fileFound) return;
		
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

		frame = new JFrame("Slide Puzzle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setPreferredSize(new Dimension(width, height+button_panel.getHeight()));
		frame.setLocationRelativeTo(null);

		//Add buttons
		JPanel button_panel = new JPanel();
		//button_panel.setBounds(0,0,width,64);
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
		
		/* Moves Counter */
		moves_counter = new JLabel("Moves: "+currMoves);
		button_panel.add(moves_counter, BorderLayout.NORTH);
		moves_counter.setVisible(true);
		
		/* Minimum Moves */
		JLabel lowMoves_counter = new JLabel("Fewest: "+lowMoves);
		button_panel.add(lowMoves_counter, BorderLayout.NORTH);
		lowMoves_counter.setVisible(true);



		JPanel field = new JPanel();
		field.setLayout(null);
		field.setBackground(Color.WHITE);
		field.setPreferredSize(new Dimension(width, height));

		tiles = new Tile[numTiles];
		r = new Rectangle[numTiles];




		field.setBounds(0, 0, 192*2, 192*2);
		for(int i=0; i<numTiles; i++){

			tiles[i] = new Tile(i);
			//currID++;
			tiles[i].setOpaque(true);

			tiles[i].setBounds(tiles[i].x*64, tiles[i].y*64, tiles[i].width*64, tiles[i].height*64);
			tiles[i].addMouseMotionListener(this);
			tiles[i].addMouseListener(this);

			field.add(tiles[i]);
		}
		setRect();
		//field.repaint();




		frame.setResizable(false);
		frame.getContentPane().add(field, BorderLayout.CENTER);
		//frame.getContentPane().add(tile1);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void endgame(){
		JOptionPane.showMessageDialog(frame, "Congratulations, You've won!");
		frame.dispose();
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
		setRect();
		Tile tile = (Tile)e.getSource();

		Rectangle rtemp = new Rectangle(0,0,64,64);

		clicX = e.getX();
		clicY = e.getY();
		x1 = tile.getX(); //starting x position of tile
		y1 = tile.getY(); //starting y position of tile

		// TODO: loop left, right, up, and down to find bounds for legal moves
		boolean passed = false;
		if(tile.horizontal)
			for(int i = 0; i < rows; i++){
				rtemp.setLocation(i*64,y1);
				for(int j = 0; j < num; j++){
					if(rtemp.intersects(r[tile.id])){
						passed = true;
						continue;
					}


					if(rtemp.intersects(r[j])){
						if(passed && rightBound == this.width){ //if the current tile has been passed and a new right bound has not yet been found
							rightBound = tiles[j].getX();
							System.out.println("rightBound: " + rightBound);
						}
						else if (!passed){
							leftBound = tiles[j].getX() + tiles[j].getWidth();
							System.out.println("leftBound: " + leftBound);
						}
					}
				}
			}
		passed = false;
		if(tile.vertical)
			for(int i = 0; i < cols; i++){
				rtemp.setLocation(x1,i*64);
				for(int j = 0; j < getNum(); j++){
					if(rtemp.intersects(r[tile.id])){
						passed = true;
						continue;
					}


					if(rtemp.intersects(r[j])){
						if(passed && lowerBound == this.height){ //if the current tile has been passed and a new right bound has not yet been found
							lowerBound = tiles[j].getY();
							System.out.println("lowerBound: " + lowerBound);
						}
						else if (!passed){
							upperBound = tiles[j].getY() + tiles[j].getHeight();
							System.out.println("upperBound: " + upperBound);
						}
					}
				}
			}
		passed = false;

	}
	@Override
	public void mouseReleased(MouseEvent e) {

		JComponent jc = (JComponent)e.getSource();
		
		//get x and y locations relative to grid
		int modX = jc.getX() % 64;
		int modY = jc.getY() % 64;
		
		//Snap tile to nearest point
		if (modX < 32){
			jc.setLocation(jc.getX()-modX, jc.getY());
		}
		else{
			jc.setLocation(jc.getX()+(64-modX), jc.getY());
		}
		if (modY < 32){
			jc.setLocation(jc.getX(), jc.getY()-modY);
		}
		else{
			jc.setLocation(jc.getX(), jc.getY()+(64-modY));
		}

		//reset bounds to frame
		this.rightBound = this.width;
		this.leftBound = 0;
		this.upperBound = 0;
		this.lowerBound = this.height;
		
		if(jc.getY() != y1 || jc.getX() != x1){
			currMoves++;
			moves_counter.setText("Current moves: "+currMoves);
		}
		
		if(tiles[0].getX() == width-tiles[0].getWidth()) endgame(); //When the Z tile is on the right edge, end the game
	}
	@Override
	public void actionPerformed(ActionEvent e) {


	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Give credit to source for jc.getX()+e.getX()-clicX, jc.getY()+e.getY()-clicY
		Tile tile = (Tile)e.getSource();


		if(tile.horizontal == true){ //Move the tile on the horizontal axis
			tile.setLocation(tile.getX()+e.getX()-clicX, tile.getY());
			if(Math.abs(tile.getX() - x1) > Math.abs(tile.getY() - y1)){
				tile.setLocation(tile.getX(), y1);
			}
			if(tile.getX() < leftBound) tile.setLocation(leftBound, tile.getY());
			else if(tile.getX() > rightBound-(64*tile.width)) tile.setLocation(rightBound-(64*tile.width), tile.getY());
		}

		if(tile.vertical==true){  //Move the tile on the vertical axis
			tile.setLocation(tile.getX(), tile.getY()+e.getY()-clicY);
			if(Math.abs(tile.getX() - x1) < Math.abs(tile.getY() - y1)){
				tile.setLocation(x1, tile.getY());
			}
			if(tile.getY() < upperBound) tile.setLocation(tile.getX(), upperBound);
			else if(tile.getY() > lowerBound-(64*tile.height)) tile.setLocation(tile.getX(), lowerBound-(64*tile.height));
		}

	}
	@Override
	public void mouseMoved(MouseEvent arg0) {


	}

}
