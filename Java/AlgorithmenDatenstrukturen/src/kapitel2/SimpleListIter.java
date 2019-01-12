package kapitel2;

public class SimpleListIter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "eins";
		String s2 = "zwei";
		String s3 = "drei";
		String s4 = "vier";
		SimpleList liste = new SimpleList();
		
		liste.addFirst(s2);
		liste.addFirst(s1);
		liste.addLast(s3);
		liste.addLast(s4);
		liste.addLast("fünf");
		
		IteratorIF iter=liste.iterator();
		
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}

}
