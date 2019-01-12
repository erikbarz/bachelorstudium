package de.dhbw.exercises.library;

import java.util.*;

public class Book {

	private static int nextBookID = 1000;
	
	private final int bookid;
	private final int numOfPages;
	private final String title;
	private final String author;
	private final Date publishDate;
	
	private String category;

	public Book(String title, String author, int numOfPages, Date publishDate, String category) {
		this.title = title;
		this.author = author;

		if (numOfPages <= 0) {
			throw new IllegalArgumentException("The number of pages in a book must be > 0");
		}
		this.numOfPages = numOfPages;
		this.publishDate = publishDate;
		this.category = category;
		this.bookid = nextBookID++;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getNumberOfPages() {
		return numOfPages;
	}

	public Date getPulishDate() {
		return (Date) publishDate.clone();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return getClass().getName() + ": " + bookid + "  " + title + "  " + author + "  " + numOfPages + "  " + publishDate + "  " + category;
	}
}
