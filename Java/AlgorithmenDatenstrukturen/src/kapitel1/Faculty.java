package kapitel1;

public class Faculty {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long f = 10;
		long erg = faculty(f);
		long erg2 = facultyRec(f);
		System.out.println(erg);
		System.out.println(erg2);

		performanceTest();
	}

	public static long faculty(long n) {
		long hilf = 1;
		for (long i = n; i > 1; i--) {
			hilf = hilf * i;
		}
		return hilf;
	}

	public static long facultyRec(long n) {
		if (n == 0 || n == 1)
			return 1;
		else
			return n * facultyRec(n - 1);
	}

	public static void performanceTest() {
		long start = System.currentTimeMillis();
		for (int i = 1; i < 10000; i++) {

			faculty(25);

		}
		System.out.println(System.currentTimeMillis() - start
				+ "ms mit iterativ");
		start = System.currentTimeMillis();
		for (int i = 1; i < 10000; i++) {

			facultyRec(25);

		}
		System.out.println(System.currentTimeMillis() - start
				+ "ms mit rekursiv");
	}
}
