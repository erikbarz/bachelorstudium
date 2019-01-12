package kapitel4;

public class BinaereSuche {

	public static void main(String[] args) {
		int[] f = { 2, 4, 5, 6, 7, 8, 9, 11 };
		int k = 11;
		int i = search(f, k);

		if (i != -1)
			System.out.println("lineare Suche - Index = " + i);
		else
			System.out.println("element nicht gefunden");
	}

	public static int search(int[] array, int key) {
		int anfang = 0;
		int ende = array.length - 1;
		int i;

		while(anfang<=ende){
			i = ((anfang + ende) / 2);
			if(array[i]==key){
				return i;
			}
			else{
				if(array[i]>key){
					ende=i-1;
				}
				else{
					anfang=i+1;
				}
			}
		}

		return -1;
	}
}
