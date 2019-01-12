package kapitel4;

public class LineareSuche {
	public static int search(int[] array, int key) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == key)
				return i;
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] f = { 2, 4, 5, 6, 7, 8, 9, 11 };
		int k = 11;
		int i = search(f, k);

		if (i != -1)
			System.out.println("lineare Suche - Index = " + i);
		else
			System.out.println("element nicht gefunden");
	}
}
