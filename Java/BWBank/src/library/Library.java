package library;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import bank.model.Account;

public class Library implements LibraryIF {

	public List<Book> bookList = new ArrayList();
	
	
	public Library() {
		load();
	}
	

	public void load(){
		Book book1=new Book("bibel", "god", 1000, 1);
		Book book2=new Book("bibel2", "god", 2000, 2);
		Book book3=new Book("koran", "god", 3000, 1);
		
		bookList.add(book1);
		bookList.add(book2);
		bookList.add(book3);
		
	}
	
	@Override
	public void addBook(Book b) {
		if(bookList.contains(b)){
			throw new IllegalArgumentException("Buch exists");
		}
		bookList.add(b);
	}
	

	@Override
	public void removeBook(Book b) {
		bookList.remove(b);
	}

	@Override
	public Book getBook(int index) {
		return bookList.get(index);
	}

	@Override
	public int getBookCount() {
		return bookList.size();
	}

	@Override
	public int getTotalPageCount() {
		int pages = 0;
		for (Book book : bookList) {
			pages += book.getNumOfPages();
		}
		return pages;
	}

	@Override
	public Book findBookById(int bookId) {
		for (Book book : bookList) {
			if (book.getBookID() == bookId) {
				return book;
			}
		}
		return null;
	}

	@Override
	public Book[] findBooksByAuthor(String author) {
		 List<Book> bla = new ArrayList();
		for (Book book : bookList) {
			if (book.getAuthor().equals(author)) {
				bla.add(book);
			}
		}
		return bla.toArray(new Book[bla.size()]);
	}

	@Override
	public Book[] getAllBooks() {
		return bookList.toArray(new Book[bookList.size()]);
	}

}
