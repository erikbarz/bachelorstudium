package exercises;

public class NumbersIn {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args[0].equals("NumbersIn")) {
			String bla = args[1];
			for (int i = 0; i < bla.length(); i++) {
				if (Character.isDigit(bla.charAt(i))) {
					System.out.print(bla.charAt(i));
				}
			}

		}

	}
	

}
