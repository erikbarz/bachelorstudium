package kapitel2;

import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

public class Lotto {

	public static void main(String[] args) {
		TreeSet set = new TreeSet();
		Random ran = new Random();

		while (set.size() < 6) {
			int zahl = ran.nextInt(49) + 1;
			set.add(zahl);
		}

		System.out.println("Die Lottozahlen: ");
		
		Iterator iter=set.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
}
