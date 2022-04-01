import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class csvCombiner {
	public static void main(String[] args) {
		//Do not run program if less than 2 inputFile args are passed
		if((args.length < 2)){
			System.out.println("Error: Must pass in at least 2 input files as arguments");
			System.exit(1);
		}
		//Write top line of output
		System.out.println("\"email_hash\",\"category\",\"filename\"");
		//Loop through args and open corrseponding files, writing each line of files to output
		for(int i=0; i<args.length; i++) {
			//Extract the filename from the passed in file path
			StringTokenizer st = new StringTokenizer(args[i],"/");
			String filename = "";
			while(st.hasMoreTokens()){
				filename = st.nextToken();
			}
			try {
				FileInputStream currentFile = new FileInputStream(args[i]);
				Scanner sc = new Scanner(currentFile);
				sc.nextLine();	//Skip top line of each input file
				while(sc.hasNextLine()){
					String currentLine = sc.nextLine();
					System.out.println(currentLine + ",\"" + filename + "\"");
				}
				sc.close();
				filename = null;
				st = null;
				sc = null;
				currentFile = null;
				System.gc();	//Call garbage collection after every file to clear out memory
			} catch(FileNotFoundException e) {	//Display error if file cannot be opened
				System.out.println("Error: Could not open file "+args[i]);
				e.printStackTrace();
			}
		}	
	}
}
