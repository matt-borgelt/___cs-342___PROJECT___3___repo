import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {
	
	
	public FileIO(){
		/*
		File f = new File("level1.txt");
		try {
			BufferedReader inFile = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//Scanner in = new Scanner(new FileReader("level1.txt"));
		
		//File file = new File("level1.txt");
		//Scanner in = new Scanner(file);
		
		
		
		String line;
		try{
			File f = new File("level1.txt");
			BufferedReader inFile = new BufferedReader(new FileReader(f));
			int i=0;
			while((line = inFile.readLine()) != null){
				//System.out.println(line);
				if (i == 0){
					//System.out.println(line);
					Layout.setRows(  1 );
				}
				i++;
			}
			inFile.close();
		}catch(IOException e){
			System.err.println("File not found... :(");
		}
		
		
		
		
		
		
	}
	
}
