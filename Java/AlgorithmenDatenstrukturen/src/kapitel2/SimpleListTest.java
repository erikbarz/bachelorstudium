package kapitel2;

import java.util.NoSuchElementException;

public class SimpleListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s1 = "eins";
		String s2 = "zwei";
		String s3 = "drei";
		String s4 = "vier";
		SimpleList liste = new SimpleList();
		
		try{
		System.out.println(liste.getFirst());}
		catch(NoSuchElementException er){
			System.out.println("es wurde versucht auf eine leere Liste zuzugreifen");
		}
		
		liste.addFirst(s2);
		liste.addFirst(s1);
		liste.addLast(s3);
		liste.addLast(s4);

		System.out.println("der erste ist: " + liste.getFirst());
		System.out.println("der letzte ist: " + liste.getLast());
		System.out.println("--------------------");
		
		while (!liste.isEmpty()){
			System.out.println(liste.removeFirst());
		}
	}

}
