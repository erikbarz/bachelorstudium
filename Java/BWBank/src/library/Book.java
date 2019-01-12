package library;

import java.util.Date;

public class Book {
	private final int bookID;
	private final String title;
	private final String author;
	private final int numOfPages;
	private  Date puplishDate;
	private int category;

	static int lastBookID = 1;

	public Book(String title, String author, int numOfPages, int category) {
		bookID = ++lastBookID;
		this.title = title;
		this.author = author;
		this.numOfPages = numOfPages;
		//this.puplishDate = puplishDate;
		this.category = category;
	}

	public int getBookID() {
		return bookID;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getNumOfPages() {
		return numOfPages;
	}

	public Date getPuplishDate() {
		return puplishDate;
	}

	public int getCategory() {
		return category;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Book) {
			Book b=(Book)obj;
			if(b.getBookID()==bookID){
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public String toString() {
		return "Book [bookID=" + bookID + ", title=" + title + ", author="
				+ author + ", numOfPages=" + numOfPages + ", puplishDate="
				+ puplishDate + ", category=" + category + "]";
	}

}
