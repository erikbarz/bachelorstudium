/**
 * 
 */

/**
 * @author Erik Barz
 * 
 */
public class HelloWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println("Hello World");
//		for (int i = 0; i < args.length; i++) {
//			System.out.println("i ist " + i + " und args=" + args[i]);
//		}
//		System.out.println("----------------------");
//
//		for (String arg : args) {
//			System.out.println(arg);
//
//		}
//		System.out.println("----------------------");
//
//		String line = "";
//		for (String arg : args) {
//			System.out.println(arg);
//			line = line + " " + arg;
//		}
//		System.out.println(line);
		
		
		
//		if(args.length<2) 
//		{
//			System.out.println("dumm");
//			return;
//		}
//
//
//		int z1=Integer.parseInt(args[0]);
//		int z2=Integer.parseInt(args[1]);
//		System.out.println(z1*z2);

		
		//Taschenrechner:
		System.out.println(args[0]);
		System.out.println(args[1]);
		System.out.println(args[2]);
		double z1=Double.parseDouble(args[0]);
		double z2=Double.parseDouble(args[2]);

		System.out.println("=");

		if(args[1].equals("+")) System.out.println(z1 + z2);
		if(args[1].equals("-")) System.out.println(z1 - z2);
		if(args[1].equals("x")) System.out.println(z1 * z2);
		if(args[1].equals("/")) System.out.println(z1 / z2);	
		if(args[1].equals("w")) System.out.println(Math.sqrt(z1+z2));
		
	}

}
