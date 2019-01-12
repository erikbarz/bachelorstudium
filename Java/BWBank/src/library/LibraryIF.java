package library;

public interface LibraryIF {
	void addBook(Book b);

	void removeBook(Book b);

	Book getBook(int index);

	int getBookCount();

	int getTotalPageCount();

	Book findBookById(int bookId);

	Book[] findBooksByAuthor(String author);

	Book[] getAllBooks();
}
