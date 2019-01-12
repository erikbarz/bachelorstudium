package exercises;

public class Factorial {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long zahl = Long.parseLong(args[1]);
		if(args[0].equals("Factorial") && zahl<=25)
			System.out.println(CalcFaculty(zahl));
	}

	private static long CalcFaculty(long n) {
		if (n == 0 || n == 1)
			return 1;
		else
			return n * CalcFaculty(n - 1);
	}
}
