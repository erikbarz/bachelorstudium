package de.dhbw.exercises.library;

/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public interface Library {
	int FIST_BOOK_ID = 1000;

	void addBook(Book b);

	Book[] findBookByAuthor(String author);

	Book[] getAllBooks();

	Book getBook(int index);

	int getBookCount();

	int getTotalPageCount();

	void removeBook(Book b);

}