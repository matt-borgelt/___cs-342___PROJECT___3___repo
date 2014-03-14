import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Tile extends JButton {
	
	private int id;
	private boolean horizontal=false; 
	private boolean vertical=false;
	
	public int width;
	public int height;
	public int x;
	public int y;
	
	public Tile(int ID)
	{
		this.id=ID;
		ArrayList<String> body = Layout.getBody();
		//this.direction= body.get(ID);
		String sub = body.get(ID);
		this.x= Character.getNumericValue(sub.charAt(0));
		this.y= Character.getNumericValue(sub.charAt(3));
		this.width= Character.getNumericValue(sub.charAt(6));
		this.height= Character.getNumericValue(sub.charAt(9));
		
		if ((sub.charAt(12)=='h')||(sub.charAt(12)=='b')){
			this.horizontal = true;
		}
		if ((sub.charAt(12)=='v')||(sub.charAt(12)=='b')){
			this.vertical=true;
		}
		this.setText("" + this.id);
		
	}
	

}


	
