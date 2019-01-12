package kapitel5;

public class QuickSortTest {

	public static void main(String[] args) {
		QuickSort sorter=new QuickSort();
		int[] f = { 5,1,8,3,9,2 };
		sorter.sort(f);
		int[] i =sorter.getA();
		for(int n:i){
			System.out.println(n);
		}

	}

}
