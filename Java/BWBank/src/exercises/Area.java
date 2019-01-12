package exercises;

public class Area {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double height=Double.parseDouble(args[1]);
		double width=Double.parseDouble(args[2]);
		if(args[0].equals("Area"))
		System.out.println(height*width);
		else System.out.println("Pech");
	}

}
