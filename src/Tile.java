import javax.swing.*;

//import java.awt.*;
//import java.awt.event.*;
import java.awt.Color;
import java.util.ArrayList;
//import java.util.Random;
//import java.util.Scanner;


public class Tile extends JButton {
	private static final long serialVersionUID = 1L;
	
	public int id;
	public boolean horizontal=false; 
	public boolean vertical=false;

	public int width;
	public int height;
	public int x;
	public int y;

	public Tile(int ID)
	{
		this.id=ID;
		ArrayList<String> body = Layout.getBody();
		String sub = body.get(ID);
		this.x= Character.getNumericValue(sub.charAt(0))-1;
		this.y= Character.getNumericValue(sub.charAt(3))-1;
		this.width= Character.getNumericValue(sub.charAt(6));
		this.height= Character.getNumericValue(sub.charAt(9));

		if (sub.charAt(12)=='h'){
			this.horizontal = true;
			this.setBackground(new Color(50, 220,90));
		}
		else if (sub.charAt(12)=='v'){
			this.vertical=true;
			this.setBackground(new Color(130, 170, 255));
		}
		else if (sub.charAt(12)=='b'){
			this.horizontal = true;
			this.vertical = true;
			this.setBackground(new Color(255, 100, 255));
		}
		if(this.id != 0)
			this.setText("" + this.id);
		else{
			this.setText("Z");
			this.setBackground(new Color(230, 100, 80));
		}

	}


}
