package library;

public class LibraryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Library bibo = new Library();
		System.out.println(bibo.getBookCount());
		Book book4=new Book("feuchtgebiete", "god", 4000, 6);
		Book book5=new Book("feuchtgebiete", "god", 4000, 6);
		bibo.addBook(book4);
		System.out.println(bibo.getBookCount());
		bibo.removeBook(book4);
		System.out.println(bibo.getBookCount());
		System.out.println(bibo.getTotalPageCount());
		bibo.addBook(book5);
		System.out.println(book4.equals(book5));
	}

}
