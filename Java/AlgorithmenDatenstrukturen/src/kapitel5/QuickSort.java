package kapitel5;

public class QuickSort {

	private int[] a;

	public int[] getA() {
		return a;
	}

	int[] f = { 5,1,8,3,9,2 };
	
	private int n;

	public void sort(int[] a) {
		this.a = a;
		n = a.length;
		quicksort(0, n - 1);
	}

	private void quicksort(int lo, int hi) {
		int i = lo, j = hi;

		// Vergleichs­element x
		int x = a[(lo + hi) / 2];

		// Aufteilung
		while (i <= j) {
			while (a[i] < x)
				i++;
			while (a[j] > x)
				j--;
			if (i <= j) {
				exchange(i, j);
				i++;
				j--;
			}
		}

		// Rekursion
		if (lo < j)
			quicksort(lo, j);
		if (i < hi)
			quicksort(i, hi);
	}

	private void exchange(int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}
