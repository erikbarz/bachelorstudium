package de.dhbw.demo;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class TestException {
	
	public static void main(String[] args) throws Exception {
		int i = 0;
		
		while (true) {
			System.out.println(args[i++]);
			if (i == 1) {
				throw new Exception("Stop here");
			}
		}
	}

}
