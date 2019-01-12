package de.dhbw.exercises.library;

import java.util.*;

public class TestLibrary {

	public static void main(String[] args) {
		Book b1 = new Book("Ich und meine Welt Teil1", "Ich", 200, new Date(), "Unterhaltung");
		Book b2 = new Book("Ich und meine Welt Teil2", "Ich", 200, new Date(), "Unterhaltung");
		Book b3 = new Book("Java ist auch eine Insel", "Christian Ullenboom", 1475, new Date(), "Programmierung");

		Library lib = new BookLibrary();
		lib.addBook(b1);
		lib.addBook(b2);
		lib.addBook(b3);

		Book[] allBooks = new Book[lib.getBookCount()];

		System.out.println("How many books are in the library?");
		System.out.println(lib.getBookCount() + " books");

		System.out.println();
		System.out.println("Give me all books.");
		allBooks = lib.getAllBooks();
		for (int i = 0; i < allBooks.length; i++) {
			System.out.println(allBooks[i]);
		}

		System.out.println();
		System.out.println("Give me the book(s) i look for.");
		Book[] findBooks = lib.findBookByAuthor("Ich");
		for (int i = 0; i < findBooks.length; i++) {
			System.out.println(findBooks[i]);
		}

		System.out.println();
		System.out.println("Give me the total number of bookpages.");
		System.out.println(lib.getTotalPageCount() + " pages");

		System.out.println();
		System.out.println("Remove the book b2 and give me the new booklist of all books in the library.");
		lib.removeBook(b2);
		allBooks = lib.getAllBooks();
		for (int i = 0; i < allBooks.length; i++) {
			System.out.println(allBooks[i]);
		}

		System.out.println();
		System.out.println("Give me the new total number of bookpages.");
		System.out.println(lib.getTotalPageCount() + " pages");
	}

}
