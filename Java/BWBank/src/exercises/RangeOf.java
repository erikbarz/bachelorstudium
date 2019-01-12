package exercises;

public class RangeOf {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int min = Integer.parseInt(args[1]);
		int max = Integer.parseInt(args[1]);

		if (args[0].equals("RangeOf") && args.length > 2) {
			for (int i = 2; i <= args.length-1; i++) {
				if (Integer.parseInt(args[i]) < min)
					min = Integer.parseInt(args[i]);
				if (Integer.parseInt(args[i]) > max)
					max = Integer.parseInt(args[i]);
			}
			System.out.println("max:"+max+", min:"+min);
		}

	}

}
