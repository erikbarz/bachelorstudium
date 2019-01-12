package de.dhbw.exercises.library;

import java.util.*;

public class BookLibrary implements Library {

	private final List<Book> bookList = new LinkedList();
	

	public void addBook(Book b) {
		bookList.add(b);
	}

	public Book[] findBookByAuthor(String author) {
		ArrayList<Book> hitList = new ArrayList();

		for (int i = 0; i < bookList.size(); i++) {
			Book b = getBook(i);

			if (b.getAuthor().equals(author)) {
				hitList.add(b);
			}
		}
		return hitList.toArray(new Book[hitList.size()]);
	}

	public Book[] getAllBooks() {
		return bookList.toArray(new Book[bookList.size()]);
		/*
		Book[] allBooks = new Book[bookList.size()];

		for (int i = 0; i < bookList.size(); i++) {
			allBooks[i] = getBook(i);
		}
		return allBooks;
		*/
	}

	public Book getBook(int index) {
		return bookList.get(index);
	}

	public int getBookCount() {
		return bookList.size();
	}

	public int getTotalPageCount() {
		return 0;
	}

	public void removeBook(Book b) {
		bookList.remove(b);
	}

}
