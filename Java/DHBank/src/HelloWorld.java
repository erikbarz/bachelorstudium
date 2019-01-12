import java.io.*;



/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class HelloWorld {
	
	public static void main(String[] args) throws IOException {
		
		
		String str = args[0];
		
		String result = "";
		
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (Character.isDigit(c)) {
				result += c;
			}
		}
		System.out.println("######## "+result); // DEL
	}
}
