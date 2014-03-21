import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {

	int counter = 0;

	public FileIO(int level){
		ArrayList<String> stringList = new ArrayList<String>();

		String line;

		try{
			File f = new File("level"+level+".txt");
			BufferedReader inFile = new BufferedReader(new FileReader(f));
			int i=0;
			while((line = inFile.readLine()) != null){
				//System.out.println(line);
				//line.trim();
				if ( (i == 0) && (line.charAt(1)==' ') ){
					/* the header of the input file --  rows x columns */
					Layout.setRows(  Character.getNumericValue(line.charAt(0)) );
					Layout.setCols(  Character.getNumericValue(line.charAt(3)) );
					Layout.lowMoves = Character.getNumericValue(line.charAt(6));
				}
				else{
					/* input file body */
					stringList.add(line);
					counter++;
				}
				i++;
			}
			Layout.setBody(stringList);
			Layout.setNum(counter);
			inFile.close();
			Layout.fileFound = true;
		}catch(IOException e){
			System.err.println("File not found... :(");
			Layout.fileFound = false;
		}
	}
}
